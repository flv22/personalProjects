package com.digix.memories.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class MultipartToFile {
	
	public static File convert(MultipartFile file) throws IllegalStateException, IOException{
		File result = new File(file.getOriginalFilename());
		file.transferTo(result);
		return result;
	}
	
}
