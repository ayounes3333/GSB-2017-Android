package com.zaita.aliyounes.gsbvc2017.pojos;

/**
 * Created by Elie Ohanian on 7/17/2017.
 */

public class Branch {

    private int CodeBr;
    private String NameBr;
    private String TelBr;
    private String AddressBr;

    public Branch() {

    }

    //For dummy branches
    public Branch(String nameBr, String telBr, String addressBr) {
        NameBr = nameBr;
        TelBr = telBr;
        AddressBr = addressBr;
    }

    public int getCodeBr() {
        return CodeBr;
    }

    public void setCodeBr(int codeBr) {
        CodeBr = codeBr;
    }



    public String getNameBr() {
        return NameBr;
    }

    public void setNameBr(String nameBr) {
        NameBr = nameBr;
    }



    public String getTelBr() {
        return TelBr;
    }

    public void setTelBr(String telBr) {
        TelBr = telBr;
    }


    public String getAddressBr() {
        return AddressBr;
    }

    public void setAddressBr(String addressBr) {
        AddressBr = addressBr;
    }




}
