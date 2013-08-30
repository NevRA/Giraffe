package com.home.giraffe;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import roboguice.inject.InjectView;

public class SignInActivity extends RoboSherlockActivity {
    @InjectView(R.id.logo_layout) LinearLayout mLogoLayout;
    @InjectView(R.id.logo_animation1) ImageView mLogoAnimation1;
    @InjectView(R.id.logo_animation2) ImageView mLogoAnimation2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);

        ShowStartupAnimation();
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
}
