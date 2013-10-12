package com.home.giraffe.objects.Jive;

public class JiveContent {
    private String type;
    private String text;

    public JiveContent(String text){
        this.type = "text/html";
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
