package com.zaita.aliyounes.gsbvc2017.network.datamodels;
// Generated Aug 10, 2017 10:15:42 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer usrCode;
     private String usrFullname;
     private String usrUsername;
     private String usrPassword;
     private String usrMobile;
     private String usrAddress;
     private Set userRoles = new HashSet(0);
     private Set orders = new HashSet(0);

    public User() {
    }

	
    public User(String usrUsername, String usrPassword) {
        this.usrUsername = usrUsername;
        this.usrPassword = usrPassword;
    }
    public User(String usrFullname, String usrUsername, String usrPassword, String usrMobile, String usrAddress, Set userRoles, Set orders) {
       this.usrFullname = usrFullname;
       this.usrUsername = usrUsername;
       this.usrPassword = usrPassword;
       this.usrMobile = usrMobile;
       this.usrAddress = usrAddress;
       this.userRoles = userRoles;
       this.orders = orders;
    }
   
    public Integer getUsrCode() {
        return this.usrCode;
    }
    
    public void setUsrCode(Integer usrCode) {
        this.usrCode = usrCode;
    }
    public String getUsrFullname() {
        return this.usrFullname;
    }
    
    public void setUsrFullname(String usrFullname) {
        this.usrFullname = usrFullname;
    }
    public String getUsrUsername() {
        return this.usrUsername;
    }
    
    public void setUsrUsername(String usrUsername) {
        this.usrUsername = usrUsername;
    }
    public String getUsrPassword() {
        return this.usrPassword;
    }
    
    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }
    public String getUsrMobile() {
        return this.usrMobile;
    }
    
    public void setUsrMobile(String usrMobile) {
        this.usrMobile = usrMobile;
    }
    public String getUsrAddress() {
        return this.usrAddress;
    }
    
    public void setUsrAddress(String usrAddress) {
        this.usrAddress = usrAddress;
    }
    public Set getUserRoles() {
        return this.userRoles;
    }
    
    public void setUserRoles(Set userRoles) {
        this.userRoles = userRoles;
    }
    public Set getOrders() {
        return this.orders;
    }
    
    public void setOrders(Set orders) {
        this.orders = orders;
    }




}

