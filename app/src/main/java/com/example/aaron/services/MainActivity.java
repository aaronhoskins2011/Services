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

public class MainActivity extends AppCompatActivity {
    MyBoundService myBoundService;
    public static String TAG = "MAin";
    boolean isBound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void startService(View view) {
        Intent normalIntent = new Intent(this , MyNormalService.class);
        Intent intentService = new Intent(this, MyIntentService.class);
        Intent boundIntent = new Intent(this,MyBoundService.class);
        switch(view.getId()){
            case R.id.btnStartNormalService:
                normalIntent.putExtra("data","This is a normal Service");
                startService(normalIntent);
                break;
            case R.id.btnStopNormalService:
                stopService(normalIntent);
                break;
            case R.id.btnStartIntentService:
                intentService.putExtra("data", "This is a intent service profile");
                intentService.setAction("repo");
                startService(intentService);
                break;
            case R.id.btnStartIntentServicePro:
                intentService.putExtra("data", "This is a intent service profile");
                intentService.setAction("profile");
                startService(intentService);
                break;
            case R.id.btnBindService:
                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);

                break;
            case R.id.btnUnbindService:
                if(isBound) {
                    unbindService(serviceConnection);
                    isBound = false;
                }
                break;
            case R.id.btnGotoNextActivity:
                final String i = "23";
                Intent intent = new Intent(this, Main2Activity.class);
                intent.putExtra("data", i);
                startActivity(intent);
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder= (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            Log.d(TAG, "onServiceConnected: " + myBoundService.getRandomData());
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound = false;

        }
    };
}
