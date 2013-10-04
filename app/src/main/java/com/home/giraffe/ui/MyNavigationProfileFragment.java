package com.home.giraffe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.google.inject.Inject;
import com.home.giraffe.R;
import com.home.giraffe.SignInActivity;
import com.home.giraffe.interfaces.IImageLoader;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.network.NetworkUtils;
import com.home.giraffe.objects.Jive.JiveAuthor;
import com.home.giraffe.tasks.GetUserProfileTask;
import roboguice.inject.InjectView;

public class MyNavigationProfileFragment extends RoboSherlockFragment implements LoaderManager.LoaderCallbacks<JiveAuthor>  {

    @Inject
    IImageLoader mImageLoader;

    @Inject
    ISettingsManager mSettingsManager;

    @Inject
    IUiManager mUiManager;

    @Inject
    NetworkUtils mNetworkUtils;

    @InjectView(R.id.userDisplayName)
    TextView userDisplayName;

    @InjectView(R.id.userJobTitle)
    TextView userJobTitle;

    @InjectView(R.id.userPic)
    ImageView userPic;

    @InjectView(R.id.signOut)
    Button signOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_navigation_profile, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        updateView();

        getActivity().getSupportLoaderManager().restartLoader(3, null, this);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    @Override
    public Loader<JiveAuthor> onCreateLoader(int i, Bundle bundle) {
        return new GetUserProfileTask(getActivity(), mSettingsManager.getUserId());
    }

    @Override
    public void onLoadFinished(Loader<JiveAuthor> personLoader, JiveAuthor jiveAuthor) {
        if(jiveAuthor != null){
            updateView(jiveAuthor);
        }
    }

    private void signOut(){
        Intent intent = new Intent(getActivity(), SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mUiManager.startActivityFromIntent(intent);
    }

    private void updateView() {
        userDisplayName.setText(mSettingsManager.getUserDisplayName());
        userJobTitle.setText(mSettingsManager.getUserJobTitle());
        mImageLoader.DisplayImage(mNetworkUtils.getMyAvatarUrl(), userPic);
    }

    private void updateView(JiveAuthor jiveAuthor) {
        mSettingsManager.setUserDisplayName(jiveAuthor.getDisplayName());
        mSettingsManager.setUserJobTitle(jiveAuthor.getJobTitle());
        mSettingsManager.setUserId(jiveAuthor.getId());

        updateView();
    }

    @Override
    public void onLoaderReset(Loader<JiveAuthor> personLoader) {
    }
}
