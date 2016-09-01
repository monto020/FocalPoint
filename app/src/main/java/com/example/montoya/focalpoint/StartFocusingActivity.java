package com.example.montoya.focalpoint;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
// You will need the following imports

import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class StartFocusingActivity extends ListActivity {

    public final static String EXTRA_MESSAGE_ID = "com.example.montoya.focalpoint.id";


    static ArrayList<String> blockedAppNamesList = new ArrayList<String>();
    static Boolean timerStopped=false;
    DestroyService destroyService=new DestroyService();
    ArrayList<Integer> idList = new ArrayList<Integer>();
    private int seekBarProgress = 0;
    static ArrayList<String> backgroundNameList = new ArrayList<>();
    // MySQLiteHelper mysqlite = new MySQLiteHelper(this);
    MySQLiteHelper mysqlite = new MySQLiteHelper(this);
    AppNameAdapter2 appNameAdapter2;
    AppName appName = new AppName();
    ArrayList<AppName> appNameArrayList = new ArrayList<AppName>();
    SeekBar timeSeekBar;
    TextView timeText;
    ImageView focusTimeImage;
    Button beginFocusingButton;
    CheckBox appCheckBox;
    //Context context;
    ProcessManager processManager;
    View view;

    static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_focusing);

        Intent intent = getIntent();
        id = intent.getIntExtra(MainMenuActivity.EXTRA_MESSAGE_ID, 0);

        focusTimeImage = (ImageView) findViewById(R.id.focus_time_image);
        focusTimeImage.setImageResource(R.drawable.focus_time_logo);


        loadList();

//        Toast.makeText(this, "Blocked list: "+ blockedAppNamesList,
//                Toast.LENGTH_LONG).show();

        ListView appNameListView = getListView();

        appNameAdapter2 = new AppNameAdapter2(this,R.layout.list_row ,appNameArrayList);
        appNameListView.setAdapter(appNameAdapter2);

        beginFocusingButton = (Button) findViewById(R.id.start_focusing_button);

           getSeekBarTme();

    }


    public void loadList() {

        PackageManager packageManager = getPackageManager();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);


        List<String> apps = null;
        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent, 0);
        Collections.sort(appList, new ResolveInfo.DisplayNameComparator(packageManager));
        List<ApplicationInfo> packs = packageManager.getInstalledApplications(0);

        //Boolean fix = null;

        blockedAppNamesList.clear();


//<<<<open from here
//        Cursor cursor=mysqlite.getBlockedApps(id);
//
//                     while( cursor.moveToNext()){
//                         blockedAppNamesList.add(cursor.getString(1));
//                         // do what ever you want here
//
//        }cursor.close();
//
//for(int i=0;i<ProcessManager.getRunningApps().size();i++){
//
//    backgroundNameList.add(ProcessManager.getRunningApps().get(i).name);
//
//}
//<<<<<<to here

        //for (int i = 0; i < blockedAppNamesList.size(); i++) {
        for (int i = 0; i < packs.size(); i++) {
 //          for (int i = 0; i <backgroundNameList.size(); i++) {

            //appNameArrayList.add(new AppName(blockedAppNamesList.get(i)));
          //appNameArrayList.add(new AppName(backgroundNameList.get(i)));
           appNameArrayList.add(new AppName(packageManager.getApplicationLabel(packs.get(i)).toString()));
           mysqlite.insertBlockedApp(id, packageManager.getApplicationLabel(packs.get(i)).toString(), 0);

        }//end for


    }




public void getSeekBarTme() {
    timeSeekBar = (SeekBar) findViewById(R.id.time_seek_bar);
    timeText = (TextView) findViewById(R.id.time_seekbar_text);


    timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        //seekBarProgress=0;
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            seekBarProgress = progress;

        }


        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            timeText.setText(("Focus Time: " + seekBarProgress + " /" + timeSeekBar.getMax()) + " min");
            beginFocusingButton.setText("Begin " + seekBarProgress + " Minute Focus Session! ");
        }

    });
}

    public void onClickTimeToFocus(final View view) {

        Toast.makeText(getApplicationContext(),"Time to work! You got this!!!",
                Toast.LENGTH_SHORT).show();

        Intent serviceIntent = new Intent(getBaseContext(), DestroyService.class);
        serviceIntent.setPackage("com.example.montoya.focalpoint");
      serviceIntent.putExtra("UserID", id);

        startService(serviceIntent);


       // Toast.makeText(this, "Blocked App List: " + blockedAppNamesList, Toast.LENGTH_LONG).show();

        Calendar c = Calendar.getInstance();
        final int currentMonth = c.get(Calendar.MONTH);
        final int currentYear = c.get(Calendar.YEAR);






        new CountDownTimer(seekBarProgress*60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {


            }


            @Override
            public void onFinish() {

                Toast.makeText(getApplicationContext(), "Success!!! Focus Session Complete.  ",
                        Toast.LENGTH_LONG).show();

                mysqlite.insertFocusSession(id, seekBarProgress, 1, currentMonth, currentYear);

                timerStopped=true;
                stopService(view);
            //destroyService.onDestroy();
            mysqlite.close();


              finish();
//                boolean sentAppToBackground =  (false);
           }
        }.start();


    }

    public void stopService(View view) {
//        Toast.makeText(getApplicationContext(), "Inside Stop",
//                Toast.LENGTH_LONG).show();

        stopService(new Intent(this, DestroyService.class));


    }

}






