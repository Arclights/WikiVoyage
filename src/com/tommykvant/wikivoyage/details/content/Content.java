package com.tommykvant.wikivoyage.details.content;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;

public abstract class Content implements Parcelable {

	public abstract View getView(Context context);
}
