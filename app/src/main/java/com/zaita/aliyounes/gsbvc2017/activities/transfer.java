package com.zaita.aliyounes.gsbvc2017.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;

public class transfer extends AppCompatActivity {

    private TextInputLayout textInput_OrdNum;
    private TextInputLayout textInput_OrdQty;
    private TextInputLayout textInput_OrdDate;
    private Spinner spinner_Brcode;
    private Spinner spinner_Prcode;
    private Spinner spinner_Trcode;
    private Spinner spinner_Brcodeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        setupViews();
    }

    private void setupViews() {

        textInput_OrdDate    = (TextInputLayout) findViewById(R.id.OrdDate);
        textInput_OrdQty    = (TextInputLayout) findViewById(R.id.OrdQty);
        spinner_Trcode      = (Spinner) findViewById(R.id.spinner_Trcode);
        spinner_Prcode      = (Spinner) findViewById(R.id.spinner_Prcode);
        spinner_Brcode      = (Spinner) findViewById(R.id.spinner_Brcode);
        spinner_Brcodeto    = (Spinner) findViewById(R.id.spinner_Brcodeto);



        Button button_ajouter = (Button) findViewById(R.id.button_ajouter);
        button_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkFields())
                    Toast.makeText(transfer.this, "Erreur", Toast.LENGTH_SHORT).show();
                else
                    addTransaction();
            }
        });
    }

    private boolean checkFields() {
        if( textInput_OrdQty.getEditText()  != null &&
                textInput_OrdDate.getEditText() != null
                ) {


            if (textInput_OrdQty.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_OrdQty.setError("Quantity est obligatoire");
                return false;
            }
            if (textInput_OrdDate.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_OrdDate.setError("Date est obligatoire");
                return false;
            }
            if(spinner_Trcode.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner un Transaction", Toast.LENGTH_SHORT).show();
            }
            if(spinner_Prcode.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner un Product", Toast.LENGTH_SHORT).show();
            }
            if(spinner_Brcode.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner From  Branch", Toast.LENGTH_SHORT).show();
            }
            if(spinner_Brcodeto.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner To Branch", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;
    }



    private void addTransaction() {
        //TODO: implement API call
    }
}
