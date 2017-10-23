package com.example.utsav.edufind;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UniversityCourseDetailsUI extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Intent intent;
    private NavigationView navigationView;

    TextView CourseGrade;
    TextView CourseIntake;
    TextView CourseName;
    TextView SchoolName;
    ImageView SchoolLogo;
    ImageView CourseWebsite;
    TextView schDescription;
    TextView courseDescription;
    TextView career;
    TextView direction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.university_course_details_ui);

        //INIT TOOLBAR
        initializeToolbar("University Details");
        //

        CourseGrade = (TextView) findViewById(R.id.uni_Course_Grade);
        CourseIntake = (TextView) findViewById(R.id.uni_Course_Intake);
        CourseName = (TextView) findViewById(R.id.uni_Course_name);
        SchoolName = (TextView)findViewById(R.id.uni_School_name);
        SchoolLogo = (ImageView) findViewById(R.id.uni_School_Logo);
        CourseWebsite = (ImageView) findViewById(R.id.uni_Course_Website);

        schDescription = (TextView) findViewById(R.id.uni_school_desc_text);
        courseDescription = (TextView) findViewById(R.id.uni_course_desc_text);
        direction = (TextView) findViewById(R.id.uni_direction);
        career = (TextView) findViewById(R.id.uni_career_prospect_text);

        CourseGrade.setText("1");
        CourseIntake.setText("1000");
        CourseName.setText("Diploma In Information Security");
        SchoolName.setText("Singapore Polytechnic");
        SchoolLogo.setImageResource(R.mipmap.sp);
        CourseWebsite.setImageResource(R.drawable.website);

        schDescription.setPaintFlags(schDescription.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        courseDescription.setPaintFlags(courseDescription.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        direction.setPaintFlags(direction.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        career.setPaintFlags(career.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Intent in = getIntent();
        String cName = in.getStringExtra("courseName");
        String sName = in.getStringExtra("schoolName");

//        TextView tv = (TextView) findViewById(R.id.textView3);
//        tv.setText(cName);
//        TextView tv2 = (TextView) findViewById(R.id.textView4);
//        tv2.setText(sName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_polytechnic_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
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
}