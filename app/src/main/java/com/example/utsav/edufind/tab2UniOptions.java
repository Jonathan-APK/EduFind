package com.example.utsav.edufind;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import controller.CourseSearchController;
import entity.Course;
import entity.PolytechnicCourse;

/**
 * This class displays the search results of university courses based on the user's
 * polytechnic course selection in the form of a recycler and card view.
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class tab2UniOptions extends Fragment {
    private DrawerLayout mDrawerLayout;
    private Intent intent;
    private NavigationView navigationView;

    private ArrayList<Course> courseList;
    private RecyclerView rv;

    //Store user inputs from previous activity
    private String interest;
    private String specialization;
    private int postalCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2_uni_options, container, false);

        rv= rootView.findViewById(R.id.unirv);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        Intent i = getActivity().getIntent();
        interest = i.getExtras().getString("interest", "No interest found");
        specialization = i.getExtras().getString("specialization", "No specialization found");
        postalCode = i.getExtras().getInt("postalCode", 000000);

        initializeData();
        initializeAdapter();

        return rootView;
    }

    /**
     * This method retrieves all the search results from the course search controller class
     * and puts them in an ArrayList of Course objects consisting of only university courses.
     */
    private void initializeData(){
        CourseSearchController c1 = new CourseSearchController();
        courseList = c1.search(interest, specialization, 0, postalCode);

        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i) instanceof PolytechnicCourse) {
                courseList.remove(i--);
            }
        }
    }

    /**
     * This method puts all the the Array List Course items in the Recycler View. The Recycler View
     * populates the respective course items in a card view.
     */
    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(courseList, interest, specialization, postalCode);
        rv.setAdapter(adapter);
    }
}

