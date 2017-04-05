package com.pechhetti.expandablelistdemo;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ExpandableListView;

/**
 * @author SACHIN PECHHETTI
 */
public class ExpListActivity extends ExpandableListActivity {

	private ExpandableListView mExpList;
	private ExpListAdapter mExpListAdapter;

	final String mArrGroupelements[] = { "India", "Australia", "England", "South Africa" };
	final String mArrChildelements[][] = { { "Sachin Tendulkar", "Raina", "Dhoni", "Yuvi" },
			{ "Ponting", "Adam Gilchrist", "Michael Clarke" },
			{ "Andrew Strauss", "kevin Peterson", "Nasser Hussain" },
			{ "Graeme Smith", "AB de villiers", "Jacques Kallis" } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitt_explist);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;

		mExpList = getExpandableListView();
		//this code for adjusting the group indicator into right side of the view
		mExpList.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));
		mExpListAdapter = new ExpListAdapter(this, mArrGroupelements, mArrChildelements);
		mExpList.setAdapter(mExpListAdapter);
	}

	public int GetPixelFromDips(float pixels) {
		// Get the screen's density scale 
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}
}
