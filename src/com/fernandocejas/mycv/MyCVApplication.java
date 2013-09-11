package com.fernandocejas.mycv;

import android.app.Application;

/**
 * The android10 coder: http://www.android10.org/
 * @author Fernando Cejas <http://fernandocejas.com/>
 */
public class MyCVApplication extends Application {
	
	private static MyCVApplication singleton;
	
	public static MyCVApplication getInstance() {
		return singleton;
	}

	@Override
	public final void onCreate() {
		super.onCreate();
		singleton = this;
	}
}
