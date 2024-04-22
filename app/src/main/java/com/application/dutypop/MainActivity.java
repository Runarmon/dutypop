package com.application.dutypop;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    NotificationManagerCompat notificationManagerCompat;
    Notification notification;
    NotificationChannel channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("NotificationTest","onCreate");
        createNotificationBuilder();
        buildNotification();
        super.onCreate(savedInstanceState);
    }

    void buildNotification() {
        Log.d("NotificationTest","buildNotification");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "test")
                .setSmallIcon(R.drawable.isaac)
                .setContentTitle("Duty found!")
                .setContentText("Duty found2")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Duty duty duty ..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notification = builder.build();
        notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("NotificationTest","RETURN");
            return;
        }
        Log.d("NotificationTest","pushNotification");
        notificationManagerCompat.notify(1, notification);
    }


    void createNotificationBuilder(){
        Log.d("NotificationTest","creatingBuilder");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "name";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            channel = new NotificationChannel("test", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

