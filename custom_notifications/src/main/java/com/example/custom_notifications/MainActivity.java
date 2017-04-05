package com.example.custom_notifications;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn;
    String a;
        ViewHolder viewHolder;
    List<Drawable> drawables;
    List<ApplicationInfo> tempList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        a="aassssssss";
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("ass");
//                intent.putExtra("aa",a);
                sendBroadcast(intent);


                PackageManager gl_PackManger = getPackageManager();
                tempList = gl_PackManger.getInstalledApplications(PackageManager.GET_META_DATA);
                drawables = new ArrayList<>();
                for (ApplicationInfo appInfo : tempList) {
                    drawables.add(appInfo.loadIcon(gl_PackManger));
                }
        viewHolder =new ViewHolder();
//        viewHolder.name=(TextView)findViewById(R.id.)
                viewHolder.textView3 = (TextView) findViewById(R.id.textView3);
                viewHolder.imageLayout = (LinearLayout) findViewById(R.id.imageLayout);

                for (int i = 0; i < 3; i++) {
                    ImageView image = new ImageView(getBaseContext());
                    image.setImageDrawable(drawables.get(i));
                    int width = 24;
                    int height = 24;

//            if (3 * 50 > gl_display.x) {
//                width = gl_display.x / 3 - 5;
//            }

                    LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width, height);
                    image.setLayoutParams(parms);
//
                    viewHolder.imageLayout.addView(image);
                }
                v.setTag(viewHolder);
            }

        });


    }


    protected class ViewHolder {
//        private TextView name;
//        private ImageView icon;
        private TextView textView3;
        private LinearLayout imageLayout;
    }

}
