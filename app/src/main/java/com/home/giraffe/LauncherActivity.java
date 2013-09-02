package com.home.giraffe;

import android.os.Bundle;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.google.inject.Inject;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.interfaces.IUiManager;

public class LauncherActivity extends RoboSherlockActivity {
    @Inject
    ISettingsManager mSettingsManager;

    @Inject
    IUiManager mUiManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUiManager.startActivity(this,
                mSettingsManager.isLoggedOn() ?
                        Main.class :
                        SignInActivity.class);
    }
}
