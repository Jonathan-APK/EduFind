package boundary;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.utsav.edufind.MainAppUI;
import com.example.utsav.edufind.R;

import java.util.Calendar;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import strategy.BookmarkImplementation;
import factory.DataStoreFactory;
import strategy.DataStoreInterface;
import controller.SearchController;

import entity.Course;
import entity.UniversityCourse;
import entity.Bookmark;

/**
 * This class displays the search results of polytechnic courses based on the user's
 * input in the form of a recycler and card view.
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class SearchResultsUI extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Intent intent;
    private NavigationView navigationView;

    private ArrayList<Course> courseList;
    private RecyclerView rv;

    //Store user inputs from previous activity
    private String interest;
    private String specialisation;
    private int L1R4;
    private int postalCode;
    private ProgressDialog dialog;
    private boolean click = true;
    private ArrayList<Bookmark> bookmarkList;

    private class ProgressTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            initializeData();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    initializeAdapter();

                }
            });
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            dialog.dismiss();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Get data from previous activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_ui);

       dialog = ProgressDialog.show(this, "",
                "Loading. Please wait...", true);

        Intent i = getIntent();
        interest = i.getExtras().getString("interest", "No interest found");
        specialisation = i.getExtras().getString("specialisation", "No specialisation found");
        L1R4 = i.getExtras().getInt("L1R4", 20);
        postalCode = i.getExtras().getInt("postalCode", 000000);

        if (postalCode == 574415) {
            postalCode = 000000;
        }


        initializeToolbar("Search Results");

        rv= (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        new ProgressTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

       /* initializeData();
        initializeAdapter();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        DataStoreInterface di = DataStoreFactory.createDatastore("bookmark",this);
        bookmarkList = new ArrayList<>();
        bookmarkList = (ArrayList<Bookmark>)(Object)di.retrieveList();
        for(int j =0; j<bookmarkList.size(); j++)
        {
            if(bookmarkList.get(j).getInterest().equals(interest) && bookmarkList.get(j).getspecialisation().equals(specialisation) && bookmarkList.get(j).getL1R4() == L1R4 && bookmarkList.get(j).getPostalCode() == postalCode)
            {
               menu.getItem(0).setIcon(R.drawable.heart);
               click=false;
                break;
            }
        }

        // Make bookmark icon visible
        menu.getItem(0).setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        df2.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        String date = df.format(c.getTime());
        String time = df2.format(c.getTime());
        Bookmark bm = new Bookmark(interest, specialisation, L1R4, postalCode, date, time);

        DataStoreInterface di = DataStoreFactory.createDatastore("bookmark",this);

        if (click) {
            if (id == R.id.bookmarkSearchParametersBtn) {
                //Add a bookmark
                click=false;
                item.setIcon(R.drawable.heart);
                ((BookmarkImplementation)di).addBookmark(bm);

                // Show confirmation via Builder Design Pattern
                AlertDialog.Builder builder= new AlertDialog.Builder(SearchResultsUI.this);
                builder.setMessage("Search parameters are bookmarked!");
                builder.setPositiveButton("OK", null);
                AlertDialog alert = builder.create();
                alert.show();

                return true;
            }}
        else {
            //Remove a Bookmark
            click=true;
            bookmarkList = new ArrayList<>();
            bookmarkList = (ArrayList<Bookmark>)(Object)di.retrieveList();


            for(int j =0; j<bookmarkList.size(); j++)
            {
                if(bookmarkList.get(j).getInterest().equals(interest) && bookmarkList.get(j).getspecialisation().equals(specialisation) && bookmarkList.get(j).getL1R4() == L1R4 && bookmarkList.get(j).getPostalCode() == postalCode)
                {
                    item.setIcon(R.drawable.heartunchecked);
                    bookmarkList.remove(bookmarkList.get(j));
                    ((BookmarkImplementation)di).updateBookmark(bookmarkList);
                    // Show confirmation via Builder Design Pattern
                    AlertDialog.Builder builder= new AlertDialog.Builder(SearchResultsUI.this);
                    builder.setMessage("Bookmark removed!");
                    builder.setPositiveButton("OK", null);
                    AlertDialog alert = builder.create();
                    alert.show();
                    break;
                }
            }


        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method retrieves all the search results from the course search controller class
     * and puts them in an ArrayList of Course objects consisting of only polytechnic courses.
     */
    public void initializeData(){
        SearchController c1 = new SearchController(this);
        courseList = c1.search(interest, specialisation, L1R4, postalCode);

        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i) instanceof UniversityCourse) {
                courseList.remove(i--);
            }
        }
    }

    /**
     * This method sets and displays the layout of the recycler view with the user's input parameters
     */
    private void initializeAdapter(){
        CoursesRVAdapter adapter = new CoursesRVAdapter(courseList, interest, specialisation, postalCode);
        rv.setAdapter(adapter);

        if (adapter.getItemCount() == 0) {
            // Show no results
            AlertDialog.Builder builder = new AlertDialog.Builder(SearchResultsUI.this);
            builder.setMessage("Your search returned no results!");
            builder.setPositiveButton("Return", new DialogInterface.OnClickListener() { //when click on DELETE
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();  //show alert dialog
        }
    }

    public void initializeToolbar(@NonNull String toolbarTitle){
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(toolbarTitle);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home:
                        intent = new Intent(SearchResultsUI.super.getApplication(), MainAppUI.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;
                    case R.id.bookmarks:
                        intent = new Intent(SearchResultsUI.super.getApplication(), BookmarksUI.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;
                    case R.id.aboutus:
                        intent = new Intent(SearchResultsUI.super.getApplication(), AboutUsUI.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;
                    default:
                        return true;
                }
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,myToolbar,R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
            mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        //calling sync state is necessary or else your hamburger icon wont show up
            actionBarDrawerToggle.syncState();
    }
}