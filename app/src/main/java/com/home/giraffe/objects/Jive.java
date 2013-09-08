package com.home.giraffe.objects;


import java.util.ArrayList;

public class Jive {
    String username;
    ArrayList<Profile> profile;

    public String getJobTitle(){
        for (Profile prof : profile){
            if(prof.jive_label.equalsIgnoreCase("title")){
                return prof.value;
            }
        }

        return null;
    }
}
