package com.home.giraffe;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.home.giraffe.interfaces.IConnector;
import com.home.giraffe.interfaces.IRequestsManager;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.network.Connector;
import com.home.giraffe.network.RequestsManager;
import com.home.giraffe.settings.SettingsManager;
import com.home.giraffe.ui.UiManager;

public class Configurator extends AbstractModule {
    @Override
    protected void configure() {
        bind(ISettingsManager.class).to(SettingsManager.class).in(Scopes.SINGLETON);
        bind(IRequestsManager.class).to(RequestsManager.class).in(Scopes.SINGLETON);
        bind(IUiManager.class).to(UiManager.class).in(Scopes.SINGLETON);
        bind(IConnector.class).to(Connector.class);
    }
}
