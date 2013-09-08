package com.home.giraffe;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.home.giraffe.ui.MyNavigationProfileFragment;

public class Main extends RoboSherlockFragmentActivity{

    MyNavigationProfileFragment mMyNavigationProfileFragment;
    DrawerLayout mDrawerLayout;
    ScrollView mDrawerNavigation;
    ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;
    //MenuListAdapter mMenuAdapter;

    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            if (mDrawerLayout.isDrawerOpen(mDrawerNavigation)) {
                mDrawerLayout.closeDrawer(mDrawerNavigation);
            } else {
                mDrawerLayout.openDrawer(mDrawerNavigation);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drawer_main);

        // Locate DrawerLayout in drawer_main.xml
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Locate ListView in drawer_main.xml
        mDrawerNavigation = (ScrollView) findViewById(R.id.drawer_main);

        // Set a custom shadow that overlays the main content when the drawer
        // opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        // Enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                // TODO Auto-generated method stub
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                // Set the title on the action when drawer open
                getSupportActionBar().setTitle("111");
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    private void selectItem(int position) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Locate Position
        switch (position) {
            case 0:
                //ft.replace(R.id.content_frame, fragment1);
                break;
            case 1:
                //ft.replace(R.id.content_frame, fragment2);
                break;
            case 2:
                //ft.replace(R.id.content_frame, fragment3);
                break;
        }

        ft.commit();
        // Get the title followed by the position
        //setTitle(title[position]);
        // Close drawer
        mDrawerLayout.closeDrawer(mDrawerNavigation);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
}
