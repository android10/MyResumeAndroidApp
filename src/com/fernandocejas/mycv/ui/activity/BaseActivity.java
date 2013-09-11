package com.fernandocejas.mycv.ui.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.ViewStub;
import com.fernandocejas.mycv.R;
import com.fernandocejas.mycv.ui.helper.ActivityHelper;

/**
 * The android10 coder: http://www.android10.org/
 * @author Fernando Cejas <http://fernandocejas.com/>
 */
public abstract class BaseActivity extends FragmentActivity {

	final ActivityHelper mActivityHelper = ActivityHelper.createInstance(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		throw new RuntimeException("invalid method. you must call Activity.init(Bundle savedInstanceState, " +
				"int layoutResID) instead");
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

	/**
	 * Gets the activity helper associated with this Activity.
	 * @return The current {@link ActivityHelper} instance.
	 */
	protected ActivityHelper getActivityHelper() {
		return mActivityHelper;
	}

	/**
	 * Shows a toast for a short amount of time (3 seconds).
	 *
	 * @param message The message to show
	 */
	protected void toastShort(String message) {
		mActivityHelper.toastShort(message);
	}

	/**
	 * Shows a toast for a long amount of time (5 seconds).
	 *
	 * @param message The message to show
	 */
	protected void toastLong(String message) {
		mActivityHelper.toastLong(message);
	}

	/**
	 * Checks if the current activity has one pane (for mobile phones) or two panes (for tablets)
	 *
	 * @return True the activity has one pane, otherwise false.
	 */
	protected boolean isOnePaneLayout() {
		try {
			boolean result = getResources().getBoolean(R.bool.has_one_pane);
			return result;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Adds a Fragment to this activity
	 *
	 * @param fragment The fragment to add.
	 * @param viewGroupId Viewgroup which will be used to embed the fragment.
	 */
	protected void addFragment(Fragment fragment, int viewGroupId) {
		mActivityHelper.addFragment(fragment, viewGroupId);
	}

	/**
	 * Removes a Fragment to this activity
	 *
	 * @param fragment The fragment to remove.
	 */
	protected void removeFragment(Fragment fragment) {
		mActivityHelper.removeFragment(fragment);
	}

	/**
	 * Replaces a Fragment in this activity with another one.
	 *
	 * @param fragment The fragment to add.
	 * @param viewGroupId Viewgroup which will be used to embed the fragment.
	 * @param addToBackStack True if the fragment should be added to the backstack, otherwise false.
	 */
	protected void replaceFragment(Fragment fragment, int viewGroupId, boolean addToBackStack) {
		mActivityHelper.replaceFragment(fragment, viewGroupId, addToBackStack);
	}
}