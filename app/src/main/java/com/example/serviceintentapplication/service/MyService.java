package com.example.serviceintentapplication.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.serviceintentapplication.R;
import com.example.serviceintentapplication.activity.DetailsActivity;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Le service est commencé", Toast.LENGTH_SHORT).show();
      //  showNotification();
        showPendingNotification();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "Le service est detruit", Toast.LENGTH_SHORT).show();
    }


    public void showNotification() {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(getBaseContext(), "ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Message")
                .setContentText("Vous avez recu un nouveau message !")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("text long ..............."))
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .build();

        notificationManager.notify(0, notification);


    }

    public void showPendingNotification() {

        Intent intentNotif = new Intent(this, DetailsActivity.class);
        intentNotif.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


        PendingIntent notifyPendingIntent = PendingIntent.getActivity(this, 0, intentNotif, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(getBaseContext(), "ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Message")
                .setContentText("Vous avez recu un nouveau message !")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("text long ..............."))
                .setAutoCancel(true)
                .setContentIntent(notifyPendingIntent)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .build();

        notificationManager.notify(0, notification);


    }
}
