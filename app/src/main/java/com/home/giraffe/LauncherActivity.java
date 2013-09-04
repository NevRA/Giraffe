package com.home.giraffe;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.google.inject.Inject;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.interfaces.IUiManager;

public class LauncherActivity extends RoboSherlockFragmentActivity {
    @Inject
    ISettingsManager mSettingsManager;

    @Inject
    IUiManager mUiManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUiManager.startActivity(
                mSettingsManager.isLoggedOn() ?
                        Main.class :
                        SignInActivity.class);
    }
}
