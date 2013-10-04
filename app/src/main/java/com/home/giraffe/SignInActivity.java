package com.home.giraffe;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.google.inject.Inject;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.tasks.SignInTask;
import com.home.giraffe.ui.ProgressDialogFragment;
import roboguice.inject.InjectView;

public class SignInActivity extends RoboSherlockFragmentActivity implements LoaderManager.LoaderCallbacks {
    @InjectView(R.id.signin) Button mSignIn;
    @InjectView(R.id.communityUrl) EditText mCommunityUrl;
    @InjectView(R.id.userName) EditText mUserName;
    @InjectView(R.id.userPassword) EditText mUserPassword;
    @InjectView(R.id.appVersion) TextView mAppVersion;
    @InjectView(R.id.logo_layout) LinearLayout mLogoLayout;
    @InjectView(R.id.logo_animation1) ImageView mLogoAnimation1;
    @InjectView(R.id.logo_animation2) ImageView mLogoAnimation2;

    @Inject
    ISettingsManager mSettingsManager;

    ProgressDialogFragment mWaiter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);

        Init();
        ShowStartupAnimation();
    }

    private void Init() {
        showAppVersion();
        resetSettings();
        mSignIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportLoaderManager().restartLoader(1, null, SignInActivity.this);
            }
        });
    }

    private void showAppVersion() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            mAppVersion.setText("v " + pInfo.versionName);

        } catch (PackageManager.NameNotFoundException e) {
            // TODO
        }
    }

    private void resetSettings() {
        mSettingsManager.clear();
    }

    private void ShowStartupAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.layout.logo_animation);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLogoAnimation1.setVisibility(View.GONE);
                mLogoAnimation2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mLogoLayout.setAnimation(animation);
        animation.startNow();
        mLogoLayout.invalidate();
    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        mWaiter = new ProgressDialogFragment();
        mWaiter.show(getSupportFragmentManager(), "");

        return new SignInTask(this, mCommunityUrl.getText().toString(), mUserName.getText().toString(), mUserPassword.getText().toString());
    }

    @Override
    public void onLoadFinished(Loader loader, Object o) {
        new Handler().post(new Runnable() {
            public void run() {
                mWaiter.dismiss();
                if(mSettingsManager.isLoggedOn()){
                    startActivity(new Intent(SignInActivity.this, Main.class));
                }
            }
        });
    }

    @Override
    public void onLoaderReset(Loader loader) {
    }
}
