package com.fernandocejas.mycv.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.fernandocejas.mycv.R;

/**
 * The android10 coder: http://www.android10.org/
 * @author Fernando Cejas <http://fernandocejas.com/>
 */
public final class ShowcaseFragment extends BaseFragment {
	
	public interface OnShowcaseChosenListener {
		public void onShowcaseGdgChosen();
		public void onShowcaseNfcChosen();
		public void onShowcaseAndroid10Chosen();
		public void onShowcaseAppcircusChosen();
		public void onShowcaseSmartphonedayChosen();
		public void onShowcasePushChosen();
		public void onShowcaseScrumChosen();
		public void onShowcaseMasterbranchChosen();
		public void onShowcaseSlideshareChosen();
	}
	
	private OnShowcaseChosenListener mOnShowcaseChosenListener;	
	
	private View mView;
	private RelativeLayout mRelativeLayoutGdg;
	private RelativeLayout mRelativeLayoutNfc;
	private RelativeLayout mRelativeLayoutAndroid10;
	private RelativeLayout mRelativeLayoutAppcircus;
	private RelativeLayout mRelativeLayoutSmartphoneday;
	private RelativeLayout mRelativeLayoutPush;
	private RelativeLayout mRelativeLayoutScrum;
	private RelativeLayout mRelativeLayoutMasterbranch;
	private RelativeLayout mRelativeLayoutSlideshare;
	
	public ShowcaseFragment() { super(); }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try {
			mOnShowcaseChosenListener = (OnShowcaseChosenListener)activity;
			
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString() + " must implement on OnShowcaseChosenListener");
		}		
	}	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.lyt_fragment_showcase, container, false);
		
		mRelativeLayoutGdg = (RelativeLayout)mView.findViewById(R.id.gdg);
		mRelativeLayoutGdg.setOnClickListener(gdgOnClickListener);
		
		mRelativeLayoutNfc = (RelativeLayout)mView.findViewById(R.id.nfc);
		mRelativeLayoutNfc.setOnClickListener(nfcOnClickListener);
		
		mRelativeLayoutAndroid10 = (RelativeLayout)mView.findViewById(R.id.android10);
		mRelativeLayoutAndroid10.setOnClickListener(android10OnClickListener);
		
		mRelativeLayoutAppcircus = (RelativeLayout)mView.findViewById(R.id.appcircus);
		mRelativeLayoutAppcircus.setOnClickListener(appcircusOnClickListener);
		
		mRelativeLayoutSmartphoneday = (RelativeLayout)mView.findViewById(R.id.smartphoneday);
		mRelativeLayoutSmartphoneday.setOnClickListener(smartphonedayOnClickListener);
		
		mRelativeLayoutPush = (RelativeLayout)mView.findViewById(R.id.push);
		mRelativeLayoutPush.setOnClickListener(pushOnClickListener);
		
		mRelativeLayoutScrum = (RelativeLayout)mView.findViewById(R.id.scrum);
		mRelativeLayoutScrum.setOnClickListener(scrumOnClickListener);
		
		mRelativeLayoutMasterbranch = (RelativeLayout)mView.findViewById(R.id.masterbranch);
		mRelativeLayoutMasterbranch.setOnClickListener(masterbranchOnClickListener);
		
		mRelativeLayoutSlideshare = (RelativeLayout)mView.findViewById(R.id.slideshare);
		mRelativeLayoutSlideshare.setOnClickListener(slideshareOnClickListener);		
		
		return mView;
	}
	
	private OnClickListener gdgOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnShowcaseChosenListener.onShowcaseGdgChosen();
		}
	};
	
	private OnClickListener nfcOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnShowcaseChosenListener.onShowcaseNfcChosen();
		}
	};
	
	private OnClickListener android10OnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnShowcaseChosenListener.onShowcaseAndroid10Chosen();
		}
	};
	
	private OnClickListener appcircusOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnShowcaseChosenListener.onShowcaseAppcircusChosen();
		}
	};
	
	private OnClickListener smartphonedayOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnShowcaseChosenListener.onShowcaseSmartphonedayChosen();
		}
	};
	
	private OnClickListener pushOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnShowcaseChosenListener.onShowcasePushChosen();
		}
	};
	
	private OnClickListener scrumOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnShowcaseChosenListener.onShowcaseScrumChosen();
		}
	};
	
	private OnClickListener masterbranchOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnShowcaseChosenListener.onShowcaseMasterbranchChosen();
		}
	};
	
	private OnClickListener slideshareOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mOnShowcaseChosenListener.onShowcaseSlideshareChosen();
		}
	};			
}
