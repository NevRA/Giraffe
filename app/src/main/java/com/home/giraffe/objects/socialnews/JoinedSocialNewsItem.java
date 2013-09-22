package com.home.giraffe.objects.socialnews;

import com.home.giraffe.objects.Jive.JiveContainer;
import com.home.giraffe.objects.Jive.JiveObject;

public class JoinedSocialNewsItem extends BaseSocialNewsItem {
    private String mGroup;

    public JoinedSocialNewsItem(JiveContainer jiveContainer){
        fromJiveContainer(jiveContainer);
    }

    public String getGroup() {
        return mGroup;
    }

    public void setGroup(String group) {
        this.mGroup = group;
    }

    @Override
    public void fromJiveContainer(JiveContainer jiveContainer) {
        super.fromJiveContainer(jiveContainer);

        JiveObject jiveObject = jiveContainer.getObject();
        setGroup(jiveObject.getDisplayName());
    }
}
