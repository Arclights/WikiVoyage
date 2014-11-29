package utils;

import android.content.res.Resources;

public class Utils {
	public static int dpsToPixels(Resources res,int dps) {
		final float scale = res.getDisplayMetrics().density;
		return (int) (dps * scale + 0.5f);
	}
}
