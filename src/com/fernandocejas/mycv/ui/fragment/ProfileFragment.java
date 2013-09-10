package com.fernandocejas.mycv.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fernandocejas.mycv.R;

public final class ProfileFragment extends BaseFragment {
	
	private View mView;
	
	public ProfileFragment() { super(); }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.lyt_fragment_profile, container, false);
		return mView;
	}
}
