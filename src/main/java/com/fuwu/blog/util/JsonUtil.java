package com.fuwu.blog.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;


public class JsonUtil {
	public static Map<String, Object> getRequestBody(HttpServletRequest request){
		Map<String, Object> map=new HashMap<>();
		try {
			String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
	        JsonParser jsonParser=JsonParserFactory.getJsonParser();
	        if(StringUtils.hasText(body)) {
	        	map=jsonParser.parseMap(body);
	        }  
		} catch (IOException e) {
			e.printStackTrace();
		}
        return map;
	}
}
