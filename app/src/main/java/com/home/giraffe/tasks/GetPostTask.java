package com.home.giraffe.tasks;


import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.Comment;
import com.home.giraffe.objects.Jive.JivePost;
import com.home.giraffe.objects.Jive.JivePosts;
import com.home.giraffe.objects.Post;
import com.home.giraffe.utils.Utils;

public class GetPostTask extends BaseTaskLoader<Post> {

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

            Post post = Utils.getPostFromObjectType(jivePost.getType());
            post.fromJivePost(jivePost);

            JivePosts jiveComments = mRequestsManager.getJivePosts(jivePost.getCommentsId());
            for(JivePost container : jiveComments.getList()){
                Comment comment = new Comment(container);
                post.addComment(comment);
            }

            FragmentActivity activity = getActivity();
            if(activity != null)
                new GetBadgesCountTask(activity).execute();

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
