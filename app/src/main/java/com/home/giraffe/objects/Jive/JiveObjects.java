package com.home.giraffe.objects.Jive;

import java.util.ArrayList;

public class JiveObjects<T> {
    private ArrayList<T> list;
    private JiveLinks links;

    public ArrayList<T> getList() {
        return list;
    }

    public JiveLinks getLinks() {
        return links;
    }
}
