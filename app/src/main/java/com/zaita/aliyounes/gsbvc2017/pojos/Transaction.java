package com.zaita.aliyounes.gsbvc2017.pojos;

/**
 * Created by Elie Ohanian on 7/17/2017.
 */

public class Transaction {
    private int TransactionId;
    private String TransactionType;
    private String TransactionDate;
    private int TransactionNo;
    private String TransactionDbCr;
    private int CodePr;
    private int CodeBr;
    private int CodeSupBrand;
    private String CreationDateSt;
    private String CreationUserSt;
    public int getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(int transactionId) {
        TransactionId = transactionId;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        TransactionDate = transactionDate;
    }

    public int getTransactionNo() {
        return TransactionNo;
    }

    public void setTransactionNo(int transactionNo) {
        TransactionNo = transactionNo;
    }

    public String getTransactionDbCr() {
        return TransactionDbCr;
    }

    public void setTransactionDbCr(String transactionDbCr) {
        TransactionDbCr = transactionDbCr;
    }

    public int getCodePr() {
        return CodePr;
    }

    public void setCodePr(int codePr) {
        CodePr = codePr;
    }

    public int getCodeBr() {
        return CodeBr;
    }

    public void setCodeBr(int codeBr) {
        CodeBr = codeBr;
    }

    public int getCodeSupBrand() {
        return CodeSupBrand;
    }

    public void setCodeSupBrand(int codeSupBrand) {
        CodeSupBrand = codeSupBrand;
    }

    public String getCreationDateSt() {
        return CreationDateSt;
    }

    public void setCreationDateSt(String creationDateSt) {
        CreationDateSt = creationDateSt;
    }

    public String getCreationUserSt() {
        return CreationUserSt;
    }

    public void setCreationUserSt(String creationUserSt) {
        CreationUserSt = creationUserSt;
    }



}
