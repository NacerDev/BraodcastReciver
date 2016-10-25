package com.dev.nacer.broadcastreciver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mednaceur on 23/10/2016.
 */
public class NetworkStateReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo()!=null){
            notificationBuilder(context,"Network State Changed .. connected");
        }
        else{

            notificationBuilder(context,"Network State Changed ..Not connected");
        }
    }
    private void notificationBuilder(Context context,String state){
        // Instantiate a Builder object.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
// Creates an Intent for the Activity
        Intent notifyIntent =
                new Intent(context, MainActivity.class);
// Sets the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
// Creates the PendingIntent
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

// Puts the PendingIntent into the notification builder
        builder.setContentIntent(notifyPendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(state);
// Notifications are issued by sending them to the
// NotificationManager system service.
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification note = builder.build();
        note.defaults |= Notification.DEFAULT_VIBRATE;
        note.defaults |= Notification.DEFAULT_SOUND;
// Builds an anonymous Notification object from the builder, and
// passes it to the NotificationManager
        mNotificationManager.notify(123456, note);
    }
}
