package com.tommykvant.wikivoyage.details.data;

import android.content.Context;
import android.os.Parcel;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.tommykvant.wikivoyage.creators.TextFormatter;
import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.content.ContentHtml;
import com.tommykvant.wikivoyage.parsers.LineIterator;
import com.tommykvant.wikivoyage.parsers.StringIterator;

public class TextContent extends Content {

	String text;

	public TextContent(LineIterator iterator) {
//		if (iterator.peekNext().contains("{{")) {
//            System.out.println("To parse with templates");
//            this.text = parseWithTemplates(iterator);
//		} else {
			this.text = TextFormatter.format(iterator.next());
//		}
	}

	private String parseWithTemplates(LineIterator iterator) {
		StringBuilder textBuilder = new StringBuilder();
		StringBuilder template = new StringBuilder();
		StringIterator strIter;
		boolean foundTemplate = false;
		while (iterator.hasNext()) {
			strIter = new StringIterator(iterator.next());
			while (strIter.hasNext()) {
				if (strIter.peekNext2().equals("{{")) {
					strIter.next();
					strIter.next();
					foundTemplate = true;
				} else if (strIter.peekNext2().equals("}}")) {
					foundTemplate = false;
				} else if (!foundTemplate) {
					textBuilder.append(strIter.next());
				} else {
					template.append(strIter.next());
				}
			}
		}
        System.out.println(textBuilder);
        return textBuilder.toString();
	}

	@Override
	public View getView(Context context) {
		TextView tv = new TextView(context);
		tv.setText(ContentHtml.fromHtml(text));
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
