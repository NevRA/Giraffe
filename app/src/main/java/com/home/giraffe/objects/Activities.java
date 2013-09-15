package com.home.giraffe.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Activities {
    private List<ActivityItem> mActivityItems;

    public Activities() {
        mActivityItems = Collections.synchronizedList(new ArrayList<ActivityItem>());
    }

    public List<ActivityItem> getActivities() {
        return mActivityItems;
    }

    public void addActivity(ActivityItem activityItem) {
        mActivityItems.add(activityItem);
    }

    public ActivityItem getActivity(String id, BaseObjectTypes type) {
        for (ActivityItem activity : getActivities()) {
            if (activity.getId().equalsIgnoreCase(id) &&
                    activity.getType() == type) {
                return activity;
            }
        }
        return null;
    }
}
