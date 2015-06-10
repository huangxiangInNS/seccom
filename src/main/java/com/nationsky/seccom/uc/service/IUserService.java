package com.nationsky.seccom.uc.service;

import java.util.Date;

import com.nationsky.seccom.uc.domain.LoginInfoRequestData;
import com.nationsky.seccom.uc.domain.Request;
import com.nationsky.seccom.uc.domain.UserRequestData;
import com.nationsky.seccom.uc.model.UserBasicInfo;

public interface IUserService {
	String addUser(Request<UserRequestData> request);
	boolean deleteUserBasicInfoByUserId(String userId);
	Date updateUserBasicInfo(Request<UserRequestData> request);
	UserBasicInfo getUserBasicInfoByUserId(String userId);
	String createLoginInfo(LoginInfoRequestData loginInfoRequestData);
	String checkLoginInfo(LoginInfoRequestData loginInfoRequestData);
	String getSign(String userId);
    boolean setSign(String userId, String sign);
}
