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
import controller.RVAdapter;
import entity.Course;
import entity.PolytechnicCourse;
import entity.UniversityCourse;


public class tab2UniOptions extends Fragment {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2_uni_options, container, false);

        rv= rootView.findViewById(R.id.unirv);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();

        return rootView;
    }

    private void initializeData(){
        // CourseSearchController c1 = new CourseSearchController();
        // courseList = c1.search(interest, specialization, L1R4, postalCode);
        courseList = new ArrayList<>();

        courseList.add(new UniversityCourse("Degree in Business Management", "National University of Singapore",10, 1, "http://www.nus.edu.sg/"));
        courseList.add(new UniversityCourse("Degree in Digital Media", "National University of Singapore", 40, 12, "http://www.nie.edu.sg/"));
        courseList.add(new UniversityCourse("Degree in Accountancy", "Nanyang Technological University", 60, 15, "http://www.ntu.edu.sg/Pages/home.aspx"));
        courseList.add(new UniversityCourse("Degree in Biological Sciences", "Singapore Management University", 30, 11, "https://www.smu.edu.sg/"));

    }

    private void initializeAdapter(){
        RVAdapter adapter= new RVAdapter(courseList);
        rv.setAdapter(adapter);
    }

}

