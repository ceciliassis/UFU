package com.example.amandaspolti.todoapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Vinícius Resende on 06/12/2016.
 */

public class NotificationService extends Service {

    public static ArrayList<String> datas = new ArrayList<>();
    private Timer mTimer;
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Log.e("Log", "Running");
            notifiy();
        }
    };
    private NotificationManager notificationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mTimer.cancel();
        timerTask.cancel();

        Intent intent = new Intent("com.example.sample");
        intent.putExtra("yourvalue", "torestore");
        sendBroadcast(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mTimer = new Timer();
        mTimer.schedule(timerTask, 2000, 5 * 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void notifiy() {

        try {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            String ss = "";
            ss += day;
            ss += "/";
            ss += month + 1;
            ss += "/";
            ss += year;
            if(datas.size() > 0)
            for (String data: datas) {
                if (ss.equals(data)) {
                    datas.remove(ss);
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("RSSPullService");
                    Intent myIntent = new Intent(this, ListView.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, myIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
                    Context context = getApplicationContext();
                    Notification.Builder builder;
                    builder = new Notification.Builder(context)
                            .setContentTitle("Alerta!")
                            .setContentText("Tarefa para hoje")
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.notification);

                    Notification notification = builder.build();
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(1, notification);
                }
            }
      } catch(Exception e){
            e.printStackTrace();
        }
    }
}