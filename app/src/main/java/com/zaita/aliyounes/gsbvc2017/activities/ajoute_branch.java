package com.zaita.aliyounes.gsbvc2017.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;

public class ajoute_branch extends AppCompatActivity {
    private TextInputLayout textInput_nom;
    private TextInputLayout textInput_addresse;
    private TextInputLayout textInput_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoute_branch);
        setupViews();
    }
    private void setupViews() {
        textInput_nom      = (TextInputLayout) findViewById(R.id.textInput_branch_nom);
        textInput_addresse = (TextInputLayout) findViewById(R.id.textInput_branch_adr);
        textInput_tel      = (TextInputLayout) findViewById(R.id.textInput_Tel);
        Button button_ajouter = (Button) findViewById(R.id.button_ajouter);
        button_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkFields())
                    Toast.makeText(ajoute_branch.this, "Erreur", Toast.LENGTH_SHORT).show();
                else
                    addBranch();
            }
        });
    }
    private boolean checkFields() {
        if( textInput_nom.getEditText()      != null &&
                textInput_addresse.getEditText() != null &&
                textInput_tel.getEditText()      != null) {
            if (textInput_nom.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_nom.setError("Le Nom Branch est obligatoire");
                return false;
            }

            if (textInput_addresse.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_addresse.setError("L'addesse est obligatoire");
                return false;
            }

            if (textInput_tel.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_tel.setError("Le numero de telephone est obligatoire");
                return false;
            }

            return true;
        }
        return false;
    }



    private void addBranch() {
        //TODO: implement API call
    }
}
