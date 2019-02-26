package com.example.serviceintentapplication.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import static com.example.serviceintentapplication.activity.ServiceIntentActivity.ACTION_SEND;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String msg = intent.getStringExtra("msg");
            Log.e("MEssage", msg);
            intent.setAction(ACTION_SEND);

            String messageRetour = msg + "\n" + "J'ai recu votre message";
            intent.putExtra("retourMessage", messageRetour);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

        }
    }

}
