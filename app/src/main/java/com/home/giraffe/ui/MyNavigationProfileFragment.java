package com.home.giraffe.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.R;
import com.home.giraffe.interfaces.IImageLoader;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.network.NetworkUtils;
import com.home.giraffe.objects.Author;
import com.home.giraffe.tasks.GetUserProfileTask;
import roboguice.inject.InjectView;

public class MyNavigationProfileFragment extends RoboSherlockFragment implements LoaderManager.LoaderCallbacks<Author>  {

    @Inject
    IImageLoader mImageLoader;

    @Inject
    ISettingsManager mSettingsManager;

    @Inject
    NetworkUtils mNetworkUtils;

    @InjectView(R.id.userDisplayName)
    TextView userDisplayName;

    @InjectView(R.id.userJobTitle)
    TextView userJobTitle;

    @InjectView(R.id.userPic)
    ImageView userPic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_navigation_profile, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        updateView();

        getActivity().getSupportLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Author> onCreateLoader(int i, Bundle bundle) {
        return new GetUserProfileTask(getActivity(), Constants.ME);
    }

    @Override
    public void onLoadFinished(Loader<Author> personLoader, Author author) {
        if(author != null){
            updateView(author);
        }
    }

    private void updateView() {
        userDisplayName.setText(mSettingsManager.getUserDisplayName());
        userJobTitle.setText(mSettingsManager.getUserJobTitle());
        mImageLoader.DisplayImage(mNetworkUtils.getMyAvatarUrl(), userPic);
    }

    private void updateView(Author author) {
        mSettingsManager.setUserDisplayName(author.getDisplayName());
        mSettingsManager.setUserJobTitle(author.getJobTitle());
        mSettingsManager.setUserId(author.getId());

        updateView();
    }

    @Override
    public void onLoaderReset(Loader<Author> personLoader) {
    }
}
