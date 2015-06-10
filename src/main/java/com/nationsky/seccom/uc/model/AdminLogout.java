package com.nationsky.seccom.uc.model;

import java.util.Date;

public class AdminLogout {
    private String adminId;

    private Date logoutTime;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }
}