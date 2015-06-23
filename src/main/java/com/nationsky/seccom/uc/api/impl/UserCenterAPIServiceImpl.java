package com.nationsky.seccom.uc.api.impl;

import com.nationsky.seccom.uc.api.IUserCenterAPIService;
import com.nqsky.meap.spc.IServiceRequest;
import com.nqsky.meap.spc.IServiceResponse;

public class UserCenterAPIServiceImpl implements IUserCenterAPIService {

	@Override
	public IServiceResponse login(String companyAlias, String userName,
			String password, IServiceRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse getCaptcha(String companyAlias, String userName,
			String userMobile, String captchaType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse checkCaptcha(String companyAlias, String userName,
			String captcha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse findPassword(String companyAlias, String userId,
			String newPassword, String confirmPassword, String captcha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse editPassword(String companyAlias, String userName,
			String oldPassword, String newPassword, String confirmPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse getDeptByDeptId(String companyAlias, String userId,
			String deptId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse getUserByUserId(String companyAlias, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse editUser(String companyAlias, String userId,
			String nickName, String userMobile, String minUserHead,
			String maxUserHead, String userEmail, String personSign,
			String userPhone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse logout(String companyAlias, String userId,
			String SSOTicket, IServiceRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse findUserByKeyword(String companyAlias,
			String userId, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse getUserMobile(String companyAlias, String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse findCommonContact(String companyAlias, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse addCommonContact(String companyAlias,
			String userId, IServiceRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse deleteCommonContact(String companyAlias,
			String userId, IServiceRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse checkSSOTicket(String companyAlias, String userId,
			String SSOTicket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceResponse getUserInfoBySSOTicket(String SSOTicket) {
		// TODO Auto-generated method stub
		return null;
	}

}
