package com.tommykvant.wikivoyage.details.data;

import android.content.Context;
import android.os.Parcel;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.tommykvant.wikivoyage.creators.TextFormatter;
import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.content.ContentHtml;
import com.tommykvant.wikivoyage.details.data.templates.Template;
import com.tommykvant.wikivoyage.details.data.templates.TemplateFactory;
import com.tommykvant.wikivoyage.parsers.LineIterator;
import com.tommykvant.wikivoyage.parsers.StringIterator;

import java.util.ArrayList;

public class TextContent extends Content {

    ArrayList<TextContentContainer> text;

    public TextContent(LineIterator iterator) {
        text = new ArrayList<>();
        parseText(iterator);
    }

    private void parseText(LineIterator iterator) {
        StringBuilder textBuilder = new StringBuilder();
        StringBuilder template = new StringBuilder();
        StringIterator strIter;
        strIter = new StringIterator(iterator.next());
        while (strIter.hasNext()) {
            if (strIter.peekNext2().equals("{{")) {
                strIter = parseTemplate(iterator, strIter);
            } else {
                textBuilder.append(strIter.next());
            }
        }
        text.add(new TextContentText(TextFormatter.format(textBuilder.toString())));
    }

    private StringIterator parseTemplate(LineIterator lIter, StringIterator sIter) {
        StringBuilder sb = new StringBuilder();
        sb.append(sIter.next());
        sb.append(sIter.next());
        // Dummy for now
        do {
            while (sIter.hasNext()) {
                if (sIter.peekNext2().equals("}}")) {
                    sb.append(sIter.next());
                    sb.append(sIter.next());
                    System.out.println("Template: " + sb.toString());
                    text.add(TemplateFactory.getTemplate(sb.toString()));
                    return sIter;
                }
                sb.append(sIter.next());
            }
            if (!sIter.hasNext()) {
                sIter = new StringIterator(lIter.next());
            }
        } while (lIter.hasNext());
        return sIter;
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
        // TODO Auto-generated method stub

    }

}
