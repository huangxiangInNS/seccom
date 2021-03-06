package com.nationsky.seccom.uc.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.nationsky.seccom.uc.domain.*;
import com.nationsky.seccom.uc.util.FatherToChildUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.nationsky.seccom.uc.model.UserBasicInfo;
import com.nationsky.seccom.uc.service.IUserService;
import com.nationsky.seccom.uc.util.Json2Request;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@Controller
@RequestMapping(value = "/v1/user", method = POST)
public class UserController extends BaseController{

	@Autowired
	private IUserService userService;
	@RequestMapping(value = "/addUser", method = POST)
	@ResponseBody
	public Response<UserBasicInfo> addUser(HttpServletRequest httpRequest)
	{

		/*从json中得到请求数据*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<UserRequestData> request =
				gson.fromJson(json, new Json2Request<Request<UserRequestData>>(){}.getType());
		
		/*添加用户*/
		String userId = userService.addUser(request);
		
		/*设置返回数据体*/
		Response<UserBasicInfo> response = new Response<UserBasicInfo>();
		Result result = new Result();
		response.setResult(result);
		result.setRequestId(request.getOperId().getRequestId());
        UserBasicInfo userResponseData = new UserBasicInfo();
		response.setResponseData(userResponseData);
		
		if (userId == null)
		{
			result.setCode("1");
			result.setMessage("添加用户失败！");
		}
		else
		{
			result.setCode("0");
			result.setMessage("添加成功！");
            userResponseData.setUserId(userId);
		}

		return response;
	}
	
	@RequestMapping(value = "/getUser", method = POST)
	@ResponseBody
	public Response<UserResponseData> getUserById(HttpServletRequest httpRequest)
	{

		/*从json中得到请求数据*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<UserRequestData> request =
				gson.fromJson(json, new Json2Request<Request<UserRequestData>>() {
                }.getType());
		
		/*获得用户信息*/
		String userId = request.getRequestData().getUserId();
		UserBasicInfo userBasicInfo = userService.getUserBasicInfoByUserId(userId);

        UserResponseData userResponseData = new UserResponseData();

        FatherToChildUtils.fatherToChild(userBasicInfo, userResponseData);
        userResponseData.setDeptName("Seccom");
        userResponseData.setJobName("开发工程师");
		
		/*设置返回数据体*/
		Response<UserResponseData> response = new Response<UserResponseData>();
		Result result = new Result();
		response.setResult(result);
		result.setRequestId(request.getOperId().getRequestId());

		if (userBasicInfo == null)
		{
			result.setCode("1");
			result.setMessage("获取用户信息失败！");
		}
		else
		{
			result.setCode("0");
			result.setMessage("获取用户信息成功！");
			response.setResponseData(userResponseData);
		}

		return response;
	}
	
	@RequestMapping(value = "/modifyUser", method = POST)
	@ResponseBody
	public Response<UserResponseData> modifyUser(HttpServletRequest httpRequest)
	{
		/*从json中得到请求数据*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<UserRequestData> request =
				gson.fromJson(json, new Json2Request<Request<UserRequestData>>() {
                }.getType());
		
		/*设置返回数据体*/
		Response<UserResponseData> response = new Response<UserResponseData>();
		Result result = new Result();
		response.setResult(result);
		result.setRequestId(request.getOperId().getRequestId());
		
		Date updateTime = userService.updateUserBasicInfo(request);
		if (updateTime == null)
		{
			result.setCode("1");
			result.setMessage("更新用户信息失败！");
		}
		else
		{
			result.setCode("0");
			result.setMessage("更新用户成功！");
			UserResponseData userResponseData = new UserResponseData();
			userResponseData.setUpdateTime(updateTime);
			response.setResponseData(userResponseData);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/deleteUser", method = POST)
	@ResponseBody
	public Response<UserResponseData> deleteUser(HttpServletRequest httpRequest)
	{
		/*从json中得到请求数据*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<UserRequestData> request =
				gson.fromJson(json, new Json2Request<Request<UserRequestData>>() {
                }.getType());
		
		String userId = request.getRequestData().getUserId();
		boolean rmUserResult = userService.deleteUserBasicInfoByUserId(userId);
		
		/*设置返回数据体*/
		Response<UserResponseData> response = new Response<UserResponseData>();
		Result result = new Result();
		response.setResult(result);
		result.setRequestId(request.getOperId().getRequestId());
		
		if (rmUserResult == false)
		{
			result.setCode("1");
			result.setMessage("删除用户失败！");
		}
		else
		{
			result.setCode("0");
			result.setMessage("删除用户成功！");
		}
		
		return response;
	}


	@RequestMapping(value = "/checkLoginInfo", method = POST)
	@ResponseBody
	public Response<LoginInfoResponseData> checkLoginInfo(HttpServletRequest httpRequest)
	{
		/*从json中得到请求数据*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<LoginInfoRequestData> request =
				gson.fromJson(json, new Json2Request<Request<LoginInfoRequestData>>() {
                }.getType());

		LoginInfoRequestData loginInfoRequestData = request.getRequestData();
        String userId = userService.checkLoginInfo(loginInfoRequestData);

		/*设置返回数据体*/
		Response<LoginInfoResponseData> response = new Response<LoginInfoResponseData>();
		Result result = new Result();
		response.setResult(result);
		result.setRequestId(request.getOperId().getRequestId());

		if (userId == null)
		{
			result.setCode("1");
			result.setMessage("用户信息校验失败！");
		}
		else
		{
			result.setCode("0");
			result.setMessage("用户信息校验成功！");
            LoginInfoResponseData loginInfoResponseData = new LoginInfoResponseData();
            loginInfoResponseData.setUserId(userId);
            response.setResponseData(loginInfoResponseData);
		}
		return response;
	}



    @RequestMapping(value = "/createLoginInfo", method = POST)
    @ResponseBody
    public Response<LoginInfoResponseData> createLoginInfo(HttpServletRequest httpRequest)
    {
		/*从json中得到请求数据*/
        String json = getRequestJson(httpRequest);
        System.out.println(json);
        Gson gson = new Gson();
        Request<LoginInfoRequestData> request =
                gson.fromJson(json, new Json2Request<Request<LoginInfoRequestData>>() {
                }.getType());

        /*设置返回数据体*/
        Response<LoginInfoResponseData> response = new Response<LoginInfoResponseData>();
        Result result = new Result();
        response.setResult(result);
        result.setRequestId(request.getOperId().getRequestId());

        LoginInfoRequestData loginInfoRequestData = request.getRequestData();

        try
        {
            String userId = userService.createLoginInfo(loginInfoRequestData);
            result.setCode("0");
            result.setMessage("用户信息创建成功！");
            LoginInfoResponseData loginInfoResponseData = new LoginInfoResponseData();
            loginInfoResponseData.setUserId(userId);
            response.setResponseData(loginInfoResponseData);
        }
        catch (RuntimeException e)
        {
            Logger logger = Logger.getLogger(this.getClass());
            logger.error(e.getMessage());
            result.setCode("1");
            result.setMessage("用户信息创建失败！");
        }

        return response;
    }
}
