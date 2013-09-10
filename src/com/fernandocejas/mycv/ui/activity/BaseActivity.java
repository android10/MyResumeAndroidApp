package com.fernandocejas.mycv.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.ViewStub;
import com.fernandocejas.mycv.R;
import com.fernandocejas.mycv.ui.helper.ActivityHelper;

public abstract class BaseActivity extends FragmentActivity {

	private static final String LOG_TAG = BaseActivity.class.getSimpleName();

	final ActivityHelper mActivityHelper = ActivityHelper.createInstance(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		throw new RuntimeException("invalid method. you must call Activity.init(Bundle savedInstanceState, int layoutResID) instead");
	}

	protected void initActivity(Bundle savedInstanceState, int pLayoutResID) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.lyt_activity_base);
		((ViewStub) findViewById(R.id.lyt_base_container)).setLayoutResource(pLayoutResID);
		((ViewStub) findViewById(R.id.lyt_base_container)).inflate();
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		return mActivityHelper.onKeyLongPress(keyCode, event) ||
				super.onKeyLongPress(keyCode, event);
	}

	protected ActivityHelper getActivityHelper() {
		return mActivityHelper;
	}

	protected void toastShort(String message) {
		mActivityHelper.toastShort(message);
	}

	protected void toastLong(String message) {
		mActivityHelper.toastLong(message);
	}

	protected boolean isOnePaneLayout() {
		try {
			boolean result = getResources().getBoolean(R.bool.has_one_pane);
			return result;

		} catch (Exception e) {
			return false;
		}
	}

	protected void addFragment(Fragment fragment, int viewGroupId) {
		mActivityHelper.addFragment(fragment, viewGroupId);
	}

	protected void removeFragment(Fragment fragment) {
		mActivityHelper.removeFragment(fragment);
	}

	protected void replaceFragment(Fragment fragment, int viewGroupId, boolean addToBackStack) {
		mActivityHelper.replaceFragment(fragment, viewGroupId, addToBackStack);
	}
}