package com.home.giraffe.objects;

public abstract class BaseJiveObject {
    private BaseJiveObjectTypes mType;
    private String mId;
    private Author mAuthor;
    private String mTitle;
    private String mContent;

    public BaseJiveObject(String id, BaseJiveObjectTypes type){
        setId(id);
        setType(type);
    }

    public Author getAuthor() {
        return mAuthor;
    }

    public void setAuthor(Author author) {
        mAuthor = author;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public BaseJiveObjectTypes getType() {
        return mType;
    }

    public void setType(BaseJiveObjectTypes type) {
        mType = type;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
