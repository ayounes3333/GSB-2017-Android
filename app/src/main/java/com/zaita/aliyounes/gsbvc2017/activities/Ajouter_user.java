package com.zaita.aliyounes.gsbvc2017.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;

public class Ajouter_user extends AppCompatActivity {
    private TextInputLayout textInput_Code_user;
    private TextInputLayout textInput_FullName;
    private TextInputLayout textInput_TelUsr ;
    private TextInputLayout textInput_AdrUsr;
    private TextInputLayout textView_Password1;
    private TextInputLayout textView_Password2 ;
    private Spinner spinner_branch;
    private String email;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_user);
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

        Button button_ajouter = (Button) findViewById(R.id.button_ajouter);

        button_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkFields())
                    Toast.makeText(Ajouter_user.this, "Erreur", Toast.LENGTH_SHORT).show();
                else
                    addUser();
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

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    boolean cancel = false;
    View focusView = null;

    private boolean isPasswordValid1(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
    // Check for a valid password, if the user entered one.
        //String password = mPasswordView.getText().toString();
        //if (!isPasswordValid1((EditText)findViewById(R.id.textView_Password1)) {
        //mPasswordView.setError(getString(R.string.error_invalid_password));
        //focusView = mPasswordView;
        //cancel = true;
    //}
    private void addUser() {
        //TODO: implement API call
    }
}
