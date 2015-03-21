package com.tommykvant.wikivoyage.details.data;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import com.tommykvant.wikivoyage.creators.TextFormatter;
import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.content.ContentHtml;
import com.tommykvant.wikivoyage.details.data.templates.Climate;
import com.tommykvant.wikivoyage.details.data.templates.RegionList;
import com.tommykvant.wikivoyage.details.data.templates.RouteBox;
import com.tommykvant.wikivoyage.details.data.templates.Template;
import com.tommykvant.wikivoyage.details.data.templates.TemplateFactory;
import com.tommykvant.wikivoyage.parsers.StringIterator;

import java.util.ArrayList;

public class TextContent implements Content {

    ArrayList<TextContentContainer> text;

    public TextContent(StringIterator iterator, ArrayList<Content> sectionContent) {
        text = new ArrayList<>();
        parseText(iterator, sectionContent);
    }

    private void parseText(StringIterator iter, ArrayList<Content> sectionContent) {
        StringBuilder textBuilder = new StringBuilder();
        while (iter.hasNext() && iter.peekNext() != '\n') {
            if (iter.isAtStartOfTemplate()) {
                text.add(new TextContentText(TextFormatter.format(textBuilder.toString())));
                textBuilder = new StringBuilder();
                parseTemplate(iter, sectionContent);
            } else {
                textBuilder.append(iter.next());
            }
        }
        if (iter.hasNext() && iter.peekNext() == '\n') {
            iter.next();
        }
        text.add(new TextContentText(TextFormatter.format(textBuilder.toString())));
    }

    private void parseTemplate(StringIterator iter, ArrayList<Content> sectionContent) {
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        sb.append(iter.next());
        sb.append(iter.next());
        while (iter.hasNext()) {
            if (iter.isAtEndOfTemplate() && (depth == 0)) {
                sb.append(iter.next());
                sb.append(iter.next());
                System.out.println("Template: " + sb.toString() + " :Template");
                Template template = TemplateFactory.getTemplate(sb.toString());
                if (template instanceof RegionList || template instanceof Climate || template instanceof RouteBox) {
                    sectionContent.add((Content) template);
                } else {
                    text.add(template);
                }
                return;
            } else if (iter.isAtStartOfTemplate()) {
                depth++;
            } else if (iter.isAtEndOfTemplate()) {
                depth--;
            }
            if (iter.peekNext() == '\n') {
                iter.next();
            } else {
                sb.append(iter.next());
            }
        }
    }

    private String gatherText() {
        StringBuilder out = new StringBuilder();
        for (TextContentContainer t : text) {
            out.append(t.getContent());
        }
        return out.toString();
    }

    @Override
    public View getView(Context context) {
        TextView tv = new TextView(context);
        tv.setText(ContentHtml.fromHtml(gatherText(), context));
        TextFormatter.makeTextViewLinkFriendly(tv);
        return tv;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(text);
    }

    public static final Parcelable.Creator<TextContent> CREATOR
            = new Parcelable.Creator<TextContent>() {
        public TextContent createFromParcel(Parcel in) {
            return new TextContent(in);
        }

        public TextContent[] newArray(int size) {
            return new TextContent[size];
        }
    };

    private TextContent(Parcel in) {
        text = new ArrayList<>();
        in.readList(text, TextContentContainer.class.getClassLoader());
    }

}
