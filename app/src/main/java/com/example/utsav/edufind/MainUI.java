package com.example.utsav.edufind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Initialize and display Main Home page
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class MainUI extends AppCompatActivity{

    /**
     * Initialize layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Instantiate menu XML files into Menu objects when menu options are created
     * @param menu The menu in the side pane
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles event when a menu option is selected
     * @param item An item button in the menu of the side pane
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    /**
     * Direct to New Search view
     * @param view The current view
     */
    public void goToSearchUI(View view) {
        Intent intent = new Intent(this, NewSearchUI.class);
        startActivity(intent);
    }

    /**
     * Direct to Saved Bookmarks view
     * @param view The current view
     */
    public void goToBookmarksUI(View view) {
        Intent intent = new Intent(this, BookmarksUI.class);
        startActivity(intent);
    }
}