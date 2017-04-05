package com.pechhetti.expandablelistdemo;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author SACHIN PECHHETTI
 */
public class ExpListAdapter extends BaseExpandableListAdapter {

	Context mContext;
	String[] mArrGroupelements;
	String[][] mArrChildelements;

	public ExpListAdapter(Context context, String[] mArrGroupelements, String[][] mArrChildelements) {
		mContext = context;
		this.mArrGroupelements = mArrGroupelements;
		this.mArrChildelements = mArrChildelements;
	}

	@Override
	public int getGroupCount() {
		return mArrGroupelements.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mArrChildelements[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupPosition;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		TextView tv = (TextView) convertView;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			tv = (TextView) inflater.inflate(R.layout.list_cell, null);
		}
		tv.setText(mArrGroupelements[groupPosition]);
		convertView = tv;
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		TextView tv = (TextView) convertView;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			tv = (TextView) inflater.inflate(R.layout.list_cell, null);
		}
		tv.setText(mArrChildelements[groupPosition][childPosition]);
		convertView = tv;
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
