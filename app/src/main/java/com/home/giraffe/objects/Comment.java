package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.JiveContainer;

import java.text.ParseException;

public class Comment extends BaseObjectWithContent implements Comparable<Comment> {
    private int mLevel;

    public Comment(JiveContainer jiveContainer) throws ParseException {
        super(null);
        fromJiveContainer(jiveContainer);
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

        result.append(" Id: " + getId() + "\n");
        result.append(" Parent Id: " + getParentId());

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
