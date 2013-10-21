package com.home.giraffe.objects.Jive;

import com.home.giraffe.utils.Utils;

public class JiveContent {
    private String type;
    private String text;

    public JiveContent(String text){
        this.type = "text/html";
        this.text = Utils.addHtmlTags(text);
    }

    public String getText() {
        return text;
    }
}
