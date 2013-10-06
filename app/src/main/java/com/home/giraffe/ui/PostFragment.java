package com.home.giraffe.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.objects.Post;
import com.home.giraffe.storages.ObjectsStorage;

public class PostFragment extends RoboSherlockFragmentActivity {
    private Post mPost;

    @Inject
    ObjectsStorage mObjectsStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String id = getIntent().getStringExtra(Constants.ID_EXTRA);
        mPost = (Post) mObjectsStorage.get(id);

        setTitle(mPost.getFriendlyName());
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
