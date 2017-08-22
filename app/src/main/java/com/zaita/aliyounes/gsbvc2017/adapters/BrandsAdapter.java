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
import com.zaita.aliyounes.gsbvc2017.pojos.Brand;

import java.util.List;

/**
 * Created by Ali Younes on 8/11/2017.
 */

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.BrandVieHolder> {

    //List of brands to show
    private List<Brand> brands;

    public BrandsAdapter(List<Brand> brands) {
        this.brands = brands;
    }

    @Override
    public BrandVieHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_brand_layout, viewGroup , false);
        return new BrandVieHolder(view);
    }

    @Override
    public void onBindViewHolder(BrandVieHolder brandVieHolder, int position) {
        Brand brand = brands.get(position);
        brandVieHolder.bind(brand);
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    class BrandVieHolder extends RecyclerView.ViewHolder {

        private final LinearLayout linearLayout_iconBackground;
        private TextView textView_brandName;
        private CardView cardView_brand;

        BrandVieHolder(View itemView) {
            super(itemView);
            textView_brandName  = (TextView) itemView.findViewById(R.id.textView_nomBrand);
            cardView_brand      = (CardView) itemView.findViewById(R.id.cardView_brand);
            linearLayout_iconBackground = (LinearLayout) itemView.findViewById(R.id.linearLayout_iconBackground);
        }

        //Bind data to List item
        void bind(Brand brand) {
            textView_brandName.setText(brand.getNameBrd());
            linearLayout_iconBackground.setBackgroundColor(ColorHelper.getRandomColor());
            cardView_brand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Add action for brand item click
                }
            });
        }
    }
}
