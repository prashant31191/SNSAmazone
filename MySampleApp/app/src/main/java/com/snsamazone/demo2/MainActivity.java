//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.18
//
package com.snsamazone.demo2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobilehelper.auth.IdentityManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.snsamazone.App;
import com.snsamazone.R;
import com.snsamazone.demo.DemoConfiguration;
import com.snsamazone.demo.HomeDemoFragment;
import com.snsamazone.navigation.NavigationDrawer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /** Class name for log messages. */
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String TAG = MainActivity.class.getSimpleName();

    /** Bundle key for saving/restoring the toolbar title. */
    private static final String BUNDLE_KEY_TOOLBAR_TITLE = "title";

    /** The identity manager used to keep track of the current user account. */
    private IdentityManager identityManager;

    /** The toolbar view control. */
    private Toolbar toolbar;

    /** Our navigation drawer class for handling navigation drawer logic. */
    private NavigationDrawer navigationDrawer;

    /** The helper class used to toggle the left navigation drawer open and closed. */
    private ActionBarDrawerToggle drawerToggle;

    /** Data to be passed between fragments. */
    private Bundle fragmentBundle;

    /**
     * Initializes the Toolbar for use with the activity.
     */
    private void setupToolbar(final Bundle savedInstanceState) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Set up the activity to use this toolbar. As a side effect this sets the Toolbar's title
        // to the activity's title.
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            // Some IDEs such as Android Studio complain about possible NPE without this check.
            assert getSupportActionBar() != null;

            // Restore the Toolbar's title.
            getSupportActionBar().setTitle(
                savedInstanceState.getCharSequence(BUNDLE_KEY_TOOLBAR_TITLE));
        }
    }


    /**
     * Initializes the navigation drawer menu to allow toggling via the toolbar or swipe from the
     * side of the screen.
     */
    private void setupNavigationMenu(final Bundle savedInstanceState) {
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ListView drawerItems = (ListView) findViewById(R.id.nav_drawer_items);

        // Create the navigation drawer.
        navigationDrawer = new NavigationDrawer(this, toolbar, drawerLayout, drawerItems,
            R.id.main_fragment_container);

        // Add navigation drawer menu items.
        // Home isn't a demo, but is fake as a demo.
        DemoConfiguration.DemoFeature home = new DemoConfiguration.DemoFeature();
        home.iconResId = R.mipmap.icon_home;
        home.titleResId = R.string.main_nav_menu_item_home;
        navigationDrawer.addDemoFeatureToMenu(home);

        for (DemoConfiguration.DemoFeature demoFeature : DemoConfiguration.getDemoFeatureList()) {
            navigationDrawer.addDemoFeatureToMenu(demoFeature);
        }

        if (savedInstanceState == null) {
            // Add the home fragment to be displayed initially.
            navigationDrawer.showHome();
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtain a reference to the mobile client. It is created in the App class,
        // but in case a custom App class is not used, we initialize it here if necessary.
        AWSMobileClient.initializeMobileClientIfNecessary(this);

        // Obtain a reference to the mobile client. It is created in the App class.
        final AWSMobileClient awsMobileClient = AWSMobileClient.defaultMobileClient();

        // Obtain a reference to the identity manager.
        identityManager = awsMobileClient.getIdentityManager();

        setContentView(R.layout.aws_activity_main);

        setupToolbar(savedInstanceState);

        setupNavigationMenu(savedInstanceState);


        App.getInstance().trackScreenView(getResources().getString(R.string.scrn_MainActivity));
        setSendDataAnalytics();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here excluding the home button.

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        // Save the title so it will be restored properly to match the view loaded when rotation
        // was changed or in case the activity was destroyed.
        if (toolbar != null) {
            bundle.putCharSequence(BUNDLE_KEY_TOOLBAR_TITLE, toolbar.getTitle());
        }
    }

    @Override
    public void onClick(final View view) {
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        final FragmentManager fragmentManager = this.getSupportFragmentManager();
        
        if (navigationDrawer.isDrawerOpen()) {
            navigationDrawer.closeDrawer();
            return;
        }

        if (fragmentManager.getBackStackEntryCount() == 0) {
            if (fragmentManager.findFragmentByTag(HomeDemoFragment.class.getSimpleName()) == null) {
                final Class fragmentClass = HomeDemoFragment.class;
                // if we aren't on the home fragment, navigate home.
                final Fragment fragment = Fragment.instantiate(this, fragmentClass.getName());

                fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, fragment, fragmentClass.getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();

                // Set the title for the fragment.
                final ActionBar actionBar = this.getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(getString(R.string.app_name));
                }
                return;
            }
        }
        super.onBackPressed();
    }


    /**
     * Stores data to be passed between fragments.
     * @param fragmentBundle fragment data
     */
    public void setFragmentBundle(final Bundle fragmentBundle) {
        this.fragmentBundle = fragmentBundle;
    }

    /**
     * Gets data to be passed between fragments.
     * @return fragmentBundle fragment data
     */
    public Bundle getFragmentBundle() {
        return this.fragmentBundle;
    }



    private void setSendDataAnalytics() {
        try {
             FirebaseAnalytics mFirebaseAnalytics;
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

            Log.d(TAG, "---FirebaseAnalytics.Event.SELECT_CONTENT------");
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "222");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Prince222 - SELECT_CONTENT");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image222");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            Log.d(TAG, "---FirebaseAnalytics.Event.SHARE------");
            Bundle bundle2 = new Bundle();
            bundle2.putString(FirebaseAnalytics.Param.ITEM_ID, "222");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Prince222 - SHARE");
            bundle2.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text222");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);

        } catch (Exception e) {
            Log.d(TAG, "---setSendDataAnalytics-Error send analytics--");
            e.printStackTrace();
        }
    }

}
