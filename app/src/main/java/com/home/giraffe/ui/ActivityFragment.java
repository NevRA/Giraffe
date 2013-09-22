package com.home.giraffe.ui;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ArrayAdapter;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.base.BaseListFragment;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.objects.activity.Activities;
import com.home.giraffe.tasks.GetActivitiesTask;

public class ActivityFragment extends BaseListFragment<Activities> {

    @Inject
    ISettingsManager mSettingsManager;

    private Activities mActivities;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addPullToRefresh();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    public void init() {
        if (mActivities == null) {

            mActivities = new Activities();
            String url = mSettingsManager.getCommunityUrl() + Constants.ACTIVITIES;
            mActivities.setPrevious(url);
            mActivities.setCurrent(url);
            mActivities.setNext(url);

            addFooter();
            ActivitiesAdapter adapter = new ActivitiesAdapter(getActivity(), android.R.layout.simple_list_item_1, mActivities.getActivities());
            setListAdapter(adapter);
            removeFooter();

            setListShown(false);

            loadNext();
        } else {
            updateView();
        }
    }

    private void restartLoader() {
        getActivity().getSupportLoaderManager().restartLoader(1, null, this);
    }

    @Override
    public void loadPrevious() {
        mActivities.setCurrent(mActivities.getPrevious());
        restartLoader();
    }

    @Override
    public void loadNext() {
        mActivities.setCurrent(mActivities.getNext());
        restartLoader();
    }

    @Override
    protected void onLoadFinished(Activities activities) {
        if(!activities.getActivities().isEmpty())
            updateView(activities);
    }

    @Override
    public Loader<Activities> onCreateLoader(int i, Bundle bundle) {
        return new GetActivitiesTask(getActivity(), mActivities.getCurrent());
    }

    private void updateView() {
        updateView(mActivities);
    }

    private void updateView(Activities activities) {
        if(activities.getCurrent().equals(mActivities.getNext()) &&
            activities.getCurrent().equals(mActivities.getPrevious())){
            mActivities.setPrevious(activities.getPrevious());
            mActivities.setNext(activities.getNext());
            mActivities.addActivities(activities.getActivities());
        }
        else if(activities.getCurrent().equals(mActivities.getPrevious())){
            mActivities.setPrevious(activities.getPrevious());
            mActivities.addActivities(0, activities.getActivities());
        }
        else if(activities.getCurrent().equals(mActivities.getNext())){
            mActivities.addActivities(activities.getActivities());
            mActivities.setNext(activities.getNext());
        }

        ((ArrayAdapter) getListAdapter()).notifyDataSetChanged();
    }
}
