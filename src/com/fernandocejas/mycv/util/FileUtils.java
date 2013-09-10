package com.fernandocejas.mycv.util;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
	
	private FileUtils() {}
	
	public static String getExternalStorageDir() {
		String dir = Environment.getExternalStorageDirectory().getAbsolutePath(); 
		
		if (!dir.endsWith(File.separator))
			dir+= File.separator;
		
		return dir;
	}
	
	public static boolean isExternalStorageAvailableReadWrite() {
		String state = Environment.getExternalStorageState();

		return Environment.MEDIA_MOUNTED.equals(state);
	}
	
	public static void copyFile(InputStream in, OutputStream out) throws IOException {
	    byte[] buffer = new byte[1024];
	    int read;
	    
	    while((read = in.read(buffer)) != -1){
	    	out.write(buffer, 0, read);
	    }
	    
	    in.close(); in = null;
	    out.flush(); out.close(); out = null;
	}
}
