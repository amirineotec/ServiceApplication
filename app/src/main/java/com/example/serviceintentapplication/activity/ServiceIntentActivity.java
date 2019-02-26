package com.example.serviceintentapplication.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.serviceintentapplication.R;
import com.example.serviceintentapplication.service.MyIntentService;

public class ServiceIntentActivity extends AppCompatActivity {

    EditText edtMsg;
    Button btnSend;
    TextView txtAffiche;
    MyReceiver myReceiver = null;
    public final static String ACTION_SEND = "send_msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_intent);
        setTitle("ServiceIntentActivity");
        edtMsg = findViewById(R.id.edtMsg);
        btnSend = findViewById(R.id.btnSend);
        txtAffiche = findViewById(R.id.txtAffiche);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = edtMsg.getText().toString();
                if (!TextUtils.isEmpty(msg))
                    sendMEssage(msg);
            }
        });
    }

    @Override
    protected void onStart() {
        configRecever();
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (myReceiver != null) {
                unregisterReceiver(myReceiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void configRecever() {

        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_SEND);
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, intentFilter);
    }

    public void sendMEssage(String msg) {

        Intent intent = new Intent(ServiceIntentActivity.this, MyIntentService.class);
        intent.putExtra("msg", msg);
        startService(intent);
        edtMsg.setText("");
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String retourMessage = intent.getStringExtra("retourMessage");
            txtAffiche.append(retourMessage + "\n");
        }
    }

}
