package com.zaita.aliyounes.gsbvc2017.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.network.apis.BrandsNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.Brand;


import java.io.IOException;
import java.net.SocketException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AjouteBbrandActivity extends AppCompatActivity {
    private TextInputLayout textInput_nom;
    private CompositeDisposable compositeDisposable;

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
                    Toast.makeText(AjouteBbrandActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
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
        final Dialog pd = new Dialog(this);
        pd.setCancelable(false);
        pd.setContentView(R.layout.adding_loading_popup);
        pd.show();
        Brand brand= new Brand();
        if( textInput_nom.getEditText() != null ) {
            brand.setBrdName(textInput_nom.getEditText().getText().toString());


            //Call the API
            BrandsNetworkCalls.addBrand(brand).subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    compositeDisposable.add(d);
                }
                //Called when the request succeed
                @Override
                public void onNext(Boolean value) {
                    //Value is the return of the API call
                    //In this case it is the brand ID
                    //For more info see Mohammad faour's code (ManagedObjects/ClientController.java)
                    Log.i("Add Brand" , "Brand added successfully");
                    pd.dismiss();
                    Toast.makeText(AjouteBbrandActivity.this , "Client added successfully" , Toast.LENGTH_SHORT).show();
                    AjouteBbrandActivity.this.finish();
                }

                //Called if the request fail
                @Override
                public void onError(Throwable e) {
                    Log.e("Add Brand" , "Error adding new Brand" , e);
                    pd.dismiss();
                    if(e instanceof SocketException || e instanceof IOException) {
                        Toast.makeText(AjouteBbrandActivity.this , R.string.no_internet , Toast.LENGTH_SHORT).show();
                    } else if (e instanceof Exception) {
                        Toast.makeText(AjouteBbrandActivity.this , e.getMessage() , Toast.LENGTH_LONG).show();
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
        super.onDestroy();
        compositeDisposable.dispose();
    }

    @Override
    public void onPause() {
        super.onPause();
        compositeDisposable.dispose();
    }

    @Override
    public void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();
    }
}
