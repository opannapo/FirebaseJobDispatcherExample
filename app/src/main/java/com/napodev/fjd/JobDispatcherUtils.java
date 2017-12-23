package com.napodev.fjd;

import com.firebase.jobdispatcher.JobTrigger;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by opannapo on 12/21/17.
 */

public class JobDispatcherUtils {
    public static JobTrigger periodicTrigger(int frequency, int tolerance) {
        return Trigger.executionWindow(frequency - tolerance, frequency);
    }
}
