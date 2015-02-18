package com.tommykvant.wikivoyage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        EditText searchBox = (EditText) findViewById(R.id.searchBox);
        searchBox.setOnEditorActionListener(new OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                search(null);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    public void search(View view) {
        String searchWord = ((EditText) findViewById(R.id.searchBox)).getText()
                .toString();
//		searchWord = "Stockholm";
        Intent intent = new Intent(this, SearchResults.class);
        if (searchWord.equals("")) {
            intent.putExtra(SearchResults.EXTRA_SEARCH_TERM, "Stockholm");
        } else {
            intent.putExtra(SearchResults.EXTRA_SEARCH_TERM, searchWord);
        }
        startActivity(intent);
    }

}
