package com.zaita.aliyounes.gsbvc2017.network.datamodels;
// Generated Aug 10, 2017 10:15:42 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Role generated by hbm2java
 */
public class Role  implements java.io.Serializable {


     private Integer roCode;
     private String roName;
     private Set userRoles = new HashSet(0);
     private Set roleBranches = new HashSet(0);
     private Set roleActions = new HashSet(0);

    public Role() {
    }

    public Role(String roName, Set userRoles, Set roleBranches, Set roleActions) {
       this.roName = roName;
       this.userRoles = userRoles;
       this.roleBranches = roleBranches;
       this.roleActions = roleActions;
    }
   
    public Integer getRoCode() {
        return this.roCode;
    }
    
    public void setRoCode(Integer roCode) {
        this.roCode = roCode;
    }
    public String getRoName() {
        return this.roName;
    }
    
    public void setRoName(String roName) {
        this.roName = roName;
    }
    public Set getUserRoles() {
        return this.userRoles;
    }
    
    public void setUserRoles(Set userRoles) {
        this.userRoles = userRoles;
    }
    public Set getRoleBranches() {
        return this.roleBranches;
    }
    
    public void setRoleBranches(Set roleBranches) {
        this.roleBranches = roleBranches;
    }
    public Set getRoleActions() {
        return this.roleActions;
    }
    
    public void setRoleActions(Set roleActions) {
        this.roleActions = roleActions;
    }




}


