package com.bitmesra.bitotsav.features.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.util.Log;
import android.widget.Toast;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.notification.NotificationItem;
import com.bitmesra.bitotsav.features.MainActivity;
import com.bitmesra.bitotsav.utils.Foreground;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Batdroid on 9/2/17 for Bitotsav.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        try {
            Log.d(TAG, remoteMessage.getData().toString() + "");
            Log.d(TAG, remoteMessage.getFrom() + "");
            Log.d(TAG, remoteMessage.getMessageId() + "");
            Log.d(TAG, remoteMessage.getSentTime() + "");
            NotificationItem item = new NotificationItem()
                    .setId(remoteMessage.getMessageId())
                    .setTime(remoteMessage.getSentTime())
                    .setFrom(remoteMessage.getFrom())
                    .setTitle(remoteMessage.getData().get("title"))
                    .setBody(remoteMessage.getData().get("body"));
            DataManager.getDataManager().getRealmManager().addNotificationItem(item);
            if (Foreground.get(this).isForeground()) {
                Observable.just(1)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(integer -> {
                            Toast.makeText(getApplicationContext(),
                                    "OH Hey! You got a new notification", Toast.LENGTH_SHORT).show();
                        });
            } else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                Notification notification = new Notification.Builder(this)
                        .setContentTitle(item.getTitle())
                        .setContentText(item.getBody())
                        .setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT))
                        .setSmallIcon(R.drawable.ic_home)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_logo))
                        .setAutoCancel(true)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .build();
                NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(100, notification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}