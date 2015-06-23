package com.nationsky.seccom.uc.api;

import com.nqsky.meap.spc.IServiceRequest;
import com.nqsky.meap.spc.IServiceResponse;


public interface IUserCenterAPIService {
	
	

	/**
	 * 登陆接口  
	 */
	public IServiceResponse login(String companyAlias , String userName,String password,IServiceRequest request);
	
	/**
	 * 获取验证码接口 定制
	 */
	public IServiceResponse getCaptcha(String companyAlias ,String userName,String userMobile,String captchaType);
	
	/**
	 * 检测验证码接口 定制
	 */
	public IServiceResponse checkCaptcha(String companyAlias ,String userName,String captcha);
	
	/**
	 * 找回密码接口
	 */
	public IServiceResponse findPassword(String companyAlias ,String userId,String newPassword,String confirmPassword,String captcha);
	
	/**
	 * 修改密码接口
	 */
	public IServiceResponse editPassword(String companyAlias ,String userName,String oldPassword,String newPassword,String confirmPassword);
	
	/**
	 * 按部门编号查询部门接口  
	 */
	public IServiceResponse getDeptByDeptId(String companyAlias ,String userId,String deptId);
	
	/**
	 * 按用户编号查询用户接口 
	 */
	public IServiceResponse getUserByUserId(String companyAlias ,String userId);
	
	/**
	 * 修改用户信息接口
	 */
	public IServiceResponse editUser(String companyAlias, String userId,String nickName,String userMobile,String minUserHead,String maxUserHead,String userEmail,String personSign,String userPhone);
	
	/**
	 * 登出接口 ？待定
	 */
	public IServiceResponse logout(String companyAlias ,String userId,String SSOTicket,IServiceRequest request);
	
	/**
	 * 按关键字查询用户接口 优先级稍弱
	 */
	public IServiceResponse findUserByKeyword(String companyAlias, String userId,String keyword);
	
	/**
	 * 获取用户手机号接口  定制
	 */
	public IServiceResponse getUserMobile(String companyAlias, String userName);
	
	/**
	 * 查询用户常用联系接口 定制
	 */
	public IServiceResponse findCommonContact(String companyAlias, String userId);
	
	/**
	 * 添加常用联系人接口 定制
	 */
	public IServiceResponse addCommonContact(String companyAlias, String userId,IServiceRequest request);
	
	/**
	 * 删除常用联系人接口 定制
	 */
	public IServiceResponse deleteCommonContact(String companyAlias, String userId,IServiceRequest request);
	
	/**
	 * 通过单点登录token获取用户信息接口 ？待定
	 */
	public IServiceResponse checkSSOTicket(String companyAlias, String userId,String SSOTicket);
	
	/**
	 * 通过单点登录token获取用户信息接口 ？待定
	 */
	public IServiceResponse getUserInfoBySSOTicket(String SSOTicket);
}
