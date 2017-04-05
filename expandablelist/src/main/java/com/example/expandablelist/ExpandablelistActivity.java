package com.example.expandablelist;
 
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences; 
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class ExpandablelistActivity extends Activity {
	final Context context = this;
    private static final String[][] data = {{"audia4","audiq7","audir8"},{"bmwm6","bmwx6"},{"ferrarienzo","ferrarif430","ferrarif430italia"}};
    private ExpandableListView expandableListView;
    Button button1;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandablelist);
        button1 = (Button)findViewById(R.id.btn1);
        expandableListView = (ExpandableListView)findViewById(R.id.expandableListView1);
        expandableListView.setAdapter(new SampleExpandableListAdapter(context, this, data)); 
        
        button1.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub 
				SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);  
		    	String buffer = null;
		        String output_String = "";
		        for(int i=0; i<3; i++){
		        	buffer = settings.getString(String.valueOf((int)i),"false");
		        	if(buffer.equals("true"))
		        		output_String += "group " + i + " "; 
		        }
		        output_String += "is checked"; 
				Toast.makeText(ExpandablelistActivity.this, output_String, Toast.LENGTH_SHORT).show();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_expandablelist, menu);
        return true;
    }
    
}
