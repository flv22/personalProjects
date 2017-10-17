package com.digix.memories.utils;

import java.util.HashMap;

import com.digix.memories.constants.MemoryType;


public class ExtensionsMapping {
	
	private static HashMap<String, String> hm = new HashMap<String,String>();
	
	static{
		hm.put("mp4", MemoryType.VIDEO);
		hm.put("wmv", MemoryType.VIDEO);
		hm.put("avi", MemoryType.VIDEO);
		hm.put("mp3", MemoryType.SOUND);
		hm.put("txt", MemoryType.NOTE);
		hm.put("doc", MemoryType.NOTE);
		hm.put("docx", MemoryType.NOTE);
		hm.put("rtf", MemoryType.NOTE);
		hm.put("pdf", MemoryType.NOTE);
		hm.put("jpg", MemoryType.PHOTO);
		hm.put("JPG", MemoryType.PHOTO);
		hm.put("jpeg", MemoryType.PHOTO);
		hm.put("JPEG", MemoryType.PHOTO);
		hm.put("png", MemoryType.PHOTO);
		hm.put("gif", MemoryType.PHOTO);
	}
	
	public static String getMemoryType(String fileExtension){
		return hm.get(fileExtension);
	}
	
}
