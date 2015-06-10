package com.nationsky.seccom.uc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.nationsky.seccom.uc.domain.DeptRequestData;
import com.nationsky.seccom.uc.domain.DeptResponseData;
import com.nationsky.seccom.uc.domain.Request;
import com.nationsky.seccom.uc.domain.Response;
import com.nationsky.seccom.uc.domain.Result;
import com.nationsky.seccom.uc.model.DeptBasicInfo;
import com.nationsky.seccom.uc.model.UserBasicInfo;
import com.nationsky.seccom.uc.service.IDeptService;
import com.nationsky.seccom.uc.service.IUserService;
import com.nationsky.seccom.uc.util.Json2Request;

@Controller
@RequestMapping(value = "/v1/dept", method = POST)
public class DeptmentController extends BaseController
{
	@Autowired
	private IDeptService deptService;
	
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/addDept", method = POST)
	@ResponseBody
	public Response<DeptResponseData> addDept(HttpServletRequest httpRequest)
	{
		/*将请求json数据转换成相应的请求体*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<DeptRequestData> request = gson.fromJson(json, new Json2Request<Request<DeptRequestData>>(){}.getType());
		DeptRequestData requestData = request.getRequestData();
		
		/*生成响应体*/
		Response<DeptResponseData> response = new Response<DeptResponseData>();
		Result result = new Result();
		response.setResult(result);
		DeptResponseData deptResponseData = new DeptResponseData();
		response.setResponseData(deptResponseData);
		
		/*添加部门，返回部门的id*/
		String deptId = deptService.addDept(requestData);
		
		if (deptId == null)
		{
			result.setCode("1");
			result.setMessage("创建部门失败！");
		}
		else
		{
			result.setCode("0");
			result.setMessage("创建部门成功！");
			deptResponseData.setDeptId(deptId);
		}

		return response;
	}
	
	@RequestMapping(value = "/getDept", method = POST)
	@ResponseBody
	public Response<DeptBasicInfo> getDept(
			HttpServletRequest httpRequest)
	{
		/*将请求json数据转换成相应的请求体*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<DeptRequestData> request =
				gson.fromJson(json, new Json2Request<Request<DeptRequestData>>(){}.getType());

		/*生成响应体*/
		Response<DeptBasicInfo> response = new Response<DeptBasicInfo>();
		Result result = new Result();
		response.setResult(result);
		
		
		/*获得部门基本信息*/
		String deptId = request.getRequestData().getDeptId();
		DeptBasicInfo deptBasicInfo = deptService.getDepartmentBasicInfo(deptId);
		
		if (deptBasicInfo == null)
		{
			result.setCode("1");
			result.setMessage("获取部门信息失败！");
		}
		else
		{
			result.setCode("0");
			result.setMessage("获取部门信息成功！");
			response.setResponseData(deptBasicInfo);
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/modifyDept", method = POST)
	@ResponseBody
	public Response<DeptBasicInfo> modifyDept(
			HttpServletRequest httpRequest)
	{
		/*将请求json数据转换成相应的请求体*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<DeptBasicInfo> request =
				gson.fromJson(json, new Json2Request<Request<DeptBasicInfo>>(){}.getType());

		/*生成响应体*/
		Response<DeptBasicInfo> response = new Response<DeptBasicInfo>();
		Result result = new Result();
		response.setResult(result);
		
		
		/*从请求体中获取修改的部门基本信息*/
		DeptBasicInfo deptBasicInfo = request.getRequestData();
		Date updateTime = deptService.updateDeptBasicInfo(deptBasicInfo);
		
		if (updateTime == null)
		{
			result.setCode("1");
			result.setMessage("修改部门信息失败！");
		}
		else
		{
			deptBasicInfo.setUpdateTime(updateTime);
			result.setCode("0");
			result.setMessage("修改部门信息成功！");
			response.setResponseData(deptBasicInfo);
		}
		
		return response;
	}
	
	
	@RequestMapping(value = "/deleteDept", method = POST)
	@ResponseBody
	public Response<DeptBasicInfo> deleteDept(
			HttpServletRequest httpRequest)
	{
		/*将请求json数据转换成相应的请求体*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<DeptBasicInfo> request =
				gson.fromJson(json, new Json2Request<Request<DeptBasicInfo>>(){}.getType());

		/*生成响应体*/
		Response<DeptBasicInfo> response = new Response<DeptBasicInfo>();
		Result result = new Result();
		response.setResult(result);
		
		
		/*从请求体中获取需要删除的部门id*/
		String deptId = request.getRequestData().getDeptId();
		boolean rmResult = deptService.deleteDept(deptId);

		if (rmResult == false)
		{
			result.setCode("1");
			result.setMessage("删除部门信息失败！");
		}
		else
		{
			result.setCode("0");
			result.setMessage("删除部门成功！");
		}
		return response;
	}
	
	
	@RequestMapping(value = "/getSubDepts", method = POST)
	@ResponseBody
	public Response<List<String>> getSubDepts(
			HttpServletRequest httpRequest)
	{
		/*将请求json数据转换成相应的请求体*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<DeptBasicInfo> request =
				gson.fromJson(json, new Json2Request<Request<DeptBasicInfo>>(){}.getType());

		/*生成响应体*/
		Response<List<String>> response = new Response<List<String>>();
		Result result = new Result();
		response.setResult(result);
		
		
		/*从请求体中获取部门id*/
		String deptId = request.getRequestData().getDeptId();
		
		/*获取所有的子部门*/
		List<String> subDepts = deptService.getSubDepts(deptId, 1);
		if (subDepts == null || subDepts.isEmpty())
		{
			result.setCode("1");
			result.setMessage("子部门为空");
		} 
		else
		{
			result.setCode("0");
			result.setMessage("获取子部门成功！");
			response.setResponseData(subDepts);
		}
		return response;
	}
	
	
	
	@RequestMapping(value = "/getUsersInDept", method = POST)
	@ResponseBody
	public Response<List<UserBasicInfo>> getUsersInDept(
			HttpServletRequest httpRequest)
	{
		/*将请求json数据转换成相应的请求体*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<DeptBasicInfo> request =
				gson.fromJson(json, new Json2Request<Request<DeptBasicInfo>>(){}.getType());

		/*生成响应体*/
		Response<List<UserBasicInfo>> response = new Response<List<UserBasicInfo>>();
		Result result = new Result();
		response.setResult(result);
		List<UserBasicInfo> userBasicInfos = new ArrayList<UserBasicInfo>();
		
		/*从请求体中获取部门id*/
		String deptId = request.getRequestData().getDeptId();
		
		/*获取所有的子部门*/
		List<String> userIds = deptService.getAllUserIdOfDeptExcludiingSubDeptByDeptId(deptId);
		if (userIds == null || userIds.isEmpty())
		{
			result.setCode("1");
			result.setMessage("部门用户为空！");
		} 
		else
		{
			for(String eachUserIds : userIds)
			{
				UserBasicInfo userBasicInfo =
						userService.getUserBasicInfoByUserId(eachUserIds);
				userBasicInfos.add(userBasicInfo);
			}
			result.setCode("0");
			result.setMessage("获取子部门成功！");
			response.setResponseData(userBasicInfos);
		}
		return response;
	}
	
	
	@RequestMapping(value = "/getRootDept", method = POST)
	@ResponseBody
	public Response<List<DeptBasicInfo>> getRootDept(HttpServletRequest httpRequest) 
	{
		/*将请求json数据转换成相应的请求体*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<DeptBasicInfo> request =
				gson.fromJson(json, new Json2Request<Request<DeptBasicInfo>>(){}.getType());
		
		/*生成响应体*/
		Response<List<DeptBasicInfo>> response = new Response<List<DeptBasicInfo>>();
		Result result = new Result();
		response.setResult(result);
		
		String companyId = request.getRequestData().getCompanyId();
		List<DeptBasicInfo> rootDepts = deptService.getRootDept(companyId);
		
		if (rootDepts == null)
		{
			result.setCode("1");
			result.setMessage("根部门为空！");
		}
		else
		{
			result.setCode("0");
			result.setMessage("根部门获取成功！");
			response.setResponseData(rootDepts);
		}
		return response;
	}
}
