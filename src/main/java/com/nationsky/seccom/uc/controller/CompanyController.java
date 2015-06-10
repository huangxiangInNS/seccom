package com.nationsky.seccom.uc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.nationsky.seccom.uc.domain.CompanyRequestData;
import com.nationsky.seccom.uc.domain.Request;
import com.nationsky.seccom.uc.domain.Response;
import com.nationsky.seccom.uc.domain.Result;
import com.nationsky.seccom.uc.domain.CompanyResponseData;
import com.nationsky.seccom.uc.model.CompanyInfo;
import com.nationsky.seccom.uc.service.ICompanyService;
import com.nationsky.seccom.uc.util.Json2Request;

@Controller
@RequestMapping(value = "/v1/company", method = POST)
public class CompanyController extends BaseController
{
	@Autowired
	private ICompanyService companyService;

	@RequestMapping(value = "/addCompany", method = POST)
	@ResponseBody
	public Response<CompanyResponseData> addCompany(HttpServletRequest httpRequest)
	{

		/*将请求json数据转换成相应的请求体*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<CompanyRequestData> request = gson.fromJson(json, new Json2Request<Request<CompanyRequestData>>(){}.getType());
		CompanyRequestData requestData = request.getRequestData();

		/*生成响应体*/
		Response< CompanyResponseData> response = new Response< CompanyResponseData>();
		Result result = new Result();
		response.setResult(result);
		CompanyResponseData responseData = new CompanyResponseData();
		response.setResponseData(responseData);

		try
		{
			String descendantCompanyId = companyService.addCompany(requestData);
			result.setRequestId(request.getOperId().getRequestId());
			result.setCode("0");// TODO定义错误码
			result.setMessage("添加公司成功！");
			responseData.setCompanyId(descendantCompanyId);
		} catch (RuntimeException e)
		{
			result.setRequestId(request.getOperId().getRequestId());
			result.setCode("1");// TODO定义错误码
			result.setMessage("添加公司失败！");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(e);
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value = "/deleteCompany", method = POST)
	@ResponseBody
	public Response<CompanyResponseData> deleteCompany(HttpServletRequest httpRequest)
	{
		/*将请求json数据转换成相应的请求体*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<CompanyRequestData> request =
				gson.fromJson(json, new Json2Request<Request<CompanyRequestData>>(){}.getType());
		
		/*生成返回体*/
		Response< CompanyResponseData> response = new Response< CompanyResponseData>();
		Result result = new Result();
		response.setResult(result);

		String companyId = request.getRequestData().getCompanyId();
		try
		{
			companyService.deleteCompany(companyId);
			result.setRequestId(request.getOperId().getRequestId());
			result.setCode("0");// TODO定义错误码
			result.setMessage("删除公司成功！");
		} catch (RuntimeException e)
		{
			result.setRequestId(request.getOperId().getRequestId());
			result.setCode("1");// TODO定义错误码
			result.setMessage("删除公司失败！");
			Logger logger = Logger.getLogger(this.getClass());
			logger.warn(e);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/getCompany", method = POST)
	@ResponseBody
	public Response<CompanyResponseData> getCompany(HttpServletRequest httpRequest)
	{
		/*将请求json数据转换成相应的请求体*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<CompanyRequestData> request =
				gson.fromJson(json, new Json2Request<Request<CompanyRequestData>>(){}.getType());
		
		/*生成响应体*/
		Response< CompanyResponseData> response = new Response< CompanyResponseData>();
		Result result = new Result();
		response.setResult(result);

		/*获得响应数据体*/
		String companyId = request.getRequestData().getCompanyId();
		CompanyResponseData companyResponseData = companyService.getCompanyInfoById(companyId);

		/*设置响应结果*/
		if (companyResponseData == null)
		{
			result.setCode("1");
			result.setMessage("获取公司信息失败！");
		}
		else
		{
			result.setCode("0");
			result.setMessage("获取公司信息成功！");
		}

		response.setResponseData(companyResponseData);
		return response;
	}
	
	
	@RequestMapping(value = "/updateCompanyInfo", method = POST)
	@ResponseBody
	public Response<CompanyInfo> updateCompanyInfo(HttpServletRequest httpRequest)
	{
		/*将请求json数据转换成相应的请求体*/
		String json = getRequestJson(httpRequest);
		System.out.println(json);
		Gson gson = new Gson();
		Request<CompanyInfo> request =
				gson.fromJson(json, new Json2Request<Request<CompanyInfo>>(){}.getType());
		
		/*生成响应体*/
		Response< CompanyInfo> response = new Response< CompanyInfo>();
		Result result = new Result();
		response.setResult(result);
		
		/*从请求体中获取更新的公司信息*/
		CompanyInfo companyInfo = request.getRequestData();
		Date updateDate = companyService.udpateCompanyInfo(companyInfo);
		if (updateDate == null)
		{
			result.setCode("1");
			result.setMessage("更新公司信息失败！");
		}
		else
		{
			result.setCode("0");
			result.setMessage("更新公司信息成功！");
			CompanyInfo reponseCompanyInfo = new CompanyInfo();
			reponseCompanyInfo.setUpdateTime(updateDate);
			response.setResponseData(reponseCompanyInfo);
		}
		return response;
	}
}
