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
import com.zaita.aliyounes.gsbvc2017.helpers.TextUtils;
import com.zaita.aliyounes.gsbvc2017.pojos.Product;

import java.util.List;

/**
 * Created by Ali Younes on 8/11/2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductVieHolder> {

    //List of products to show
    private List<Product> products;

    public ProductsAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public ProductVieHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_layout, viewGroup , false);
        return new ProductVieHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductVieHolder productVieHolder, int position) {
        Product product = products.get(position);
        productVieHolder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductVieHolder extends RecyclerView.ViewHolder {

        private final LinearLayout linearLayout_iconBackground;
        private TextView textView_productName;
        private TextView textView_productType;
        private TextView textView_productFamille;
        private TextView textView_productSaison;
        private TextView textView_productPrixRevient;
        private TextView textView_productPrixVent;
        private CardView cardView_product;

        ProductVieHolder(View itemView) {
            super(itemView);
            textView_productName        = (TextView) itemView.findViewById(R.id.textView_nomPro);
            textView_productType        = (TextView) itemView.findViewById(R.id.textView_type_prd);
            textView_productFamille     = (TextView) itemView.findViewById(R.id.textView_fam_prod);
            textView_productSaison      = (TextView) itemView.findViewById(R.id.textView_Sais_prd);
            textView_productPrixRevient = (TextView) itemView.findViewById(R.id.textView_prix_revient);
            textView_productPrixVent    = (TextView) itemView.findViewById(R.id.textView_prix_vente);
            cardView_product            = (CardView) itemView.findViewById(R.id.cardView_product);
            linearLayout_iconBackground = (LinearLayout) itemView.findViewById(R.id.linearLayout_iconBackground);
        }

        //Bind data to List item
        void bind(Product product) {
            textView_productName.setText(product.getNamePr());
            textView_productType.setText(product.getTypePr());
            textView_productFamille.setText(product.getFamilyPr());
            textView_productSaison.setText(product.getSeasonPr());
            textView_productPrixRevient.setText(TextUtils.formatPrice(product.getCostPrice()));
            textView_productPrixVent.setText(TextUtils.formatPrice(product.getSellingPrice()));
            linearLayout_iconBackground.setBackgroundColor(ColorHelper.getRandomColor());
            cardView_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Add action for product item click
                }
            });
        }
    }
}
