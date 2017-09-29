package com.zaita.aliyounes.gsbvc2017.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.network.apis.BranchesNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.Branch;


import java.io.IOException;
import java.net.SocketException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AjouteBranchActivity extends AppCompatActivity {
    private TextInputLayout textInput_nom;
    private TextInputLayout textInput_addresse;
    private TextInputLayout textInput_tel ;
    private CompositeDisposable compositeDisposable;


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
                    Toast.makeText(AjouteBranchActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
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
       Branch branch = new Branch();
        if( textInput_nom.getEditText()          != null &&
                textInput_tel.getEditText()      != null &&
                textInput_addresse.getEditText() != null) {
            branch.setBrName(textInput_nom.getEditText().getText().toString());
            branch.setBrTel(textInput_tel.getEditText().getText().toString());
            branch.setBrAddress(textInput_addresse.getEditText().getText().toString());


            //Call the API
            BranchesNetworkCalls.addBranch(branch).subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    compositeDisposable.add(d);
                }
                //Called when the request succeed
                @Override
                public void onNext(Boolean value) {
                    //Value is the return of the API call
                    //In this case it is the Branch ID
                    //For more info see Mohammad faour's code (ManagedObjects/ClientController.java)
                    Log.i("Add Branch" , "Branch added successfully");
                    Toast.makeText(AjouteBranchActivity.this , "Branch added successfully" , Toast.LENGTH_SHORT).show();
                    AjouteBranchActivity.this.finish();
                }

                //Called if the request fail
                @Override
                public void onError(Throwable e) {
                    Log.e("Add Branch" , "Error adding new Branch" , e);
                    if(e instanceof SocketException || e instanceof IOException) {
                        Toast.makeText(AjouteBranchActivity.this , R.string.no_internet , Toast.LENGTH_SHORT).show();
                    } else if (e instanceof Exception) {
                        Toast.makeText(AjouteBranchActivity.this , e.getMessage() , Toast.LENGTH_LONG).show();
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
