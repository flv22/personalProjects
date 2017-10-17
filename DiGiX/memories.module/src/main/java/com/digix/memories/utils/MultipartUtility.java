package com.digix.memories.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MultipartUtility {
	
	private final String boundary;
	private static final String LINE_FEED = "\r\n";
	private HttpURLConnection httpConn;
	private OutputStream outputStream;
	private PrintWriter writer;
	
	public MultipartUtility(String requestURL, String charset) throws IOException{
		// creates a unique boundary based on time stamp
		boundary = "===" + System.currentTimeMillis() + "===";
		
		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setUseCaches(false);
		httpConn.setDoOutput(true);	// indicates POST method
		httpConn.setDoInput(true);
		httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
		
		outputStream = httpConn.getOutputStream();
		
		writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
		
	}
	
    public void addFilePart(String fieldName, String filename, byte[] sourceBytes, long uid) throws IOException {
    	
        String fileName = filename;
        
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
        writer.append("uid: " + uid).append(LINE_FEED);
        writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();

        outputStream.write(sourceBytes, 0, sourceBytes.length);

        outputStream.flush();
         
        writer.append(LINE_FEED);
        writer.flush();    
    }
	
    
    public List<String> finish() throws IOException {
        List<String> response = new ArrayList<String>();
 
        writer.append(LINE_FEED).flush();
        writer.append("--" + boundary + "--").append(LINE_FEED);
        writer.close();
 
        // checks server's status code first
        int status = httpConn.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
            reader.close();
            httpConn.disconnect();
        } else {
            throw new IOException("Server returned non-OK status: " + status);
        }
 
        return response;
    }
}
