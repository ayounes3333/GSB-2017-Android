package com.zaita.aliyounes.gsbvc2017.network;

import android.net.Uri;
import android.util.Log;

/**
 * Created by Lenovo on 8/18/2017.
 */

public class UrlManager {

    //TODO: Fill in the details for the server URLs
    public static final String BASE_URL = "http://35.194.131.184:8090/";
    public static final String BASE_URL_API = BASE_URL + "StockManagement.Services/webresources/";
    public static final String BASE_URL_UPLOADS = "";

    public static String getAllClientsURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Client")
                //.appendPath("all")
                .build();
        Log.i("Get All Clients URL" , uri.toString());
        return uri.toString();
    }
    public static String addClientURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Client")
                .appendPath("add")
                .build();
        Log.i("Add Client URL" , uri.toString());
        return uri.toString();
    }
    public static String getAllBranchesURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Branch")
                //.appendPath("all")
                .build();
        Log.i("Get All Branches URL" , uri.toString());
        return uri.toString();
    }
    public static String addBranchURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Branch")
                .appendPath("add")
                .build();
        Log.i("Add Branch URL" , uri.toString());
        return uri.toString();
    }
    public static String getAllBrandsURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Brand")
                //.appendPath("all")
                .build();
        Log.i("Get All Brands URL" , uri.toString());
        return uri.toString();
    }
    public static String addBrandURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Brand")
                .appendPath("add")
                .build();
        Log.i("Add Brand URL" , uri.toString());
        return uri.toString();
    }
    public static String deleteBrandURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Brand")
                .appendPath("delete")
                .build();
        Log.i("Delete Brand URL" , uri.toString());
        return uri.toString();
    }

    public static String deleteBranchURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Branch")
                .appendPath("delete")
                .build();
        Log.i("Delete Branch URL" , uri.toString());
        return uri.toString();
    }

    public static String deleteClientURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Client")
                .appendPath("delete")
                .build();
        Log.i("Delete Client URL" , uri.toString());
        return uri.toString();
    }

    public static String deleteProductURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Product")
                .appendPath("delete")
                .build();
        Log.i("Delete Product URL" , uri.toString());
        return uri.toString();
    }

    public static String deleteSupplierURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Supplier")
                .appendPath("delete")
                .build();
        Log.i("Delete Supplier URL" , uri.toString());
        return uri.toString();
    }

    public static String getAllProductsURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Product")
                //.appendPath("all")
                .build();
        Log.i("Get All Products URL" , uri.toString());
        return uri.toString();
    }
    public static String addProductURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Product")
                .appendPath("add")
                .build();
        Log.i("Add Product URL" , uri.toString());
        return uri.toString();
    }
    public static String getAllSuppliersURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Supplier")
                //.appendPath("all")
                .build();
        Log.i("Get All Suppliers URL" , uri.toString());
        return uri.toString();
    }
    public static String addSupplierURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Supplier")
                .appendPath("add")
                .build();
        Log.i("Add Supplier URL" , uri.toString());
        return uri.toString();
    }

    public static String loginURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("User")
                .appendPath("login")
                .build();
        Log.i("login URL" , uri.toString());
        return uri.toString();
    }

    public static String getAllTransactionsURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Order")
                //.appendPath("all")
                .build();
        Log.i("Get All Transaction URL" , uri.toString());
        return uri.toString();
    }
    public static String addTransationURL() {
        Uri uri = Uri.parse(BASE_URL_API)
                .buildUpon()
                .appendPath("Order")
                .appendPath("add")
                .build();
        Log.i("Add Transaction URL" , uri.toString());
        return uri.toString();
    }

}
