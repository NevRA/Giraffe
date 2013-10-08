package com.home.giraffe.tasks;


import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.Jive.JiveContainer;
import com.home.giraffe.objects.Jive.JiveObjects;
import com.home.giraffe.objects.Jive.JivePost;
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

            JiveObjects jiveObjects = mRequestsManager.getJiveObjects(jivePost.getCommentsId());
            for(JiveContainer container : jiveObjects.getList()){
                Utils.d(container.getContent());
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
