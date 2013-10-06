package com.home.giraffe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.base.BaseListFragment;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.objects.BaseObject;
import com.home.giraffe.objects.Post;
import com.home.giraffe.objects.activity.BaseObjectContainer;
import com.home.giraffe.tasks.GetBaseObjectsListTask;

public class ActivityFragment extends BaseListFragment<BaseObjectContainer> {

    @Inject
    ISettingsManager mSettingsManager;

    @Inject
    IUiManager mUiManager;

    private BaseObjectContainer mBaseObjectContainer;
    private BaseObjectsAdapter mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    @Override
    protected void itemSelected(Object item) {
        if (item instanceof Post) {
            Post post = (Post) item;

            Intent intent = new Intent(getActivity(), PostFragment.class);
            intent.putExtra(Constants.ID_EXTRA, post.getId());
            mUiManager.startActivity(getActivity(), intent);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url = mSettingsManager.getCommunityUrl() + Constants.ACTIVITIES;

        if (mBaseObjectContainer == null) {
            mBaseObjectContainer = new BaseObjectContainer();
            mBaseObjectContainer.setPrevious(url);
            mBaseObjectContainer.setCurrent(url);
            mBaseObjectContainer.setNext(url);
        }
    }

    public void init() {
        if (mAdapter == null)
            mAdapter = new BaseObjectsAdapter(getActivity(), mBaseObjectContainer.getActivities());

        setListAdapter(mAdapter);
        enableOnScrollUpdates();

        if (getItemsCount() == 0) {
            setListShown(false);
            loadNext();
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
        if (!baseObjectContainer.getActivities().isEmpty())
            updateView(baseObjectContainer);
    }

    @Override
    public Loader<BaseObjectContainer> onCreateLoader(int i, Bundle bundle) {
        return new GetBaseObjectsListTask(getActivity(), mBaseObjectContainer.getCurrent());
    }

    private void updateView(BaseObjectContainer baseObjectContainer) {
        if (baseObjectContainer.getCurrent().equals(mBaseObjectContainer.getNext()) &&
                baseObjectContainer.getCurrent().equals(mBaseObjectContainer.getPrevious())) {
            mBaseObjectContainer.setPrevious(baseObjectContainer.getPrevious());
            mBaseObjectContainer.setNext(baseObjectContainer.getNext());
            mBaseObjectContainer.addActivities(baseObjectContainer.getActivities());
        } else if (baseObjectContainer.getCurrent().equals(mBaseObjectContainer.getPrevious())) {
            mBaseObjectContainer.setPrevious(baseObjectContainer.getPrevious());
            mBaseObjectContainer.addActivities(0, baseObjectContainer.getActivities());
        } else if (baseObjectContainer.getCurrent().equals(mBaseObjectContainer.getNext())) {
            mBaseObjectContainer.addActivities(baseObjectContainer.getActivities());
            mBaseObjectContainer.setNext(baseObjectContainer.getNext());
        }

        mAdapter.notifyDataSetChanged();
    }
}
