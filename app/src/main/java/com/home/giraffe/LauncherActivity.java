package com.home.giraffe;

import android.content.Intent;
import android.os.Bundle;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.google.inject.Inject;
import com.home.giraffe.settings.SettingsManager;

public class LauncherActivity extends RoboSherlockActivity {
    @Inject
    SettingsManager mSettingsManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!mSettingsManager.isLoggedOn()){
            Intent i = new Intent(this, SignInActivity.class);
            startActivity(i);
        }
    }
}
