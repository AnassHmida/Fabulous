package com.example.Fabulous.Utils.Alarms;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.util.Log;

import com.example.Fabulous.Utils.util;
import com.google.gson.JsonElement;

import io.paperdb.Paper;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.example.Fabulous.Utils.util.mypreference;

public class RefreshUserTokenID extends BroadcastReceiver

{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();
        SharedPreferences prefs = context.getSharedPreferences(mypreference, MODE_PRIVATE);
        String user = prefs.getString(util.user, "0");
        String password = prefs.getString(util.password, "0");
     //   loadLoginData(user,password);
        wl.release();
    }

    public void setAlarm(Context context,Integer timeinmilli)
    {
        Log.d(TAG, "setAlarm: +"+String.valueOf(timeinmilli));
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, RefreshUserTokenID.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), timeinmilli, pi);
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, RefreshUserTokenID.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
    public void successfulLogin(JsonElement loginResponseModel) {
        // TODO: show UI or navigate to other activity from here e.g.
        Paper.book().write(util.bookkey, loginResponseModel);
    }

    /**
     * In case of an error , the show error function is called , to reveal a bottom sheet that contains an error message.
     * **/



/*
    public void loadLoginData(String email, String password) {

      LoginClient.getInstance().getLoginResponse(new User(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonElement>() {

                    @Override
                    public void onNext(JsonElement loginResponseModel) {
                        if(loginResponseModel != null){

                            String id =   ProfileDetailsParser.getIDfromResponse((loginResponseModel));
                            Log.d(TAG, "onNext: "+String.valueOf(id));
                            if(id != null|| !id.isEmpty()){
                                successfulLogin(loginResponseModel);

                            }else{
//fail
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //fail

                    }


                });

    }*/
}