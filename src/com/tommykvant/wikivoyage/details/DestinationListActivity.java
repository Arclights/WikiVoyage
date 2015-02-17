package com.tommykvant.wikivoyage.details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.tommykvant.wikivoyage.R;
import com.tommykvant.wikivoyage.details.data.Details;
import com.tommykvant.wikivoyage.details.data.Section;
import com.tommykvant.wikivoyage.fetchers.UriGenerator;
import com.tommykvant.wikivoyage.fetchers.WebFetcher;
import com.tommykvant.wikivoyage.parsers.DetailParser;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * An activity representing a list of Sections. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link DestinationDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link DestinationListFragment} and the item details (if present) is a
 * {@link DestinationDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link DestinationListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class DestinationListActivity extends ActionBarActivity implements
		DestinationListFragment.Callbacks {

	public static final String DETAIL_PAGE_NAME = "com.tommykvant.wikivoyage.detailPageName";
	public static final String DETAIL_DETAILS = "com.tommykvant.wikivoyage.details";

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	private String pageName;
	private Details details;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_section_list);

		if (findViewById(R.id.section_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((DestinationListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.section_list))
					.setActivateOnItemClick(true);
		}

		pageName = getIntent().getExtras().getString(DETAIL_PAGE_NAME);
		Log.d("DestinationListActivity.onCreate", "Starting an activity for "+pageName);
		new DetailLoader().execute(pageName);
		setTitle(pageName);
	}

	private class DetailLoader extends AsyncTask<String, Void, Details> {

		@Override
		protected Details doInBackground(String... params) {
			String page = params[0];
			URI uri = null;
			try {
				uri = UriGenerator.generateDetailURI(page);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String result = WebFetcher.fetch(uri);
            System.out.println("result: "+result);
			Details d = null;
			try {
				d = DetailParser.parse(result, page);
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return d;
		}

		@Override
		protected void onPostExecute(Details result) {
			details = result;
			sendDetailsToFragment();
		}

	}

	private void sendDetailsToFragment() {
		((DestinationListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.section_list)).setDetails(details);
	}

	/**
	 * Callback method from {@link DestinationListFragment.Callbacks} indicating
	 * that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(Section section) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putParcelable(DestinationDetailFragment.ARG_SECTION,
					section);
			DestinationDetailFragment fragment = new DestinationDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.section_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this,
					DestinationDetailActivity.class);
			detailIntent.putExtra(DestinationDetailFragment.ARG_SECTION,
					section);
			startActivity(detailIntent);
		}
	}
}
