package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.Activities;
import com.home.giraffe.objects.Author;
import com.home.giraffe.objects.BaseJiveObject;
import com.home.giraffe.objects.Discussion;
import com.home.giraffe.objects.Jive.*;

import java.util.ArrayList;

public class GetActivitiesTask extends BaseTask<Activities> {
    public GetActivitiesTask(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public Activities loadInBackground() {
        try {
            Activities activities = new Activities();

            JiveActivities jiveActivities = getRequestsManager().getActivities();
            for (JiveContainer container : jiveActivities.getList()){
                Jive jive = container.getJive();
                JiveActor jiveActor = container.getActor();
                JiveObject jiveObject = container.getObject();
                JiveObject parent = jive.getParent();

                if(activities.findItemById(jiveObject.getId()) != null){
                    continue;
                }

                switch (container.getObject().getType()) {
                    case Unknown:
                        continue;
                    case JiveMessage:
                        continue;
                    case JiveComment:
                        if(parent == null){
                            continue; // TODO why?
                        }
                        BaseJiveObject object = activities.findItemById(parent.getId());
                        if(object == null){
                            JivePost jivePost = mRequestsManager.getPost(parent.getId());
                            if(jivePost.getType() == JiveTypes.JiveDiscussion){
                                object = Discussion.fromJivePost(parent.getId(), jivePost);
                                activities.getItems().add(object);
                            }
                            else{
                            }
                        }
                        continue;
                    case JiveInstance:
                        continue;
                    case JiveGroup:
                        continue;
                    case JivePerson:
                        continue;
                    case JiveDiscussion:
                        activities.getItems().add(Discussion.fromJiveContainer(container));
                        continue;
                    case JiveFile:
                        continue;
                    case JiveSpace:
                        continue;
                    case JiveProject:
                        continue;
                    case JiveLevel:
                        continue;
                }
            }

            return activities;

        } catch (Exception e) {
            getUiManager().showError(getActivity(), e);
        }

        return null;
    }
}
