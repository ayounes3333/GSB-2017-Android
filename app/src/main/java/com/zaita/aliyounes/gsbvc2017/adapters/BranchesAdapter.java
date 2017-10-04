package com.zaita.aliyounes.gsbvc2017.adapters;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;
import com.zaita.aliyounes.gsbvc2017.helpers.ColorHelper;
import com.zaita.aliyounes.gsbvc2017.network.apis.BranchesNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.network.apis.BrandsNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.pojos.Branch;
import com.zaita.aliyounes.gsbvc2017.pojos.Brand;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ali Younes on 8/11/2017.
 */

public class BranchesAdapter extends RecyclerView.Adapter<BranchesAdapter.BranchVieHolder> {

    //List of branches to show
    private List<Branch> branches;
    private List<Branch> pendingBranches;
    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    private HashMap<String, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a r


    public BranchesAdapter(List<Branch> branches) {
        this.branches = branches;
        this.pendingBranches = new ArrayList<>();
    }

    @Override
    public BranchVieHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_branch_layout, viewGroup , false);
        return new BranchVieHolder(view);
    }

    @Override
    public void onBindViewHolder(BranchVieHolder branchViewHolder, int position) {
        final Branch branch = branches.get(position);
        if (pendingBranches.contains(branch)) {
            // we need to show the "undo" state of the row
            branchViewHolder.itemView.setBackgroundColor(Color.RED);
            branchViewHolder.textView_branchName.setVisibility(View.GONE);
            branchViewHolder.textView_branchName_title.setVisibility(View.GONE);
            branchViewHolder.textView_branchTelephone.setVisibility(View.GONE);
            branchViewHolder.textView_branchTelephone_title.setVisibility(View.GONE);
            branchViewHolder.textView_branchAddresse.setVisibility(View.GONE);
            branchViewHolder.textView_branchAddresse_title.setVisibility(View.GONE);
            branchViewHolder.linearLayout_iconBackground.setVisibility(View.GONE);
            branchViewHolder.buttonUndo.setVisibility(View.VISIBLE);
            branchViewHolder.buttonUndo.setText(R.string.button_undo);
            branchViewHolder.buttonUndo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // user wants to undo the removal, let's cancel the pending task
                    Runnable pendingRemovalRunnable = pendingRunnables.get(String.valueOf(branch.getCodeBr()));
                    pendingRunnables.remove(String.valueOf(branch.getCodeBr()));
                    if (pendingRemovalRunnable != null)
                        handler.removeCallbacks(pendingRemovalRunnable);
                    pendingBranches.remove(branch);
                    // this will rebind the row in "normal" state
                    notifyItemChanged(branches.indexOf(branch));
                }
            });
            if(branch.isRemoving()) {
                //Deleting in progress
                branchViewHolder.itemView.setBackgroundColor(Color.GRAY);
                branchViewHolder.textView_branchName.setVisibility(View.GONE);
                branchViewHolder.textView_branchName_title.setVisibility(View.GONE);
                branchViewHolder.textView_branchTelephone.setVisibility(View.GONE);
                branchViewHolder.textView_branchTelephone_title.setVisibility(View.GONE);
                branchViewHolder.textView_branchAddresse.setVisibility(View.GONE);
                branchViewHolder.textView_branchAddresse_title.setVisibility(View.GONE);
                branchViewHolder.linearLayout_iconBackground.setVisibility(View.GONE);
                branchViewHolder.buttonUndo.setVisibility(View.VISIBLE);
                branchViewHolder.buttonUndo.setText(R.string.deleting);
                branchViewHolder.buttonUndo.setEnabled(false);
            }
        } else {
            // we need to show the "normal" state
            branchViewHolder.itemView.setBackgroundColor(Color.WHITE);
            branchViewHolder.textView_branchName.setVisibility(View.VISIBLE);
            branchViewHolder.textView_branchName_title.setVisibility(View.VISIBLE);
            branchViewHolder.textView_branchTelephone.setVisibility(View.VISIBLE);
            branchViewHolder.textView_branchTelephone_title.setVisibility(View.VISIBLE);
            branchViewHolder.textView_branchAddresse.setVisibility(View.VISIBLE);
            branchViewHolder.textView_branchAddresse_title.setVisibility(View.VISIBLE);
            branchViewHolder.linearLayout_iconBackground.setVisibility(View.VISIBLE);
            branchViewHolder.bind(branch);
            branchViewHolder.buttonUndo.setVisibility(View.GONE);
            branchViewHolder.buttonUndo.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return branches.size();
    }

    public void pendingRemoval(int position) {
        final Branch branch = branches.get(position);
        if (!pendingBranches.contains(branch)) {
            pendingBranches.add(branch);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(branches.indexOf(branch));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(String.valueOf(branch.getCodeBr()), pendingRemovalRunnable);
        }
    }

    public void remove(final int position) {
        branches.get(position).setRemoving(true);
        notifyItemChanged(position);
        BranchesNetworkCalls.deleteBranch(String.valueOf(branches.get(position).getCodeBr())).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean value) {
                Branch branch = branches.get(position);
                if (pendingBranches.contains(branch)) {
                    pendingBranches.remove(branch);
                }
                if (branches.contains(branch)) {
                    branches.remove(position);
                    notifyItemRemoved(position);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Get Brands" , "Error getting Brands" , e);
                if(e instanceof SocketException || e instanceof IOException) {
                    Toast.makeText(GSBApplication.getInstance(), R.string.no_internet , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GSBApplication.getInstance() , e.getMessage() , Toast.LENGTH_LONG).show();
                }
                Branch branch = branches.get(position);
                // user wants to undo the removal, let's cancel the pending task
                Runnable pendingRemovalRunnable = pendingRunnables.get(String.valueOf(branch.getCodeBr()));
                pendingRunnables.remove(String.valueOf(branch.getCodeBr()));
                if (pendingRemovalRunnable != null) handler.removeCallbacks(pendingRemovalRunnable);
                pendingBranches.remove(branch);
                // this will rebind the row in "normal" state
                notifyItemChanged(branches.indexOf(branch));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public boolean isPendingRemoval(int position) {
        Branch item = branches.get(position);
        return pendingBranches.contains(item);
    }
    
    class BranchVieHolder extends RecyclerView.ViewHolder {

        private TextView textView_branchName;
        private TextView textView_branchName_title;
        private TextView textView_branchTelephone;
        private TextView textView_branchTelephone_title;
        private TextView textView_branchAddresse;
        private TextView textView_branchAddresse_title;
        private Button   buttonUndo;
        private LinearLayout linearLayout_iconBackground;
        private CardView cardView_branch;
        
        BranchVieHolder(View itemView) {
            super(itemView);
            textView_branchName             = (TextView) itemView.findViewById(R.id.textView_nomBranch);
            textView_branchName_title       = (TextView) itemView.findViewById(R.id.textView_nomBranch_titre);
            textView_branchTelephone        = (TextView) itemView.findViewById(R.id.textView_telBranch);
            textView_branchTelephone_title  = (TextView) itemView.findViewById(R.id.textView_telBranch_titre);
            textView_branchAddresse         = (TextView) itemView.findViewById(R.id.textView_addrBranch);
            textView_branchAddresse_title   = (TextView) itemView.findViewById(R.id.textView_addrBranch_titre);
            buttonUndo                      = (Button)   itemView.findViewById(R.id.undo_button); 
            cardView_branch                 = (CardView) itemView.findViewById(R.id.cardView_branch);
            linearLayout_iconBackground     = (LinearLayout) itemView.findViewById(R.id.linearLayout_iconBackground);
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
