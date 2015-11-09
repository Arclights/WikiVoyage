package com.tommykvant.wikivoyage.creators;


import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class TextFormatter {
    public static String format(String text) {
        // Bold
        text = text.replaceAll("\'\'\'(.+?)\'\'\'", "<b>$1</b>");
        // Iltalic
        text = text.replaceAll("\'\'(.+?)\'\'", "<i>$1</i>");
        // Bold italic
        text = text.replaceAll("\'\'\'\'\'(.+?)\'\'\'\'\'", "<b><i>$1</i></b>");
        // Internal links without text
        text = text.replaceAll("\\[\\[([^:\\|]+?)\\]\\]",
                "<a href=\"$1\">$1</a>");
        // Internal links with text
        text = text.replaceAll("\\[\\[([^:\\|]+?)\\|(.+?)\\]\\]",
                "<a href=\"$1\">$2</a>");
        // Links without text
        text = text.replaceAll("(?<!\\[)\\[([^ \\[]+?)\\]",
                "<a href=\"$1\">Link</a>");
        // Links with text
        text = text.replaceAll("(?<!\\[)\\[([^ \\[]+?) (.+?)\\]",
                "<a href=\"$1\">$2</a>");
        // Skip images
        text = text.replaceAll("\\[\\[Image:(\\w+)\\.\\w+(?:\\|.*)?\\]\\]", "<img src=$1>");
        // Skip files
        text = text.replaceAll("\\[\\[File.+?\\]\\]", "");
        // Remove list markup
        text = text.replaceAll("^\\*+", "");
        // Remove indentation markup
        text = text.replaceAll("^:+", "");
        // Handle forecastNOAA nested template
        text = text.replaceAll("\\{\\{forecastNOAA\\|([\\w\\s]+?)\\|([\\-\\d.]+?)\\|([\\-\\d.]+?)\\}\\}", "<a href=\"http://forecast.weather.gov/MapClick.php?textField1=$2&textField2=$3\">See $1's 7 day forecast</a>");
        // Handle nested IATA template
        text = text.replaceAll("\\{\\{IATA\\|(\\w+)\\}\\}", "<a href=\"https://en.wikipedia.org/wiki/IATA_code\">IATA</a>: <b>$1</b>");

        return text;
    }

    public static void makeTextViewLinkFriendly(TextView tv) {
        tv.setLinksClickable(true);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
