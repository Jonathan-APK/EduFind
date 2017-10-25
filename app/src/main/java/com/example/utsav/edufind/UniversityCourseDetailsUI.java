package com.example.utsav.edufind;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import controller.MapController;

/**
 * Initializes and display University Course Details view for the selected University Course
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class UniversityCourseDetailsUI extends AppCompatActivity implements OnMapReadyCallback {
    private DrawerLayout mDrawerLayout;
    private Intent intent;
    private NavigationView navigationView;
    TextView CourseGrade;
    TextView CourseIntake;
    TextView CourseName;
    TextView InstitutionName;
    ImageView InstitutionLogo;
    ImageView CourseWebsite;
    TextView institutionDescription;
    TextView courseDescription;
    TextView career;
    TextView direction;
    TextView CourseDescription;
    TextView InstitutionDescription;
    GoogleMap mGoogleMap;
    String postalCode;
    String insName;

    /**
     * Sets data and display details of the Course dynamically
     * @param savedInstanceState Current state of application
     * @return View University Course Details view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.university_course_details_ui);
        //INIT TOOLBAR
        initializeToolbar("University Details");
        CourseGrade = (TextView) findViewById(R.id.uni_Course_Grade);
        CourseIntake = (TextView) findViewById(R.id.uni_Course_Intake);
        CourseName = (TextView) findViewById(R.id.uni_Course_name);
        InstitutionName = (TextView)findViewById(R.id.uni_Institution_name);
        InstitutionLogo = (ImageView) findViewById(R.id.uni_Institution_Logo);
        CourseWebsite = (ImageView) findViewById(R.id.uni_Course_Website);
        institutionDescription = (TextView) findViewById(R.id.uni_institution_desc_text);
        courseDescription = (TextView) findViewById(R.id.uni_course_desc_text);
        direction = (TextView) findViewById(R.id.uni_direction);
        career = (TextView) findViewById(R.id.uni_career_prospect_text);
        CourseDescription = (TextView) findViewById(R.id.uni_course_desc_detail_text);
        InstitutionDescription = (TextView) findViewById(R.id.uni_institution_desc_detail_text);

        Intent i = getIntent();
        String courseName = i.getExtras().getString("courseName", "No courseName found");
        final String courseWebsite = i.getExtras().getString("courseWebsite", "No courseWebsite found");
        String institutionDesc = i.getExtras().getString("institutionDescription", "No institutionDescription found");
        String courseDesc = i.getExtras().getString("courseDescription", "No courseDescription found");
        String institutionName = i.getExtras().getString("institutionName", "No institutionName found");
        String courseGrade = String.valueOf(i.getExtras().getDouble("courseGrade", 0));
        String courseIntake = String.valueOf(i.getExtras().getInt("courseIntake", 0));
        String postCode = String.valueOf(i.getExtras().getInt("postalCode", 238801));
        postalCode = postCode;
        insName = institutionName;

        CourseGrade.setText(courseGrade);
        CourseIntake.setText(courseIntake);
        CourseName.setText(courseName);
        InstitutionName.setText(institutionName);
        CourseDescription.setText(courseDesc);
        InstitutionDescription.setText(institutionDesc);

        switch (institutionName) {
            case "Singapore University of Technology and Design": {
                InstitutionLogo.setImageResource(R.mipmap.sutd);
                break;
            }
            case "Nanyang Technological University": {
                InstitutionLogo.setImageResource(R.mipmap.ntu);
                break;
            }
            case "Singapore Management University": {
                InstitutionLogo.setImageResource(R.mipmap.smu);
                break;
            }
            case "National University of Singapore": {
                InstitutionLogo.setImageResource(R.mipmap.nus);
                break;
            }
            case "Singapore Institute of Technology": {
                InstitutionLogo.setImageResource(R.mipmap.sit);
                break;
            }
            case "Digipen Institute of Technology Singapore": {
                InstitutionLogo.setImageResource(R.mipmap.digipen);
                break;
            }
            default:
        }

        FragmentManager myFragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) myFragmentManager.findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync((OnMapReadyCallback) this);

        CourseWebsite.setImageResource(R.drawable.website);
        CourseWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(courseWebsite)));
                (v.getContext()).startActivity(browserIntent);
            }
        });
        institutionDescription.setPaintFlags(institutionDescription.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        courseDescription.setPaintFlags(courseDescription.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        direction.setPaintFlags(direction.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        career.setPaintFlags(career.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    /**
     * Instantiate menu XML files into Menu objects when menu options are created
     * @param menu The menu in the side pane
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_polytechnic_details, menu);
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
     * Initialize and implements toolbar, drawer, and side panel UI and functions
     * @param toolbarTitle Title of the page
     */
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
                    intent = new Intent(UniversityCourseDetailsUI.this, MainUI.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    return true;
                case R.id.bookmarks:
                    intent = new Intent(UniversityCourseDetailsUI.this, BookmarksUI.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    return true;
                case R.id.aboutus:
                    intent = new Intent(UniversityCourseDetailsUI.this, AboutUs.class);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        MapController m1 = new MapController();
        m1.execute(postalCode);
        double[] latlng = {1.35,103.82};
        try {
            double[] location = m1.get();
            latlng[0] = location[0];
            latlng[1] = location[1];
        }catch (Exception e){
            Log.d("Address Error", "Can't get latitude and longitude");
        }
        LatLng LOCATION = new LatLng(latlng[0], latlng[1]);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION, 10));
        mGoogleMap.addMarker(new MarkerOptions().position(LOCATION).title(insName));
    }
}