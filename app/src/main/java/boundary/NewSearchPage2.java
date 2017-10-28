package boundary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.utsav.edufind.MainAppUI;
import com.example.utsav.edufind.R;

/**
 * Initializes and display page 2 of the New Search UI for the user to enter his L1R4 and residential postal code
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class NewSearchPage2 extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Intent intent;
    private NavigationView navigationView;

    //Store user inputs from previous activity
    private String interest;
    private String specialisation;

    /**
     * Initialize layout
     * @param savedInstanceState Current state of application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Get data from previous activity
        Intent i = getIntent();
        interest = i.getExtras().getString("interest", "No interest found");
        specialisation = i.getExtras().getString("specialisation", "No specialisation found");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_search_uipage2);
        initializeToolbar("New Search");

        //Populate L1R4Spinner
        Spinner LIR4Spinner = (Spinner) findViewById(R.id.L1R4Spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.L1R4Values, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        LIR4Spinner.setAdapter(adapter);

        // Set Help drawable of Postal Code to show help
        final TextView L1R4TextView = (TextView) findViewById(R.id.L1R4TextView);
        L1R4TextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            final int DRAWABLE_LEFT = 0;
            final int DRAWABLE_TOP = 1;
            final int DRAWABLE_RIGHT = 2;
            final int DRAWABLE_BOTTOM = 3;

            //ACTION_UP not detected
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if(event.getRawX() >= (L1R4TextView.getRight() - L1R4TextView.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    // Show help via Builder Design Pattern
                    AlertDialog.Builder builder= new AlertDialog.Builder(NewSearchPage2.this);
                    builder.setMessage("L1 stands for 1 First Language, which must be either English/Higher Mother Tongue.\n\n" +
                            "R4 stands for 4 Relevant Subjects.\n2 subjects must be either Humanities / Higher Art / Higher Music / Mathematics / Science / MSP / CSP / Bahasa Indonesia.\n" +
                            "The other 2 subjects can be any GCE O Level subjects or CCAs, excluding Religious Knowledge.");
                    builder.setTitle("What is L1R4?");
                    builder.setPositiveButton("OK", null);

                    AlertDialog alert = builder.create();
                    alert.show();

                    return true;
                }
            }
            return false;
            }
        });
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

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method passes the search parameters and directs the user to the results of his search
     * @param view The current view
     */
    public void submitSearchResults(View view) {
        Intent intent = new Intent(this, SearchResultsUI.class);
        //Passing data to next activity
        Spinner L1R4Spinner = (Spinner) findViewById(R.id.L1R4Spinner);
        int L1R4 = Integer.parseInt(L1R4Spinner.getSelectedItem().toString());
        EditText postalCodeTextInput = (EditText) findViewById(R.id.postalCodeTextInput);

        //If postal code not given, set postal code as center of SG
        int postalCode;
        if(postalCodeTextInput.getText().toString().matches("")){
            postalCode = 574415;
        }
        else {
            postalCode = Integer.parseInt(postalCodeTextInput.getText().toString());
        }
        intent.putExtra("interest", interest);
        intent.putExtra("specialisation", specialisation);
        intent.putExtra("L1R4", L1R4);
        intent.putExtra("postalCode", postalCode);
        startActivity(intent);
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
                    intent = new Intent(NewSearchPage2.this, MainAppUI.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    return true;
                case R.id.bookmarks:
                    intent = new Intent(NewSearchPage2.this, BookmarksUI.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    return true;
                case R.id.aboutus:
                    intent = new Intent(NewSearchPage2.this, AboutUsUI.class);
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