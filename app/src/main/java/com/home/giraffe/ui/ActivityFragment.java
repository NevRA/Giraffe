package com.home.giraffe.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.home.giraffe.objects.Activities;
import com.home.giraffe.objects.JiveContainer;
import com.home.giraffe.tasks.GetActivitiesTask;

import java.util.ArrayList;
import java.util.List;

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

        List<String> data = new ArrayList<String>();
        for (JiveContainer container : activities.getList()) {
            String title = container.getTitle();
            if (title != null)
                data.add(container.getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Activities> inboxLoader) {
    }
}
