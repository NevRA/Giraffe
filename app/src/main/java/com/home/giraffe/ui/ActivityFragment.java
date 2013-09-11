package com.home.giraffe.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.home.giraffe.objects.Activities;
import com.home.giraffe.tasks.GetActivitiesTask;

public class ActivityFragment extends RoboSherlockListFragment implements LoaderManager.LoaderCallbacks<Activities> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().getSupportLoaderManager().restartLoader(1, null, this);
    }

    @Override
    public Loader<Activities> onCreateLoader(int i, Bundle bundle) {
        return new GetActivitiesTask(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Activities> inboxLoader, Activities activities) {
        if (activities != null) {
            updateView(activities);
        }
    }

    private void updateView(Activities activities) {
        JiveContainerAdapter adapter = new JiveContainerAdapter(getActivity(), android.R.layout.simple_list_item_1, activities.getList());
        setListAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Activities> inboxLoader) {
    }
}
