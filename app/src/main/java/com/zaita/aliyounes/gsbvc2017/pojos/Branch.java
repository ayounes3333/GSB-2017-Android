package com.zaita.aliyounes.gsbvc2017.pojos;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.JsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elie Ohanian on 7/17/2017.
 */

public class Branch {

    private int CodeBr;
    private String NameBr;
    private String TelBr;
    private boolean isRemoving = false;
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

    public boolean isRemoving() {
        return isRemoving;
    }

    public void setRemoving(boolean removing) {
        isRemoving = removing;
    }

    public static class BranchesListParser {
        public static List<Branch> fromJsonArray(JsonArray jsonArray) {
            List<Branch> branches = new ArrayList<>();
            for(JsonElement element : jsonArray) {
                branches.add(BranchParser.fromJsonElement(element));
            }
            return branches;
        }
    }

    public static class BranchParser {
        public static Branch fromJsonElement(JsonElement jsonElement) {
            Branch branch = new Branch();
            if(!JsonHelper.isNull(jsonElement ,"brCode")) {
                branch.setCodeBr(jsonElement.getAsJsonObject().get("brCode").getAsInt());
            }
            if(!JsonHelper.isNull(jsonElement ,"brName")) {
                branch.setNameBr(jsonElement.getAsJsonObject().get("brName").getAsString());
            }
            if(!JsonHelper.isNull(jsonElement ,"brTel")) {
                branch.setTelBr(jsonElement.getAsJsonObject().get("brTel").getAsString());
            }
            if(!JsonHelper.isNull(jsonElement ,"brAddress")) {
                branch.setAddressBr(jsonElement.getAsJsonObject().get("brAddress").getAsString());
            }
            return branch;
        }
    }


}
