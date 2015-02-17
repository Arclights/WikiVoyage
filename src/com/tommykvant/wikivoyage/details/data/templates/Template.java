package com.tommykvant.wikivoyage.details.data.templates;

import com.tommykvant.wikivoyage.details.data.TextContentContainer;

/**
 * Created by tommy on 2015-02-17.
 */
public abstract class Template implements TextContentContainer {

    public String type;

    public Template(String[] parts) {
        parse(parts);
    }

    protected abstract void parse(String[] parts);
}
