package com.zaita.aliyounes.gsbvc2017.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;

public class  ajouter_supplier_brand extends AppCompatActivity {
    private Spinner spinner_sup;
    private Spinner spinner_brand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_supplier_brand);
        setupViews();
    }
    private void setupViews() {
        spinner_sup      = (Spinner) findViewById(R.id.spinner_supcode);
        spinner_brand      = (Spinner) findViewById(R.id.spinner_brand);
        Button button_ajouter = (Button) findViewById(R.id.button_ajouter);
        button_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkFields())
                    Toast.makeText(ajouter_supplier_brand.this, "Erreur", Toast.LENGTH_SHORT).show();
                else
                    addSupBrand();
            }
        });
    }

    private boolean checkFields() {


            if(spinner_sup.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner un titre", Toast.LENGTH_SHORT).show();
            }
            if(spinner_brand.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner un titre", Toast.LENGTH_SHORT).show();
            }
            return true;

    }


    private void addSupBrand() {
        //TODO: implement API call
    }
}

