package com.example.testintent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Paul on 29/11/2016.
 */

public class testIntentBundle extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
    if (intent.getAction().equalsIgnoreCase("e"))
    {
        Bundle bundle = intent.getBundleExtra("w"+String.valueOf(1));
        String aa = bundle.getString("1");
        String bb = bundle.getString("2");
        Toast.makeText(context, aa+bb, Toast.LENGTH_SHORT).show();    }
    }
}
