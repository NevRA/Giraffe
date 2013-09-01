package com.home.giraffe;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.google.inject.Inject;
import com.home.giraffe.tasks.SignInTask;
import roboguice.RoboGuice;
import roboguice.inject.InjectView;

public class SignInActivity extends RoboSherlockFragmentActivity implements LoaderManager.LoaderCallbacks {
    @InjectView(R.id.signin) Button mSignIn;
    @InjectView(R.id.logo_layout) LinearLayout mLogoLayout;
    @InjectView(R.id.logo_animation1) ImageView mLogoAnimation1;
    @InjectView(R.id.logo_animation2) ImageView mLogoAnimation2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);

        Init();
        ShowStartupAnimation();
    }

    private void Init() {
         mSignIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportLoaderManager().initLoader(0, null, SignInActivity.this);
            }
        });
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
        return new SignInTask(this, "", "", "");
    }

    @Override
    public void onLoadFinished(Loader loader, Object o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onLoaderReset(Loader loader) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
