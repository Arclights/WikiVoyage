package com.tommykvant.wikivoyage.details.data;

import utils.Utils;
import android.content.Context;
import android.os.Parcel;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tommykvant.wikivoyage.details.content.Content;

public class ListContentItem extends Content{
	Content item;

	public ListContentItem(Content item) {
		this.item = item;
	}

	public View getView(Context context) {
		RelativeLayout l = new RelativeLayout(context);
		l.setPadding(Utils.dpsToPixels(context.getResources(), 5), 0, 0, 0);

		TextView bullet = new TextView(context);
		bullet.setText("•");
		bullet.setId(1542);

		l.addView(bullet);

		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		lp.addRule(RelativeLayout.RIGHT_OF, bullet.getId());
		l.addView(item.getView(context), lp);

		return l;
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
