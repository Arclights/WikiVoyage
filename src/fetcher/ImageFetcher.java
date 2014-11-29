package fetcher;

import java.io.InputStream;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageFetcher {
	public static Bitmap fetch(URI url) {
		Log.d("ImageFetcher", "URI: "+url);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		Bitmap out = null;
		try {
			HttpResponse execute = client.execute(httpGet);
			InputStream content = execute.getEntity().getContent();
			out = BitmapFactory.decodeStream(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ImageFetcher: "+out);
		return out;
	}
}
