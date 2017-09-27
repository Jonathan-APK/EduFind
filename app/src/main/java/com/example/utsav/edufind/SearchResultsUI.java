package com.example.utsav.edufind;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import controller.RVAdapter;
import entity.PolytechnicCourse;

public class SearchResultsUI extends Activity {

    private List<PolytechnicCourse> polyCourses;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_results_ui);

        rv= findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        polyCourses = new ArrayList<>();
        polyCourses.add(new PolytechnicCourse("Diploma in Computer Security", 12));
        polyCourses.add(new PolytechnicCourse("Diploma in Business Management", 13));
        polyCourses.add(new PolytechnicCourse("Diploma in Digital Media", 11));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(polyCourses);
        rv.setAdapter(adapter);
    }
}