package fetcher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class WebFetcher {

	public static String fetch(URI url) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		StringBuilder out = new StringBuilder();
		try {
			HttpResponse execute = client.execute(httpGet);
			InputStream content = execute.getEntity().getContent();

			BufferedReader buffer = new BufferedReader(new InputStreamReader(
					content));
			String line = "";
			while ((line = buffer.readLine()) != null) {
				out.append(line).append("\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return out.toString();
	}
}
