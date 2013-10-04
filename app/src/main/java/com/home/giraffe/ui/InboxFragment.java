package com.home.giraffe.ui;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ArrayAdapter;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.base.BaseListFragment;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.objects.activity.BaseObjectContainer;
import com.home.giraffe.tasks.GetActivitiesTask;

public class InboxFragment extends BaseListFragment<BaseObjectContainer> {

    @Inject
    ISettingsManager mSettingsManager;

    private BaseObjectContainer mBaseObjectContainer;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    public void init() {
        if (mBaseObjectContainer == null) {

            mBaseObjectContainer = new BaseObjectContainer();
            mBaseObjectContainer.setCurrent(mSettingsManager.getCommunityUrl() + Constants.INBOX);

            ActivitiesAdapter adapter = new ActivitiesAdapter(getActivity(), android.R.layout.simple_list_item_1, mBaseObjectContainer.getActivities());
            setListAdapter(adapter);

            setListShown(false);

            update();
        } else {
            updateView();
        }
    }

    private void restartLoader() {
        getActivity().getSupportLoaderManager().restartLoader(4, null, this);
    }

    @Override
    public void update() {
        restartLoader();
    }

    @Override
    protected void onLoadFinished(BaseObjectContainer baseObjectContainer) {
        if (!baseObjectContainer.getActivities().isEmpty())
            updateView(baseObjectContainer);
    }

    @Override
    public Loader<BaseObjectContainer> onCreateLoader(int i, Bundle bundle) {
        return new GetActivitiesTask(getActivity(), mBaseObjectContainer.getCurrent());
    }

    private void updateView() {
        updateView(mBaseObjectContainer);
    }

    private void updateView(BaseObjectContainer baseObjectContainer) {
        mBaseObjectContainer.setActivities(baseObjectContainer.getActivities());
        ((ArrayAdapter) getListAdapter()).notifyDataSetChanged();
    }
}
