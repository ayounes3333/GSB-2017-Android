package com.zaita.aliyounes.gsbvc2017.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.helpers.ColorHelper;
import com.zaita.aliyounes.gsbvc2017.pojos.Branch;

import java.util.List;

/**
 * Created by Ali Younes on 8/11/2017.
 */

public class BranchesAdapter extends RecyclerView.Adapter<BranchesAdapter.BranchVieHolder> {

    //List of brances to show
    private List<Branch> branches;

    public BranchesAdapter(List<Branch> branches) {
        this.branches = branches;
    }

    @Override
    public BranchVieHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_branch_layout, viewGroup , false);
        return new BranchVieHolder(view);
    }

    @Override
    public void onBindViewHolder(BranchVieHolder branchVieHolder, int position) {
        Branch branch = branches.get(position);
        branchVieHolder.bind(branch);
    }

    @Override
    public int getItemCount() {
        return branches.size();
    }

    class BranchVieHolder extends RecyclerView.ViewHolder {

        private TextView textView_branchName;
        private TextView textView_branchTelephone;
        private TextView textView_branchAddresse;
        private LinearLayout linearLayout_iconBackground;
        private CardView cardView_branch;

        BranchVieHolder(View itemView) {
            super(itemView);
            textView_branchName         = (TextView) itemView.findViewById(R.id.textView_nomBranch);
            textView_branchTelephone    = (TextView) itemView.findViewById(R.id.textView_telBranch);
            textView_branchAddresse     = (TextView) itemView.findViewById(R.id.textView_addrBranch);
            cardView_branch             = (CardView) itemView.findViewById(R.id.cardView_branch);
            linearLayout_iconBackground = (LinearLayout) itemView.findViewById(R.id.linearLayout_iconBackground);
        }

        //Bind data to List item
        void bind(Branch branch) {
            textView_branchAddresse.setText(branch.getAddressBr());
            textView_branchName.setText(branch.getNameBr());
            textView_branchTelephone.setText(branch.getTelBr());
            linearLayout_iconBackground.setBackgroundColor(ColorHelper.getRandomColor());
            cardView_branch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Add action for branch item click
                }
            });
        }
    }
}
