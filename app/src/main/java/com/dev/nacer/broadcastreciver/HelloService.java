package com.dev.nacer.broadcastreciver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mednaceur on 22/10/2016.
 */
public class HelloService extends Service {
    /** indicates how to behave if the service is killed */
    int mStartMode;

    /** interface for clients that bind */
    IBinder mBinder;

    /** indicates whether onRebind should be used */
    boolean mAllowRebind;

    /** Called when the service is being created. */
    @Override
    public void onCreate() {

        Toast.makeText(this, "Service onCreate", Toast.LENGTH_LONG).show();
    }

    /** The service is starting, due to a call to startService() */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                            try {
                                // Sleep for 5 seconds
                                Thread.sleep(5*1000);
                            } catch (InterruptedException e) {
                                Log.d("SERVICE", "sleep failure");
                            }
                             notificationBuilder();
                    }
                }).start();
       // notificationBuilder();

        return START_STICKY;
    }

    private void notificationBuilder(){
    // Instantiate a Builder object.
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
// Creates an Intent for the Activity
    Intent notifyIntent =
            new Intent(this, MainActivity.class);
// Sets the Activity to start in a new, empty task
    notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
// Creates the PendingIntent
    PendingIntent notifyPendingIntent =
            PendingIntent.getActivity(
                    this,
                    0,
                    notifyIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );

// Puts the PendingIntent into the notification builder
    builder.setContentIntent(notifyPendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Service Started");
// Notifications are issued by sending them to the
// NotificationManager system service.
    NotificationManager mNotificationManager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// Builds an anonymous Notification object from the builder, and
// passes it to the NotificationManager
    mNotificationManager.notify(123456, builder.build());
}

    /** A client is binding to the service with bindService() */
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Service onBind", Toast.LENGTH_LONG).show();
        return mBinder;

    }

    /** Called when all clients have unbound with unbindService() */
    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "Service onUnbind", Toast.LENGTH_LONG).show();
        return mAllowRebind;
    }

    /** Called when a client is binding to the service with bindService()*/
    @Override
    public void onRebind(Intent intent) {
        Toast.makeText(this, "Service onRebind", Toast.LENGTH_LONG).show();

    }

    /** Called when The service is no longer used and is being destroyed */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
