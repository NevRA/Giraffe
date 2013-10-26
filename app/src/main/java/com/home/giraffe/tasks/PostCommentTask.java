package com.home.giraffe.tasks;


import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.home.giraffe.objects.Jive.JiveContent;
import com.home.giraffe.objects.Jive.JivePost;
import com.home.giraffe.objects.Post;
import com.home.giraffe.utils.Utils;

public class PostCommentTask extends BaseTaskLoader<Post> {
    private Post mPost;
    private NewComment mComment;

    public PostCommentTask(FragmentActivity activity, Post post, NewComment comment) {
        super(activity);
        mPost = post;
        mComment = comment;
    }

    @Override
    public Post loadInBackground() {
        Utils.d("Started PostCommentTask");

        try {
            String postUrl;
            JivePost post = new JivePost();
            post.setType(mComment.getType());
            post.setContent(new JiveContent(mComment.getMessage()));
            if(!TextUtils.isEmpty(mComment.getParentId()))
                postUrl = mComment.getParentId();
            else
                postUrl = mPost.getCommentsId();

            Utils.v(String.format("New comment" +
                    "\n\tType: %s" +
                    "\n\tText: %s" +
                    "\n\tPost id: %s" +
                    "\n\tParent id: %s", mComment.getType(), mComment.getMessage(), mPost.getId(), mComment.getParentId()));

            mRequestsManager.postMessage(postUrl, post);

            GetPostTask task = new GetPostTask(getActivity(), mPost.getId());
            return task.loadInBackground();
        } catch (Exception e) {
            mUiManager.showError(getActivity(), e);
        }

        finally {
            Utils.d("Finished PostCommentTask");
        }

        return null;
    }
}
