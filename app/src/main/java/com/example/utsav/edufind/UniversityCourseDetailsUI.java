package com.example.utsav.edufind;

import android.content.Intent;
import android.graphics.Paint;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import controller.CourseSearchController;
import controller.RVAdapter;
import entity.Course;
import entity.PolytechnicCourse;
import entity.UniversityCourse;

public class UniversityCourseDetailsUI extends AppCompatActivity {

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
    }


}