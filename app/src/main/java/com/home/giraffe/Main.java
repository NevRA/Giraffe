package com.home.giraffe;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.inject.Inject;
import com.home.giraffe.base.BaseFragmentActivity;
import com.home.giraffe.events.InboxUnreadCountEvent;
import com.home.giraffe.ui.ActionsFragment;
import com.home.giraffe.ui.ActivityFragment;
import com.home.giraffe.ui.InboxFragment;
import roboguice.inject.InjectView;

public class Main extends BaseFragmentActivity {
    @Inject
    ActivityFragment mActivityFragment;

    @Inject
    InboxFragment mInboxFragment;

    @Inject
    ActionsFragment mActionsFragment;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @InjectView(R.id.drawer_main)
    ScrollView mDrawerNavigation;

    @InjectView(R.id.inboxBadge)
    TextView mInboxBadge;

    @InjectView(R.id.activityLayout)
    RelativeLayout mActivityLayout;

    @InjectView(R.id.inboxLayout)
    RelativeLayout mInboxLayout;

    @InjectView(R.id.actionsLayout)
    RelativeLayout mActionsLayout;

    ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;

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
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("Navigation");
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(mActivityFragment, "Activity");
        }

        mActivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(mActivityFragment, "Activity");
            }
        });

        mInboxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(mInboxFragment, "Inbox");
            }
        });

        mActionsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(mActionsFragment, "Actions");
            }
        });
    }

    private void selectItem(Fragment fragment, String title) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
        setTitle(title);
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

    public void onEvent(final InboxUnreadCountEvent event) {
        mInboxBadge.setText(String.valueOf(event.getCount()));
    }
}
