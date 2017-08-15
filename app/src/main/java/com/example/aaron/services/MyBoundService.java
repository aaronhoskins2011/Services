package com.example.aaron.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyBoundService extends Service {
    private static final String  TAG = "MyBoundSer";
    IBinder iBinder = new MyBinder();

    public MyBoundService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "onBind: ");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {

        public MyBoundService getService(){
            return MyBoundService.this;
        }
    }

    public int getRandomData(){
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(20);
    }
    public int addFive(int passedNumber){
        Log.d(TAG, "addFive: Passed Number is " + passedNumber);
        return passedNumber + 5;
    }
    public String generateRandomStrings(){

            Random generator = new Random();
            StringBuilder randomStringBuilder = new StringBuilder();
            int randomLength = generator.nextInt(10);
            char tempChar;
            for (int j = 0; j < randomLength; j++){
                tempChar = (char) (generator.nextInt(96) + 32);
                randomStringBuilder.append(tempChar);
            }
            Log.d(TAG, "generateRandomStrings: " + randomStringBuilder.toString());




        return  randomStringBuilder.toString();
    }
}
