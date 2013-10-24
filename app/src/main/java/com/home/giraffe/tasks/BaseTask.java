package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.google.inject.Inject;
import com.home.giraffe.interfaces.IRequestsManager;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.interfaces.IUiManager;
import de.greenrobot.event.EventBus;
import roboguice.util.RoboAsyncTask;

import java.lang.ref.WeakReference;

public abstract class BaseTask<T> extends RoboAsyncTask<T> {
    @Inject
    IRequestsManager mRequestsManager;

    @Inject
    IUiManager mUiManager;

    @Inject
    ISettingsManager mSettingsManager;

    @Inject
    EventBus mBus;

    WeakReference<FragmentActivity> mActivity;

    protected BaseTask(FragmentActivity activity) {
        super(activity);
        setActivity(activity);
    }

    public FragmentActivity getActivity() {
        return mActivity.get();
    }

    public void setActivity(FragmentActivity activity) {
        this.mActivity = new WeakReference<FragmentActivity>(activity);
    }
}
