package com.fernandocejas.mycv.ui.helper;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;
import com.fernandocejas.mycv.ui.activity.MyCVMainActivity;

public class ActivityHelper { 
    protected FragmentActivity mActivity;
    
    private static final int FRAGMENT_OPERATION_ADD = 1;
    private static final int FRAGMENT_OPERATION_REMOVE = 2;
    private static final int FRAGMENT_OPERATION_REPLACE = 3;

    public static ActivityHelper createInstance(FragmentActivity activity) {
        return new ActivityHelper(activity);
    }

    protected ActivityHelper(FragmentActivity activity) {
        mActivity = activity;
    }

    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goHome();
            return true;
        }
        return false;
    }

    public void goHome() {
        if (mActivity instanceof MyCVMainActivity) {
            return;
        }

        final Intent intent = MyCVMainActivity.getCallingIntent(mActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mActivity.startActivity(intent);
    }
    
    public void toastShort(String message) {
    	toast(message, Toast.LENGTH_SHORT);
    }
    
    public void toastLong(String message) {
    	toast(message, Toast.LENGTH_LONG);
    }
    
    public void addFragment(Fragment fragment, int viewGroupId) {
    	doFragmentOperation(FRAGMENT_OPERATION_ADD, fragment, viewGroupId, false);
    }
    
    public void removeFragment(Fragment fragment) {
    	doFragmentOperation(FRAGMENT_OPERATION_REMOVE, fragment, 0, false);
    }
    
    public void replaceFragment(Fragment fragment, int viewGroupId, boolean addToBackStack) {
    	doFragmentOperation(FRAGMENT_OPERATION_REPLACE, fragment, viewGroupId, addToBackStack);
    }    
    
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
    
    private void toast(String message, int lenght) {
    	Toast.makeText(mActivity, message, lenght).show();
    }    
}
