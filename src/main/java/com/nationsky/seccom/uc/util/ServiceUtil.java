package com.nationsky.seccom.uc.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class ServiceUtil {
	
	public static String getRandomIntId(int maxNumber)
	{
		Random random = new Random(System.currentTimeMillis());
		long randomInt = random.nextInt(maxNumber);
		return String.format("%d", randomInt);
	}
	
	public static String getRandomLongId()
	{
		Random random = new Random(System.currentTimeMillis());
		long randomLong = random.nextLong();
		return String.format("%d", randomLong);
	}
	
	public static String getRandomString(int length) 
	{ //length表示生成字符串的长度
	    String base =
	    		"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }   


	public static Date getCurrentTime()
	{
		return new Date(System.currentTimeMillis());
	}


	public static String getUUID()
	{
		return UUID.randomUUID().toString();
	}
}
