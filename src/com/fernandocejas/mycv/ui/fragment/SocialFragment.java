package com.fernandocejas.mycv.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fernandocejas.mycv.R;

public final class SocialFragment extends BaseFragment {
	
	public interface OnSocialNetworkChosenListener {
		public void onTwitterSelected();
		public void onLinkedinSelected();
		public void onFacebookSelected();
		public void onGooglePlusSelected();
	}
	
	private OnSocialNetworkChosenListener mOnSocialNetworkListener;
	
	private LinearLayout mLinearLayoutTwitter;
	private LinearLayout mLinearLayoutLinkedin;
	private LinearLayout mLinearLayoutFacebook;
	private LinearLayout mLinearLayoutGooglePlus;
	private View mView;
	
	public SocialFragment() { super(); }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try {
			mOnSocialNetworkListener = (OnSocialNetworkChosenListener)activity;
			
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString() + " must implement on OnSocialNetworkListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.lyt_fragment_social, container, false);
		
		mLinearLayoutTwitter = (LinearLayout)mView.findViewById(R.id.twitter);
		mLinearLayoutTwitter.setOnClickListener(mTwitterOnClickListener);
		
		mLinearLayoutLinkedin = (LinearLayout)mView.findViewById(R.id.linkedin);
		mLinearLayoutLinkedin.setOnClickListener(mLinkedinOnClickListener);
		
		mLinearLayoutFacebook = (LinearLayout)mView.findViewById(R.id.facebook);
		mLinearLayoutFacebook.setOnClickListener(mFacebookOnClickListener);
		
		mLinearLayoutGooglePlus = (LinearLayout)mView.findViewById(R.id.googleplus);
		mLinearLayoutGooglePlus.setOnClickListener(mGooglePlusOnClickListener);
		
		return mView;
	}
	
	private OnClickListener mTwitterOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnSocialNetworkListener.onTwitterSelected();
		}
	};
	
	private OnClickListener mLinkedinOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnSocialNetworkListener.onLinkedinSelected();
		}
	};	
	
	private OnClickListener mFacebookOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnSocialNetworkListener.onFacebookSelected();
		}
	};
	
	private OnClickListener mGooglePlusOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnSocialNetworkListener.onGooglePlusSelected();
		}
	};
}
