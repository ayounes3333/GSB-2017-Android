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
import com.zaita.aliyounes.gsbvc2017.helpers.SpinnersHelper;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransferFragment extends Fragment {

    private TextInputLayout textInput_OrdQty;
    private TextInputLayout textInput_OrdDate;
    private CompositeDisposable compositeDisposable;
    private Spinner spinner_Brcode;
    private Spinner spinner_Prcode;
    private Spinner spinner_Trcode;
    private Spinner spinner_Brcodeto;

    public TransferFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        compositeDisposable = new CompositeDisposable();
        SpinnersHelper.populateProductSpinner(getContext() , spinner_Prcode , compositeDisposable);
        SpinnersHelper.populateBranchSpinner(getContext() , spinner_Brcode , compositeDisposable);
        SpinnersHelper.populateBranchSpinner(getContext() , spinner_Brcodeto , compositeDisposable);
    }

    private void setupViews(View rootView) {

        textInput_OrdDate = (TextInputLayout) rootView.findViewById(R.id.OrdDate);
        textInput_OrdQty = (TextInputLayout) rootView.findViewById(R.id.OrdQty);
        spinner_Trcode = (Spinner) rootView.findViewById(R.id.spinner_Trcode);
        spinner_Prcode = (Spinner) rootView.findViewById(R.id.spinner_Prcode);
        spinner_Brcode = (Spinner) rootView.findViewById(R.id.spinner_Brcode);
        spinner_Brcodeto = (Spinner) rootView.findViewById(R.id.spinner_Brcodeto);

        Button button_ajouter = (Button) rootView.findViewById(R.id.button_ajouter);
        button_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkFields())
                    Toast.makeText(getContext(), "Erreur", Toast.LENGTH_SHORT).show();
                else
                    addTransaction();
            }
        });
    }

    private boolean checkFields() {
        if (
                textInput_OrdQty.getEditText() != null  &&
                textInput_OrdDate.getEditText() != null)
            {

            if (textInput_OrdQty.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_OrdQty.setError("Quantity est obligatoire");
                return false;
            }
            if (textInput_OrdDate.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_OrdDate.setError("Date est obligatoire");
                return false;
            }
            if (spinner_Trcode.getSelectedItem() == null) {
                Toast.makeText(getContext(), "Selectionner un Transaction", Toast.LENGTH_SHORT).show();
            }
            if (spinner_Prcode.getSelectedItem() == null) {
                Toast.makeText(getContext(), "Selectionner un Product", Toast.LENGTH_SHORT).show();
            }
            if (spinner_Brcode.getSelectedItem() == null) {
                Toast.makeText(getContext(), "Selectionner From  Branch", Toast.LENGTH_SHORT).show();
            }
            if (spinner_Brcodeto.getSelectedItem() == null) {
                Toast.makeText(getContext(), "Selectionner To Branch", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;
    }


    private void addTransaction() {
        //TODO: implement API call
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transfer, container, false);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
