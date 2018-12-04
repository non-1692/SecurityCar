package com.securitycar.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.securitycar.MainActivity;
import com.securitycar.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity {

    private static final long SPLASH_SCREEN_DELAY = 3000;

    //private OSPermissionSubscriptionState status;
    private String userIdToken, pushToken = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //OneSignal
/*        status = OneSignal.getPermissionSubscriptionState();
        userIdToken = status.getSubscriptionStatus().getUserId();
        pushToken = status.getSubscriptionStatus().getPushToken();

        Log.d("OneSignal", "userIdToken: " + userIdToken);
        Log.d("OneSignal", "pushToken: " + pushToken);*/

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_screen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent().setClass(SplashScreenActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
