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
import com.zaita.aliyounes.gsbvc2017.helpers.TextUtils;
import com.zaita.aliyounes.gsbvc2017.network.apis.ProductsNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.pojos.Product;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ali Younes at 8/11/2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductVieHolder> {

    //List of products to show
    private List<Product> products;
    private List<Product> pendingProducts;
    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    private HashMap<String, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a r


    public ProductsAdapter(List<Product> products) {
        this.products = products;
        this.pendingProducts = new ArrayList<>();
    }

    @Override
    public ProductVieHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_layout, viewGroup , false);
        return new ProductVieHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductVieHolder productViewHolder, int position) {
        final Product product = products.get(position);
        if (pendingProducts.contains(product)) {
            // we need to show the "undo" state of the row
            productViewHolder.itemView.setBackgroundColor(Color.RED);
            productViewHolder.textView_productName.setVisibility(View.GONE);
            productViewHolder.textView_productName_title.setVisibility(View.GONE);
            productViewHolder.textView_productType.setVisibility(View.GONE);
            productViewHolder.textView_productType_title.setVisibility(View.GONE);
            productViewHolder.textView_productFamille.setVisibility(View.GONE);
            productViewHolder.textView_productFamille_title.setVisibility(View.GONE);
            productViewHolder.textView_productSaison.setVisibility(View.GONE);
            productViewHolder.textView_productSaison_title.setVisibility(View.GONE);
            productViewHolder.textView_productPrixRevient.setVisibility(View.GONE);
            productViewHolder.textView_productPrixRevient_title.setVisibility(View.GONE);
            productViewHolder.textView_productPrixVent.setVisibility(View.GONE);
            productViewHolder.textView_productPrixVent_title.setVisibility(View.GONE);
            productViewHolder.linearLayout_iconBackground.setVisibility(View.GONE);
            productViewHolder.buttonUndo.setVisibility(View.VISIBLE);
            productViewHolder.buttonUndo.setText(R.string.button_undo);
            productViewHolder.buttonUndo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // user wants to undo the removal, let's cancel the pending task
                    Runnable pendingRemovalRunnable = pendingRunnables.get(String.valueOf(product.getCodeBr()));
                    pendingRunnables.remove(String.valueOf(product.getCodeBr()));
                    if (pendingRemovalRunnable != null)
                        handler.removeCallbacks(pendingRemovalRunnable);
                    pendingProducts.remove(product);
                    // this will rebind the row in "normal" state
                    notifyItemChanged(products.indexOf(product));
                }
            });
            if(product.isRemoving()) {
                //Deleting in progress
                productViewHolder.itemView.setBackgroundColor(Color.GRAY);
                productViewHolder.textView_productName.setVisibility(View.GONE);
                productViewHolder.textView_productName_title.setVisibility(View.GONE);
                productViewHolder.textView_productType.setVisibility(View.GONE);
                productViewHolder.textView_productType_title.setVisibility(View.GONE);
                productViewHolder.textView_productFamille.setVisibility(View.GONE);
                productViewHolder.textView_productFamille_title.setVisibility(View.GONE);
                productViewHolder.textView_productSaison.setVisibility(View.GONE);
                productViewHolder.textView_productSaison_title.setVisibility(View.GONE);
                productViewHolder.textView_productPrixRevient.setVisibility(View.GONE);
                productViewHolder.textView_productPrixRevient_title.setVisibility(View.GONE);
                productViewHolder.textView_productPrixVent.setVisibility(View.GONE);
                productViewHolder.textView_productPrixVent_title.setVisibility(View.GONE);
                productViewHolder.linearLayout_iconBackground.setVisibility(View.GONE);
                productViewHolder.buttonUndo.setVisibility(View.VISIBLE);
                productViewHolder.buttonUndo.setText(R.string.deleting);
                productViewHolder.buttonUndo.setEnabled(false);
            }
        } else {
            // we need to show the "normal" state
            productViewHolder.itemView.setBackgroundColor(Color.WHITE);
            productViewHolder.textView_productName.setVisibility(View.VISIBLE);
            productViewHolder.textView_productName_title.setVisibility(View.VISIBLE);
            productViewHolder.textView_productType.setVisibility(View.VISIBLE);
            productViewHolder.textView_productType_title.setVisibility(View.VISIBLE);
            productViewHolder.textView_productFamille.setVisibility(View.VISIBLE);
            productViewHolder.textView_productFamille_title.setVisibility(View.VISIBLE);
            productViewHolder.textView_productSaison.setVisibility(View.VISIBLE);
            productViewHolder.textView_productSaison_title.setVisibility(View.VISIBLE);
            productViewHolder.textView_productPrixRevient.setVisibility(View.VISIBLE);
            productViewHolder.textView_productPrixRevient_title.setVisibility(View.VISIBLE);
            productViewHolder.textView_productPrixVent.setVisibility(View.VISIBLE);
            productViewHolder.textView_productPrixVent_title.setVisibility(View.VISIBLE);
            productViewHolder.linearLayout_iconBackground.setVisibility(View.VISIBLE);
            productViewHolder.bind(product);
            productViewHolder.buttonUndo.setVisibility(View.GONE);
            productViewHolder.buttonUndo.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void pendingRemoval(int position) {
        final Product product = products.get(position);
        if (!pendingProducts.contains(product)) {
            pendingProducts.add(product);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(products.indexOf(product));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(String.valueOf(product.getCodeBr()), pendingRemovalRunnable);
        }
    }

    public void remove(final int position) {
        products.get(position).setRemoving(true);
        notifyItemChanged(position);
        ProductsNetworkCalls.deleteProduct(String.valueOf(products.get(position).getCodePr())).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean value) {
                Product product = products.get(position);
                if (pendingProducts.contains(product)) {
                    pendingProducts.remove(product);
                }
                if (products.contains(product)) {
                    products.remove(position);
                    notifyItemRemoved(position);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Get Products" , "Error getting Products" , e);
                if(e instanceof SocketException || e instanceof IOException) {
                    Toast.makeText(GSBApplication.getInstance(), R.string.no_internet , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GSBApplication.getInstance() , e.getMessage() , Toast.LENGTH_LONG).show();
                }
                Product product = products.get(position);
                // user wants to undo the removal, let's cancel the pending task
                Runnable pendingRemovalRunnable = pendingRunnables.get(String.valueOf(product.getCodeBr()));
                pendingRunnables.remove(String.valueOf(product.getCodeBr()));
                if (pendingRemovalRunnable != null) handler.removeCallbacks(pendingRemovalRunnable);
                pendingProducts.remove(product);
                // this will rebind the row in "normal" state
                notifyItemChanged(products.indexOf(product));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public boolean isPendingRemoval(int position) {
        Product item = products.get(position);
        return pendingProducts.contains(item);
    }
    
    class ProductVieHolder extends RecyclerView.ViewHolder {

        private final LinearLayout linearLayout_iconBackground;
        private TextView textView_productName;
        private TextView textView_productName_title;
        private TextView textView_productType;
        private TextView textView_productType_title;
        private TextView textView_productFamille;
        private TextView textView_productFamille_title;
        private TextView textView_productSaison;
        private TextView textView_productSaison_title;
        private TextView textView_productPrixRevient;
        private TextView textView_productPrixRevient_title;
        private TextView textView_productPrixVent;
        private TextView textView_productPrixVent_title;
        private Button   buttonUndo;
        private CardView cardView_product;

        ProductVieHolder(View itemView) {
            super(itemView);
            textView_productName              = (TextView) itemView.findViewById(R.id.textView_nomPro);
            textView_productName_title        = (TextView) itemView.findViewById(R.id.textView_nomPro_titre);
            textView_productType              = (TextView) itemView.findViewById(R.id.textView_type_prd);
            textView_productType_title        = (TextView) itemView.findViewById(R.id.textView_type_prd_titre);
            textView_productFamille           = (TextView) itemView.findViewById(R.id.textView_fam_prod);
            textView_productFamille_title     = (TextView) itemView.findViewById(R.id.textView_fam_prd_titre);
            textView_productSaison            = (TextView) itemView.findViewById(R.id.textView_Sais_prd);
            textView_productSaison_title      = (TextView) itemView.findViewById(R.id.textView_Sais_prd_titre);
            textView_productPrixRevient       = (TextView) itemView.findViewById(R.id.textView_prix_revient);
            textView_productPrixRevient_title = (TextView) itemView.findViewById(R.id.textView_prix_revient_titre);
            textView_productPrixVent          = (TextView) itemView.findViewById(R.id.textView_prix_vente);
            textView_productPrixVent_title    = (TextView) itemView.findViewById(R.id.textView_prix_vente_titre);
            buttonUndo                        = (Button) itemView.findViewById(R.id.undo_button);
            cardView_product                  = (CardView) itemView.findViewById(R.id.cardView_product);
            linearLayout_iconBackground       = (LinearLayout) itemView.findViewById(R.id.linearLayout_iconBackground);
        }

        //Bind data to List item
        void bind(Product product) {
            textView_productName.setText(product.getNamePr());
            textView_productType.setText(String.valueOf(product.getTypePr()));
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
