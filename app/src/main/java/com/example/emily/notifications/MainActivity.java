package com.example.emily.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;

public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_ID = "com.emily.ANDROID";
    public static final int NOTIFY_ID = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();

            }
        });
    }

    private void createNotification(){
        Intent intent = new Intent(this, NotificationResultActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFY_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Sample Notification")
                .setContentText("This is a notification.")
                .setAutoCancel(true)
                .setSubText("Tap to View")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setChannelId(CHANNEL_ID)
                .setContentIntent(pendingIntent);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("This is a big notification");
        bigTextStyle.bigText(getResources().getString(R.string.longMsg));
        builder.setStyle(bigTextStyle);

        Intent notificaitonResult = new Intent(this, NotificationResultActivity.class);
        notificaitonResult.putExtra("notifiyID", NOTIFY_ID);

        builder.setVisibility(Notification.VISIBILITY_PUBLIC);

        Notification notification = builder.build();
        NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mgr.notify(NOTIFY_ID, notification);

    }
}
