package com.example.notificationcompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Use Material Design them if API 23 or later; Holo Light if earlier
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            dialogTheme = AlertDialog.THEME_HOLO_LIGHT;  // Deprecated with API 23
//        } else {
//            dialogTheme = R.style.MyDialogTheme;
//        }

        //notifyBefore();
        //notifyOneBefore();
         notificationBeforeToday();
         notificationBeforeToday();
        notify2();

    }
    int me=1;
    public void notify2()
    {
        NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("New Message");
        mBuilder.setContentText("You've received new message.");
        mBuilder.setTicker("New Message Alert!");
        mBuilder.setSubText("assdasdas");
        mBuilder.setSmallIcon(android.R.drawable.stat_sys_warning);
        mBuilder.setAutoCancel(true);
//        NotificationCompat.BigTextStyle inboxStyle = new NotificationCompat.BigTextStyle();

//        mBuilder.setWhen((int)System.currentTimeMillis()+11000);
        Intent resultIntent = new Intent(this, Main2Activity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Main2Activity.class);
   /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

//        mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText("asdasssssssssssssss\nsssssssssssssssssssssssssssss"));
        mBuilder.addAction(new NotificationCompat.Action(R.drawable.ic_access_alarms_black_24dp,"RemoteViews",resultPendingIntent));

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder.setShowWhen(true);
        mNotificationManager.notify(1, mBuilder.build());
    }
    public void notificationBeforeToday()
    {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Intent actionIntent=new Intent(context, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Main2Activity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pending_intent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//        PendingIntent actionNotifyPending = PendingIntent.getActivity(context, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this);
        mNotifyBuilder.setSmallIcon(android.R.drawable.stat_sys_warning);
        mNotifyBuilder.setTicker("You are Task in CrastiNot");
        mNotifyBuilder.setContentTitle("Task will need to do.");
        mNotifyBuilder.setContentText("In minutes You will have a Task need to do.");
        mNotifyBuilder.setSubText("Click View Detail Task");
        mNotifyBuilder.setAutoCancel(true);
//        mNotifyBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        mNotifyBuilder.setVibrate(new long[]{1000,2000});
        mNotifyBuilder.setLights(Color.BLUE,400,400);


        //set context for a task Today
        NotificationCompat.BigTextStyle bigTextStyle=new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("Context task: ");
        bigTextStyle.setSummaryText("aaaaaaaaa");
        bigTextStyle.bigText("bbbbbb");
        mNotifyBuilder.setStyle(bigTextStyle);
//        mNotifyBuilder.addAction(R.drawable.ic_action_home,"Open",actionNotifyPending);
//        mNotifyBuilder.addAction(new NotificationCompat.Action(R.drawable.ic_access_alarms_black_24dp,"RemoteViews",resultPendingIntent));


//        if (Boolean.parseBoolean(timeNotification[8])) {
//            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
//            mNotifyBuilder.setSound(sound);
//        }
        mNotifyBuilder.setContentIntent(pending_intent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify((int) System.currentTimeMillis(), mNotifyBuilder.build());
        }
    public void notifyBefore() {
        NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("New Message");
        mBuilder.setContentText("You've received new message.");
        mBuilder.setTicker("New Message Alert!");
        mBuilder.setSmallIcon(android.R.drawable.stat_sys_warning);
        mBuilder.setAutoCancel(true);
//        mBuilder.setProgress(100,30,false);
//        mBuilder.setContentInfo("asd");


//        mBuilder.setOnlyAlertOnce(false);
//        mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
//mBuilder.setDefaults(1);
   /* Increase notification number every time a new notification arrives */
        mBuilder.setNumber(me++);

   /* Add Big View Specific Configuration */
//        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        NotificationCompat.BigTextStyle inboxStyle = new NotificationCompat.BigTextStyle();

        String[] events = new String[6];
        events[0] = new String("This is first line11111111111111111\\n 111111111111111111111111111111....");
        events[1] = new String("This is second line...");
        events[2] = new String("This is third line.1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111..");
        events[3] = new String("This is 4th line...");
        events[4] = new String("This is 5th line...");
        events[5] = new String("This is 6th line...");

        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("Big Title Details:");

        // Moves events into the big view
        for (int i=0; i < events.length; i++) {
            inboxStyle.bigText(events[2]);
//            inboxStyle.addLine(events[2]);
        }
        mBuilder.setStyle(inboxStyle);

   /* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(this, Main2Activity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Main2Activity.class);
   /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

//        RemoteViews removeWidget = new RemoteViews(getPackageName(), R.layout.activity_main2);
//        removeWidget.setOnClickPendingIntent(R.id.button2, resultPendingIntent);
        mBuilder.setColor(255);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

   /* notificationID allows you to update the notification later on. */
        mNotificationManager.notify(0, mBuilder.build());
//        mBuilder.setDeleteIntent(resultPendingIntent);
    }

//    public void notifyBefore(Context context) {
////        a=listAll.get(1).getString(8);
//        Intent intent = new Intent(context, DetailForNotification.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        pending_intent = PendingIntent.getActivity(context, 0,
//                intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Notification notifiBuilder = new Notification.Builder(context)
////                .setSound(sound)
//                .setSmallIcon(android.R.drawable.stat_sys_warning/*R.drawable.ic_stat_notification*/)
//                .setAutoCancel(true)
//                .setTicker("You are Task in CrastiNot")
//                .setContentTitle("Task will need to do.")
//                .setContentText("In " + Helper.getDataForNotification()[2] + " minutes You will have a Task need to do.")
//                .setSubText("Click View Detail Task")
//
//                .setContentIntent(pending_intent).getNotification();
//        Notification.InboxStyle aa=new Notification.InboxStyle();
//        notifiBuilder.flags=Notification.FLAG_ONLY_ALERT_ONCE;
//        if (Boolean.parseBoolean(timeNotification[8])) {
//            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
//            notifiBuilder.sound = sound;
//        }
//        notifiBuilder.flags = Notification.FLAG_AUTO_CANCEL;
//        NotificationManager notificationManagerm = (NotificationManager) context.getSystemService(
//                context.NOTIFICATION_SERVICE);
//        notificationManagerm.notify((int) System.currentTimeMillis(), notifiBuilder);
//    }

    public void notifyOneBefore() {
//        a=listAll.get(1).getString(8);
        Intent intent = new Intent(this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pending_intent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(this);
//                .setSound(sound)
        notifiBuilder.setSmallIcon(android.R.drawable.stat_sys_warning/*R.drawable.ic_stat_notification*/)
                .setAutoCancel(true)
                .setTicker("You are Task in CrastiNot")
                .setContentTitle("Task will need to do.")
                .setContentText("In a minutes You will have a Task need to do.")
                .setSubText("Click View Detail Task")
                .setNumber(++me)
                .setColor(120)
                .setContentIntent(pending_intent).getNotification();

        notifiBuilder.setVisibility(1);
//        notifiBuilder.setDefaults(1000);
        NotificationManager notificationManagerm = (NotificationManager) this.getSystemService(
                this.NOTIFICATION_SERVICE);
        notificationManagerm.notify((int) System.currentTimeMillis(), notifiBuilder.build());
    }
}
