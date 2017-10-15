package com.zaita.aliyounes.gsbvc2017.activities;

import android.app.Dialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.network.apis.SuppliersNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.Supplier;


import java.io.IOException;
import java.net.SocketException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AjouteSupplierActivity extends AppCompatActivity {
    private TextInputLayout textInput_nom;
    private TextInputLayout textInput_email;
    private TextInputLayout textInput_addresse;
    private TextInputLayout textInput_mobile;
    private TextInputLayout textInput_tel;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_supplier);
        compositeDisposable  = new CompositeDisposable();
        setupViews();
    }
    private void setupViews() {
        textInput_nom      = (TextInputLayout) findViewById(R.id.textInput_supname);
        textInput_email    = (TextInputLayout) findViewById(R.id.textInput_email);
        textInput_addresse = (TextInputLayout) findViewById(R.id.textInput_SupAdresse);
        textInput_mobile   = (TextInputLayout) findViewById(R.id.textInput_mobile);
        textInput_tel      = (TextInputLayout) findViewById(R.id.textInput_telephone);
        Button button_ajouter = (Button) findViewById(R.id.button_ajouter);
        button_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkFields())
                    Toast.makeText(AjouteSupplierActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
                else
                    addSupplier();
            }
        });
}
    private boolean checkFields() {
        if( textInput_nom.getEditText()      != null &&
                textInput_email.getEditText()    != null &&
                textInput_addresse.getEditText() != null &&
                textInput_mobile.getEditText()   != null &&
                textInput_tel.getEditText()      != null) {
            if (textInput_nom.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_nom.setError("Le nom est obligatoire");
                return false;
            }
            if (!isValidEmailAddress(textInput_email.getEditText().getText().toString())) {
                textInput_email.setError("Le format de l'email est invalid");
                return false;
            }
            if (textInput_addresse.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_addresse.setError("L'addesse est obligatoire");
                return false;
            }
            if (textInput_mobile.getEditText().getText().toString().equalsIgnoreCase("")) {
                textInput_mobile.setError("Le numero de mobile est obligatoire");
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

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private void addSupplier() {
        final Dialog pd = new Dialog(this);
        pd.setCancelable(false);
        pd.setContentView(R.layout.adding_loading_popup);
        pd.show();
        Supplier supplier= new Supplier();
        if( textInput_nom.getEditText()          != null &&
                textInput_email.getEditText()    != null &&
                textInput_addresse.getEditText() != null &&
                textInput_mobile.getEditText()   != null &&
                textInput_tel.getEditText()      != null) {
            supplier.setSupName(textInput_nom.getEditText().getText().toString());
            supplier.setSupEmail(textInput_email.getEditText().getText().toString());
            supplier.setSupMobile(textInput_mobile.getEditText().getText().toString());
            supplier.setSupTel(textInput_tel.getEditText().getText().toString());
            supplier.setSupAddress(textInput_addresse.getEditText().getText().toString());


            //Call the API
            SuppliersNetworkCalls.addSupplier(supplier).subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    compositeDisposable.add(d);
                }
                //Called when the request succeed
                @Override
                public void onNext(Boolean value) {
                    //Value is the return of the API call
                    //In this case it is the Supplier ID
                    //For more info see Mohammad faour's code (ManagedObjects/SuppliersController.java)
                    Log.i("Add Supplier" , "Supplier added successfully");
                    Toast.makeText(AjouteSupplierActivity.this , "Supplier "+value+" added successfully" , Toast.LENGTH_SHORT).show();
                    AjouteSupplierActivity.this.finish();
                }

                //Called if the request fail
                @Override
                public void onError(Throwable e) {
                    Log.e("Add Supplier" , "Error adding new Supplier" , e);
                    if(e instanceof SocketException || e instanceof IOException) {
                        Toast.makeText(AjouteSupplierActivity.this , R.string.no_internet , Toast.LENGTH_SHORT).show();
                    } else if (e instanceof Exception) {
                        Toast.makeText(AjouteSupplierActivity.this , e.getMessage() , Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}