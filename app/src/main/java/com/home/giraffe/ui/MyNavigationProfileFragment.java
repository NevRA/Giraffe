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
import com.home.giraffe.tasks.UserProfileTask;
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

        View rootView = inflater.inflate(R.layout.my_navigation_profile, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Person> onCreateLoader(int i, Bundle bundle) {
        return new UserProfileTask(getActivity(), "@me");
    }

    @Override
    public void onLoadFinished(Loader<Person> personLoader, Person person) {
        if(person != null){
            updateView(person);
        }
    }

    private void updateView(Person person) {
        userDisplayName.setText(person.displayName);
        userJobTitle.setText(person.jive.profile.get(0).value);
        mImageLoader.DisplayImage(mSettingsManager.getCommunityUrl() + String.format(Constants.AVATAR, person.id), userPic);
    }

    @Override
    public void onLoaderReset(Loader<Person> personLoader) {
    }
}
