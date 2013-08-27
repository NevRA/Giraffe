package com.home.giraffe;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.home.giraffe.Interfaces.ISettingsManager;
import com.home.giraffe.settings.SettingsManager;

public class Configurator extends AbstractModule {
    @Override
    protected void configure() {
        bind(ISettingsManager.class).to(SettingsManager.class).in(Scopes.SINGLETON);
    }
}
