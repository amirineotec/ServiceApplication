package com.example.serviceintentapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.serviceintentapplication.R;
import com.example.serviceintentapplication.service.MyService;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        setTitle("ServiceActivity");


    }

    public void startService(View view) {

        Intent intent = new Intent(ServiceActivity.this, MyService.class);
        startService(intent);

    }

    public void endServices(View view) {
        Intent intent = new Intent(ServiceActivity.this, MyService.class);
        stopService(intent);

    }
}
