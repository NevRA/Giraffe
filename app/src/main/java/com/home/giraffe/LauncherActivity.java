package com.home.giraffe;

import android.content.Intent;
import android.os.Bundle;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.google.inject.Inject;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.ui.SignInActivity;

public class LauncherActivity extends RoboSherlockFragmentActivity {
    @Inject
    ISettingsManager mSettingsManager;

    @Inject
    IUiManager mUiManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this,
                mSettingsManager.isLoggedOn() ?
                    Main.class :
                    SignInActivity.class);
        mUiManager.startActivity(this, intent);
    }
}
