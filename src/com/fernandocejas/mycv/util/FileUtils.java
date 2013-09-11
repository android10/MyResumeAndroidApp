package com.fernandocejas.mycv.util;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The android10 coder: http://www.android10.org/
 *
 * Utility/Helper for using with files
 *
 * @author Fernando Cejas <http://fernandocejas.com/>
 */
public class FileUtils {
	
	private FileUtils() {}

	/**
	 * Gets the External Storage Directory of the device/handset.
	 *
	 * @return A String with a path pointing to the external storage.
	 */
	public static String getExternalStorageDir() {
		String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
		
		if (!dir.endsWith(File.separator))
			dir+= File.separator;
		
		return dir;
	}

	/**
	 * Checks whether the external storage is available on the current device/handset.
	 *
	 * @return True is available, otherwise false.
	 */
	public static boolean isExternalStorageAvailableReadWrite() {
		final String state = Environment.getExternalStorageState();

		return Environment.MEDIA_MOUNTED.equals(state);
	}

	/**
	 * Copy two files. Take into account that this method run on the UI thread.
	 *
	 * @param in The origin InputStream
	 * @param out The destiny OutputStream
	 *
	 * @throws IOException
	 */
	public static void copyFile(InputStream in, OutputStream out) throws IOException {
	    final byte[] buffer = new byte[1024];
	    int read;
	    
	    while((read = in.read(buffer)) != -1){
	    	out.write(buffer, 0, read);
	    }
	    
	    in.close(); in = null;
	    out.flush(); out.close(); out = null;
	}
}
