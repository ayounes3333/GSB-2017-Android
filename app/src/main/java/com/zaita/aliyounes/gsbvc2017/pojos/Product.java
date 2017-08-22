package com.zaita.aliyounes.gsbvc2017.pojos;

/**
 * Created by Elie Ohanian on 7/17/2017.
 */

public class Product {

    private int CodePr;
    private String BarCodePr;
    private String NamePr;
    private String TypePr;
    private String FamilyPr;
    private String StatusPr;
    private String MadeInPr;
    private int CodeBr;
    private String CostPrice;
    private String SellingPrice;
    private int CodeSupBrand;
    private String SeasonPr;
    private String CreationDatePr;
    private String CreationUserPr;

    //Constructor for dummy products
    public Product(String namePr, String typePr, String familyPr, String costPrice, String sellingPrice, String seasonPr) {
        NamePr = namePr;
        TypePr = typePr;
        FamilyPr = familyPr;
        CostPrice = costPrice;
        SellingPrice = sellingPrice;
        SeasonPr = seasonPr;
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

    public String getTypePr() {
        return TypePr;
    }

    public void setTypePr(String typePr) {
        TypePr = typePr;
    }

    public String getFamilyPr() {
        return FamilyPr;
    }

    public void setFamilyPr(String familyPr) {
        FamilyPr = familyPr;
    }

    public String getStatusPr() {
        return StatusPr;
    }

    public void setStatusPr(String statusPr) {
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

    public String getCostPrice() {
        return CostPrice;
    }

    public void setCostPrice(String costPrice) {
        CostPrice = costPrice;
    }

    public String getSellingPrice() {
        return SellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
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







}
