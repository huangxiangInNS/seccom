package com.nationsky.seccom.uc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.nationsky.seccom.uc.domain.Request;
import com.nationsky.seccom.uc.domain.Response;
import com.nationsky.seccom.uc.domain.Result;
import com.nationsky.seccom.uc.model.Admin;
import com.nationsky.seccom.uc.model.AdminLogin;
import com.nationsky.seccom.uc.service.IAdminLoginService;
import com.nationsky.seccom.uc.util.Json2Request;
import com.nationsky.seccom.uc.util.ServiceUtil;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@Controller
@RequestMapping(value = "/v1/", method = POST)
public class AdminLoginController extends BaseController{

	@Autowired
	private IAdminLoginService iAdminLoginService;
	
	
	@ResponseBody
	@RequestMapping(value = "/adminLogin", method = POST)
	public Response<Object> addUser(HttpServletRequest httpRequest)
	{

		/*从json中得到请求数据*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		
		Gson gson = new Gson();
		Request<Admin> request =
				gson.fromJson(json, new Json2Request<Request<Admin>>(){}.getType());
		
		Admin admin = request.getRequestData();
		
		int findAdmin = iAdminLoginService.findAdmin(admin);
		
		
		/*设置返回数据体*/
		Response<Object> response = new Response<Object>();
		
		Result result = new Result();
		
		response.setResult(result);
		
		response.setResponseData(null);
		
		if(findAdmin>0){
			//登陆成功
			
			//记录Session
			HttpSession session = httpRequest.getSession();
			session.setAttribute("admin", admin);

			//记录登陆成功日志
			//TODO
			AdminLogin adminLogin = new AdminLogin();
			String loginIp = httpRequest.getLocalAddr();
			
			String id = ServiceUtil.getRandomString(12);
			adminLogin.setAdminId(id);
			adminLogin.setLoginArea(null);
			adminLogin.setLoginIp(loginIp);
			iAdminLoginService.insertLoginLog(adminLogin);
			
			
			
			result.setCode("0");
			result.setMessage("login success ！");
			
			return response;
			
		}else{
			//登陆失败
			
			result.setCode("1");
			result.setMessage("login fail ！");
			
			return response;
		}
	}
	
	@RequestMapping(value = "/adminLoginSuccess", method =POST)
	public ModelAndView adminLoginSuccess(HttpServletRequest httpRequest)
	{
		
		
		
		return new ModelAndView("WEB-INF/jsp/index");
	}
	

}
