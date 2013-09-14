package com.home.giraffe.objects.Jive;


public class JiveAuthor {
    String displayName;
    int id;
    Jive jive;
    private JiveResources resources;

    public String getDisplayName() {
        return displayName;
    }

    public int getId() {
        return id;
    }

    public String getJobTitle(){
        return jive.getJobTitle();
    }

    public JiveResources getResources() {
        return resources;
    }
}
