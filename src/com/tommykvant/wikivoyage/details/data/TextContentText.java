package com.tommykvant.wikivoyage.details.data;

/**
 * Created by tommy on 2015-02-17.
 */
public class TextContentText implements TextContentContainer {
    String text;

    public TextContentText(String text) {
        this.text = text;
    }

    @Override
    public String getContent() {
        return text;
    }
}
