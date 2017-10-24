package com.example.utsav.edufind;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Toast;

public class NewSearchUI extends AppCompatActivity implements OnItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private Intent intent;
    private NavigationView navigationView;
    private ArrayAdapter<CharSequence> speciliazationAdaptor;
    private Spinner specializationSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_search_ui);

        initializeToolbar("New Search");

        //Populate areaOfInterestSpinner
        Spinner areaOfInterestSpinner = (Spinner) findViewById(R.id.areaOfInterestSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.areaOFInterestData, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        areaOfInterestSpinner.setOnItemSelectedListener(this);

        areaOfInterestSpinner.setAdapter(adapter);


        //Populate specializationSpinner
         specializationSpinner = (Spinner) findViewById(R.id.specializationSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout


        speciliazationAdaptor = ArrayAdapter.createFromResource(this,
         R.array.appliedScience, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        speciliazationAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        specializationSpinner.setAdapter(speciliazationAdaptor);

    }

        public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
        long arg3) {
            // TODO Auto-generated method stub
            switch(pos){
                case 0:
                    speciliazationAdaptor = ArrayAdapter.createFromResource(this,
                            R.array.appliedScience, android.R.layout.simple_spinner_item);
                    speciliazationAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    specializationSpinner.setAdapter(speciliazationAdaptor);

                    break;
                case 1:
                    speciliazationAdaptor = ArrayAdapter.createFromResource(this,
                            R.array.builtEnvironment, android.R.layout.simple_spinner_item);
                    speciliazationAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    specializationSpinner.setAdapter(speciliazationAdaptor);

                    break;
                case 2:
                    speciliazationAdaptor = ArrayAdapter.createFromResource(this,
                            R.array.business, android.R.layout.simple_spinner_item);
                    speciliazationAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    specializationSpinner.setAdapter(speciliazationAdaptor);
                    break;
                case 3:
                    speciliazationAdaptor = ArrayAdapter.createFromResource(this,
                            R.array.engineering, android.R.layout.simple_spinner_item);
                    speciliazationAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    specializationSpinner.setAdapter(speciliazationAdaptor);
                    break;
                case 4:
                    speciliazationAdaptor = ArrayAdapter.createFromResource(this,
                            R.array.healthScience, android.R.layout.simple_spinner_item);
                    speciliazationAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    specializationSpinner.setAdapter(speciliazationAdaptor);
                    break;
                case 5:
                    speciliazationAdaptor = ArrayAdapter.createFromResource(this,
                            R.array.humanities, android.R.layout.simple_spinner_item);
                    speciliazationAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    specializationSpinner.setAdapter(speciliazationAdaptor);
                    break;
                case 6:
                    speciliazationAdaptor = ArrayAdapter.createFromResource(this,
                            R.array.informationTech, android.R.layout.simple_spinner_item);
                    speciliazationAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    specializationSpinner.setAdapter(speciliazationAdaptor);
                    break;
                case 7:
                    speciliazationAdaptor = ArrayAdapter.createFromResource(this,
                            R.array.maritime, android.R.layout.simple_spinner_item);
                    speciliazationAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    specializationSpinner.setAdapter(speciliazationAdaptor);
                    break;
                case 8:
                    speciliazationAdaptor = ArrayAdapter.createFromResource(this,
                            R.array.media, android.R.layout.simple_spinner_item);
                    speciliazationAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    specializationSpinner.setAdapter(speciliazationAdaptor);
                    break;

            }


        }

        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

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

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void goToNewSearchUIPage2(View view) {
        intent = new Intent(this, NewSearchUIPage2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Passing data to next activity
        /*intent.putExtra("interest", interest);
        intent.putExtra("specialization", specialization);
        intent.putExtra("L1R4", L1R4);*/
        startActivity(intent);
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
                        intent = new Intent(NewSearchUI.super.getApplication(), MainUI.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;

                    case R.id.bookmarks:
                        intent = new Intent(NewSearchUI.super.getApplication(), BookmarksUI.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;

                    case R.id.aboutus:
                        intent = new Intent(NewSearchUI.super.getApplication(), AboutUs.class);
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
        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }
}
