package com.zaita.aliyounes.gsbvc2017.network.datamodels;
// Generated Aug 10, 2017 10:15:42 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Product generated by hbm2java
 */
public class Product  implements java.io.Serializable {


     private Integer prCode;
     private String brand;
     private String prCreationDate;
     private String supplier;
     private String prBarCode;
     private String prName;
     private String prType;
     private String prFamily;
     private boolean prStatus;
     private String prSeason;
     private String costPrice;
     private String sellingPrice;
     private Set stockProducts = new HashSet(0);
     private Set branchProducts = new HashSet(0);

    public Product() {
    }

	
    public Product(String brand, String supplier, boolean prStatus) {
        this.brand = brand;
        this.supplier = supplier;
        this.prStatus = prStatus;
    }
    public Product(String brand, String supplier, String prBarCode, String prName, String prType, String prFamily, boolean prStatus, String prSeason, String costPrice, String sellingPrice, Set stockProducts, Set branchProducts) {
       this.brand = brand;
       this.supplier = supplier;
       this.prBarCode = prBarCode;
       this.prName = prName;
       this.prType = prType;
       this.prFamily = prFamily;
       this.prStatus = prStatus;
       this.prSeason = prSeason;
       this.costPrice = costPrice;
       this.sellingPrice = sellingPrice;
       this.stockProducts = stockProducts;
       this.branchProducts = branchProducts;
    }
   
    public Integer getPrCode() {
        return this.prCode;
    }
    
    public void setPrCode(Integer prCode) {
        this.prCode = prCode;
    }
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getSupplier() {
        return this.supplier;
    }
    
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getPrBarCode() {
        return this.prBarCode;
    }
    
    public void setPrBarCode(String prBarCode) {
        this.prBarCode = prBarCode;
    }
    public String getPrName() {
        return this.prName;
    }
    
    public void setPrName(String prName) {
        this.prName = prName;
    }
    public String getPrType() {
        return this.prType;
    }
    
    public void setPrType(String prType) {
        this.prType = prType;
    }
    public String getPrFamily() {
        return this.prFamily;
    }
    
    public void setPrFamily(String prFamily) {
        this.prFamily = prFamily;
    }
    public boolean isPrStatus() {
        return this.prStatus;
    }
    
    public void setPrStatus(boolean prStatus) {
        this.prStatus = prStatus;
    }
    public String getPrSeason() {
        return this.prSeason;
    }
    
    public void setPrSeason(String prSeason) {
        this.prSeason = prSeason;
    }
    public String getCostPrice() {
        return this.costPrice;
    }
    
    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }
    public String getSellingPrice() {
        return this.sellingPrice;
    }
    
    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public Set getStockProducts() {
        return this.stockProducts;
    }
    
    public void setStockProducts(Set stockProducts) {
        this.stockProducts = stockProducts;
    }
    public Set getBranchProducts() {
        return this.branchProducts;
    }
    
    public void setBranchProducts(Set branchProducts) {
        this.branchProducts = branchProducts;
    }


    public String getPrCreationDate() {
        return prCreationDate;
    }

    public void setPrCreationDate(String prCreationDate) {
        this.prCreationDate = prCreationDate;
    }
}


