package com.digix.memories.controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digix.memories.constants.StorageServerConstants;
import com.digix.memories.entities.Memory;
import com.digix.memories.factories.MemoryFactory;
import com.digix.memories.services.contracts.MemoryService;
import com.digix.memories.utils.ExtensionsMapping;
import com.digix.memories.utils.MultipartUtility;
import com.digix.memories.utils.URLGenerator;

@RestController
@ComponentScan("com.digix.memories.services.contracts")
@ComponentScan("com.digix.memories.utils")
public class MemoryController {
	
	private Logger log = Logger.getLogger(MemoryController.class.getName());
	private MemoryFactory memFactory = new MemoryFactory();
	
	@Autowired
	MemoryService memoryService;
	
	//create a memory
	@CrossOrigin
	@RequestMapping(value="/memories", method=RequestMethod.POST)
	public ResponseEntity<String> createMemory(@RequestParam(value="taglist", required=true) String taglist, 
											   @RequestParam(value="fileup") MultipartFile file, 
											   @RequestParam(value="date") String date,
											   HttpServletRequest request) {
		
		log.info("Called POST on /memories, trying to create a new Memory");
		
		boolean success = false;
		
		log.info("UID: " + request.getHeader("uid"));
		log.info("date " + request.getHeader("date"));
		
		String origFilename = file.getOriginalFilename();
		String[] fileExtension = origFilename.split("\\.");
		int fileExtensionIdx = fileExtension.length-1;
		
		long uid = Long.parseLong(request.getHeader("uid"));
//		Date memDate = new Date(request.getHeader("memDate"));
		String memoryType = ExtensionsMapping.getMemoryType(fileExtension[fileExtensionIdx]);
		log.info("Content type: " + fileExtension[fileExtensionIdx]);
		String url = "";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		url += StorageServerConstants.STORAGE_ADDRESS + ":" + StorageServerConstants.STORAGE_PORT + "/" + StorageServerConstants.STORAGE_ACCESS_FOLDER + "/";
		url += URLGenerator.generateURL(uid);
		url += "." + fileExtension[fileExtensionIdx];
		
		log.info("BUILT URL: " + url);
		log.info("DATE: " + date);
		
		Memory mem = memFactory.getMemory(memoryType);
			mem.setUrl(url);
			mem.setTaglist(taglist);
			mem.setOwner(uid); //se va lua din request, user id, care la randul lui va fi in sesiune
			if(date != null && !date.equals("")){
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date1;
				java.sql.Date date2 = null;
				
				try {
					date1 = df.parse(date);
					date2 = new Date(date1.getTime());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				mem.setMemDate(date2);
			}
				
			
		success = memoryService.saveMemory(mem);
		//////////////********//////////////////
		
		try {
			String reqURL = StorageServerConstants.STORAGE_ADDRESS + ":" + StorageServerConstants.STORAGE_PORT + "/" + StorageServerConstants.STORAGE_APP + "/" + StorageServerConstants.STORAGE_FOLDER + "/";
			
			MultipartUtility multipart = new MultipartUtility(reqURL, "UTF-8");
			multipart.addFilePart("file", mem.getUrl(), file.getBytes(), uid);
			
			List<String> response = multipart.finish();
			
			log.info(response);
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		///////////////////***********//////////////
		
		if(success){
			String successString = "Created new memory with URL " + mem.getUrl() + "<a href='http://10.230.51.144:8081/ClientDiGiX/gallery.html'>Gallery</a>";
			log.info(successString);
			return new ResponseEntity<String>(successString, responseHeaders, HttpStatus.CREATED);
		}else{
			log.info("Error on creating new memory");
			return new ResponseEntity<String>("Error on creating new memory", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//return all memories
	@CrossOrigin
	@RequestMapping(value="/memories", method=RequestMethod.GET)
	public ResponseEntity<List<Memory>> getMemories(HttpServletRequest req) {
		
		long id = -1;
		String tags = null;
		
		try{
			id = Long.parseLong(req.getHeader("uid"));
			tags = req.getHeader("tagList");
		}catch(Exception e){
			
		}
		log.info("GET uid: " + id);
		log.info("GET tags: " + tags);
		
		List<Memory> memories = memoryService.findMemoriesByTag(id, tags);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus status = HttpStatus.CREATED;
		
		if(memories.size() == 0)
			status = HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<List<Memory>>(memories, responseHeaders, status);
	}
	
//	return a specific memory
//	@RequestMapping(value="/memories/{id}", method=RequestMethod.GET)
//	public Memory getMemory() {
//		Memory mem = new Memory();
//		
//		return mem;
//	}
	
	//return all memories matching the taglist
	@CrossOrigin
	@RequestMapping(value="/memories/{taglist}", method=RequestMethod.GET)
	public List<Memory> getMemoriesWithTaglist(@PathVariable(value="taglist")String tag, HttpServletRequest req) {
		
		long id = Long.parseLong(req.getHeader("uid"));
		log.info("GET uid: " + id);
		
		return memoryService.findMemoriesByTagList(0, null);//memoryService.findMemoriesByTagList(tag, id);
	}
	
	//removes a memory
	@RequestMapping(value="/memories/{id}", method=RequestMethod.DELETE)
	public void removeMemory() {
		
		
		
	}
	
}