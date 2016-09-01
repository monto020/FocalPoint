package com.example.montoya.focalpoint;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainMenuActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener{

    public final static String EXTRA_MESSAGE_ID="com.example.montoya.focalpoint.id";
    SQLiteDatabase database;
    GoogleApiClient mGoogleApiClient;
    int userId;
  //  AnimationDrawable frameAnimation;
    LoginScreen loginScreen=new LoginScreen();
    Button startFocusingButton;
    Button viewPerformanceButton;
    Button focusTips;
    Button about;
    Intent intent;
    ImageView wholeLogoImage;
     int id;
    @Override


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_menu);


        Intent intent=getIntent();
        id=intent.getIntExtra(loginScreen.EXTRA_MESSAGE_ID,0);
       // Toast.makeText(getApplicationContext(), String.valueOf(id),
         //       Toast.LENGTH_LONG).show();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

//        logoBlink.setImageResource(R.drawable.blink);
//        frameAnimation = (AnimationDrawable) logoBlink.getDrawable();
//        frameAnimation.start();

//        startFocusingButton=(ImageButton)findViewById(R.id.beginFocusing);
//      startFocusingButton.setImageResource(R.drawable.shapes_blink);//red mark not error
//      frameAnimation = (AnimationDrawable) startFocusingButton.getDrawable();
//        frameAnimation.start();

        startFocusingButton=(Button)findViewById(R.id.begin_focusing_button);
        viewPerformanceButton=(Button)findViewById(R.id.view_performance_button);
        focusTips=(Button)findViewById(R.id.focus_tips_button);
        about=(Button)findViewById(R.id.about_button);
        wholeLogoImage=(ImageView)findViewById(R.id.whole_logo2);
        wholeLogoImage.setImageResource(R.drawable.whole_logo);

    }

    public void onClickFocusTips(View v){
        Tip.populateTipsList();
        intent = new Intent(this, FocusTipsActivity.class);
        //intent.putExtra(EXTRA_MESSAGE_ID,userId);
        startActivity(intent);
    }

    public void onClickViewPerformance(View v){
        intent = new Intent(this, ViewPerformanceActivity.class);
        intent.putExtra(EXTRA_MESSAGE_ID, id);
        startActivity(intent);
    }

    public void onClickStartFocusing(View v){
        Toast.makeText(getApplicationContext(),"Opening Start Activity Page. Excuse the delay, the loading of background applications is slow.",
                Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"Opening Start Activity Page. Excuse the delay, the loading of background applications is slow.",
                Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"Opening Start Activity Page. Excuse the delay, the loading of background applications is slow.",
                Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"Opening Start Activity Page. Excuse the delay, the loading of background applications is slow.",
                Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"Opening Start Activity Page. Excuse the delay, the loading of background applications is slow.",
                Toast.LENGTH_LONG).show();
       // Toast.makeText(getApplicationContext(), "Main menu id: "+id ,
            //    Toast.LENGTH_LONG).show();
        intent = new Intent(this, StartFocusingActivity.class);
        intent.putExtra(EXTRA_MESSAGE_ID, id);
        startActivity(intent);
    }


    public void onClickAbout(View v){
        intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
          // LoginScreen.signOut();
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            // [START_EXCLUDE]
                            Toast.makeText(getApplicationContext(), "Signed Out! ",
                                    Toast.LENGTH_LONG).show();
                            finish();

                            // [END_EXCLUDE]
                        }
                    });
        }
        // [END signOut]


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
