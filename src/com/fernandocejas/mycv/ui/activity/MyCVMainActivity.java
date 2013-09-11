package com.fernandocejas.mycv.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import com.fernandocejas.mycv.R;
import com.fernandocejas.mycv.ui.adapter.TabsAdapter;
import com.fernandocejas.mycv.ui.fragment.ProfileFragment;
import com.fernandocejas.mycv.ui.fragment.ShowcaseFragment;
import com.fernandocejas.mycv.ui.fragment.ShowcaseFragment.OnShowcaseChosenListener;
import com.fernandocejas.mycv.ui.fragment.SocialFragment;
import com.fernandocejas.mycv.ui.fragment.SocialFragment.OnSocialNetworkChosenListener;
import com.fernandocejas.mycv.util.FileUtils;
import com.fernandocejas.mycv.util.IntentUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The android10 coder: http://www.android10.org/
 * @author Fernando Cejas <http://fernandocejas.com/>
 */
public class MyCVMainActivity extends BaseActivity implements OnSocialNetworkChosenListener, OnShowcaseChosenListener {
	
	private static final String LOG_TAG = MyCVMainActivity.class.getSimpleName();
	
	private static final int ACTION_BAR_ITEM_ID_MAIL = 1;
	private static final int ACTION_BAR_ITEM_ID_CALL = 2;
	private static final int ACTION_BAR_ITEM_ID_PDF = 3; 
	
    TabHost mTabHost;
    ViewPager  mViewPager;
    TabsAdapter mTabsAdapter;
	
	public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, MyCVMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
	}	
	
    @Override
    public void onCreate(Bundle savedInstanceState) { 
    	Log.i(LOG_TAG, "Initializing Main Activity...");
        initActivity(savedInstanceState, R.layout.lyt_activity_main);
      
        //Check for Tablet or Phone Layout
        if (isOnePaneLayout())
        	loadTabs(savedInstanceState);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Get the state of the tab
        if (isOnePaneLayout() && outState != null) 
        	outState.putString("tab", mTabHost.getCurrentTabTag());
    } 
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(1, ACTION_BAR_ITEM_ID_MAIL, 1, R.string.mail)
    	.setIcon(R.drawable.ic_action_mail)
    	.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    	
        menu.add(1, ACTION_BAR_ITEM_ID_CALL, 2, R.string.call)
            .setIcon(R.drawable.ic_action_call) 
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS); 

        menu.add(1, ACTION_BAR_ITEM_ID_PDF, 3, R.string.pdf)
        	.setIcon(R.drawable.ic_action_info) 
        	.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW); 
        
        return true;
    }      
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
	    	case ACTION_BAR_ITEM_ID_MAIL:
	    		IntentUtils.composeMail(this, 
	    								getString(R.string.send_email), 
	    								getString(R.string.email_address), 
	    								"", "");
	    		break;
	    		
	    	case ACTION_BAR_ITEM_ID_CALL:
	    		IntentUtils.dialNumber(this, "tel:" + getString(R.string.phone_number));
	    		break;    	
	    		
	    	case ACTION_BAR_ITEM_ID_PDF:
	    		openPDF();
	    		break;
    	}
    	return super.onOptionsItemSelected(item);
	}

	/**
	 * Load tabs that belong to this Activity
	 *
	 * @param savedInstanceState {@link Bundle}
	 */
	private void loadTabs(Bundle savedInstanceState) {  
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(); 

        mViewPager = (ViewPager)findViewById(R.id.pager);

        mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager); 
        mTabsAdapter.addTab(mTabHost.newTabSpec(getString(R.string.profile)).setIndicator(getString(R.string.profile)), ProfileFragment.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec(getString(R.string.showcase)).setIndicator(getString(R.string.showcase)),ShowcaseFragment.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec(getString(R.string.social)).setIndicator(getString(R.string.social)),SocialFragment.class, null);

        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }
    }

	/**
	 * Opens a PDF file
	 */
	private void openPDF() {
		try {
			final String pdfFileName = getString(R.string.resume_file_name);
			final String pdfFilePath = FileUtils.getExternalStorageDir() + pdfFileName;
			
			final File pdfFile = new File(pdfFilePath);
			if (!pdfFile.exists()) {
				if (FileUtils.isExternalStorageAvailableReadWrite()) {
					
					InputStream in = getAssets().open(pdfFileName);
					OutputStream out = new FileOutputStream(pdfFilePath);
					FileUtils.copyFile(in, out);
					 
				}else{
					toastShort(getString(R.string.exception_message_external_storage_access)); 
				}  
			}
			 
			IntentUtils.openFile(this, pdfFile);
		
		}catch (IOException e) {
			toastShort(getString(R.string.exception_message_external_storage_access));
			Log.e(LOG_TAG, e.getMessage());
			
		}catch (ActivityNotFoundException e) {
			toastShort(getString(R.string.exception_message_no_application_available));
			Log.e(LOG_TAG, e.getMessage());
			
        }catch (Exception e) {
        	toastShort(getString(R.string.exception_message_pdf_opening_error));
        	Log.e(LOG_TAG, e.getMessage());
        }		
	}

	//--------------------------------------------------------------------------
	// S O C I A L
	//--------------------------------------------------------------------------	
	@Override
	public void onTwitterSelected() {
		IntentUtils.viewURL(this, getString(R.string.my_social_twitter));
	}

	@Override
	public void onLinkedinSelected() {
		IntentUtils.viewURL(this, getString(R.string.my_social_linkedin));
	}

	@Override
	public void onFacebookSelected() {
		IntentUtils.viewURL(this, getString(R.string.my_social_facebook));	
	}

	@Override
	public void onGooglePlusSelected() {
		IntentUtils.viewURL(this, getString(R.string.my_social_googleplus)); 		
	}

	//--------------------------------------------------------------------------
	// S H O W C A S E
	//--------------------------------------------------------------------------
	@Override
	public void onShowcaseGdgChosen() {
		IntentUtils.viewURL(this, getString(R.string.my_showcase_gdg_url));
	}

	@Override
	public void onShowcaseNfcChosen() {
		IntentUtils.viewURL(this, getString(R.string.my_showcase_nfc_url));
	}

	@Override
	public void onShowcaseAndroid10Chosen() {
		IntentUtils.viewURL(this, getString(R.string.my_showcase_android10_url));
		
	}

	@Override
	public void onShowcaseAppcircusChosen() {
		IntentUtils.openYoutubeApplicationVideo(this, getString(R.string.my_showcase_appcircus_url_videoid));
	}

	@Override
	public void onShowcaseSmartphonedayChosen() {
		IntentUtils.viewURL(this, getString(R.string.my_showcase_smartphoneday_url));
	}

	@Override
	public void onShowcasePushChosen() {
		IntentUtils.viewURL(this, getString(R.string.my_showcase_push_url));
	}

	@Override
	public void onShowcaseScrumChosen() {
		IntentUtils.openYoutubeApplicationVideo(this, getString(R.string.my_showcase_scrum_url_videoid));
	}

	@Override
	public void onShowcaseMasterbranchChosen() {
		IntentUtils.viewURL(this, getString(R.string.my_showcase_masterbranch_url));
	}

	@Override
	public void onShowcaseSlideshareChosen() {
		IntentUtils.viewURL(this, getString(R.string.my_showcase_slideshare_url));
	}
}