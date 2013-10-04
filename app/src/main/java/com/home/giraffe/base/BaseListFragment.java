package com.home.giraffe.base;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.home.giraffe.R;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;

public abstract class BaseListFragment<T> extends RoboSherlockListFragment implements LoaderManager.LoaderCallbacks<T>, PullToRefreshAttacher.OnRefreshListener {

    private LinearLayout mFooter;
    private PullToRefreshAttacher mPullToRefreshAttacher;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPullToRefreshAttacher = getPullToRefreshAttacher();
        addPullToRefresh();
    }

    public boolean isViewDestroyed(){
        return getView() == null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

        addFooter();

        getListView().setDividerHeight(0);
        getListView().setDivider(null);
    }

    public void enableOnScrollUpdates(){
        getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view,
                                             int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                int lastItem = firstVisibleItem + visibleItemCount;
                if (firstVisibleItem > 3 && lastItem == totalItemCount) {
                    if (!isFooterVisible()) {
                        showFooter();
                        loadNext();
                    }
                }
            }
        });
    }

    public void hideFooter(){
        mFooter.setVisibility(View.GONE);
    }

    public void showFooter(){
        mFooter.setVisibility(View.VISIBLE);
    }

    public boolean isFooterVisible() {
        return mFooter.getVisibility() == View.VISIBLE;
    }

    private void addFooter() {
        if(mFooter == null){
            mFooter = (LinearLayout) getLayoutInflater(null).inflate(R.layout.footer, getListView(), false);
            getListView().addFooterView(mFooter);
        }
        hideFooter();
    }

    public void update() {
    }

    public void loadNext() {
    }

    public void addPullToRefresh(){
        mPullToRefreshAttacher.addRefreshableView(getListView(), this);
    }

    public void completePullToRefresh(){
        mPullToRefreshAttacher.setRefreshComplete();
    }

    @Override
    public void onRefreshStarted(View view) {
        update();
    }

    @Override
    public Loader<T> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<T> inboxLoader, T activities) {
        if(!isViewDestroyed()){
            setListShown(true);
            completePullToRefresh();
            hideFooter();
        }
        if (activities != null) {
            onLoadFinished(activities);
        }
    }

    protected abstract void onLoadFinished(T activities);

    @Override
    public void onLoaderReset(Loader<T> activitiesLoader) {
    }

    public PullToRefreshAttacher getPullToRefreshAttacher() {
        return ((BaseFragmentActivity)getActivity()).getPullToRefreshAttacher();
    }
}
