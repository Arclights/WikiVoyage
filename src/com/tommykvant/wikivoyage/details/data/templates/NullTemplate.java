package com.tommykvant.wikivoyage.details.data.templates;

/**
 * Created by tommy on 2015-02-17.
 */
public class NullTemplate extends Template {

    public NullTemplate(String[] parts) {
        super(parts);
    }

    @Override
    protected void parse(String[] parts) {

    }

    @Override
    public String getContent() {
        return "";
    }
}
