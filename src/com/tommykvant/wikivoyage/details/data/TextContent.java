package com.tommykvant.wikivoyage.details.data;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.tommykvant.wikivoyage.creators.TextFormatter;
import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.content.ContentHtml;
import com.tommykvant.wikivoyage.details.data.templates.Climate;
import com.tommykvant.wikivoyage.details.data.templates.RegionList;
import com.tommykvant.wikivoyage.details.data.templates.Template;
import com.tommykvant.wikivoyage.details.data.templates.TemplateFactory;
import com.tommykvant.wikivoyage.parsers.LineIterator;
import com.tommykvant.wikivoyage.parsers.StringIterator;

import java.util.ArrayList;

public class TextContent implements Content {

    ArrayList<TextContentContainer> text;

    public TextContent(LineIterator iterator, ArrayList<Content> sectionContent) {
        text = new ArrayList<>();
        parseText(iterator, sectionContent);
    }

    private void parseText(LineIterator iterator, ArrayList<Content> sectionContent) {
        StringBuilder textBuilder = new StringBuilder();
        StringBuilder template = new StringBuilder();
        StringIterator strIter;
        strIter = new StringIterator(iterator.next());
        while (strIter.hasNext()) {
            if (strIter.peekNext2().equals("{{")) {
                parseTemplate(iterator, strIter, sectionContent);
            } else {
                textBuilder.append(strIter.next());
            }
        }
        text.add(new TextContentText(TextFormatter.format(textBuilder.toString())));
    }

    private void parseTemplate(LineIterator lIter, StringIterator sIter, ArrayList<Content> sectionContent) {
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        sb.append(sIter.next());
        sb.append(sIter.next());
        do {
            while (sIter.hasNext()) {
                if (sIter.peekNext2().equals("}}") && depth == 0) {
                    sb.append(sIter.next());
                    sb.append(sIter.next());
                    System.out.println("Template: " + sb.toString());
                    Template template = TemplateFactory.getTemplate(sb.toString());
                    if (template instanceof RegionList || template instanceof Climate) {
                        sectionContent.add((Content) template);
                    } else {
                        text.add(template);
                    }
                    return;
                } else if (sIter.peekNext2().equals("{{")) {
                    depth++;
                } else if (sIter.peekNext2().equals("}}")) {
                    depth--;
                }
                sb.append(sIter.next());
            }
            if (!sIter.hasNext()) {
                sIter = new StringIterator(lIter.next());
            }
        } while (lIter.hasNext());
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
        tv.setLinksClickable(true);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
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
