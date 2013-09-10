package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.google.inject.Inject;
import com.home.giraffe.interfaces.IRequestsManager;
import com.home.giraffe.interfaces.IUiManager;
import roboguice.content.RoboAsyncTaskLoader;

import java.lang.ref.WeakReference;

public abstract class BaseTask<T> extends RoboAsyncTaskLoader<T> {
    @Inject
    IRequestsManager mRequestsManager;

    @Inject
    IUiManager mUiManager;

    WeakReference<FragmentActivity> mActivity;

    public BaseTask(FragmentActivity activity) {
        super(activity);
        setActivity(activity);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }

    public FragmentActivity getActivity() {
        return mActivity.get();
    }

    public void setActivity(FragmentActivity activity) {
        this.mActivity = new WeakReference<FragmentActivity>(activity);
    }

    public IRequestsManager getRequestsManager() {
        return mRequestsManager;
    }

    public IUiManager getUiManager() {
        return mUiManager;
    }
}