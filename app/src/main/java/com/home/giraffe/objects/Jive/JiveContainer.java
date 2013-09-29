package com.home.giraffe.objects.Jive;

public class JiveContainer {
    String verb;
    String title;
    String content;
    String published;
    JiveObject object;
    JiveActor actor;
    Jive jive;
    String updated;

    public Jive getJive() {
        return jive;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public JiveObject getObject() {
        return object;
    }

    public JiveActor getActor() {
        return actor;
    }

    public String getPublished() {
        return published;
    }

    public String getUpdated() {
        return updated;
    }

    public JiveVerbTypes getVerbType(){
        if(verb.equals("jive:created")){
            return JiveVerbTypes.JiveCreated;
        }

        if(verb.equals("jive:liked")){
            return JiveVerbTypes.JiveLiked;
        }

        if(verb.equals("jive:modified")){
            return JiveVerbTypes.JiveModified;
        }

        if(verb.equals("jive:replied")){
            return JiveVerbTypes.JiveReplied;
        }

        if(verb.equals("jive:commented")){
            return JiveVerbTypes.JiveCommented;
        }

        if(verb.equals("jive:promoted")){
            return JiveVerbTypes.JivePromoted;
        }

        if(verb.equals("jive:completed")){
            return JiveVerbTypes.JiveCompleted;
        }

        if(verb.equals("jive:correct_answer_set")){
            return JiveVerbTypes.JiveCorrectAnswerSet;
        }

        if(verb.equals("jive:joined")){
            return JiveVerbTypes.JiveJoined;
        }

        if(verb.equals("jive:bookmarked")){
            return JiveVerbTypes.JiveBookmarked;
        }

        if(verb.equals("jive:installed")){
            return JiveVerbTypes.JiveInstalled;
        }

        if(verb.equals("jive:voted")){
            return JiveVerbTypes.JiveVoted;
        }

        if(verb.equals("jive:projectCompleted")){
            return JiveVerbTypes.JiveProjectCompleted;
        }

        return JiveVerbTypes.JiveUnsupported;
    }
}
