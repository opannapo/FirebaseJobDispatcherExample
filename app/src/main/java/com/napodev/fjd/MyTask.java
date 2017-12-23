package com.napodev.fjd;

import android.util.Log;
import android.widget.Toast;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by opannapo on 12/21/17.
 */

public class MyTask extends JobService {
    private static final String TAG = "fjd.MyTask";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy H:m:s");
        Date netDate = (new Date(System.currentTimeMillis()));
        Log.d(TAG, "TASK onStartJob :: " + sdf.format(netDate));
        Toast.makeText(this, "TASK Running", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "TASK onStopJob!");
        return false;
    }
}
