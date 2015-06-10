package com.nationsky.seccom.uc.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController
{
	public String getRequestJson(HttpServletRequest httpRequest)
	{
		return (String)httpRequest.getAttribute("json");
	}
}
