package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.JiveContainer;
import com.home.giraffe.objects.Jive.JivePost;

import java.text.ParseException;

public class Comment extends BaseObjectWithContent implements Comparable<Comment> {
    private int mLevel;

    public Comment(JiveContainer jiveContainer) throws ParseException {
        super(null);
        fromJiveActivityContainer(jiveContainer);
    }

    public Comment(JivePost jivePost) throws ParseException {
        super(null);
        fromJivePost(jivePost);
    }

    public int getLevel(){
        return mLevel;
    }

    public void setLevel(int level){
        mLevel = level;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof Comment){
            return getId().equals(((Comment)another).getId());
        }

        return super.equals(another);
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("\n");
        result.append(" Id: " + getId() + "\n");
        result.append(" Parent Id: " + getParentId() + "\n");
        result.append(" Level: " + getLevel());

        return result.toString();
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public int compareTo(Comment another) {
        return getId().compareTo(another.getId());
    }
}
