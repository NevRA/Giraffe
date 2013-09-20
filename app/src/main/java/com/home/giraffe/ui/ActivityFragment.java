package com.home.giraffe.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.Main;
import com.home.giraffe.R;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.objects.Activities;
import com.home.giraffe.tasks.GetActivitiesTask;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;

public class ActivityFragment extends RoboSherlockListFragment implements LoaderManager.LoaderCallbacks<Activities>, PullToRefreshAttacher.OnRefreshListener {

    @Inject
    ISettingsManager mSettingsManager;

    private PullToRefreshAttacher mPullToRefreshAttacher;
    private LinearLayout mFooter;
    private Activities mActivities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.

        mPullToRefreshAttacher = ((Main) getActivity()).getPullToRefreshAttacher();
        mPullToRefreshAttacher.addRefreshableView(getListView(), this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    public void init() {
        getListView().setDividerHeight(0);
        getListView().setDivider(null);

        if (mActivities == null) {
            mActivities = new Activities();
            mActivities.setNext(mSettingsManager.getCommunityUrl() + Constants.ACTIVITIES);

            addFooter();
            ActivitiesAdapter adapter = new ActivitiesAdapter(getActivity(), android.R.layout.simple_list_item_1, mActivities.getActivities());
            setListAdapter(adapter);
            removeFooter();

            setListShown(false);

            load();
        } else {
            updateView();
        }

        getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view,
                                             int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                int lastItem = firstVisibleItem + visibleItemCount;
                if (firstVisibleItem > 5 && lastItem == totalItemCount) {
                    loadNext();
                }
            }
        });
    }

    private boolean isFooterShowing(){
       return getListView().getFooterViewsCount() != 0;
    }

    private void addFooter() {
        if (mFooter == null)
            mFooter = (LinearLayout) getLayoutInflater(null).inflate(R.layout.footer, null);
        if (!isFooterShowing())
            getListView().addFooterView(mFooter);
    }

    private void removeFooter() {
        if (isFooterShowing())
            getListView().removeFooterView(mFooter);
    }

    private void load() {
        getActivity().getSupportLoaderManager().restartLoader(1, null, this);
    }

    private void loadNext() {
        if (!isFooterShowing()){
            addFooter();
            load();
        }
    }

    @Override
    public Loader<Activities> onCreateLoader(int i, Bundle bundle) {
        return new GetActivitiesTask(getActivity(), mActivities.getNext());
    }

    @Override
    public void onLoadFinished(Loader<Activities> inboxLoader, Activities activities) {
        setListShown(true);
        removeFooter();
        if (activities != null) {
            updateView(activities);
        }
    }

    private void updateView() {
        updateView(mActivities);
    }

    private void updateView(Activities activities) {
        mActivities.addActivities(activities.getActivities());
        mActivities.setNext(activities.getNext());
        mActivities.setPrevious(activities.getPrevious());

        ((ArrayAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Activities> inboxLoader) {
    }

    @Override
    public void onRefreshStarted(View view) {
        // TODO
    }
}
