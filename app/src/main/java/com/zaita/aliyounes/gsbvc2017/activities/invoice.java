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

public class invoice extends AppCompatActivity {

    private TextInputLayout textInput_OrdNum;
    private TextInputLayout textInput_OrdQty;
    private TextInputLayout textInput_OrdDate;
    private TextInputLayout textInput_Value;
    private Spinner spinner_Brcode;
    private Spinner spinner_Prcode;
    private Spinner spinner_Trcode;
    private Spinner spinner_Client;
    private TextInputLayout textInput_Selprice  ;
    private TextInputLayout textInput_Net       ;
    private TextInputLayout textInput_Discount  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoute_client);
        setupViews();
    }

    private void setupViews() {
        textInput_OrdNum      = (TextInputLayout) findViewById(R.id.OrdNum);
        textInput_OrdDate     = (TextInputLayout) findViewById(R.id.OrdDate);
        textInput_OrdQty      = (TextInputLayout) findViewById(R.id.OrdQty);
        spinner_Client        = (Spinner) findViewById(R.id.spinner_Client);
        textInput_Selprice    = (TextInputLayout) findViewById(R.id.OrdDate);
        textInput_Net         = (TextInputLayout) findViewById(R.id.Net);
        textInput_Value         = (TextInputLayout) findViewById(R.id.Value);
        textInput_Discount    = (TextInputLayout) findViewById(R.id.Discount);
        spinner_Trcode        = (Spinner) findViewById(R.id.spinner_Trcode);
        spinner_Prcode        = (Spinner) findViewById(R.id.spinner_Prcode);
        spinner_Brcode        = (Spinner) findViewById(R.id.spinner_Brcode);



        Button button_ajouter = (Button) findViewById(R.id.button_ajouter);
        button_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkFields())
                    Toast.makeText(invoice.this, "Erreur", Toast.LENGTH_SHORT).show();
                else
                    addInvoice();
            }
        });
    }

    private boolean checkFields() {
        if( textInput_OrdNum.getEditText()       != null &&
                textInput_OrdQty.getEditText()   != null &&
                textInput_OrdDate.getEditText()  != null &&
                textInput_Net.getEditText()      != null &&
                textInput_Value.getEditText()    != null &&
                textInput_Selprice.getEditText() != null
                ) {

            if (textInput_OrdNum.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_OrdNum.setError("Le Numero est obligatoire");
                return false;
            }
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
                Toast.makeText(this, "Selectionner un Transaction", Toast.LENGTH_SHORT).show();
            }
            if(spinner_Prcode.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner un Product", Toast.LENGTH_SHORT).show();
            }
            if(spinner_Brcode.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner un Branch", Toast.LENGTH_SHORT).show();
            }
            if(spinner_Client.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner un Client", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return false;
    }



    private void addInvoice() {
        //TODO: implement API call
    }
}
