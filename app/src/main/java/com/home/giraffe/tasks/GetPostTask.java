package com.home.giraffe.tasks;


import android.support.v4.app.FragmentActivity;
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
            Post post = new Post(mUrl);
            post.fromJivePost(mRequestsManager.getPost(mUrl));
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
