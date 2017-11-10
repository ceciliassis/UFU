package com.example.amandaspolti.todoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Vinícius Resende on 06/12/2016.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Service stops", "ohh");
        context.startService(new Intent(context, NotificationService.class));
    }
}
