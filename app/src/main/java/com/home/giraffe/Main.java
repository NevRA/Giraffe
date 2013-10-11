package com.home.giraffe;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.inject.Inject;
import com.home.giraffe.base.BaseFragmentActivity;
import com.home.giraffe.events.ActionsUnreadCountEvent;
import com.home.giraffe.events.InboxUnreadCountEvent;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.tasks.GetBadgesCountTask;
import com.home.giraffe.ui.ActionsFragment;
import com.home.giraffe.ui.ActivityFragment;
import com.home.giraffe.ui.InboxFragment;
import roboguice.inject.InjectView;

public class Main extends BaseFragmentActivity implements LoaderManager.LoaderCallbacks<Object> {
    ActivityFragment mActivityFragment = new ActivityFragment();
    InboxFragment mInboxFragment = new InboxFragment();

    @Inject
    IUiManager mUiManager;

    @Inject
    ActionsFragment mActionsFragment;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @InjectView(R.id.drawer_main)
    ScrollView mDrawerNavigation;

    @InjectView(R.id.inboxBadge)
    TextView mInboxBadge;

    @InjectView(R.id.actionsBadge)
    TextView mActionsBadge;

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
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(mActivityFragment, mUiManager.getString(R.string.main_drawer_activity_label));
        }

        mActivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(mActivityFragment, mUiManager.getString(R.string.main_drawer_activity_label));
            }
        });

        mInboxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(mInboxFragment, mUiManager.getString(R.string.main_drawer_inbox_label));
            }
        });

        mActionsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(mActionsFragment, mUiManager.getString(R.string.main_drawer_actions_label));
            }
        });

        getSupportLoaderManager().restartLoader(0, null, this);
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

    public void onEventMainThread(final InboxUnreadCountEvent event) {
        int badge = event.getCount();
        mInboxBadge.setVisibility(badge == 0 ? View.GONE : View.VISIBLE);
        mInboxBadge.setText(String.valueOf(badge));
    }

    public void onEventMainThread(final ActionsUnreadCountEvent event) {
        int badge = event.getCount();
        mActionsBadge.setVisibility(badge == 0 ? View.GONE : View.VISIBLE);
        mActionsBadge.setText(String.valueOf(badge));
    }

    @Override
    public Loader<Object> onCreateLoader(int i, Bundle bundle) {
        return new GetBadgesCountTask(this);
    }

    @Override
    public void onLoadFinished(Loader<Object> objectLoader, Object o) {
    }

    @Override
    public void onLoaderReset(Loader<Object> objectLoader) {
    }
}
