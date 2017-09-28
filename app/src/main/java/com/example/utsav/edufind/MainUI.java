package com.example.utsav.edufind;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import controller.DistanceCalculation;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainUI extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This is just a sample usage of the function
        DistanceCalculation d1 = new DistanceCalculation();
        d1.execute("637658", "637659");
        try {
            double distance = d1.get();
            Log.d("distance", String.valueOf(distance));
        }catch (Exception e){
            Log.d("distance_error", e.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user taps newSearchButton */
    public void goToSearchUI(View view) {
        Intent intent = new Intent(this, NewSearchUI.class);
        startActivity(intent);
    }

    public void goToBookmarksUI(View view) {
        Intent intent = new Intent(this, BookmarksUI.class);
        startActivity(intent);
    }

    public void goToSearchResultsUI(View view) {
        Intent intent = new Intent(this, SearchResultsUI.class);
        startActivity(intent);
    }
}