package com.home.giraffe;

import android.app.Application;
import roboguice.RoboGuice;

public class MainApplication extends Application {
    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(this), new Configurator());
    }

    public static Application getInstance(){
        return instance;
    }
}
