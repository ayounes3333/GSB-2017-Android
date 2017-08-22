package com.zaita.aliyounes.gsbvc2017.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;

public class AjouteProductActivity extends AppCompatActivity {
    private TextInputLayout textInput_nom;
    private TextInputLayout textInput_cost;
    private TextInputLayout textInput_sel;

    private Spinner spinner_prd_type;
    private Spinner spinner_prd_fam;
    private Spinner spinner_prd_season;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_product);
        setupViews();
    }
    private void setupViews() {
        textInput_nom         = (TextInputLayout) findViewById(R.id.textInput_nom);
        textInput_cost        = (TextInputLayout) findViewById(R.id.textInput_cost);
        textInput_sel        = (TextInputLayout) findViewById(R.id.textInput_sel);
        spinner_prd_type      = (Spinner) findViewById(R.id.spinner_prd_type);
        spinner_prd_fam       = (Spinner) findViewById(R.id.spinner_prd_fam);
        spinner_prd_season    = (Spinner) findViewById(R.id.spinner_prd_season);

        Button button_ajouter = (Button) findViewById(R.id.button_ajouter);
        button_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkFields())
                    Toast.makeText(AjouteProductActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
                else
                    addProduct();
            }
        });
    }

    private boolean checkFields() {
        if(     textInput_nom.getEditText()      != null &&
                textInput_cost.getEditText()      != null&&
                textInput_sel.getEditText()      != null) {
            if (textInput_nom.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_nom.setError("Le nom est obligatoire");
                return false;
            }

            if(spinner_prd_type.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner un Produit Type", Toast.LENGTH_SHORT).show();
            }
            if(spinner_prd_fam.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner un Famille ", Toast.LENGTH_SHORT).show();
            }
            if(spinner_prd_season.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner un Saison ", Toast.LENGTH_SHORT).show();
            }
            if (textInput_cost.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_cost.setError("Prix De Revient est obligatoire");
                return false;
            }
            if (textInput_sel.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_sel.setError("Prix De Vente est obligatoire");
                return false;
            }
            return true;
        }
        return false;
    }



    private void addProduct() {
        //TODO: implement API call
    }
}
