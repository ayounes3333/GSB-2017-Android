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
import com.zaita.aliyounes.gsbvc2017.pojos.Availability;

import java.util.List;

/**
 * Created by Ali Younes on 8/11/2017.
 */

public class AvailabilitiesAdapter extends RecyclerView.Adapter<AvailabilitiesAdapter.BranchVieHolder> {

    //List of availabilities to show
    private List<Availability> availabilities;

    public AvailabilitiesAdapter(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    @Override
    public BranchVieHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_availability_layout, viewGroup , false);
        return new BranchVieHolder(view);
    }

    @Override
    public void onBindViewHolder(BranchVieHolder branchVieHolder, int position) {
        Availability availability = availabilities.get(position);
        branchVieHolder.bind(availability);
    }

    @Override
    public int getItemCount() {
        return availabilities.size();
    }

    class BranchVieHolder extends RecyclerView.ViewHolder {

        private TextView textView_brand;
        private TextView textView_product;
        private TextView textView_qty;
        private LinearLayout linearLayout_iconBackground;
        private CardView cardView_availability;

        BranchVieHolder(View itemView) {
            super(itemView);
            textView_brand              = (TextView) itemView.findViewById(R.id.textView_nomBrand);
            textView_product            = (TextView) itemView.findViewById(R.id.textView_nomProd);
            textView_qty                = (TextView) itemView.findViewById(R.id.textView_qty);
            cardView_availability       = (CardView) itemView.findViewById(R.id.cardView_availability);
            linearLayout_iconBackground = (LinearLayout) itemView.findViewById(R.id.linearLayout_iconBackground);
        }

        //Bind data to List item
        void bind(Availability availability) {
            textView_brand.setText(availability.brand);
            textView_product.setText(availability.product);
            textView_qty.setText(String.valueOf(availability.quantity));
            linearLayout_iconBackground.setBackgroundColor(ColorHelper.getRandomColor());
            cardView_availability.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Add action for availability item click
                }
            });
        }
    }
}
