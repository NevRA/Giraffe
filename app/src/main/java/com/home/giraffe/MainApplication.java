package com.home.giraffe;

import android.app.Application;
import roboguice.RoboGuice;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(this), new Configurator());
    }
}
