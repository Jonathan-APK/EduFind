/*package com.example.utsav.edufind;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class tab2UniOptions extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2_uni_options, container, false);
        return rootView;
    }
}
*/

package com.example.utsav.edufind;

import android.content.Intent;
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
import java.util.ArrayList;

import controller.CourseSearchController;
import controller.RVAdapter;
import entity.PolytechnicCourse;
import entity.UniversityCourse;
import entity.Course;

public class tab2UniOptions extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Intent intent;
    private NavigationView navigationView;

    private ArrayList<Course> courseList;
    private RecyclerView rv;

    //Store user inputs from previous activity
    private String interest;
    private String specialization;
    private int L1R4;
    private int postalCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Get data from previous activity
        /*Intent i = getIntent();
        interest = i.getExtras().getString("interest", "No interest found");
        specialization = i.getExtras().getString("specialization", "No specialization found");
        L1R4 = i.getExtras().getInt("L1R4", 20);
        postalCode = i.getExtras().getInt("postalCode", 000000);*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_ui);

        initializeToolbar("Search Results");

        rv= (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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

        if (id == R.id.bookmarkSearchParametersBtn) {
            // BOOKMARK SEARCH PARAMETERS HERE

            // Show confirmation via Builder Design Pattern
            AlertDialog.Builder builder= new AlertDialog.Builder(SearchResultsUI.this);
            builder.setMessage("Search parameters are bookmarked!");
            builder.setPositiveButton("OK", null);

            AlertDialog alert = builder.create();
            alert.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeData(){
        CourseSearchController c1 = new CourseSearchController();
        courseList = new ArrayList<>();
        // courseList = c1.search(interest, specialization, L1R4, postalCode);
/*
Test Code for Polytechnic UI
        courseList.add(new PolytechnicCourse("Diploma in Business Management", "Nanyang Polytechnic",250, 13, "URL"));
        courseList.add(new PolytechnicCourse("Diploma in Digital Media", "Singapore Polytechnic", 400, 11, "URL"));
        courseList.add(new PolytechnicCourse("Diploma in Accountancy", "Singapore Polytechnic", 600, 19, "URL"));
        courseList.add(new PolytechnicCourse("Diploma in Biological Sciences", "Temasek Polytechnic", 300, 12, "URL"));
*/
/*
Test Code for University UI
        courseList.add(new UniversityCourse("Diploma in Business Management", "National University of Singapore",10, 1, "URL"));
        courseList.add(new UniversityCourse("Diploma in Digital Media", "National Institute of Education", 40, 12, "URL"));
        courseList.add(new UniversityCourse("Diploma in Accountancy", "Nanyang Technological University", 60, 15, "URL"));
        courseList.add(new UniversityCourse("Diploma in Biological Sciences", "Digipen Institute of Technology Singapore", 30, 11, "URL"));
*/
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(courseList);
        rv.setAdapter(adapter);
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
                        intent = new Intent(SearchResultsUI.super.getApplication(), NewSearchUI.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;

                    case R.id.bookmarks:
                        intent = new Intent(SearchResultsUI.super.getApplication(), BookmarksUI.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;

                    case R.id.aboutus:
                        intent = new Intent(SearchResultsUI.super.getApplication(), AboutUs.class);
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