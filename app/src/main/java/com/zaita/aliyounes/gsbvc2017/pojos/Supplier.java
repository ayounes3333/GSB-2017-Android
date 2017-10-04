package com.zaita.aliyounes.gsbvc2017.pojos;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.JsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elie Ohanian on 7/17/2017.
 */

public class Supplier {
    private int CodeSup;
    private String EmailSup;
    private String TelSup;
    private String NameSup;
    private String MobSup;
    private String AddressSup;
    private String StatusSup;
    private String CountrySup;
    private String CreationDateSup;
    private String CreationUserSup;
    private boolean isRemoving = false;

    //Constructor for dummy suppliers
    public Supplier(String emailSup, String telSup, String nameSup, String mobSup, String addressSup) {
        EmailSup = emailSup;
        TelSup = telSup;
        NameSup = nameSup;
        MobSup = mobSup;
        AddressSup = addressSup;
    }

    public Supplier() {

    }

    public int getCodeSup() {
        return CodeSup;
    }

    public void setCodeSup(int codeSup) {
        CodeSup = codeSup;
    }



    public String getNameSup() {
        return NameSup;
    }

    public void setNameSup(String nameSup) {
        NameSup = nameSup;
    }



    public String getTelSup() {
        return TelSup;
    }

    public void setTelSup(String telSup) {
        TelSup = telSup;
    }



    public String getMobSup() {
        return MobSup;
    }

    public void setMobSup(String mobSup) {
        MobSup = mobSup;
    }



    public String getAddressSup() {
        return AddressSup;
    }

    public void setAddressSup(String addressSup) {
        AddressSup = addressSup;
    }



    public String getEmailSup() {
        return EmailSup;
    }

    public void setEmailSup(String emailSup) {
        EmailSup = emailSup;
    }



    public String getStatusSup() {
        return StatusSup;
    }

    public void setStatusSup(String statusSup) {
        StatusSup = statusSup;
    }



    public String getCountrySup() {
        return CountrySup;
    }

    public void setCountrySup(String countrySup) {
        CountrySup = countrySup;
    }

    public String getCreationDateSup() {
        return CreationDateSup;
    }

    public void setCreationDateSup(String creationDateSup) {
        CreationDateSup = creationDateSup;
    }

    public String getCreationUserSup() {
        return CreationUserSup;
    }

    public void setCreationUserSup(String creationUserSup) {
        CreationUserSup = creationUserSup;
    }

    public boolean isRemoving() {
        return isRemoving;
    }

    public void setRemoving(boolean removing) {
        isRemoving = removing;
    }


    public static class SuppliersListParser {
        public static List<Supplier> fromJsonArray(JsonArray jsonArray) {
            List<Supplier> suppliers= new ArrayList<>();
            for(JsonElement element : jsonArray) {
                suppliers.add(Supplier.SupplierParser.fomJsonElement(element));
            }
            return suppliers;
        }
    }
    public static class SupplierParser {
        public static Supplier fomJsonElement(JsonElement element) {
            Supplier supplier = new Supplier();
            if(!JsonHelper.isNull(element, "supCode")) {
                supplier.setCodeSup(element.getAsJsonObject().get("supCode").getAsInt());
            }
            if(!JsonHelper.isNull(element, "supName")) {
                supplier.setNameSup(element.getAsJsonObject().get("supName").getAsString());
            }
            if(!JsonHelper.isNull(element, "supTel")) {
                supplier.setTelSup(element.getAsJsonObject().get("supTel").getAsString());
            }
            if(!JsonHelper.isNull(element, "supMobile")) {
                supplier.setMobSup(element.getAsJsonObject().get("supMobile").getAsString());
            }
            if(!JsonHelper.isNull(element, "supAddress")) {
                supplier.setAddressSup(element.getAsJsonObject().get("supAddress").getAsString());
            }
            if(!JsonHelper.isNull(element, "supEmail")) {
                supplier.setEmailSup(element.getAsJsonObject().get("supEmail").getAsString());
            }
            return supplier;
        }
    }
}
