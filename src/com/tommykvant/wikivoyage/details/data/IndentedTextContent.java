package com.tommykvant.wikivoyage.details.data;

import parsers.LineIterator;
import utils.Utils;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class IndentedTextContent extends TextContent {

	public IndentedTextContent(LineIterator iterator) {
		super(iterator);
	}

	@Override
	public View getView(Context context) {
		TextView tv = (TextView) super.getView(context);
		tv.setPadding(Utils.dpsToPixels(context.getResources(), 10), 0, 0,
				0);
		return tv;
	}

}
