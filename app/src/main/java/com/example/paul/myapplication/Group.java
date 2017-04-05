package com.example.paul.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 17/08/2016.
 */
public class Group {
    public String string;
    public final List<String> children = new ArrayList<String>();

    public Group(String string) {
        this.string = string;
    }
}
