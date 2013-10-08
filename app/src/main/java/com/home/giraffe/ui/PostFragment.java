package com.home.giraffe.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.webkit.WebView;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.R;
import com.home.giraffe.objects.Post;
import com.home.giraffe.storages.ObjectsStorage;
import com.home.giraffe.tasks.GetPostTask;
import roboguice.inject.InjectView;

public class PostFragment extends RoboSherlockFragmentActivity implements LoaderManager.LoaderCallbacks<Post> {
    private String mId;
    private Post mPost;

    @Inject
    ObjectsStorage mObjectsStorage;

    @InjectView(R.id.progress)
    View mProgressBar;

    @InjectView(R.id.post)
    WebView mPostView;

    @InjectView(R.id.comments)
    View mCommentsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.post);

        mId = getIntent().getStringExtra(Constants.ID_EXTRA);

        if (mPost == null)
            loadPost();
        else
            showPost();
    }

    private void loadPost() {
        mProgressBar.setVisibility(View.VISIBLE);
        mPostView.setVisibility(View.GONE);
        getSupportLoaderManager().restartLoader(0, null, this);
    }

    private void showPost() {
        mPostView.loadDataWithBaseURL("", mPost.getRawContent(), "text/html", "UTF-8", null);

        addComments();
    }

    private void addComments() {

    }

    @Override
    public Loader<Post> onCreateLoader(int i, Bundle bundle) {
        return new GetPostTask(this, mId);
    }

    @Override
    public void onLoadFinished(Loader<Post> postLoader, Post post) {
        mPost = post;
        if (post != null) {
            showPost();
        }

        mProgressBar.setVisibility(View.GONE);
        mPostView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<Post> postLoader) {
    }
}
