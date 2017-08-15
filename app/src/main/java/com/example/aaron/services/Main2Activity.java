package com.example.aaron.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    MyBoundService myBoundService;
    TextView tvResults;
    int passedNumber;
    int result;
    Button btnGotoNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = new Intent();
        Intent boundIntent = new Intent(this, MyBoundService.class);

        String passedString = getIntent().getExtras().getString("data");
        Log.d("Activity2", "onCreate: " + passedString);
        passedNumber = Integer.parseInt(passedString);
        btnGotoNext = (Button)findViewById(R.id.btnGotoNextActivity);
        btnGotoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextIntent = new Intent(view.getContext(), Main3Activity.class);
                startActivity(nextIntent);
            }
        });
        bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        tvResults = (TextView)findViewById(R.id.tvTotal);

        //unbindService(serviceConnection);



    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("Activity2", "onServiceConnected: " + passedNumber);
            MyBoundService.MyBinder myBinder= (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            tvResults.setText(String.valueOf(myBoundService.addFive(passedNumber)));
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
