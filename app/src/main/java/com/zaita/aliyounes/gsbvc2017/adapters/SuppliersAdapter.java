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
import com.zaita.aliyounes.gsbvc2017.pojos.Supplier;

import java.util.List;

/**
 * Created by Ali Younes on 8/11/2017.
 */

public class SuppliersAdapter extends RecyclerView.Adapter<SuppliersAdapter.SupplierVieHolder> {

    //List of suppliers to show
    private List<Supplier> suppliers;

    public SuppliersAdapter(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    public SupplierVieHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_supplier_layout, viewGroup , false);
        return new SupplierVieHolder(view);
    }

    @Override
    public void onBindViewHolder(SupplierVieHolder supplierVieHolder, int position) {
        Supplier supplier = suppliers.get(position);
        supplierVieHolder.bind(supplier);
    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    class SupplierVieHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout_iconBackground;
        private TextView textView_supplierName;
        private TextView textView_supplierEmail;
        private TextView textView_supplierTel;
        private TextView textView_supplierMobile;
        private TextView textView_supplierAddresse;
        private CardView cardView_supplier;

        SupplierVieHolder(View itemView) {
            super(itemView);
            textView_supplierName     = (TextView) itemView.findViewById(R.id.textView_nomSup);
            textView_supplierEmail    = (TextView) itemView.findViewById(R.id.textView_emailSup);
            textView_supplierTel      = (TextView) itemView.findViewById(R.id.textView_telSup);
            textView_supplierMobile   = (TextView) itemView.findViewById(R.id.textView_mobileSup);
            textView_supplierAddresse = (TextView) itemView.findViewById(R.id.textView_adrSup);
            cardView_supplier         = (CardView) itemView.findViewById(R.id.cardView_supplier);
            linearLayout_iconBackground = (LinearLayout) itemView.findViewById(R.id.linearLayout_iconBackground);
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
