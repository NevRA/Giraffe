package com.home.giraffe.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.webkit.WebView;
import android.widget.*;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.R;
import com.home.giraffe.interfaces.IImageLoader;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.objects.Actor;
import com.home.giraffe.objects.Comment;
import com.home.giraffe.objects.Discussion;
import com.home.giraffe.objects.Jive.JiveTypes;
import com.home.giraffe.objects.Post;
import com.home.giraffe.storages.ObjectsStorage;
import com.home.giraffe.tasks.BaseTask;
import com.home.giraffe.tasks.GetPostTask;
import com.home.giraffe.tasks.NewComment;
import com.home.giraffe.tasks.PostCommentTask;
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

    @InjectView(R.id.content_layout)
    View mContentView;

    @InjectView(R.id.new_comment)
    EditText mNewComment;

    @InjectView(R.id.post)
    WebView mPostView;

    @InjectView(R.id.comments)
    LinearLayout mCommentsView;

    @InjectView(R.id.post_comment)
    Button mPostComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.post);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mId = getIntent().getStringExtra(Constants.ID_EXTRA);

        mPostComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mNewComment.getText().toString().isEmpty())
                    getSupportLoaderManager().restartLoader(1, null, PostFragment.this);
            }
        });

        if (mPost == null)
            loadPost();
        else
            showPost();
    }

    private void loadPost() {
        getSupportLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showPost() {
        mPostView.loadDataWithBaseURL("", mPost.getRawContent(), "text/html", "UTF-8", null);

        addComments();
    }

    private void addComments() {
        mCommentsView.removeAllViews();

        for (Comment comment : mPost.getComments()){
            View commentView = getLayoutInflater().inflate(R.layout.comment, mCommentsView, false);

            Actor actor = comment.getActor();
            ImageView avatar = (ImageView) commentView.findViewById(R.id.avatar);
            String avatarUrl = actor.getAvatarUrl();
            mImageLoader.DisplayImage(avatarUrl, avatar);

            TextView userDisplayName = (TextView) commentView.findViewById(R.id.userDisplayName);
            userDisplayName.setText(actor.getDisplayName());

            TextView updated = (TextView) commentView.findViewById(R.id.time);
            updated.setText(comment.getUpdatedTime());

            LinearLayout contentLayout = (LinearLayout) commentView.findViewById(R.id.root);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(comment.getLevel() * mUiManager.dpToPx(10), 0, 0, mUiManager.dpToPx(5));
            contentLayout.setLayoutParams(layoutParams);

            WebView content = (WebView) commentView.findViewById(R.id.content);
            content.loadDataWithBaseURL("", "<body style=\"margin: 0; padding: 0\">" + comment.getRawContent(), "text/html", "UTF-8", null);

            mCommentsView.addView(commentView);
        }
    }

    @Override
    public Loader<Post> onCreateLoader(int i, Bundle bundle) {

        mProgressBar.setVisibility(View.VISIBLE);
        mContentView.setVisibility(View.GONE);
        mUiManager.hideKeyboard(mNewComment);

        if(i == 0)
            return new GetPostTask(this, mId);
        else
        {
            BaseTask<Post> task = new PostCommentTask(
                    this,
                    mPost,
                    new NewComment(
                            (mPost instanceof Discussion) ?
                                    JiveTypes.JiveMessage :
                                    JiveTypes.JiveComment,
                            mNewComment.getText().toString()));

            mNewComment.setText("");
            return task;
        }
    }

    @Override
    public void onLoadFinished(Loader<Post> postLoader, Post post) {
        mPost = post;
        if (post != null) {
            showPost();
        }

        mProgressBar.setVisibility(View.GONE);
        mContentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<Post> postLoader) {
    }
}
