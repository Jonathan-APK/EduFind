package boundary;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.utsav.edufind.MainAppUI;
import com.example.utsav.edufind.R;

import java.util.ArrayList;

import controller.BookmarkImplementation;
import controller.factory.DataStoreFactory;
import controller.DataStoreInterface;
import entity.Bookmark;

/**
 * Initialize and display Saved Bookmarks page
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class BookmarksUI extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Intent intent;
    private NavigationView navigationView;
    private ArrayList<Bookmark> bookmarkList;
    private RecyclerView rv;
    BookmarksRVAdapter adapter;

    /**
     * Initialize layout
     * @param savedInstanceState saved instance date
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Get data from previous activity
        Intent i = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmarks_ui);

        initializeToolbar("Bookmarks");
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        initializeData();
        initializeAdapter();

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                final int position = viewHolder.getAdapterPosition();

                AlertDialog.Builder builder = new AlertDialog.Builder(BookmarksUI.this); //alert for confirm to delete
                builder.setMessage("Are you sure to delete?");    //set message
                builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemRemoved(position);    //item removed from recylcerview
                        bookmarkList.remove(position);  //then remove item
                        DataStoreInterface di = DataStoreFactory.getDatastore("bookmark",getApplicationContext());
                        ((BookmarkImplementation)di).updateBookmark(bookmarkList);
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemRemoved(position + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                        adapter.notifyItemRangeChanged(position, adapter.getItemCount());   //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
                    }
                }).show();  //show alert dialog
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);
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
     * This method retrieves all the search results from the course search controller class
     * and puts them in an ArrayList of Course objects consisting of only polytechnic courses.
     */
    private void initializeData(){
        bookmarkList = new ArrayList<>();
        DataStoreInterface di = DataStoreFactory.getDatastore("bookmark",this);
        bookmarkList = (ArrayList<Bookmark>)(Object)di.retrieveList();
    }

    /**
     * This method retrieves all the search results from the course search controller class
     * and puts them in an ArrayList of Course objects consisting of only university courses.
     */
    private void initializeAdapter(){
        adapter = new BookmarksRVAdapter(bookmarkList);
        rv.setAdapter(adapter);

        if (adapter.getItemCount() == 0) {
            // Show no results
            AlertDialog.Builder builder = new AlertDialog.Builder(BookmarksUI.this);
            builder.setMessage("You do not have any bookmarks!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();  //show alert dialog
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
                        intent = new Intent(BookmarksUI.super.getApplication(), MainAppUI.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;

                    case R.id.bookmarks:
                        intent = new Intent(BookmarksUI.super.getApplication(), BookmarksUI.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;

                    case R.id.aboutus:
                        intent = new Intent(BookmarksUI.super.getApplication(), AboutUsUI.class);
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
