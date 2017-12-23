package com.napodev.fjd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

public class MainActivity extends AppCompatActivity {
    private static final String JOB_TAG = "LOG_TASK";
    private FirebaseJobDispatcher mDispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        scheduleJob();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cancelJob(JOB_TAG);
    }

    private void scheduleJob() {
        Job myJob = mDispatcher.newJobBuilder()
                .setService(MyTask.class)
                .setTag(JOB_TAG)
                .setRecurring(true)
                //.setTrigger(JobDispatcherUtils.periodicTrigger(10, 1))
                .setTrigger(Trigger.executionWindow(5, 5))
                .setLifetime(Lifetime.FOREVER)
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .build();
        mDispatcher.mustSchedule(myJob);
        Toast.makeText(this, "Job Run", Toast.LENGTH_LONG).show();
    }

    private void cancelJob(String jobTag) {
        if ("".equals(jobTag)) {
            mDispatcher.cancelAll();
        } else {
            mDispatcher.cancel(jobTag);
        }
        Toast.makeText(this, "Task Stop", Toast.LENGTH_LONG).show();
    }
}
