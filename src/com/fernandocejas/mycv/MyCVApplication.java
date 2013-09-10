package com.fernandocejas.mycv;

import android.app.Application;

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
