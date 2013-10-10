package com.home.giraffe.tasks;


import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.Comment;
import com.home.giraffe.objects.Jive.*;
import com.home.giraffe.objects.Post;
import com.home.giraffe.utils.Utils;

public class GetPostTask extends BaseTask<Post> {

    private String mUrl;

    public GetPostTask(FragmentActivity activity, String url) {
        super(activity);
        mUrl = url;
    }

    @Override
    public Post loadInBackground() {
        Utils.d("Started GetPostTask");

        try {
            JivePost jivePost = mRequestsManager.getPost(mUrl);

            Post post = new Post(mUrl);
            post.fromJivePost(jivePost);

            JivePosts jiveComments = mRequestsManager.getJivePosts(jivePost.getCommentsId());
            for(JivePost container : jiveComments.getList()){
                Comment comment = new Comment(container);
                post.addComment(comment);
            }

            return post;
        } catch (Exception e) {
            mUiManager.showError(getActivity(), e);
        }

        finally {
            Utils.d("Finished GetPostTask");
        }

        return null;
    }
}
