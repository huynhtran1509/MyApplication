package com.example.paul.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;

import java.util.HashMap;

/**
 * Created by Paul on 17/08/2016.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter implements ExpandableListAdapter {

    private final SparseArray<Group> groups;
    public LayoutInflater inflater;
    public Activity activity;
    public Context context;

    public MyExpandableListAdapter(/*Context context,*/Activity act, SparseArray<Group> groups) {
        activity = act;
        this.groups = groups;
        inflater = act.getLayoutInflater();
//        this.context=context;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = (String) getChild(groupPosition, childPosition);
        TextView text = null;
        int id_res=0;
        if(childPosition == 0) id_res = R.mipmap.ic_arrow1;
        if(childPosition == 1) id_res = R.mipmap.ic_launcher;
        if(childPosition == 2) id_res = R.mipmap.ic_launcher_web;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_details, null);
        }
        text = (TextView) convertView.findViewById(R.id.textView1);
        text.setText(children);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView);
        imageView.setImageResource(id_res);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, children,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
//    HashMap<Long,Boolean> checkboxMap = new HashMap<Long,Boolean>();
//    public class CheckListener implements CompoundButton.OnCheckedChangeListener {
//
//        long pos;
//
//        public void setPosition(long p){
//            pos = p;
//        }
//        public String []check_string_array;
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView,
//                                     boolean isChecked) {
//            Log.i("checkListenerChanged", String.valueOf(pos)+":"+String.valueOf(isChecked));
//            checkboxMap.put(pos, isChecked);
//            if(isChecked == true) check_string_array[(int)pos] = "true";
//            else				  check_string_array[(int)pos] = "false";
//            // save checkbox state of each group
//            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
//            SharedPreferences.Editor preferencesEditor = settings.edit();
//            preferencesEditor.putString(String.valueOf((int)pos), check_string_array[(int)pos]);
//            preferencesEditor.commit();
//        }
//    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_group, null);
        }
        Group group = (Group) getGroup(groupPosition);
        long group_id = getGroupId(groupPosition);
        int id_res=0;
        if (groupPosition==1) id_res=R.drawable.check;
        ((CheckedTextView) convertView).setText(group.string);
        if (group_id==1)
        ((CheckedTextView) convertView).setChecked(true);
//        ((CheckedTextView) convertView).setEnabled(false);
//        ((TextView) convertView).setText(group.string);
//        ((CheckBox) convertView).setFocusable(false);
//        CheckListener checkL = new CheckListener();
//        checkL.setPosition(group_id);
//        ((CheckBox) convertView).setOnCheckedChangeListener(checkL);
//        ((CheckBox) convertView).setChecked(checkboxMap.get(group_id));

//// Get grouprow.xml file elements and set values
//        ((TextView) convertView.findViewById(R.id.text1)).setText(parent.getText1());
//        ((TextView) convertView.findViewById(R.id.text)).setText(parent.getText2());
//        ImageView image=(ImageView)convertView.findViewById(R.id.image);
//        image.setImageResource(getResources().getIdentifier("com.androidexample.customexpandablelist:drawable/setting"+parent.getName(),null,null));
//        ImageView rightcheck=(ImageView)convertView.findViewById(R.id.rightcheck);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
