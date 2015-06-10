package com.nationsky.seccom.uc.service;

import com.nationsky.seccom.uc.model.Admin;
import com.nationsky.seccom.uc.model.AdminLogin;

public interface IAdminLoginService {

	int findAdmin(Admin admin);

	void insertLoginLog(AdminLogin adminLogin);
	
}
