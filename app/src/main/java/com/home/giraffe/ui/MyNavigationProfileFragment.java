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
import com.home.giraffe.objects.Person;
import com.home.giraffe.tasks.GetUserProfileTask;
import roboguice.inject.InjectView;

public class MyNavigationProfileFragment extends RoboSherlockFragment implements LoaderManager.LoaderCallbacks<Person>  {

    @Inject
    IImageLoader mImageLoader;

    @Inject
    ISettingsManager mSettingsManager;

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

        getActivity().getSupportLoaderManager().restartLoader(4, null, this);
    }

    @Override
    public Loader<Person> onCreateLoader(int i, Bundle bundle) {
        return new GetUserProfileTask(getActivity(), Constants.ME);
    }

    @Override
    public void onLoadFinished(Loader<Person> personLoader, Person person) {
        if(person != null){
            updateView(person);
        }
    }

    private void updateView() {
        userDisplayName.setText(mSettingsManager.getUserDisplayName());
        userJobTitle.setText(mSettingsManager.getUserJobTitle());
        mImageLoader.DisplayImage(mSettingsManager.getCommunityUrl() + String.format(Constants.AVATAR, mSettingsManager.getUserId()), userPic);
    }

    private void updateView(Person person) {
        mSettingsManager.setUserDisplayName(person.getDisplayName());
        mSettingsManager.setUserJobTitle(person.getJobTitle());
        mSettingsManager.setUserId(person.getId());

        updateView();
    }

    @Override
    public void onLoaderReset(Loader<Person> personLoader) {
    }
}
