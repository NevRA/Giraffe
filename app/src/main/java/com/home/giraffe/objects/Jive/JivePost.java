package com.home.giraffe.objects.Jive;

public class JivePost {
    private String id;
    private String parent;
    private String subject;
    private String name;
    private String contentType;
    private String binaryURL;
    private JiveAuthor author;
    private JiveContent content;
    private boolean question;
    private String type;
    private JiveResources resources;
    private int replyCount;
    private int likeCount;
    private String published;
    private String updated;

    public String getParent(){
        return parent;
    }

    public String getId() {
        return getResources().getSelf().getRef();
    }

    public String getJiveId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBinaryURL() {
        return binaryURL;
    }


    public String getContentType() {
        return contentType;
    }

    public String getCommentsId() {
        if(getResources().getCommentsId() != null)
            return getResources().getCommentsId().getRef() + "?count=100"; // max comments limit
        return null;
    }

    public JiveAuthor getAuthor() {
        return author;
    }

    public void setContent(JiveContent content) {
        this.content = content;
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

    public int getReplyCount(){
        return replyCount;
    }

    public int getLikeCount(){
        return likeCount;
    }

    public JiveResources getResources() {
        return resources;
    }

    public String getPublished() {
        return published;
    }

    public String getUpdated() {
        return updated;
    }

    public void setType(JiveTypes jiveType){
        switch (jiveType) {

            case Unknown:
                break;
            case Unsupported:
                break;
            case JiveMessage:
                type = "message";
                break;
            case JiveComment:
                type = "comment";
                break;
            case JiveInstance:
                break;
            case JiveGroup:
                break;
            case JivePerson:
                break;
            case JiveDiscussion:
                break;
            case JiveDocument:
                break;
            case JiveFile:
                break;
            case JiveSpace:
                break;
            case JiveProject:
                break;
            case JivePoll:
                break;
            case JiveTask:
                break;
            case JiveLevel:
                break;
            case JivePost:
                break;
        }
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

        if (type.equals("post")){
            return JiveTypes.JivePost;
        }

        if (type.equals("document")){
            return JiveTypes.JiveDocument;
        }

        if (type.equals("poll")){
            return JiveTypes.JivePoll;
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

        return JiveTypes.Unsupported;
    }
}
