package com.home.giraffe.objects.Jive;

public class JivePost {
    private String subject;
    private JiveAuthor author;
    private JiveContent content;
    private boolean question;
    private String type;
    private JiveResources resources;

    public String getId() {
        return getResources().getSelf().getRef();
    }

    public JiveAuthor getAuthor() {
        return author;
    }

    public JiveContent getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isQuestion(){
        return question;
    }

    public JiveResources getResources() {
        return resources;
    }

    public JiveTypes getType() {
        if (type.equals("message")){
            return JiveTypes.JiveMessage;
        }

        if (type.equals("comment")){
            return JiveTypes.JiveComment;
        }

        if (type.equals("instance")){
            return JiveTypes.JiveInstance;
        }

        if (type.equals("group")){
            return JiveTypes.JiveGroup;
        }

        if (type.equals("person")){
            return JiveTypes.JivePerson;
        }

        if (type.equals("discussion")){
            return JiveTypes.JiveDiscussion;
        }

        if (type.equals("file")){
            return JiveTypes.JiveFile;
        }

        if (type.equals("document")){
            return JiveTypes.JiveDocument;
        }

        if (type.equals("space")){
            return JiveTypes.JiveSpace;
        }

        if (type.equals("project")){
            return JiveTypes.JiveProject;
        }

        if (type.equals("level")){
            return JiveTypes.JiveLevel;
        }

        if (type.equals("task")){
            return JiveTypes.JiveTask;
        }

        return JiveTypes.Unknown;
    }
}
