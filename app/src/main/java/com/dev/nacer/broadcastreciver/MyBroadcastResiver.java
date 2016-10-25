package com.dev.nacer.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by mednaceur on 22/10/2016.
 */
public class MyBroadcastResiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Intent Detected. i can launch any think here", Toast.LENGTH_LONG).show();
    }
}
