package com.home.giraffe;

import com.fedorvlasov.lazylist.ImageLoader;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.home.giraffe.interfaces.*;
import com.home.giraffe.network.NetworkConnector;
import com.home.giraffe.network.RequestsManager;
import com.home.giraffe.settings.SettingsManager;
import com.home.giraffe.storages.ObjectsStorage;
import com.home.giraffe.ui.UiManager;
import de.greenrobot.event.EventBus;

public class Configurator extends AbstractModule {
    @Override
    protected void configure() {
        bind(EventBus.class).in(Scopes.SINGLETON);
        bind(ISettingsManager.class).to(SettingsManager.class).in(Scopes.SINGLETON);
        bind(IRequestsManager.class).to(RequestsManager.class).in(Scopes.SINGLETON);
        bind(IUiManager.class).to(UiManager.class).in(Scopes.SINGLETON);
        bind(IImageLoader.class).to(ImageLoader.class).in(Scopes.SINGLETON);
        bind(ObjectsStorage.class).in(Scopes.SINGLETON);
        bind(INetworkConnector.class).to(NetworkConnector.class).in(Scopes.SINGLETON);
    }
}
