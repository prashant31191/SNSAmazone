package com.snsamazone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;
import com.snsamazone.demo2.MainActivity;
import com.utils.AppFlags;
public class ActSplash extends Activity
{
    private static int TIME = 5000;
    String TAG = "==ActSplacescreen==";
    App app;

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mFirebaseAuth;

    private TextView splashText;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        try {
            app = (App) getApplicationContext();
            App.showLog(TAG);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_splash);

            splashText = (TextView) findViewById(R.id.splashText);

            // Initialize FirebaseAuth
            mFirebaseAuth = FirebaseAuth.getInstance();
            // Obtain the FirebaseAnalytics instance.
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


            String refreshedToken = App.sharePrefrences.getStringPref(AppFlags.PREF_KEY_DEVICE_TOKEN);
            App.sharePrefrences.setPref(AppFlags.PREF_KEY_USER_ID, "0");

            App.showLog("====refreshedToken===device token===" + refreshedToken);
            if (refreshedToken != null && refreshedToken.length() > 5) {
                App.sharePrefrences.setPref(AppFlags.PREF_KEY_DEVICE_TOKEN, refreshedToken);
                TIME = 3000;
            } else {
                getDeviceToken();
            }

            setSendDataAnalytics();
            setSendCrashData();
            displaySplash();

            App.getInstance().trackScreenView(getResources().getString(R.string.scrn_ActSplash));

            splashText.setTypeface(App.getFont_Bold());

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }


    // Get Device token - Function
    private void getDeviceToken() {
        try {

            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Log.d(TAG, "Refreshed token: " + refreshedToken);
            // [START subscribe_topics]
            //FirebaseMessaging.getInstance().subscribeToTopic("news");
            Log.d(TAG, "Subscribed to news topic");
            // [END subscribe_topics]
            Log.d(TAG, "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());

            if (refreshedToken != null && refreshedToken.length() > 5) {
                App.sharePrefrences.setPref(AppFlags.PREF_KEY_DEVICE_TOKEN, refreshedToken);

                TIME = 3000;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // splash screen set with timing
    private void displaySplash() {
        // TODO Auto-generated method stub b

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub

                Intent iv;
                if (App.sharePrefrences.getStringPref(AppFlags.PREF_KEY_IS_LOGIN).equalsIgnoreCase("1")) {
                    iv = new Intent(ActSplash.this, MainActivity.class);
                } else {
                    iv = new Intent(ActSplash.this, MainActivity.class);
                }
                startActivity(iv);
                finish();

            }
        }, TIME);
    }

    private void setSendDataAnalytics() {
        try {
            Log.d(TAG, "---FirebaseAnalytics.Event.SELECT_CONTENT------");
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "111");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Prince");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            Log.d(TAG, "---FirebaseAnalytics.Event.SHARE------");
            Bundle bundle2 = new Bundle();
            bundle2.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "prince article");
            bundle2.putString(FirebaseAnalytics.Param.ITEM_ID, "p786");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);
        } catch (Exception e) {
            Log.d(TAG, "---setSendDataAnalytics-Error send analytics--");
            e.printStackTrace();
        }
    }

    private void setSendCrashData() {
        Log.d(TAG, "---setSendCrashData--");
        FirebaseCrash.logcat(Log.ERROR, TAG, "crash caused");
        FirebaseCrash.report(new Exception("My first Android non-fatal error"));
        FirebaseCrash.log("Activity created");

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id_a311");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name_prince");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }


}
