package com.fernandocejas.mycv.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.io.File;
import java.util.List;

public class IntentUtils {
	
	private IntentUtils() {}
	
	public static void viewURL(Context context, String url){
		final Uri uri = Uri.parse(url);
		final Intent intent = new Intent(Intent.ACTION_VIEW, uri);

		context.startActivity(intent);
	}

	/**
	 * Checks if an intent is available before launching it.
	 *
	 * @param context A context used to call the activity.
	 * @param action The action that will be checked.
	 *
	 * @author Fernando Cejas <http://fernandocejas.com/>
	 */
    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return list.size() > 0;
    }

	/**
	 * Dials a number using the built in Android intent system.
	 *
	 * @param context A context used to call the activity.
	 *
	 * @author Fernando Cejas <http://fernandocejas.com/>
	 */
    public static void dialNumber(Context context, String uri) {
    	final String action = Intent.ACTION_DIAL;
    	
		context.startActivity(new Intent(action, Uri.parse(uri)));
    }

	/**
	 * Composes a new mail using the built in Android intent system.
	 *
 	 * @param context A context used to call the activity.
	 * @param chooserTitle String representing the title of the chooser.
	 * @param mailAddress String representing destination email.
	 * @param mailSubject String representing the subject of the email.
	 * @param mailBody String representing the body of the email.
	 *
	 * @author Fernando Cejas <http://fernandocejas.com/>
	 */
    public static void composeMail(Context context, String chooserTitle, String mailAddress, String mailSubject, String mailBody) {
    	@SuppressWarnings("unused")
    	final String intentTypeEmailPlain = "text/plain";				//use this for testing in the emulator
    	final String intentTypeEmailMessage = "message/rfc822";		//use this from live device

		final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType(intentTypeEmailMessage); 
		intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mailAddress});
		intent.putExtra(Intent.EXTRA_SUBJECT, mailSubject);  
		intent.putExtra(Intent.EXTRA_TEXT, mailBody);
		
		context.startActivity(Intent.createChooser(intent, chooserTitle));
    }
    
    public static void openFile(Context context, File file) throws ActivityNotFoundException {
		Uri pathUri = Uri.fromFile(file);
		
		String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
		String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(pathUri, mimetype);  
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		context.startActivity(intent); 
    }
    
    public static void openYoutubeApplicationVideo(Context context, String videoId) {
    	String urlYoutubeApplication = "vnd.youtube:" + videoId;
    	String urlYoutubeWeb = "www.youtube.com/watch?v=" + videoId;
    	
    	Intent youtubeCall = new Intent();

		try {
	    	youtubeCall = new Intent(Intent.ACTION_VIEW, Uri.parse(urlYoutubeApplication));
	    	youtubeCall.setClassName("com.google.android.youtube", "com.google.android.youtube.PlayerActivity");
			context.startActivity(youtubeCall); 
			
		}catch (Exception e) {
			try {
		    	youtubeCall = new Intent(Intent.ACTION_VIEW, Uri.parse(urlYoutubeApplication));
				context.startActivity(youtubeCall);
				
			} catch (Exception ex) {
		    	youtubeCall = new Intent(Intent.ACTION_VIEW, Uri.parse(urlYoutubeWeb));
				context.startActivity(youtubeCall);
			}
		}
    }    
    
    public static void openAndroidMarket(Context context, String packageName) {
		String marketUrl = "market://details?id=" + packageName.trim();
		IntentUtils.viewURL(context, marketUrl);
    }
}
