package com.example.montoya.focalpoint;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.*;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class LoginScreen extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener {
    //    Toast.makeText(getApplicationContext(), "Welcome " + acct.getDisplayName(),
//    Toast.LENGTH_LONG).show();
  //  static MySQLiteHelper mysqlite;
    public final static String EXTRA_MESSAGE_ID="com.example.montoya.focalpoint.id";
    MySQLiteHelper mysqlite=new MySQLiteHelper(this);
    private SQLiteDatabase db;

    private static final int RC_SIGN_IN = 0;

    GoogleApiClient mGoogleApiClient;
    TextView textView;
    ImageView foc;
    AnimationDrawable frameAnimation;
    Intent intent;
    //private int userId=0;
    private static boolean devMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (devMode)
        {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();
    //    mysqlite=new MySQLiteHelper(this);
        //database=MySQLiteHelper.getInstance(this);


        //db=mysqlite.getWritableDatabase();

        findViewById(R.id.sign_in_button).setOnClickListener(this);

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        ///// google button details
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());
        signInButton.setColorScheme(SignInButton.COLOR_DARK);

        ///////images for page
        foc = (ImageView) findViewById(R.id.foc);
        ImageView logoBlink = (ImageView) findViewById(R.id.al_or_us);
        ImageView point = (ImageView) findViewById(R.id.point);

        foc.setImageResource(R.drawable.focal_point_foc_png);

        logoBlink.setImageResource(R.drawable.blink);
        frameAnimation = (AnimationDrawable) logoBlink.getDrawable();
        frameAnimation.start();
        point.setImageResource(R.drawable.focal_point_point_png);


    }

    @Override
    public void onClick(View v) {

        signIn();

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("log", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
         Toast.makeText(getApplicationContext(), "Welcome " + acct.getDisplayName(),
                 Toast.LENGTH_LONG).show();

//            String idString=acct.getId();

            mysqlite.insertUser(acct.getEmail(), "null");//
           // String n=""
           int userId= mysqlite.getUserId(acct.getEmail()).getInt(0);//

            //n=mysqlite.getUser(2).getString(1);
//            Toast.makeText(getApplicationContext(), "User : "+userId,
//                   Toast.LENGTH_LONG).show();
            String rows="";

            mysqlite.close();
            Intent intent = new Intent(this, MainMenuActivity.class);
            intent.putExtra(EXTRA_MESSAGE_ID,userId);
            startActivity(intent);

        } else {
            // Signed out, show unauthenticated UI.

            finish();
            // updateUI(false);
        }
    }

    // [START signOut]


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d("TAG", "onConnectionFailed:" + connectionResult);
    }



    @Override
    public void onBackPressed() {
        signOut();
    }


    public void signOut() {
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

}
