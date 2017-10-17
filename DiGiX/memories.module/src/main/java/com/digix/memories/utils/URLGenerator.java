package com.digix.memories.utils;

import java.util.Calendar;
import java.util.Date;

import com.digix.memories.constants.FileExtensions;
import com.digix.memories.constants.MemoryType;


public class URLGenerator {
	
	public static String generateURL(long uid){
		
		String result = "";
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		result += uid;
		
		int month = calendar.get(Calendar.MONTH) + 1;
		
		result += calendar.get(Calendar.DAY_OF_MONTH) + "" 
				+ month + "" 
				+ calendar.get(Calendar.YEAR) + "" 
				+ calendar.get(Calendar.HOUR_OF_DAY) + "" 
				+ calendar.get(Calendar.MINUTE) + "" 
				+ calendar.get(Calendar.SECOND) + "" 
				+ calendar.get(Calendar.MILLISECOND);
		
		return result;
	}
	
	public static String appendExtension(String url, String memType){
		
		String result = url;
		
		switch(memType){
			case MemoryType.PHOTO:
				result += FileExtensions.JPG;
			break;
			
			case MemoryType.VIDEO:
				result += FileExtensions.MP4;
			break;
			
			default:
				
				break;
		}
		
		return result;
	}
	
}
