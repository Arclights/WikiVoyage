package com.tommykvant.wikivoyage.details.data;

import parsers.LineIterator;
import android.content.Context;
import android.os.Parcel;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.tommykvant.wikivotage.creators.TextFormatter;
import com.tommykvant.wikivoyage.details.content.Content;
import com.tommykvant.wikivoyage.details.content.ContentHtml;

public class TextContent extends Content {

	String text;

	public TextContent(LineIterator iterator) {
		this.text = TextFormatter.format(iterator.next());
		// TODO deal with templates
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
