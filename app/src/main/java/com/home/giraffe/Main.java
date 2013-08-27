package com.home.giraffe;

import android.os.Bundle;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.google.inject.Inject;
import com.home.giraffe.settings.SettingsManager;

public class Main extends RoboSherlockActivity {
    @Inject
    SettingsManager mSettingsManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSettingsManager.isLoggedOn();
    }
}
