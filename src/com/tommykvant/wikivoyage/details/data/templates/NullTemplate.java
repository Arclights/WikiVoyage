package com.tommykvant.wikivoyage.details.data.templates;

import java.util.ArrayList;

/**
 * Created by tommy on 2015-02-17.
 */
public class NullTemplate extends Template {

    public NullTemplate(ArrayList<String> parts) {
        super(parts);
    }

    @Override
    protected void parse(ArrayList<String> parts) {

    }

    @Override
    public String getContent() {
        return "";
    }
}
