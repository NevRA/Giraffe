package com.home.giraffe;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;

public class SignInActivity extends RoboSherlockActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);

        LinearLayout layout = (LinearLayout) findViewById(R.id.logo);
        final ImageView logo1 = (ImageView) findViewById(R.id.logoImage1);
        final ImageView logo2 = (ImageView) findViewById(R.id.logoImage2);

        Animation animation = AnimationUtils.loadAnimation(this, R.layout.logo_animation);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo1.setVisibility(View.GONE);
                logo2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        layout.setAnimation(animation);
        animation.startNow();
        layout.invalidate();
    }
}
