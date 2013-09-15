package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.*;
import com.home.giraffe.objects.Jive.*;

public class GetActivitiesTask extends BaseTask<Activities> {
    private Activities mActivities;

    public GetActivitiesTask(FragmentActivity activity) {
        super(activity);
        mActivities = new Activities();
    }

    @Override
    public Activities loadInBackground() {
        try {
            JiveActivities jiveActivities = mRequestsManager.getActivities();
            for (JiveContainer jiveContainer : jiveActivities.getList()) {
                processJiveContainer(jiveContainer);
            }
        } catch (Exception e) {
            mUiManager.showError(getActivity(), e);
        }

        return mActivities;
    }

    private void processJiveContainer(JiveContainer jiveContainer) throws Exception {
        JiveActor jiveActor = jiveContainer.getActor();
        JiveVerbTypes jiveVerbTypes = jiveContainer.getVerbType();

        mObjectsStorage.add(Actor.fromJiveActor(jiveActor));

        if (jiveVerbTypes == JiveVerbTypes.JivePromoted) {
            processJivePromotion(jiveContainer);
        } else if (jiveVerbTypes == JiveVerbTypes.JiveLiked) {
            processJiveLike(jiveContainer);
        } else if (jiveVerbTypes == JiveVerbTypes.JiveCompleted) {
            processJiveTask(jiveContainer);
        } else if (jiveVerbTypes == JiveVerbTypes.JiveCreated) {
            processJivePost(jiveContainer);
        } else if (jiveVerbTypes == JiveVerbTypes.JiveReplied ||
                jiveVerbTypes == JiveVerbTypes.JiveCommented) {
            processJiveComment(jiveContainer);
        }
    }

    private void processJiveComment(JiveContainer jiveContainer) throws Exception {
        JiveObject jiveParent = jiveContainer.getJive().getParent();
        Post post = (Post) mObjectsStorage.get(jiveParent.getId());

        if (post == null) {
            JivePost jivePost = mRequestsManager.getPost(jiveParent.getId());
            Actor actor = (Actor) mObjectsStorage.get(jiveParent.getId());
            if (actor == null) {
                mObjectsStorage.add(Actor.fromJiveAuthor(jivePost.getAuthor()));
            }

            post = mUtils.getPostFromJivePost(jivePost);
        }

        Comment comment = mUtils.getCommentFromJiveContainer(jiveContainer);
        post.addCommentId(comment.getId());

        addObjectToStorage(comment);
        addObjectToActivities(post);
    }

    private void processJivePost(JiveContainer jiveContainer) {
        Post post = mUtils.getPostFromJiveContainer(jiveContainer);
        addObjectToActivities(post);
    }

    private void addObjectToStorage(BaseObject object){
        mObjectsStorage.add(object);
    }

    private void addObjectToActivities(BaseObject object){
        addObjectToStorage(object);

        ActivityItem activityItem = mActivities.getActivity(object.getId(), object.getType());
        if (activityItem == null) {
            activityItem = new ActivityItem(object.getId(), object.getType());
            mActivities.addActivity(activityItem);
        }
    }

    private void processJiveTask(JiveContainer jiveContainer) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void processJiveLike(JiveContainer jiveContainer) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void processJivePromotion(JiveContainer jiveContainer) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
