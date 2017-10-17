package DebuggingTools;

import java.text.NumberFormat;

public class SystemInfos {
	public SystemInfos(){
		
	}
	
	public StringBuilder showInfos(){
		 Runtime runtime = Runtime.getRuntime();

		    NumberFormat format = NumberFormat.getInstance();

		    StringBuilder sb = new StringBuilder();
		    
		    long maxMemory = runtime.maxMemory();
		    long allocatedMemory = runtime.totalMemory();
		    long freeMemory = runtime.freeMemory();
		    long memory = runtime.totalMemory() - runtime.freeMemory();	    
		    
		    sb.append("used memory is bytes: " + format.format(memory / 1024) + "\n");
		    sb.append("free memory: " + format.format(freeMemory / 1024) + "\n");
		    sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "\n");
		    sb.append("max memory: " + format.format(maxMemory / 1024) + "\n");
		    sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "\n");
		    
		    return sb;
	}
}
