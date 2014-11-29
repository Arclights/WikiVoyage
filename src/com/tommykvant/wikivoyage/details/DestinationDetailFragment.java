package com.tommykvant.wikivoyage.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tommykvant.wikivoyage.R;
import com.tommykvant.wikivoyage.details.data.Section;

/**
 * A fragment representing a single Section detail screen. This fragment is
 * either contained in a {@link DestinationListActivity} in two-pane mode (on
 * tablets) or a {@link DestinationDetailActivity} on handsets.
 */
public class DestinationDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";
	public static final String ARG_SECTION = "com.tommykvant.wikivoyage.details.section";

	private Section section;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public DestinationDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_SECTION)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			section = (Section) getArguments().getParcelable(ARG_SECTION);
		}
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_section_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (section != null) {
			((TextView) rootView.findViewById(R.id.section_detail))
					.setText(section.text);
		}

		return rootView;
	}
}
