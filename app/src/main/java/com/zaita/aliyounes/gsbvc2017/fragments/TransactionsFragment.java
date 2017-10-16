package com.zaita.aliyounes.gsbvc2017.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.helpers.SpinnersHelper;
import com.zaita.aliyounes.gsbvc2017.network.apis.BranchesNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.pojos.Branch;

import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionsFragment extends Fragment {

    private TextInputLayout textInput_OrdQty;
    private TextInputLayout textInput_OrdDate;
    private Spinner spinner_Brcode;
    private Spinner spinner_Prcode;
    private Spinner spinner_Trcode;
    private CompositeDisposable compositeDisposable;

    public TransactionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transactions, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        setupViews(view);
        SpinnersHelper.populateBranchSpinner(getContext() , spinner_Brcode , compositeDisposable);
        SpinnersHelper.populateProductSpinner(getContext() , spinner_Prcode , compositeDisposable);
    }

    private void setupViews(View rootView) {

        textInput_OrdDate   = (TextInputLayout) rootView.findViewById(R.id.OrdDate);
        textInput_OrdQty    = (TextInputLayout) rootView.findViewById(R.id.OrdQty);
        spinner_Trcode      = (Spinner)         rootView.findViewById(R.id.spinner_Trcode);
        spinner_Prcode      = (Spinner)         rootView.findViewById(R.id.spinner_Prcode);
        spinner_Brcode      = (Spinner)         rootView.findViewById(R.id.spinner_Brcode);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String dateFormat = "EEEE, dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
                textInput_OrdDate.getEditText().setText(sdf.format(myCalendar.getTime()));


            }
        };
        textInput_OrdDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), onDateSetListener,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });

        Button button_ajouter = (Button) rootView.findViewById(R.id.button_ajouter);
        button_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkFields())
                    Toast.makeText(getContext(), "Erreur", Toast.LENGTH_SHORT).show();
                else
                    addTransaction();
            }
        });
    }

    private boolean checkFields() {
        if(
                textInput_OrdQty.getEditText()  != null &&
                        textInput_OrdDate.getEditText() != null
                ) {

            if(spinner_Trcode.getSelectedItem() == null) {
                Toast.makeText(getContext(), "Selectionner un Transaction", Toast.LENGTH_SHORT).show();
            }

            if (textInput_OrdQty.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_OrdQty.setError("Quantity est obligatoire");
                return false;
            }
            if (textInput_OrdDate.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_OrdDate.setError("Date est obligatoire");
                return false;
            }

            if(spinner_Prcode.getSelectedItem() == null) {
                Toast.makeText(getContext(), "Selectionner un Product", Toast.LENGTH_SHORT).show();
            }
            if(spinner_Brcode.getSelectedItem() == null) {
                Toast.makeText(getContext(), "Selectionner un Branch", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    private void addTransaction() {
        //TODO: implement API call
    }
}
