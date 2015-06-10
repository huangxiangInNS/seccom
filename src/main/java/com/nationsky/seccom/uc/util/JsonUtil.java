package com.nationsky.seccom.uc.util;

import com.google.gson.Gson;

public class JsonUtil
{
	public static<T> T json2Bean(String json, Class<T> t) throws Exception{
		Gson gson = new Gson();
        return  gson.fromJson(json, t);
    }
}
