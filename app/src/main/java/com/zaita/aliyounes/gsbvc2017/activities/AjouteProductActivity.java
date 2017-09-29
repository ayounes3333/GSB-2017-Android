package com.zaita.aliyounes.gsbvc2017.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.network.apis.PoductsNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.Product;

import java.io.IOException;
import java.net.SocketException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;



public class AjouteProductActivity extends AppCompatActivity {
    private TextInputLayout textInput_nom;
    private TextInputLayout textInput_cost;
    private TextInputLayout textInput_sel;
    private TextInputLayout textInput_barcode;



    private Spinner spinner_prd_type;
    private Spinner spinner_prd_fam;
    private Spinner spinner_prd_season;

    private Spinner spinner_supplier;
    private Spinner spinner_brand;

    private CompositeDisposable compositeDisposable;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_product);
        setupViews();
    }
    private void setupViews() {
        textInput_nom         = (TextInputLayout) findViewById(R.id.textInput_nom);
        textInput_cost        = (TextInputLayout) findViewById(R.id.textInput_cost);
        textInput_sel         = (TextInputLayout) findViewById(R.id.textInput_sel);
        textInput_barcode     = (TextInputLayout) findViewById(R.id.textInput_barcode);
        spinner_prd_type      = (Spinner) findViewById(R.id.spinner_prd_type);
        spinner_prd_fam       = (Spinner) findViewById(R.id.spinner_prd_fam);
        spinner_prd_season    = (Spinner) findViewById(R.id.spinner_prd_season);

        spinner_supplier      = (Spinner) findViewById(R.id.spinner_Sup);
        spinner_brand         = (Spinner) findViewById(R.id.spinner_Brand);


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
        Product product = new Product();
        if( textInput_nom.getEditText()          != null &&
                textInput_cost.getEditText()     != null &&
                textInput_sel.getEditText()      != null ) {

            product.setCostPrice(textInput_cost.getEditText().getText().toString());
            product.setSellingPrice(textInput_sel.getEditText().getText().toString());
            product.setPrType(spinner_prd_type.getSelectedItem().toString());
            product.setPrFamily(spinner_prd_fam.getSelectedItem().toString());
            product.setPrSeason(spinner_prd_season.getSelectedItem().toString());
            product.setSupplier(spinner_supplier.getSelectedItem().toString());
            product.setBrand(spinner_brand.getSelectedItem().toString());
            product.setPrBarCode(textInput_barcode.getEditText().getText().toString());



            //Call the API
            PoductsNetworkCalls.addProduct(product).subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    compositeDisposable.add(d);
                }
                //Called when the request succeed
                @Override
                public void onNext(Boolean value) {
                    //Value is the return of the API call
                    //In this case it is the client ID
                    //For more info see Mohammad faour's code (ManagedObjects/ClientController.java)
                    Log.i("Add Product" , "Product "+value+" added successfully");
                    Toast.makeText(AjouteProductActivity.this , "Product "+value+" added successfully" , Toast.LENGTH_SHORT).show();
                    AjouteProductActivity.this.finish();
                }

                //Called if the request fail
                @Override
                public void onError(Throwable e) {
                    Log.e("Add Product" , "Error adding new Product" , e);
                    if(e instanceof SocketException || e instanceof IOException) {
                        Toast.makeText(AjouteProductActivity.this , R.string.no_internet , Toast.LENGTH_SHORT).show();
                    } else if (e instanceof Exception) {
                        Toast.makeText(AjouteProductActivity.this , e.getMessage() , Toast.LENGTH_LONG).show();
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
