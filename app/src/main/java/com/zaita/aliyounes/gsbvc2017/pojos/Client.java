package com.zaita.aliyounes.gsbvc2017.pojos;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.JsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elie Ohanian on 7/17/2017.
 */

public class Client {
    private int ID;
    private String NameClt;
    private String TelClt;
    private String MobClt;
    private String TitleClt;
    private boolean CatClt;
    private String AddressClt;
    private String EmailClt;
    private String StatusClt;
    private boolean SendSms;
    private boolean isRemoving = false;
    private String CreationDateClt;
    private String CreationUserClt;

    public Client(String nameClt, String telClt, String mobClt, String titleClt, String addressClt, String emailClt) {
        NameClt = nameClt;
        TelClt = telClt;
        MobClt = mobClt;
        TitleClt = titleClt;
        AddressClt = addressClt;
        EmailClt = emailClt;
    }

    public Client() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameClt() {
        return NameClt;
    }

    public void setNameClt(String nameClt) {
        NameClt = nameClt;
    }

    public String getTelClt() {
        return TelClt;
    }

    public void setTelClt(String telClt) {
        TelClt = telClt;
    }

    public String getMobClt() {
        return MobClt;
    }

    public void setMobClt(String mobClt) {
        MobClt = mobClt;
    }

    public String getTitleClt() {
        return TitleClt;
    }

    public void setTitleClt(String titleClt) {
        TitleClt = titleClt;
    }

    public boolean getCatClt() {
        return CatClt;
    }

    public void setCatClt(boolean catClt) {
        CatClt = catClt;
    }

    public String getAddressClt() {
        return AddressClt;
    }

    public void setAddressClt(String addressClt) {
        AddressClt = addressClt;
    }

    public String getEmailClt() {
        return EmailClt;
    }

    public void setEmailClt(String emailClt) {
        EmailClt = emailClt;
    }

    public String getStatusClt() {
        return StatusClt;
    }

    public void setStatusClt(String statusClt) {
        StatusClt = statusClt;
    }

    public boolean getSendSms() {
        return SendSms;
    }

    public void setSendSms(boolean sendSms) {
        SendSms = sendSms;
    }

    public String getCreationDateClt() {
        return CreationDateClt;
    }

    public void setCreationDateClt(String creationDateClt) {
        CreationDateClt = creationDateClt;
    }

    public String getCreationUserClt() {
        return CreationUserClt;
    }

    public void setCreationUserClt(String creationUserClt) {
        CreationUserClt = creationUserClt;
    }

    public boolean isRemoving() {
        return isRemoving;
    }

    public void setRemoving(boolean removing) {
        isRemoving = removing;
    }

    public static class ClientsListParser {
        public static List<Client> fromJsonArray(JsonArray jsonArray) {
            List<Client> clients = new ArrayList<>();
            for(JsonElement element : jsonArray) {
                clients.add(ClientParser.fromJsonElement(element));
            }
            return clients;
        }
    }

    public static class ClientParser {
        public static Client fromJsonElement(JsonElement jsonElement) {
            Client client = new Client();
            if(!JsonHelper.isNull(jsonElement ,"cltTitle")) {
                client.setTitleClt(jsonElement.getAsJsonObject().get("cltTitle").getAsString());
            }
            if(!JsonHelper.isNull(jsonElement ,"cltCode")) {
                client.setID(jsonElement.getAsJsonObject().get("cltCode").getAsInt());
            }
            if(!JsonHelper.isNull(jsonElement ,"cltName")) {
                client.setNameClt(jsonElement.getAsJsonObject().get("cltName").getAsString());
            }
            if(!JsonHelper.isNull(jsonElement ,"cltEmail")) {
                client.setEmailClt(jsonElement.getAsJsonObject().get("cltEmail").getAsString());
            }
            if(!JsonHelper.isNull(jsonElement ,"sendMessage")) {
                client.setSendSms(jsonElement.getAsJsonObject().get("sendMessage").getAsBoolean());
            }
            if(!JsonHelper.isNull(jsonElement ,"cltMobile")) {
                client.setMobClt(jsonElement.getAsJsonObject().get("cltMobile").getAsString());
            }
            if(!JsonHelper.isNull(jsonElement ,"sendCatalog")) {
                client.setCatClt(jsonElement.getAsJsonObject().get("sendCatalog").getAsBoolean());
            }
            return client;
        }
    }
}
