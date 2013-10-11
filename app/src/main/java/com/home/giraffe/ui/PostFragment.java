package com.home.giraffe.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.R;
import com.home.giraffe.interfaces.IImageLoader;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.objects.Actor;
import com.home.giraffe.objects.Comment;
import com.home.giraffe.objects.Post;
import com.home.giraffe.storages.ObjectsStorage;
import com.home.giraffe.tasks.GetPostTask;
import roboguice.inject.InjectView;

public class PostFragment extends RoboSherlockFragmentActivity implements LoaderManager.LoaderCallbacks<Post> {
    private String mId;
    private Post mPost;

    @Inject
    IImageLoader mImageLoader;

    @Inject
    ObjectsStorage mObjectsStorage;

    @Inject
    IUiManager mUiManager;

    @InjectView(R.id.progress)
    View mProgressBar;

    @InjectView(R.id.post)
    WebView mPostView;

    @InjectView(R.id.comments)
    LinearLayout mCommentsView;

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
         for (Comment comment : mPost.getComments()){
             View commentView = getLayoutInflater().inflate(R.layout.comment, mCommentsView, false);

             Actor actor = comment.getActor();
             ImageView avatar = (ImageView) commentView.findViewById(R.id.avatar);
             String avatarUrl = actor.getAvatarUrl();
             mImageLoader.DisplayImage(avatarUrl, avatar);

             TextView userDisplayName = (TextView) commentView.findViewById(R.id.userDisplayName);
             userDisplayName.setText(actor.getDisplayName());

             CommentRootLayout contentLayout = (CommentRootLayout) commentView.findViewById(R.id.content_layout);
             contentLayout.setLevel(comment.getLevel(), mUiManager.dpToPx(5));

             WebView content = (WebView) commentView.findViewById(R.id.content);
             content.loadDataWithBaseURL("", "<body style=\"margin: 0; padding: 0\">" + comment.getRawContent(), "text/html", "UTF-8", null);

             mCommentsView.addView(commentView);
         }
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
