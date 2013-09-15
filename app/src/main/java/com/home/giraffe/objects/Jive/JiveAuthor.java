package com.home.giraffe.objects.Jive;


public class JiveAuthor {
    String displayName;
    Jive jive;
    private JiveResources resources;

    public String getDisplayName() {
        return displayName;
    }

    public String getId() {
        return getResources().getSelf().getRef();
    }

    public String getJobTitle(){
        return jive.getJobTitle();
    }

    public JiveResources getResources() {
        return resources;
    }
}
