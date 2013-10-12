package com.home.giraffe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.widget.ArrayAdapter;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.base.BaseListFragment;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.objects.Post;
import com.home.giraffe.objects.activity.BaseObjectContainer;
import com.home.giraffe.tasks.GetBaseObjectsListTask;

public class InboxFragment extends BaseListFragment<BaseObjectContainer> {

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

        if(mBaseObjectContainer == null){
            mBaseObjectContainer = new BaseObjectContainer();
            mBaseObjectContainer.setCurrent(mSettingsManager.getCommunityUrl() + Constants.INBOX);
        }
    }

    public void init() {
        if(mAdapter == null){
            mAdapter = new BaseObjectsAdapter(getActivity(), mBaseObjectContainer.getActivities());
        }

        setListAdapter(mAdapter);
        disableOnScrollUpdates();

        if (getItemsCount() == 0) {
            setListShown(false);
            update();
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
        return new GetBaseObjectsListTask(getActivity(), mBaseObjectContainer.getCurrent());
    }

    private void updateView() {
        updateView(mBaseObjectContainer);
    }

    private void updateView(BaseObjectContainer baseObjectContainer) {
        mBaseObjectContainer.setActivities(baseObjectContainer.getActivities());
        ((ArrayAdapter) getListAdapter()).notifyDataSetChanged();
    }
}
