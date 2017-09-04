package com.zaita.aliyounes.gsbvc2017.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;


public class InvoicesFragment extends Fragment {

    private TextInputLayout textInput_OrdQty;
    private TextInputLayout textInput_OrdDate;
    private TextInputLayout textInput_Value;
    private Spinner spinner_Brcode;
    private Spinner spinner_Prcode;
    private Spinner spinner_Trcode;
    private Spinner spinner_Client;
    private TextInputLayout textInput_Selprice;
    private TextInputLayout textInput_Net;
    private TextInputLayout textInput_Discount;

    public InvoicesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invoices, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
    }

    private void setupViews(View rootView) {

        textInput_OrdDate     = (TextInputLayout) rootView.findViewById(R.id.OrdDate);
        textInput_OrdQty      = (TextInputLayout) rootView.findViewById(R.id.OrdQty);
        spinner_Client        = (Spinner)         rootView.findViewById(R.id.spinner_Client);
        textInput_Selprice    = (TextInputLayout) rootView.findViewById(R.id.Selprice);
        textInput_Net         = (TextInputLayout) rootView.findViewById(R.id.Net);
        textInput_Value       = (TextInputLayout) rootView.findViewById(R.id.Value);
        textInput_Discount    = (TextInputLayout) rootView.findViewById(R.id.Discount);
        spinner_Trcode        = (Spinner)         rootView.findViewById(R.id.spinner_Trcode);
        spinner_Prcode        = (Spinner)         rootView.findViewById(R.id.spinner_Prcode);
        spinner_Brcode        = (Spinner)         rootView.findViewById(R.id.spinner_Brcode);



        Button button_ajouter = (Button) rootView.findViewById(R.id.button_ajouter);
        button_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkFields())
                    Toast.makeText(getContext(), "Erreur", Toast.LENGTH_SHORT).show();
                else
                    addInvoice();
            }
        });
    }

    private boolean checkFields() {
        if(
                textInput_OrdQty.getEditText()   != null &&
                        textInput_OrdDate.getEditText()  != null &&
                        textInput_Net.getEditText()      != null &&
                        textInput_Value.getEditText()    != null &&
                        textInput_Selprice.getEditText() != null &&
                        textInput_Discount.getEditText() != null
                ) {


            if (textInput_Value.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_Value.setError("Value est obligatoire");
                return false;
            }
            if (textInput_Selprice.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_Selprice.setError("Selling Price est obligatoire");
                return false;
            }
            if (textInput_OrdQty.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_OrdQty.setError("Quantity est obligatoire");
                return false;
            }
            if (textInput_OrdDate.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_OrdDate.setError("Date est obligatoire");
                return false;
            }
            if(spinner_Trcode.getSelectedItem() == null) {
                Toast.makeText(getContext(), "Selectionner un Transaction", Toast.LENGTH_SHORT).show();
            }
            if(spinner_Prcode.getSelectedItem() == null) {
                Toast.makeText(getContext(), "Selectionner un Product", Toast.LENGTH_SHORT).show();
            }
            if(spinner_Brcode.getSelectedItem() == null) {
                Toast.makeText(getContext(), "Selectionner un Branch", Toast.LENGTH_SHORT).show();
            }
            if(spinner_Client.getSelectedItem() == null) {
                Toast.makeText(getContext(), "Selectionner un Client", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return false;
    }

    private void addInvoice() {
        //TODO: implement API call
    }
}
