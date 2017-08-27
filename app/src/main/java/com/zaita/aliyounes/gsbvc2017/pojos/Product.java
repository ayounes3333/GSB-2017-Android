package com.zaita.aliyounes.gsbvc2017.pojos;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.JsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elie Ohanian on 7/17/2017.
 */

public class Product {

    private int CodePr;
    private String BarCodePr;
    private String NamePr;
    private int TypePr;
    private String FamilyPr;
    private boolean StatusPr;
    private String MadeInPr;
    private int CodeBr;
    private int CostPrice;
    private int SellingPrice;
    private int CodeSupBrand;
    private String SeasonPr;
    private String CreationDatePr;
    private String CreationUserPr;

    //Constructor for dummy products
    public Product(String namePr, int typePr, String familyPr, int costPrice, int sellingPrice, String seasonPr) {
        NamePr = namePr;
        TypePr = typePr;
        FamilyPr = familyPr;
        CostPrice = costPrice;
        SellingPrice = sellingPrice;
        SeasonPr = seasonPr;
    }

    public Product() {

    }


    public int getQtyPr() {
        return QtyPr;
    }

    public void setQtyPr(int qtyPr) {
        QtyPr = qtyPr;
    }

    private int QtyPr;

    public int getCodePr() {
        return CodePr;
    }

    public void setCodePr(int codePr) {
        CodePr = codePr;
    }

    public String getBarCodePr() {
        return BarCodePr;
    }

    public void setBarCodePr(String barCodePr) {
        BarCodePr = barCodePr;
    }

    public String getNamePr() {
        return NamePr;
    }

    public void setNamePr(String namePr) {
        NamePr = namePr;
    }

    public int getTypePr() {
        return TypePr;
    }

    public void setTypePr(int typePr) {
        TypePr = typePr;
    }

    public String getFamilyPr() {
        return FamilyPr;
    }

    public void setFamilyPr(String familyPr) {
        FamilyPr = familyPr;
    }

    public boolean getStatusPr() {
        return StatusPr;
    }

    public void setStatusPr(boolean statusPr) {
        StatusPr = statusPr;
    }

    public String getMadeInPr() {
        return MadeInPr;
    }

    public void setMadeInPr(String madeInPr) {
        MadeInPr = madeInPr;
    }

    public int getCodeBr() {
        return CodeBr;
    }

    public void setCodeBr(int codeBr) {
        CodeBr = codeBr;
    }

    public int getCostPrice() {
        return CostPrice;
    }

    public void setCostPrice(int costPrice) {
        CostPrice = costPrice;
    }

    public int getSellingPrice() {
        return SellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        SellingPrice = sellingPrice;
    }

    public int getCodeSupBrand() {
        return CodeSupBrand;
    }

    public void setCodeSupBrand(int codeSupBrand) {
        CodeSupBrand = codeSupBrand;
    }

    public String getSeasonPr() {
        return SeasonPr;
    }

    public void setSeasonPr(String seasonPr) {
        SeasonPr = seasonPr;
    }

    public String getCreationDatePr() {
        return CreationDatePr;
    }

    public void setCreationDatePr(String creationDatePr) {
        CreationDatePr = creationDatePr;
    }

    public String getCreationUserPr() {
        return CreationUserPr;
    }

    public void setCreationUserPr(String creationUserPr) {
        CreationUserPr = creationUserPr;
    }

    public static class ProductsListParser {
        public static List<Product> fromJsonArray(JsonArray jsonArray) {
            List<Product> products = new ArrayList<>();
            for(JsonElement element : jsonArray) {
                products.add(ProductParser.fromJsonElement(element));
            }
            return products;
        }
    }

    public static class ProductParser {
        public static Product fromJsonElement(JsonElement jsonElement) {
            Product product = new Product();
            if(!JsonHelper.isNull(jsonElement ,"prBarCode")) {
                product.setBarCodePr(jsonElement.getAsJsonObject().get("prBarCode").getAsString());
            }
            if(!JsonHelper.isNull(jsonElement ,"prCode")) {
                product.setCodeBr(jsonElement.getAsJsonObject().get("prCode").getAsInt());
            }
            if(!JsonHelper.isNull(jsonElement ,"prType")) {
                product.setTypePr(jsonElement.getAsJsonObject().get("prType").getAsInt());
            }
            if(!JsonHelper.isNull(jsonElement ,"costPrice")) {
                product.setCostPrice(jsonElement.getAsJsonObject().get("costPrice").getAsInt());
            }
            if(!JsonHelper.isNull(jsonElement ,"sellingPrice")) {
                product.setSellingPrice(jsonElement.getAsJsonObject().get("sellingPrice").getAsInt());
            }
            if(!JsonHelper.isNull(jsonElement ,"prName")) {
                product.setNamePr(jsonElement.getAsJsonObject().get("prName").getAsString());
            }
            if(!JsonHelper.isNull(jsonElement ,"prFamily")) {
                product.setFamilyPr(jsonElement.getAsJsonObject().get("prFamily").getAsString());
            }
            if(!JsonHelper.isNull(jsonElement ,"brand")) {
                Brand brand = Brand.BrandParser.fromJsonElement(jsonElement.getAsJsonObject().get("brand"));
                product.setCodeBr(brand.getCodeBrd());
            }
            if(!JsonHelper.isNull(jsonElement ,"brand")) {
                Supplier supplier = Supplier.SupplierParser.fomJsonElement(jsonElement.getAsJsonObject().get("brand"));
                product.setCodeSupBrand(supplier.getCodeSup());
            }
            if(!JsonHelper.isNull(jsonElement ,"prSeason")) {
                product.setSeasonPr(jsonElement.getAsJsonObject().get("prSeason").getAsString());
            }
            if(!JsonHelper.isNull(jsonElement ,"prStatus")) {
                product.setStatusPr(jsonElement.getAsJsonObject().get("prStatus").getAsBoolean());
            }
            return product;
        }
    }





}
