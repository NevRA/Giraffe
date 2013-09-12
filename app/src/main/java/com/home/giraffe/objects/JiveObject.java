package com.home.giraffe.objects;

public class JiveObject {
    private String id;
    private String summary;
    private String displayName;
    private String objectType;

    public String getDisplayName() {
        return displayName;
    }

    public String getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public JiveTypes getObjectType() {
        if (objectType.equals("jive:message")){
            return JiveTypes.JiveMessage;
        }

        if (objectType.equals("jive:instance")){
            return JiveTypes.JiveInstance;
        }

        if (objectType.equals("jive:group")){
            return JiveTypes.JiveGroup;
        }

        if (objectType.equals("jive:person")){
            return JiveTypes.JivePerson;
        }

        if (objectType.equals("jive:discussion")){
            return JiveTypes.JiveDiscussion;
        }

        if (objectType.equals("jive:file")){
            return JiveTypes.JiveFile;
        }

        if (objectType.equals("jive:space")){
            return JiveTypes.JiveSpace;
        }

        if (objectType.equals("jive:project")){
            return JiveTypes.JiveProject;
        }

        return JiveTypes.Unknown;
    }
}

