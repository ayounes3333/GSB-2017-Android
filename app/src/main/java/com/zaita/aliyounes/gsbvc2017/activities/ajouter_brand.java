package com.zaita.aliyounes.gsbvc2017.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;

public class ajouter_brand extends AppCompatActivity {
    private TextInputLayout textInput_nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_brand);
        setupViews();
    }
    private void setupViews() {
        textInput_nom      = (TextInputLayout) findViewById(R.id.textInput_brand_nom);
        Button button_ajouter = (Button) findViewById(R.id.button_ajouter);
        button_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkFields())
                    Toast.makeText(ajouter_brand.this, "Erreur", Toast.LENGTH_SHORT).show();
                else
                    addBrand();
            }
        });
    }
    private boolean checkFields() {
        if( textInput_nom.getEditText()      != null ) {
            if (textInput_nom.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_nom.setError("Le Brand Nom est obligatoire");
                return false;
            }

            return true;
        }
        return false;
    }



    private void addBrand() {
        //TODO: implement API call
    }
}