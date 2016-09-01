package com.example.montoya.focalpoint;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageStats;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by Montoya on 5/14/2016.
 */
public class DestroyService extends android.app.Service {
    String foreground="";
    public static DestroyService instance;
    //List<ActivityManager.RunningAppProcessInfo> appProcesses=null;
   // List<ProcessManager.Process> backgroundNameList=new ArrayList<ProcessManager.Process>();

    MySQLiteHelper mysqlite=new MySQLiteHelper(this);
   // static int id;


    @Override
    public IBinder onBind(Intent intent) {


        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // TODO Auto-generated method stub
//       Toast.makeText(this, "Inside the destroy service", Toast.LENGTH_LONG).show();

        Cursor cursor = mysqlite.getBlockedApps(StartFocusingActivity.id);

        while (cursor.moveToNext()) {
            StartFocusingActivity.blockedAppNamesList.add(cursor.getString(1));
            // do what ever you want here

        }
        cursor.close();

        scheduleMethod();

            instance = this;

        return START_STICKY;
    }


    private void scheduleMethod() {

//        Toast.makeText(this, "Inside the schedule method",
//                Toast.LENGTH_LONG).show();


        // TODO Auto-generated method stub

        final Handler handler = new Handler();

        final int delay = 10000/2; //milliseconds


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

if(!StartFocusingActivity.timerStopped) {
    checkRunningApps();
}
else{
    stopSelf();
}
//                Toast.makeText(getApplicationContext() , "in the handler testing time and loops",
//                        Toast.LENGTH_SHORT).show();

                handler.postDelayed(this, delay);
            }
        }, delay);
    }


    public void checkRunningApps() {

        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();

        ActivityManager manager = (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> tasks = manager.getRunningAppProcesses();


//        Toast.makeText(this, "Right before checking the app on top",
//                Toast.LENGTH_LONG).show();

        String foreground=getForeGroundTasks();

        //

        String[] foreGroundProcessParts = foreground.split("\\.");
        List<String> foreGroundProcessPartsList = Arrays.asList(foreGroundProcessParts);
        String currentForeground=foreGroundProcessPartsList.get(foreGroundProcessPartsList.size() - 1);

                Toast.makeText(this, "Activity on top, should be the activity i opened " + currentForeground,
                Toast.LENGTH_SHORT).show();

//
//        Toast.makeText(this, "blocked list: "+StartFocusingActivity.blockedAppNamesList,
//                Toast.LENGTH_LONG).show();


       for (int i = 0; i < StartFocusingActivity.blockedAppNamesList.size(); i++) {
               if (StartFocusingActivity.blockedAppNamesList.get(i).toLowerCase().contains(currentForeground)) {
                   Toast.makeText(getApplicationContext(), "Focus!!! Stop Trying To Use: " + currentForeground,
                           Toast.LENGTH_LONG).show();

                   Intent lockIntent = new Intent(this, LockedActivity.class);
                  lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(lockIntent);

               }}}





    public String getForeGroundTasks(){


        //several functions to get foreground tasks are deprecated or don't work as they should, because of new android
        //security. This is a different approach to finding a foreground task. Only works in Lollipop and higher.
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager usm = (UsageStatsManager) this.getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,  time - 1000*1000, time);
            if (appList != null && appList.size() > 0) {
                SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                for (UsageStats usageStats : appList) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (mySortedMap != null && !mySortedMap.isEmpty()) {
                    foreground = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                }
            }
        } else {
            ActivityManager am = (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> tasks2 = am.getRunningAppProcesses();
            foreground = tasks2.get(0).processName;
        }
        return foreground;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        //Toast.makeText(this, "Inside On Service", Toast.LENGTH_LONG).show();

        instance.onUnbind(new Intent(DestroyService.this, StartFocusingActivity.class));
        instance.stopSelf();


        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

       }