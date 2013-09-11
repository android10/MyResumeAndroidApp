package com.fernandocejas.mycv.ui.helper;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;
import com.fernandocejas.mycv.ui.activity.MyCVMainActivity;

/**
 * The android10 coder: http://www.android10.org/
 *
 * Activity helper for managing activities utils and events.
 *
 * @author Fernando Cejas <http://fernandocejas.com/>
 */
public class ActivityHelper { 
    protected FragmentActivity mActivity;
    
    private static final int FRAGMENT_OPERATION_ADD = 1;
    private static final int FRAGMENT_OPERATION_REMOVE = 2;
    private static final int FRAGMENT_OPERATION_REPLACE = 3;

	/**
	 * Creates a new instance of ActivityHelper

	 * @param activity The activity that will be composed.
	 * @return A new instance of ActivityHelper.
	 */
    public static ActivityHelper createInstance(FragmentActivity activity) {
        return new ActivityHelper(activity);
    }

    protected ActivityHelper(FragmentActivity activity) {
        mActivity = activity;
    }

	/**
	 * {@link FragmentActivity#onKeyLongPress(int, android.view.KeyEvent)}
	 */
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goHome();
            return true;
        }
        return false;
    }

	/**
	 * Goes to the Main Application Activity
	 */
    public void goHome() {
        if (mActivity instanceof MyCVMainActivity) {
            return;
        }

        final Intent intent = MyCVMainActivity.getCallingIntent(mActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mActivity.startActivity(intent);
    }

	/**
	 * Shows a toast for a short amount of time (3 seconds).
	 *
	 * @param message The message to show
	 */
    public void toastShort(String message) {
    	toast(message, Toast.LENGTH_SHORT);
    }

	/**
	 * Shows a toast for a long amount of time (5 seconds).
	 *
	 * @param message The message to show
	 */
    public void toastLong(String message) {
    	toast(message, Toast.LENGTH_LONG);
    }

	/**
	 * Adds a Fragment to this activity
	 *
	 * @param fragment The fragment to add.
	 * @param viewGroupId Viewgroup which will be used to embed the fragment.
	 */
    public void addFragment(Fragment fragment, int viewGroupId) {
    	doFragmentOperation(FRAGMENT_OPERATION_ADD, fragment, viewGroupId, false);
    }

	/**
	 * Removes a Fragment to this activity
	 *
	 * @param fragment The fragment to remove.
	 */
    public void removeFragment(Fragment fragment) {
    	doFragmentOperation(FRAGMENT_OPERATION_REMOVE, fragment, 0, false);
    }

	/**
	 * Replaces a Fragment in this activity with another one.
	 *
	 * @param fragment The fragment to add.
	 * @param viewGroupId Viewgroup which will be used to embed the fragment.
	 * @param addToBackStack True if the fragment should be added to the backstack, otherwise false.
	 */
    public void replaceFragment(Fragment fragment, int viewGroupId, boolean addToBackStack) {
    	doFragmentOperation(FRAGMENT_OPERATION_REPLACE, fragment, viewGroupId, addToBackStack);
    }

	/**
	 * Executes a fragment operation.
	 *
	 * @param fragmentOperation The fragment operation to execute:
	 *                          {@link ActivityHelper#FRAGMENT_OPERATION_ADD}
	 *                          {@link ActivityHelper#FRAGMENT_OPERATION_REMOVE}
	 *                          {@link ActivityHelper#FRAGMENT_OPERATION_REPLACE}
	 * @param fragment The fragment on which will be the operation executed.
	 * @param viewGroupId The viewGroup where the fragment operation will be executed.
	 * @param addToBackStack True if the fragment should be added to the backstack, otherwise false.
	 */
    private void doFragmentOperation(int fragmentOperation, Fragment fragment, int viewGroupId, boolean addToBackStack) {
    	FragmentTransaction fragmentTransaction = mActivity.getFragmentManager().beginTransaction();
    	
    	switch (fragmentOperation) {
	    	case FRAGMENT_OPERATION_ADD:
	    		fragmentTransaction.add(viewGroupId, fragment);
	    		break;
	    		
	    	case FRAGMENT_OPERATION_REMOVE:
	    		fragmentTransaction.remove(fragment);
	    		break;
	    		
	    	case FRAGMENT_OPERATION_REPLACE:
    			fragmentTransaction.replace(viewGroupId, fragment);
	    		break;
    	}
    	
    	if (addToBackStack) fragmentTransaction.addToBackStack(null);
    	fragmentTransaction.commit();
    }

	/**
	 * Shows a toast message.

	 * @param message The message to show.
	 * @param lenght The lenght of the message
	 *               {@link Toast#LENGTH_SHORT}
	 *               {@link Toast#LENGTH_LONG}
	 */
    private void toast(String message, int lenght) {
    	Toast.makeText(mActivity, message, lenght).show();
    }    
}
