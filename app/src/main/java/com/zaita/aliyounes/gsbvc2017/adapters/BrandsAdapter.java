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
import com.zaita.aliyounes.gsbvc2017.network.apis.BrandsNetworkCalls;
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

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.BrandVieHolder> {

    //List of brands to show
    private List<Brand> brands;
    private List<Brand> pendingBrands;
    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    HashMap<String, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a r

    public BrandsAdapter(List<Brand> brands) {
        this.brands = brands;
        pendingBrands = new ArrayList<>();
    }

    @Override
    public BrandVieHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_brand_layout, viewGroup , false);
        return new BrandVieHolder(view);
    }

    @Override
    public void onBindViewHolder(BrandVieHolder brandViewHolder, int position) {
        final Brand brand = brands.get(position);
        if (pendingBrands.contains(brand)) {
            // we need to show the "undo" state of the row
            brandViewHolder.itemView.setBackgroundColor(Color.RED);
            brandViewHolder.textView_brandName.setVisibility(View.GONE);
            brandViewHolder.textView_brandNameTitle.setVisibility(View.GONE);
            brandViewHolder.linearLayout_iconBackground.setVisibility(View.GONE);
            brandViewHolder.buttonUndo.setVisibility(View.VISIBLE);
            brandViewHolder.buttonUndo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // user wants to undo the removal, let's cancel the pending task
                    Runnable pendingRemovalRunnable = pendingRunnables.get(brand.getNameBrd());
                    pendingRunnables.remove(brand.getNameBrd());
                    if (pendingRemovalRunnable != null) handler.removeCallbacks(pendingRemovalRunnable);
                    pendingBrands.remove(brand);
                    // this will rebind the row in "normal" state
                    notifyItemChanged(brands.indexOf(brand));
                }
            });
        } else {
            // we need to show the "normal" state
            brandViewHolder.itemView.setBackgroundColor(Color.WHITE);
            brandViewHolder.textView_brandName.setVisibility(View.VISIBLE);
            brandViewHolder.textView_brandNameTitle.setVisibility(View.VISIBLE);
            brandViewHolder.linearLayout_iconBackground.setVisibility(View.VISIBLE);
            brandViewHolder.bind(brand);
            brandViewHolder.buttonUndo.setVisibility(View.GONE);
            brandViewHolder.buttonUndo.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    public void pendingRemoval(int position) {
        final Brand brand = brands.get(position);
        if (!pendingBrands.contains(brand)) {
            pendingBrands.add(brand);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(brands.indexOf(brand));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(brand.getNameBrd(), pendingRemovalRunnable);
        }
    }

    public void remove(final int position) {
        BrandsNetworkCalls.deleteBrand(String.valueOf(brands.get(position).getCodeBrd())).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean value) {
                Brand brand = brands.get(position);
                if (pendingBrands.contains(brand)) {
                    pendingBrands.remove(brand);
                }
                if (brands.contains(brand)) {
                    brands.remove(position);
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
                Brand brand = brands.get(position);
                // user wants to undo the removal, let's cancel the pending task
                Runnable pendingRemovalRunnable = pendingRunnables.get(brand.getNameBrd());
                pendingRunnables.remove(brand.getNameBrd());
                if (pendingRemovalRunnable != null) handler.removeCallbacks(pendingRemovalRunnable);
                pendingBrands.remove(brand);
                // this will rebind the row in "normal" state
                notifyItemChanged(brands.indexOf(brand));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public boolean isPendingRemoval(int position) {
        Brand item = brands.get(position);
        return pendingBrands.contains(item);
    }

    class BrandVieHolder extends RecyclerView.ViewHolder {

        private final LinearLayout linearLayout_iconBackground;
        private TextView textView_brandName;
        private TextView textView_brandNameTitle;
        private CardView cardView_brand;
        private Button   buttonUndo;

        BrandVieHolder(View itemView) {
            super(itemView);
            textView_brandName          = (TextView) itemView.findViewById(R.id.textView_nomBrand);
            textView_brandNameTitle = (TextView) itemView.findViewById(R.id.textView_nomBrand_titre);
            cardView_brand              = (CardView) itemView.findViewById(R.id.cardView_brand);
            buttonUndo                  = (Button)   itemView.findViewById(R.id.undo_button);
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
