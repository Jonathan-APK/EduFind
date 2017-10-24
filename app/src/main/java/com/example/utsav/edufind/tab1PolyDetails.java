package com.example.utsav.edufind;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Initializes and display the actual details of for the selected Polytechnic Course
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class tab1PolyDetails extends Fragment{
    TextView CourseGrade;
    TextView CourseIntake;
    TextView CourseName;
    TextView InstitutionName;
    ImageView SchoolName;
    ImageView CourseWebsite;
    TextView schDescLabel;
    TextView courseDescLabel;
    TextView career;
    TextView direction;
    TextView CourseDescription;
    TextView SchoolDescription;

    /**
     * Sets data and display details of the Course dynamically
     * @param inflater Instantiates a layout XML
     * @param container Main view containing all other views
     * @param savedInstanceState Current state of application
     * @return View Polytechnic Course Details view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab1_details, container, false);
        CourseGrade = (TextView) rootView.findViewById(R.id.tab1_Course_Grade);
        CourseIntake = (TextView) rootView.findViewById(R.id.tab1_Course_Intake);
        CourseName = (TextView) rootView.findViewById(R.id.tab1_Course_name);
        InstitutionName = (TextView) rootView.findViewById(R.id.tab1_Institution_Name);
        SchoolName = (ImageView) rootView.findViewById(R.id.tab1_Institution_Logo);
        CourseWebsite = (ImageView) rootView.findViewById(R.id.tab1_Course_Website);
        schDescLabel = (TextView) rootView.findViewById(R.id.school_desc_text);
        courseDescLabel = (TextView) rootView.findViewById(R.id.course_desc_text);
        direction = (TextView) rootView.findViewById(R.id.direction);
        career = (TextView) rootView.findViewById(R.id.career_prospect_text);
        CourseDescription = (TextView) rootView.findViewById(R.id.course_desc_detail_text);
        SchoolDescription = (TextView) rootView.findViewById(R.id.school_desc_detail_text);

        Intent i = getActivity().getIntent();
        String courseName = i.getExtras().getString("courseName", "No courseName found");
        final String courseWebsite = i.getExtras().getString("courseWebsite", "No courseWebsite found");
        String schoolDesc = i.getExtras().getString("schDescription", "No schDescription found");
        String courseDesc = i.getExtras().getString("courseDescription", "No courseDescription found");
        String institutionName = i.getExtras().getString("institutionName", "No institutionName found");
        String courseGrade = String.valueOf(i.getExtras().getInt("courseGrade", 0));
        String courseIntake = String.valueOf(i.getExtras().getInt("courseIntake", 0));

        CourseGrade.setText(courseGrade);
        CourseIntake.setText(courseIntake);
        CourseName.setText(courseName);
        InstitutionName.setText(institutionName);
        CourseDescription.setText(courseDesc);
        SchoolDescription.setText(schoolDesc);
        switch (institutionName) {
            case "Singapore Polytechnic": {
                SchoolName.setImageResource(R.mipmap.sp);
                break;
            }
            case "Ngee Ann Polytechnic": {
                SchoolName.setImageResource(R.mipmap.np);
                break;
            }
            case "Republic Polytechnic": {
                SchoolName.setImageResource(R.mipmap.rp);
                break;
            }
            case "Nanyang Polytechnic": {
                SchoolName.setImageResource(R.mipmap.nyp);
                break;
            }
            case "Temasek Polytechnic": {
                SchoolName.setImageResource(R.mipmap.tp);
                break;
            }
            default:
        }
        CourseWebsite.setImageResource(R.drawable.website);
        CourseWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(courseWebsite)));
                (v.getContext()).startActivity(browserIntent);
            }
        });
        CourseWebsite.setImageResource(R.drawable.website);
        schDescLabel.setPaintFlags(schDescLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        courseDescLabel.setPaintFlags(courseDescLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        direction.setPaintFlags(direction.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        career.setPaintFlags(career.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        return rootView;
    }
}
