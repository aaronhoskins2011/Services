package com.example.aaron.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private static final String TAG = "act3";
    Intent playerIntent;
    MyBoundService myBoundService;
    List<String> stringList;
    RecyclerView rvRandomList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    RandomListAdaptor rdmListAdaptor;
    int n;
    boolean isSet = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        playerIntent = new Intent(this, MyService.class);
        startService(playerIntent);
        Intent boundIntent = new Intent(this, MyBoundService.class);
        bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);




    }

    @Override
    protected void onStop() {
        super.onStop();
       // stopService(playerIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(playerIntent);
    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder= (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            Log.d(TAG, "onServiceConnected: " + myBoundService.getRandomData());
            n = myBoundService.getRandomData();
            stringList = new ArrayList<>();
           String newString;
            for (int i = 0 ; i < n ; i++){
               // Log.d(TAG, "onServiceConnected: String " + myBoundService.generateRandomStrings() + " added");
               newString = myBoundService.generateRandomStrings();
                stringList.add(newString);

            }

            rdmListAdaptor = new RandomListAdaptor(stringList);
            rvRandomList = (RecyclerView)findViewById(R.id.rvRandomLIst);
            rvRandomList.setLayoutManager(layoutManager);
            rvRandomList.setItemAnimator(itemAnimator);
            rvRandomList.setAdapter(rdmListAdaptor);


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");


        }
    };
}
