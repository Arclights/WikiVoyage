package com.tommykvant.wikivoyage.details.data.templates;

/**
 * Created by tommy on 2015-02-17.
 */
public class TemplateFactory {

    public static Template getTemplate(String content) {
        String[] parts = content.substring(2, content.length() - 3).split("\\|");
        String type = parts[0];

        switch (type) {
            case "do":
                return new Do(parts);
            case "see":
                return new See(parts);
            case "eat":
                return new Eat(parts);
            case "sleep":
                return new Sleep(parts);
            case "listing":
                return new Listing(parts);
            default:
                return new NullTemplate(parts);
        }
    }
}
