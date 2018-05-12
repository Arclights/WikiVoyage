package com.tommykvant.wikivoyage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.tommykvant.wikivoyage.containers.SearchResult;
import com.tommykvant.wikivoyage.details.DestinationListActivity;
import com.tommykvant.wikivoyage.fetchers.UriGenerator;
import com.tommykvant.wikivoyage.fetchers.WebFetcher;
import com.tommykvant.wikivoyage.parsers.SearchParser;
import com.tommykvant.wikivoyage.utils.Utils;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {

	public final static String EXTRA_SEARCH_TERM = "searchTerm";

	private ResultAdapter resultsAdapter;
	private Button moreButton;

	private String searchTerm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_results);

		// TODO Add progress bar

		ListView listView = (ListView) findViewById(R.id.result_list);

		// specify an adapter (see also next example)
		resultsAdapter = new ResultAdapter(this, R.id.search_result);
		listView.setAdapter(resultsAdapter);

		// Creating button to load more results
		moreButton = new Button(this, null,
				android.R.attr.borderlessButtonStyle);
		moreButton.setText(getResources().getText(R.string.search_more));
		moreButton.setHeight(Utils.dpsToPixels(getResources(), 36));
		moreButton.setPadding(0, Utils.dpsToPixels(getResources(), 4), 0,
				Utils.dpsToPixels(getResources(), 4));
//		moreButton.setTextColor(getResources().getColor(
//				R.color.link_text_material_light));
		moreButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new PageLoader().execute(searchTerm,
						resultsAdapter.getLastItem().sroffset + "");

			}
		});
		listView.addFooterView(moreButton);

		searchTerm = getIntent().getExtras().getString(EXTRA_SEARCH_TERM);
		setTitle(getResources().getText(R.string.search_actionbar_title) + " "
				+ searchTerm);

		new PageLoader().execute(searchTerm, "0");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_results, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void gotoDetails(View view) {
		TextView tv = (TextView) view.findViewById(android.R.id.text1);
		System.out.println(tv.getText());
		Intent intent = new Intent(this, DestinationListActivity.class);
        intent.putExtra(DestinationListActivity.DETAIL_PAGE_NAME, tv.getText());
		startActivity(intent);
	}

	/**
	 * 
	 * @author tommy
	 * 
	 *         Param1 - Title to search for Param2 - sroffset
	 */
	private class PageLoader extends
			AsyncTask<String, Void, ArrayList<SearchResult>> {

		@Override
		protected ArrayList<SearchResult> doInBackground(String... params) {
			URI uri = null;
			try {
				uri = UriGenerator.generateSearch(params[0], params[1]);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String result = null;
            try {
                result = WebFetcher.fetch(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArrayList<SearchResult> parsedResults = new ArrayList<SearchResult>();
			try {
				parsedResults = SearchParser.parse(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return parsedResults;
		}

		@Override
		protected void onPostExecute(ArrayList<SearchResult> results) {
			super.onPostExecute(results);
			updateAdapter(results);
		}

	}

	private void updateAdapter(ArrayList<SearchResult> results) {
		resultsAdapter.addAll(results);
		resultsAdapter.notifyDataSetChanged();
	}

	private class ResultAdapter extends ArrayAdapter<SearchResult> {

		public ResultAdapter(Context context, int resource) {
			super(context, resource);
			// TODO Auto-generated constructor stub
		}

		public void addAll(ArrayList<SearchResult> list) {
			for (SearchResult r : list) {
				add(r);
			}
		}

		public SearchResult getLastItem() {
			return getItem(getCount() - 1);
		}

		@SuppressLint("InlinedApi")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.search_item, parent,
						false);
			}

			TextView title = (TextView) convertView
					.findViewById(android.R.id.text1);
			int buildVersion = android.os.Build.VERSION.SDK_INT;
			if (buildVersion >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				if (buildVersion >= Build.VERSION_CODES.LOLLIPOP) {
					title.setTextAppearance(getContext(),
							android.R.style.TextAppearance_Material_Subhead);
				}
				title.setTextAppearance(getContext(),
						android.R.style.TextAppearance_Holo_Medium);
			}
			TextView snippet = (TextView) convertView
					.findViewById(android.R.id.text2);
			SearchResult result = getItem(position);
			title.setText(result.title);
			snippet.setText(result.snippet);
			return convertView;
		}

	}
}
