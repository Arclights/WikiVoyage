package com.tommykvant.wikivoyage.details.data;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.parsers.LineIterator;
import com.tommykvant.wikivoyage.parsers.StringIterator;
import com.tommykvant.wikivoyage.utils.Utils;

import java.util.ArrayList;

public class IndentedTextContent extends TextContent {

    public IndentedTextContent(StringIterator iterator, ArrayList<Content> sectionContent) {
        super(iterator, sectionContent);
    }

    @Override
    public View getView(Context context) {
        TextView tv = (TextView) super.getView(context);
        tv.setPadding(Utils.dpsToPixels(context.getResources(), 10), 0, 0,
                0);
        return tv;
    }

}
