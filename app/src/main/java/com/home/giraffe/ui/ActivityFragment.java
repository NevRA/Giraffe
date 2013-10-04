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

public class ActivityFragment extends BaseListFragment<BaseObjectContainer> {

    @Inject
    ISettingsManager mSettingsManager;

    private BaseObjectContainer mBaseObjectContainer;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    public void init() {
        enableOnScrollUpdates();

        if (mBaseObjectContainer == null) {
            String url = mSettingsManager.getCommunityUrl() + Constants.ACTIVITIES;

            mBaseObjectContainer = new BaseObjectContainer();
            mBaseObjectContainer.setPrevious(url);
            mBaseObjectContainer.setCurrent(url);
            mBaseObjectContainer.setNext(url);

            ActivitiesAdapter adapter = new ActivitiesAdapter(getActivity(), android.R.layout.simple_list_item_1, mBaseObjectContainer.getActivities());
            setListAdapter(adapter);

            setListShown(false);

            loadNext();
        } else {
            updateView();
        }
    }

    private void restartLoader() {
        getActivity().getSupportLoaderManager().restartLoader(2, null, this);
    }

    @Override
    public void update() {
        mBaseObjectContainer.setCurrent(mBaseObjectContainer.getPrevious());
        restartLoader();
    }

    @Override
    public void loadNext() {
        mBaseObjectContainer.setCurrent(mBaseObjectContainer.getNext());
        restartLoader();
    }

    @Override
    protected void onLoadFinished(BaseObjectContainer baseObjectContainer) {
        if(!baseObjectContainer.getActivities().isEmpty())
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
        if(baseObjectContainer.getCurrent().equals(mBaseObjectContainer.getNext()) &&
            baseObjectContainer.getCurrent().equals(mBaseObjectContainer.getPrevious())){
            mBaseObjectContainer.setPrevious(baseObjectContainer.getPrevious());
            mBaseObjectContainer.setNext(baseObjectContainer.getNext());
            mBaseObjectContainer.addActivities(baseObjectContainer.getActivities());
        }
        else if(baseObjectContainer.getCurrent().equals(mBaseObjectContainer.getPrevious())){
            mBaseObjectContainer.setPrevious(baseObjectContainer.getPrevious());
            mBaseObjectContainer.addActivities(0, baseObjectContainer.getActivities());
        }
        else if(baseObjectContainer.getCurrent().equals(mBaseObjectContainer.getNext())){
            mBaseObjectContainer.addActivities(baseObjectContainer.getActivities());
            mBaseObjectContainer.setNext(baseObjectContainer.getNext());
        }

        ((ArrayAdapter) getListAdapter()).notifyDataSetChanged();
    }
}
