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
import com.zaita.aliyounes.gsbvc2017.network.apis.SuppliersNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.pojos.Supplier;

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

public class SuppliersAdapter extends RecyclerView.Adapter<SuppliersAdapter.SupplierVieHolder> {

    //List of suppliers to show
    private List<Supplier> suppliers;
    private List<Supplier> pendingSuppliers;
    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    private HashMap<String, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a r


    public SuppliersAdapter(List<Supplier> suppliers) {
        this.suppliers = suppliers;
        this.pendingSuppliers = new ArrayList<>();
    }

    @Override
    public SupplierVieHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_supplier_layout, viewGroup , false);
        return new SupplierVieHolder(view);
    }

    @Override
    public void onBindViewHolder(SupplierVieHolder supplierViewHolder, int position) {
        final Supplier supplier = suppliers.get(position);
        if (pendingSuppliers.contains(supplier)) {
            // we need to show the "undo" state of the row
            supplierViewHolder.itemView.setBackgroundColor(Color.RED);
            supplierViewHolder.textView_supplierName.setVisibility(View.GONE);
            supplierViewHolder.textView_supplierName_title.setVisibility(View.GONE);
            supplierViewHolder.textView_supplierEmail.setVisibility(View.GONE);
            supplierViewHolder.textView_supplierEmail_title.setVisibility(View.GONE);
            supplierViewHolder.textView_supplierTel.setVisibility(View.GONE);
            supplierViewHolder.textView_supplierTel_title.setVisibility(View.GONE);
            supplierViewHolder.textView_supplierMobile.setVisibility(View.GONE);
            supplierViewHolder.textView_supplierMobile_title.setVisibility(View.GONE);
            supplierViewHolder.textView_supplierAddresse.setVisibility(View.GONE);
            supplierViewHolder.textView_supplierAddresse_title.setVisibility(View.GONE);
            supplierViewHolder.linearLayout_iconBackground.setVisibility(View.GONE);
            supplierViewHolder.buttonUndo.setVisibility(View.VISIBLE);
            supplierViewHolder.buttonUndo.setText(R.string.button_undo);
            supplierViewHolder.buttonUndo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // user wants to undo the removal, let's cancel the pending task
                    Runnable pendingRemovalRunnable = pendingRunnables.get(String.valueOf(supplier.getCodeSup()));
                    pendingRunnables.remove(String.valueOf(supplier.getCodeSup()));
                    if (pendingRemovalRunnable != null)
                        handler.removeCallbacks(pendingRemovalRunnable);
                    pendingSuppliers.remove(supplier);
                    // this will rebind the row in "normal" state
                    notifyItemChanged(suppliers.indexOf(supplier));
                }
            });
            if(supplier.isRemoving()) {
                //Deleting in progress
                supplierViewHolder.itemView.setBackgroundColor(Color.GRAY);
                supplierViewHolder.textView_supplierName.setVisibility(View.GONE);
                supplierViewHolder.textView_supplierName_title.setVisibility(View.GONE);
                supplierViewHolder.textView_supplierEmail.setVisibility(View.GONE);
                supplierViewHolder.textView_supplierEmail_title.setVisibility(View.GONE);
                supplierViewHolder.textView_supplierTel.setVisibility(View.GONE);
                supplierViewHolder.textView_supplierTel_title.setVisibility(View.GONE);
                supplierViewHolder.textView_supplierMobile.setVisibility(View.GONE);
                supplierViewHolder.textView_supplierMobile_title.setVisibility(View.GONE);
                supplierViewHolder.textView_supplierAddresse.setVisibility(View.GONE);
                supplierViewHolder.textView_supplierAddresse_title.setVisibility(View.GONE);
                supplierViewHolder.linearLayout_iconBackground.setVisibility(View.GONE);
                supplierViewHolder.buttonUndo.setVisibility(View.VISIBLE);
                supplierViewHolder.buttonUndo.setText(R.string.deleting);
                supplierViewHolder.buttonUndo.setEnabled(false);
            }
        } else {
            // we need to show the "normal" state
            supplierViewHolder.itemView.setBackgroundColor(Color.WHITE);
            supplierViewHolder.textView_supplierName.setVisibility(View.VISIBLE);
            supplierViewHolder.textView_supplierName_title.setVisibility(View.VISIBLE);
            supplierViewHolder.textView_supplierEmail.setVisibility(View.VISIBLE);
            supplierViewHolder.textView_supplierEmail_title.setVisibility(View.VISIBLE);
            supplierViewHolder.textView_supplierTel.setVisibility(View.VISIBLE);
            supplierViewHolder.textView_supplierTel_title.setVisibility(View.VISIBLE);
            supplierViewHolder.textView_supplierMobile.setVisibility(View.VISIBLE);
            supplierViewHolder.textView_supplierMobile_title.setVisibility(View.VISIBLE);
            supplierViewHolder.textView_supplierAddresse.setVisibility(View.VISIBLE);
            supplierViewHolder.textView_supplierAddresse_title.setVisibility(View.VISIBLE);
            supplierViewHolder.linearLayout_iconBackground.setVisibility(View.VISIBLE);
            supplierViewHolder.bind(supplier);
            supplierViewHolder.buttonUndo.setVisibility(View.GONE);
            supplierViewHolder.buttonUndo.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    public void pendingRemoval(int position) {
        final Supplier supplier = suppliers.get(position);
        if (!pendingSuppliers.contains(supplier)) {
            pendingSuppliers.add(supplier);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(suppliers.indexOf(supplier));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(String.valueOf(supplier.getCodeSup()), pendingRemovalRunnable);
        }
    }

    public void remove(final int position) {
        suppliers.get(position).setRemoving(true);
        notifyItemChanged(position);
        SuppliersNetworkCalls.deleteSupplier(String.valueOf(suppliers.get(position).getCodeSup())).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean value) {
                Supplier supplier = suppliers.get(position);
                if (pendingSuppliers.contains(supplier)) {
                    pendingSuppliers.remove(supplier);
                }
                if (suppliers.contains(supplier)) {
                    suppliers.remove(position);
                    notifyItemRemoved(position);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Get Suppliers" , "Error getting Suppliers" , e);
                if(e instanceof SocketException || e instanceof IOException) {
                    Toast.makeText(GSBApplication.getInstance(), R.string.no_internet , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GSBApplication.getInstance() , e.getMessage() , Toast.LENGTH_LONG).show();
                }
                Supplier supplier = suppliers.get(position);
                // user wants to undo the removal, let's cancel the pending task
                Runnable pendingRemovalRunnable = pendingRunnables.get(String.valueOf(supplier.getCodeSup()));
                pendingRunnables.remove(String.valueOf(supplier.getCodeSup()));
                if (pendingRemovalRunnable != null) handler.removeCallbacks(pendingRemovalRunnable);
                pendingSuppliers.remove(supplier);
                // this will rebind the row in "normal" state
                notifyItemChanged(suppliers.indexOf(supplier));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public boolean isPendingRemoval(int position) {
        Supplier item = suppliers.get(position);
        return pendingSuppliers.contains(item);
    }


    class SupplierVieHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout_iconBackground;
        private TextView textView_supplierName;
        private TextView textView_supplierName_title;
        private TextView textView_supplierEmail;
        private TextView textView_supplierEmail_title;
        private TextView textView_supplierTel;
        private TextView textView_supplierTel_title;
        private TextView textView_supplierMobile;
        private TextView textView_supplierMobile_title;
        private TextView textView_supplierAddresse;
        private TextView textView_supplierAddresse_title;
        private Button   buttonUndo;
        private CardView cardView_supplier;

        SupplierVieHolder(View itemView) {
            super(itemView);
            textView_supplierName           = (TextView) itemView.findViewById(R.id.textView_nomSup);
            textView_supplierName_title     = (TextView) itemView.findViewById(R.id.textView_nomSup_titre);
            textView_supplierEmail          = (TextView) itemView.findViewById(R.id.textView_emailSup);
            textView_supplierEmail_title    = (TextView) itemView.findViewById(R.id.textView_emailSup_titre);
            textView_supplierTel            = (TextView) itemView.findViewById(R.id.textView_telSup);
            textView_supplierTel_title      = (TextView) itemView.findViewById(R.id.textView_telSup_titre);
            textView_supplierMobile         = (TextView) itemView.findViewById(R.id.textView_mobileSup);
            textView_supplierMobile_title   = (TextView) itemView.findViewById(R.id.textView_mobileSup_titre);
            textView_supplierAddresse       = (TextView) itemView.findViewById(R.id.textView_adrSup);
            textView_supplierAddresse_title = (TextView) itemView.findViewById(R.id.textView_adrSup_titre);
            cardView_supplier               = (CardView) itemView.findViewById(R.id.cardView_supplier);
            buttonUndo                      = (Button) itemView.findViewById(R.id.undo_button);
            linearLayout_iconBackground     = (LinearLayout) itemView.findViewById(R.id.linearLayout_iconBackground);
        }

        //Bind data to List item
        void bind(Supplier supplier) {
            textView_supplierName.setText(supplier.getNameSup());
            textView_supplierEmail.setText(supplier.getEmailSup());
            textView_supplierTel.setText(supplier.getTelSup());
            textView_supplierMobile.setText(supplier.getMobSup());
            textView_supplierAddresse.setText(supplier.getAddressSup());
            linearLayout_iconBackground.setBackgroundColor(ColorHelper.getRandomColor());
            cardView_supplier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Add action for supplier item click
                }
            });
        }
    }
}
