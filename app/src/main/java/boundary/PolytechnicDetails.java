package boundary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.utsav.edufind.MainUI;
import com.example.utsav.edufind.R;

/**
 * Initializes and display Polytechnic Course Details view for the selected University Course
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class PolytechnicDetails extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Intent intent;
    private NavigationView navigationView;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    /**
     * Initialize SectionsPagerAdapter to implement tabbing function in the main view
     * @param savedInstanceState Current state of application
     * @return View Polytechnic Course Details view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polytechnic_details);
        initializeToolbar("Polytechnic Details");

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
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
     * This is a FragmentPagerAdapter that returns a fragment corresponding to the selected tab.
     */
    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        /**
         * Displays the appropriate view based on the selected tab
         * @param position Selected tab in integer form
         * @return Fragment Sub-pane view
         */
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page. DELETED
            // Return a PlaceholderFragment (defined as a static inner class below). DELETED
            // Return current tabs
            switch (position){
                case 0:
                    tab1PolyDetails tab1 = new tab1PolyDetails();
                    return tab1;
                case 1:
                    tab2UniOptions tab2 = new tab2UniOptions();
                    return tab2;
                default:
                    tab1PolyDetails defaultTab = new tab1PolyDetails();
                    return defaultTab;
            }
        }

        /**
         * Returns number of tabs
         * @return 2 Number of tabs
         */
        @Override
        public int getCount() {
            return 2;
        }

        /**
         * Returns the title of the current Sub-pane view
         * @param position Current tab position
         * @return CharSequence Title of the current Sub-pane view
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Details";
                case 1:
                    return "University Options";
            }
            return null;
        }
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
                        intent = new Intent(PolytechnicDetails.this, MainUI.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;
                    case R.id.bookmarks:
                        intent = new Intent(PolytechnicDetails.this, BookmarksUI.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;
                    case R.id.aboutus:
                        intent = new Intent(PolytechnicDetails.this, AboutUs.class);
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