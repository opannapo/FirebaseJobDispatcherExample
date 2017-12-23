package com.napodev.fjd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by opannapo on 12/23/17.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BootReceiver", "DEVICE REBOOT!!!!");
        context.startService(new Intent(context, MyTask.class));
    }
}
