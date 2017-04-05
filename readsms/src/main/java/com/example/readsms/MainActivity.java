package com.example.readsms;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readSms();
                readSms2();
            }
        });
    }
    public void readSms()
    {
        Uri mSmsinboxQueryUri = Uri.parse("content://sms");
//        Uri mSmsinboxQueryUri = Uri.parse("content://sms/inbox");
//        Uri mSmsinboxQueryUri = Uri.parse("content://sms/sent");
        Cursor cursor1 = getContentResolver().query(
                mSmsinboxQueryUri,
                new String[] { "_id", "thread_id", "address", "person", "date",
                        "body", "type" }, null, null, null);
//        startManagingCursor(cursor1);
        String[] columns = new String[] { "address", "person", "date", "body",
                "type" };
        if (cursor1.getCount() > 0) {
            String count = Integer.toString(cursor1.getCount());
            Log.e("Count",count);
            while (cursor1.moveToNext()) {
//                out.write("<message>");
                String address = cursor1.getString(cursor1
                        .getColumnIndex(columns[0]));
                String name = cursor1.getString(cursor1
                        .getColumnIndex(columns[1]));
                String date = cursor1.getString(cursor1
                        .getColumnIndex(columns[2]));
                String msg = cursor1.getString(cursor1
                        .getColumnIndex(columns[3]));
                String type = cursor1.getString(cursor1
                        .getColumnIndex(columns[4]));
            }
        }
    }

    public void readSms2()
    {
        Cursor c = getContentResolver().query(Uri.parse("content://sms/"),null,null,null, null);
//        startManagingCursor(c);

        int smsEntriesCount = c.getCount();

        String[] body = new String[smsEntriesCount];
        String[] number = new String[smsEntriesCount];

        if (c.moveToFirst())
        {
            for (int i = 0; i < smsEntriesCount; i++)
            {
                body[i] = c.getString(c.getColumnIndexOrThrow("body")).toString();
                number[i] = c.getString(c.getColumnIndexOrThrow("address")).toString();
                c.moveToNext();
            }
        }
        c.close();
    }
}
