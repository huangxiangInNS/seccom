package com.nationsky.seccom.uc.domain;

/**
 * Created by huangxiang on 15-6-8.
 */
public class LoginInfoRequestData {
    private String companyAlias;
    private String loginName;
    private String password;
    private String userId;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyAlias() {
        return companyAlias;
    }

    public void setCompanyAlias(String companyAlias) {
        this.companyAlias = companyAlias;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
