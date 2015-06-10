package com.nationsky.seccom.uc.interceptor;

import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class JsonValidationInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler)
		throws Exception {
		
		
		
		
		
//		String servletPath = httpRequest.getServletPath();  //  /v1/adminLogin
//		String contextPath = httpRequest.getContextPath();	//  /uc
//
//
//		if(!servletPath.equalsIgnoreCase("/v1/adminLogin")){ //除了登陆接口
//
//
//			if (httpRequest.getSession().getAttribute("admin") == null) {
//				// 没有Session ，先登陆
//				try {
//					PrintWriter	 pw = httpResponse.getWriter();
//					httpResponse.sendRedirect(contextPath);
//					pw.close();
//
//				} catch (IOException e) {
//				}
//				return false;
//			}
//		}
		
		InputStream in = httpRequest.getInputStream();
        byte[] buffer = new byte[1024];
        in.read(buffer);
        String json = new String(buffer);
        System.out.println("request json :" + json);

        /*清空空格*/
        json = json.trim();
        
        if (json.isEmpty() == true)
        {
        	PrintWriter printWriter = httpResponse.getWriter();
        	printWriter.write("Json is empty!");
            printWriter.flush();
        	return false;
        }
        else
        {
            httpRequest.setAttribute("json", json);
            return true;
        }
	}
}
