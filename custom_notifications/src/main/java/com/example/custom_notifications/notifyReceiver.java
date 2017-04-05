package com.example.custom_notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 02/11/2016.
 */

public class notifyReceiver extends BroadcastReceiver {

    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private int notification_id;
    private RemoteViews remoteViews;

    List<Drawable> drawables;
    List<ApplicationInfo> tempList;
View view;
    ViewHolder viewHolder;
    ImageView image;
    @Override
    public void onReceive(Context context, Intent intent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.custom_notify, null);

        if (intent.getAction().equals("ass")) {
            PackageManager gl_PackManger = context.getPackageManager();
            tempList = gl_PackManger.getInstalledApplications(PackageManager.GET_META_DATA);
            drawables = new ArrayList<>();
            for (ApplicationInfo appInfo : tempList) {
                drawables.add(appInfo.loadIcon(gl_PackManger));
            }
            viewHolder =new ViewHolder();
            viewHolder.imageLayout = (LinearLayout) view.findViewById(R.id.imageLayout2);

            for (int i = 0; i < 3; i++) {
                image = new ImageView(context);
                image.setImageDrawable(drawables.get(i));
                int width = 24;
                int height = 24;
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width, height);
                image.setLayoutParams(parms);
                viewHolder.imageLayout.addView(image);
            }
            view.setTag(viewHolder);

//            PackageManager gl_PackManger = context.getPackageManager();
//            tempList = gl_PackManger.getInstalledApplications(PackageManager.GET_META_DATA);
//            drawables = new ArrayList<>();
//            for (ApplicationInfo appInfo : tempList) {
//                drawables.add(appInfo.loadIcon(gl_PackManger));
//            }
//            for (int i = 0; i < 3; i++) {
//                ImageView image = new ImageView(context);
//                image.setImageDrawable(drawables.get(i));
//                int width = 24;
//                int height = 24;
//                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width, height);
//                image.setLayoutParams(parms);
//            }
            notify_cus(context);
        }

    }

protected class ViewHolder {
    //        private TextView name;
//        private ImageView icon;
//    private TextView textView3;
    private LinearLayout imageLayout;
}
    private void notify_cus(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context);

        remoteViews = new RemoteViews(context.getPackageName(),R.layout.custom_notify);
        remoteViews.setImageViewResource(R.id.imageView,R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.textView2,"TEXT");
//        remoteViews.setProgressBar(R.id.progressBar,100,40,true);


        notification_id = (int) System.currentTimeMillis();

        Intent button_intent = new Intent("button_click");
        button_intent.putExtra("id",notification_id);
        Intent notification_intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notification_intent,0);

//remoteViews.setDisplayedChild(R.id.imageLayout2,);
//        remoteViews.setRemoteAdapter(R.id.imageLayout2,null);
//        remoteViews.reapply(context,view);

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("how are you!")
                .setContentText("I'm Hungry")
                .setAutoCancel(true)
                .setCustomBigContentView(remoteViews)
//                        .setCustomContentView(remoteViews)
//                        .setCustomHeadsUpContentView(remoteViews)
                .setUsesChronometer(true)
                .setContentIntent(pendingIntent);
        notificationManager.notify(notification_id,builder.build());

    }
}
