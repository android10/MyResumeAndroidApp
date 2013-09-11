package com.fernandocejas.mycv.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.io.File;
import java.util.List;

/**
 * The android10 coder: http://www.android10.org/
 *
 * Utils for Intents stuff
 *
 * @author Fernando Cejas <http://fernandocejas.com/>
 */
public class IntentUtils {
	
	private IntentUtils() {}

	/**
	 * Views/Opens an url.
	 *
	 * @param context A context needed to open the activity to be launched
	 * @param url String url to open/view.
	 *
	 * @author Fernando Cejas <http://fernandocejas.com/>
	 */
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

	/**
	 * Attempts to open a file using the Android built-in Intent system.
	 *
	 * @param context A context needed for opening a file.
	 * @param file The file to open.
	 *
	 * @throws ActivityNotFoundException
	 *
	 * @author Fernando Cejas <http://fernandocejas.com/>
	 */
    public static void openFile(Context context, File file) throws ActivityNotFoundException {
		final Uri pathUri = Uri.fromFile(file);
		
		final String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
		final String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);

		final Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(pathUri, mimetype);  
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		context.startActivity(intent); 
    }

	/**
	 * Attempts to open a youtube video using the native Youtube application. If this is not available
	 * let Android manages the content passed as a parameter (in this case could open an url in the default
	 * browser.
	 *
	 * @param context A context needed for opening the youtube application/activity.
	 * @param videoId A youtube video id.
	 *                Example: if the youtube url was 'http://www.youtube.com/watch?v=l_NmDalX4kc',
	 *                the video id would be 'l_NmDalX4kc'
	 *
	 * @author Fernando Cejas <http://fernandocejas.com/>
	 */
    public static void openYoutubeApplicationVideo(Context context, String videoId) {
    	final String urlYoutubeApplication = "vnd.youtube:" + videoId;
    	final String urlYoutubeWeb = "www.youtube.com/watch?v=" + videoId;
    	
    	Intent youtubeCall;

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

	/**
	 * Attempts to open the Google Play Store.
	 *
	 * @param context A context needed for opening the application/activity.
	 * @param packageName The package name of the application to open.
	 */
    public static void openAndroidPlayStore(Context context, String packageName) {
		final String marketUrl = "market://details?id=" + packageName.trim();
		IntentUtils.viewURL(context, marketUrl);
    }
}
