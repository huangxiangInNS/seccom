package com.nationsky.seccom.uc.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nationsky.seccom.uc.dao.AdminLoginMapper;
import com.nationsky.seccom.uc.dao.AdminMapper;
import com.nationsky.seccom.uc.model.Admin;
import com.nationsky.seccom.uc.model.AdminCriteria;
import com.nationsky.seccom.uc.model.AdminLogin;
import com.nationsky.seccom.uc.service.IAdminLoginService;

@Service
public class AdminLoginService implements IAdminLoginService {
	
	@Autowired
	private AdminMapper  adminMapper;
	@Autowired
	private AdminLoginMapper adminLoginMapper;
	
	
	public int findAdmin(Admin admin){
		
		AdminCriteria adminCriteria = new AdminCriteria();
		
		adminCriteria.createCriteria().andAdminUsernameEqualTo(admin.getAdminUsername()).andAdminPasswordEqualTo(admin.getAdminPassword());
		
		List<Admin> count = adminMapper.selectByExample(adminCriteria);
		
		
		return count.size();
	}


	@Override
	public void insertLoginLog(AdminLogin adminLogin) {
		
		adminLoginMapper.insert(adminLogin);
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
