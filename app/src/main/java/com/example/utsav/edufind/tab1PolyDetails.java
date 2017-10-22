package com.example.utsav.edufind;

import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Last edited by faceb on 01-Oct-17.
 */

public class tab1PolyDetails extends Fragment{

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab1_details, container, false);

        CourseGrade = (TextView) rootView.findViewById(R.id.tab1_Course_Grade);
        CourseIntake = (TextView) rootView.findViewById(R.id.tab1_Course_Intake);
        CourseName = (TextView) rootView.findViewById(R.id.tab1_Course_name);
        SchoolName = (TextView) rootView.findViewById(R.id.tab1_School_name);
        SchoolLogo = (ImageView) rootView.findViewById(R.id.tab1_School_Logo);
        CourseWebsite = (ImageView) rootView.findViewById(R.id.tab1_Course_Website);

        schDescription = (TextView) rootView.findViewById(R.id.school_desc_text);
        courseDescription = (TextView) rootView.findViewById(R.id.course_desc_text);
        direction = (TextView) rootView.findViewById(R.id.direction);
        career = (TextView) rootView.findViewById(R.id.career_prospect_text);

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


        return rootView;
    }
}
