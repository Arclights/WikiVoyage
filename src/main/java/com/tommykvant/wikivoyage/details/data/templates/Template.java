package com.tommykvant.wikivoyage.details.data.templates;

import com.tommykvant.wikivoyage.details.data.TextContentContainer;

import java.util.ArrayList;

/**
 * Created by tommy on 2015-02-17.
 */
public abstract class Template implements TextContentContainer {

    public String type;

    public Template(ArrayList<String> parts) {
        parse(parts);
    }

    public Template(){}

    protected abstract void parse(ArrayList<String> parts);
}
