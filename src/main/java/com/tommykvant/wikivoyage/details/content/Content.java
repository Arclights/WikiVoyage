package com.tommykvant.wikivoyage.details.content;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;

public interface Content extends Parcelable {

	public abstract View getView(Context context);
}
