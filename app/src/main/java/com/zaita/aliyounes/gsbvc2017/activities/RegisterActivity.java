package com.zaita.aliyounes.gsbvc2017.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout textInput_Code_user;
    private TextInputLayout textInput_FullName;
    private TextInputLayout textInput_TelUsr ;
    private TextInputLayout textInput_AdrUsr;
    private TextInputLayout textView_Password1;
    private TextInputLayout textView_Password2 ;
    private Spinner spinner_branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupViews();
    }
    private void setupViews() {
        textInput_Code_user     = (TextInputLayout) findViewById(R.id.textInput_Code_user);
        textInput_FullName      = (TextInputLayout) findViewById(R.id.textInput_FullName);
        textInput_TelUsr        = (TextInputLayout) findViewById(R.id.textInput_TelUsr);
        textInput_AdrUsr        = (TextInputLayout) findViewById(R.id.textInput_AdrUsr);
        textView_Password1      = (TextInputLayout) findViewById(R.id.textView_Password1);
        textView_Password2      = (TextInputLayout) findViewById(R.id.textView_Password2);
        spinner_branch          = (Spinner) findViewById(R.id.spinner_Branch);

        Button button_register = (Button) findViewById(R.id.button_ajouter);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkFields())
                    Toast.makeText(RegisterActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
                else
                    Register();
            }
        });
    }
    private boolean checkFields() {
        if( textInput_Code_user.getEditText()   != null &&
                textInput_FullName.getEditText()    != null &&
                textInput_TelUsr.getEditText()      != null&&
                textInput_AdrUsr.getEditText()      != null &&
                textView_Password1.getEditText()    != null &&
                textView_Password2.getEditText()    != null) {
            if (textInput_Code_user.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_Code_user.setError("Email Est Obligatoire");
                return false;
            }

            if (textInput_FullName.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_FullName.setError("Full Name Obligatoire");
                return false;
            }

            if (textInput_TelUsr.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_TelUsr.setError("Le numero de telephone est obligatoire");
                return false;
            }

            if (textInput_AdrUsr.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_AdrUsr.setError("L'addesse est obligatoire");
                return false;
            }

            if(spinner_branch.getSelectedItem() == null) {
                Toast.makeText(this, "Selectionner un Branch", Toast.LENGTH_SHORT).show();
            }

            if (textView_Password1.getEditText().getText().toString().equalsIgnoreCase("")) {
                textView_Password1.setError("Password est obligatoire");
                return false;
            }

            if (textView_Password2.getEditText().getText().toString().equalsIgnoreCase("")) {
                textView_Password2.setError("Verify Password est obligatoire");
                return false;
            }




            return true;
        }
        return false;
    }


    private void Register() {
        //TODO: implement API call
    }
}