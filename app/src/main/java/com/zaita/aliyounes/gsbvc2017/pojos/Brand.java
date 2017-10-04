package com.zaita.aliyounes.gsbvc2017.pojos;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.JsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elie Ohanian on 7/17/2017.
 */

public class Brand {
    private int CodeBrd;
    private String NameBrd;
    private boolean StatusBrd;
    private String CreationDateBrd;
    private String CreationUserBrd;
    private boolean isRemoving = false;

    public Brand(String name) { //Constructor for dummy brands
        this.NameBrd = name;
    }

    public Brand() {

    }

    public int getCodeBrd() {
        return CodeBrd;
    }

    public void setCodeBrd(int codeBrd) {
        CodeBrd = codeBrd;
    }

    public String getNameBrd() {
        return NameBrd;
    }

    public void setNameBrd(String nameBrd) {
        NameBrd = nameBrd;
    }

    public boolean getStatusBrd() {
        return StatusBrd;
    }

    public void setStatusBrd(boolean statusBrd) {
        StatusBrd = statusBrd;
    }

    public String getCreationDateBrd() {
        return CreationDateBrd;
    }

    public void setCreationDateBrd(String creationDateBrd) {
        CreationDateBrd = creationDateBrd;
    }

    public String getCreationUserBrd() {
        return CreationUserBrd;
    }

    public void setCreationUserBrd(String creationUserBrd) {
        CreationUserBrd = creationUserBrd;
    }

    public boolean isRemoving() {
        return isRemoving;
    }

    public void setRemoving(boolean removing) {
        isRemoving = removing;
    }

    public static class BrandsListParser {
        public static List<Brand> fromJsonArray(JsonArray jsonArray) {
            List<Brand> brands = new ArrayList<>();
            for(JsonElement element : jsonArray) {
                brands.add(BrandParser.fromJsonElement(element));
            }
            return brands;
        }
    }

    public static class BrandParser {
        public static Brand fromJsonElement(JsonElement jsonElement) {
            Brand brand = new Brand();
            if(!JsonHelper.isNull(jsonElement ,"brdCode")) {
                brand.setCodeBrd(jsonElement.getAsJsonObject().get("brdCode").getAsInt());
            }
            if(!JsonHelper.isNull(jsonElement ,"brdName")) {
                brand.setNameBrd(jsonElement.getAsJsonObject().get("brdName").getAsString());
            }
            if(!JsonHelper.isNull(jsonElement ,"brdStatus")) {
                brand.setStatusBrd(jsonElement.getAsJsonObject().get("brdStatus").getAsBoolean());
            }
            return brand;
        }
    }

}
