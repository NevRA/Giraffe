package com.home.giraffe.objects;


import java.util.ArrayList;

public class Jive {
    JiveObject parent;
    String username;
    boolean question;
    int replyCount;

    ArrayList<Profile> profile;

    public String getParentId() {
        return parent.getId();
    }

    public String getParentSummary() {
        return parent.getSummary();
    }

    public int getReplyCount(){
        return replyCount;
    }

    public boolean isQuestion(){
        return question;
    }

    public String getJobTitle(){
        for (Profile prof : profile){
            if(prof.jive_label.equalsIgnoreCase("title")){
                return prof.value;
            }
        }

        return null;
    }
}
