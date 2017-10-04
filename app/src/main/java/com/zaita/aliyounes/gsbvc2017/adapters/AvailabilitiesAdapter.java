package com.zaita.aliyounes.gsbvc2017.adapters;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.helpers.ColorHelper;
import com.zaita.aliyounes.gsbvc2017.pojos.Availability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ali Younes at 8/11/2017.
 */

public class AvailabilitiesAdapter extends RecyclerView.Adapter<AvailabilitiesAdapter.AvailabilityViewHolder> {

    //List of availabilities to show
    private List<Availability> availabilities;
    private List<Availability> pendingAvailabilities;
    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    private HashMap<String, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a r


    public AvailabilitiesAdapter(List<Availability> availabilities) {
        this.availabilities = availabilities;
        this.pendingAvailabilities = new ArrayList<>();
    }

    @Override
    public AvailabilityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_availability_layout, viewGroup , false);
        return new AvailabilityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AvailabilityViewHolder availabilityViewHolder, int position) {
        final Availability availability = availabilities.get(position);
        if (pendingAvailabilities.contains(availability)) {
            // we need to show the "undo" state of the row
            availabilityViewHolder.itemView.setBackgroundColor(Color.RED);
            availabilityViewHolder.textView_brand.setVisibility(View.GONE);
            availabilityViewHolder.textView_brand_titre.setVisibility(View.GONE);
            availabilityViewHolder.textView_product.setVisibility(View.GONE);
            availabilityViewHolder.textView_product_titre.setVisibility(View.GONE);
            availabilityViewHolder.textView_qty.setVisibility(View.GONE);
            availabilityViewHolder.textView_qty_titre.setVisibility(View.GONE);
            availabilityViewHolder.linearLayout_iconBackground.setVisibility(View.GONE);
            availabilityViewHolder.buttonUndo.setVisibility(View.VISIBLE);
            availabilityViewHolder.buttonUndo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // user wants to undo the removal, let's cancel the pending task
                    Runnable pendingRemovalRunnable = pendingRunnables.get(availability.brand+":"+availability.product);
                    pendingRunnables.remove(availability.brand+":"+availability.product);
                    if (pendingRemovalRunnable != null)
                        handler.removeCallbacks(pendingRemovalRunnable);
                    pendingAvailabilities.remove(availability);
                    // this will rebind the row in "normal" state
                    notifyItemChanged(availabilities.indexOf(availability));
                }
            });
            if(availability.isRemoving) {
                //Deleting in progress
                availabilityViewHolder.itemView.setBackgroundColor(Color.GRAY);
                availabilityViewHolder.textView_brand.setVisibility(View.GONE);
                availabilityViewHolder.textView_brand_titre.setVisibility(View.GONE);
                availabilityViewHolder.textView_product.setVisibility(View.GONE);
                availabilityViewHolder.textView_product_titre.setVisibility(View.GONE);
                availabilityViewHolder.textView_qty.setVisibility(View.GONE);
                availabilityViewHolder.textView_qty_titre.setVisibility(View.GONE);
                availabilityViewHolder.linearLayout_iconBackground.setVisibility(View.GONE);
                availabilityViewHolder.buttonUndo.setVisibility(View.VISIBLE);
                availabilityViewHolder.buttonUndo.setText(R.string.deleting);
                availabilityViewHolder.buttonUndo.setEnabled(false);
            }
        } else {
            // we need to show the "normal" state
            availabilityViewHolder.itemView.setBackgroundColor(Color.WHITE);
            availabilityViewHolder.textView_brand.setVisibility(View.VISIBLE);
            availabilityViewHolder.textView_brand_titre.setVisibility(View.VISIBLE);
            availabilityViewHolder.textView_product.setVisibility(View.VISIBLE);
            availabilityViewHolder.textView_product_titre.setVisibility(View.VISIBLE);
            availabilityViewHolder.textView_qty.setVisibility(View.VISIBLE);
            availabilityViewHolder.textView_qty_titre.setVisibility(View.VISIBLE);
            availabilityViewHolder.linearLayout_iconBackground.setVisibility(View.VISIBLE);
            availabilityViewHolder.bind(availability);
            availabilityViewHolder.buttonUndo.setVisibility(View.GONE);
            availabilityViewHolder.buttonUndo.setOnClickListener(null);
        }
    }
    public void pendingRemoval(int position) {
        final Availability availability = availabilities.get(position);
        if (!pendingAvailabilities.contains(availability)) {
            pendingAvailabilities.add(availability);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(availabilities.indexOf(availability));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(availability.brand+":"+availability.product, pendingRemovalRunnable);
        }
    }

    public void remove(final int position) {
        availabilities.get(position).isRemoving = true;
        notifyItemChanged(position);
        //TODO: Remove after implementing network call
        Availability availability = availabilities.get(position);
        if (pendingAvailabilities.contains(availability)) {
            pendingAvailabilities.remove(availability);
        }
        if (availabilities.contains(availability)) {
            availabilities.remove(position);
            notifyItemRemoved(position);
        }
      /*AvailabilitiesNetworkCalls.deleteAvailability(String.valueOf(availabilities.get(position).getCodeBrd())).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean value) {
                Availability availability = availabilities.get(position);
                if (pendingAvailabilities.contains(availability)) {
                    pendingAvailabilities.remove(availability);
                }
                if (availabilities.contains(availability)) {
                    availabilities.remove(position);
                    notifyItemRemoved(position);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Get Availabilitys" , "Error getting Availabilitys" , e);
                if(e instanceof SocketException || e instanceof IOException) {
                    Toast.makeText(GSBApplication.getInstance(), R.string.no_internet , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GSBApplication.getInstance() , e.getMessage() , Toast.LENGTH_LONG).show();
                }
                Availability availability = availabilities.get(position);
                // user wants to undo the removal, let's cancel the pending task
                Runnable pendingRemovalRunnable = pendingRunnables.get(availability.brand+":"+availability.product);
                pendingRunnables.remove(availability.brand+":"+availability.product);
                if (pendingRemovalRunnable != null) handler.removeCallbacks(pendingRemovalRunnable);
                pendingAvailabilities.remove(availability);
                // this will rebind the row in "normal" state
                notifyItemChanged(availabilities.indexOf(availability));
            }

            @Override
            public void onComplete() {

            }
        });*/
    }

    public boolean isPendingRemoval(int position) {
        Availability item = availabilities.get(position);
        return pendingAvailabilities.contains(item);
    }
    @Override
    public int getItemCount() {
        return availabilities.size();
    }

    class AvailabilityViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_brand;
        private TextView textView_brand_titre;
        private TextView textView_product;
        private TextView textView_product_titre;
        private TextView textView_qty;
        private TextView textView_qty_titre;
        private LinearLayout linearLayout_iconBackground;
        private CardView cardView_availability;
        private Button buttonUndo;

        AvailabilityViewHolder(View itemView) {
            super(itemView);
            textView_brand              = (TextView) itemView.findViewById(R.id.textView_nomBrand);
            textView_brand_titre        = (TextView) itemView.findViewById(R.id.textView_nomBrand_titre);
            textView_product            = (TextView) itemView.findViewById(R.id.textView_nomProd);
            textView_product_titre      = (TextView) itemView.findViewById(R.id.textView_nomProd_titre);
            textView_qty                = (TextView) itemView.findViewById(R.id.textView_qty);
            textView_qty_titre          = (TextView) itemView.findViewById(R.id.textView_qty_titre);
            cardView_availability       = (CardView) itemView.findViewById(R.id.cardView_availability);
            buttonUndo                  = (Button) itemView.findViewById(R.id.undo_button); 
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
