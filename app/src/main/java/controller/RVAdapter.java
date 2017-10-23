package controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;


import com.example.utsav.edufind.PolytechnicDetails;
import com.example.utsav.edufind.R;
import java.util.List;

import entity.PolytechnicCourse;
import entity.UniversityCourse;
import entity.Course;
import com.example.utsav.edufind.PolytechnicDetails;
import com.example.utsav.edufind.UniversityCourseDetailsUI;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CourseViewHolder> {

    //private Context context;

    public static class CourseViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView CourseName;
        TextView SchoolName;
        TextView CourseGrade;
        TextView CourseIntake;
        ImageView CourseWebsite;
        ImageView SchoolLogo;
        TextView gradeTitle;

        CourseViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            CourseName = itemView.findViewById(R.id.Course_name);
            CourseGrade= itemView.findViewById(R.id.Course_Grade);
            SchoolName = itemView.findViewById(R.id.School_name);
            CourseIntake = itemView.findViewById(R.id.Course_Intake);
            CourseWebsite = itemView.findViewById(R.id.Course_Website);
            SchoolLogo = itemView.findViewById(R.id.School_Logo);
            gradeTitle = itemView.findViewById(R.id.Course_Grade_Title);

        }
    }

    List<Course> courses;

    public RVAdapter(List<Course> courses){
        this.courses = courses;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        CourseViewHolder pvh = new CourseViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CourseViewHolder CourseViewHolder, final int i) {
        if(courses.get(i) instanceof PolytechnicCourse) {
            switch (courses.get(i).getSchool()) {

                case "Singapore Polytechnic": {
                    CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.sp);
                    break;
                }

                case "Ngee Ann Polytechnic": {
                    CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.np);
                    break;
                }

                case "Republic Polytechnic": {
                    CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.rp);
                    break;
                }

                case "Nanyang Polytechnic": {
                    CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.nyp);
                    break;
                }

                case "Temasek Polytechnic": {
                    CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.tp);
                    break;
                }

                default:
            }
            CourseViewHolder.SchoolName.setText(courses.get(i).getSchool());
            CourseViewHolder.CourseName.setText(courses.get(i).getCourseName());
            CourseViewHolder.CourseGrade.setText(String.valueOf(((PolytechnicCourse) courses.get(i)).getL1R4()));
            CourseViewHolder.CourseIntake.setText(String.valueOf(courses.get(i).getIntake()));
            CourseViewHolder.CourseWebsite.setImageResource(R.drawable.website);
            CourseViewHolder.CourseWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(courses.get(i).getWebsite())));
                    (v.getContext()).startActivity(browserIntent);
                }
            });
            CourseViewHolder.gradeTitle.setText("L1R4");

            final String courseName = courses.get(i).getCourseName();
            final String schoolName = courses.get(i).getSchool();
            CourseViewHolder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(v.getContext(), PolytechnicDetails.class);

                    in.putExtra("courseName", courseName);
                    in.putExtra("schoolName", schoolName);
                    v.getContext().startActivity(in);
                }
            });
        }

        //Replace Logo when Ze Hao finds correct logo
        else if(courses.get(i) instanceof UniversityCourse) {
            switch (courses.get(i).getSchool()) {

                case "Singapore University of Technology and Design": {
                    CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.sutd);
                    break;
                }

                case "Nanyang Technological University": {
                    CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.ntu);
                    break;
                }

                case "Singapore Management University": {
                    CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.smu);
                    break;
                }

                case "National University of Singapore": {
                    CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.nus);
                    break;
                }

                case "Singapore Institute of Technology": {
                    CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.sit);
                    break;
                }
                default:
            }
            CourseViewHolder.SchoolName.setText(courses.get(i).getSchool());
            CourseViewHolder.CourseName.setText(courses.get(i).getCourseName());
            CourseViewHolder.CourseGrade.setText(String.valueOf(((UniversityCourse) courses.get(i)).getGradePointAverage()));
            CourseViewHolder.CourseIntake.setText(String.valueOf(courses.get(i).getIntake()));
            CourseViewHolder.CourseWebsite.setImageResource(R.drawable.website);
            CourseViewHolder.CourseWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(courses.get(i).getWebsite())));
                    (v.getContext()).startActivity(browserIntent);
                }
            });
            CourseViewHolder.gradeTitle.setText("GPA");
            CourseViewHolder.CourseGrade.setTextSize(20);

            final String courseName = courses.get(i).getCourseName();
            final String schoolName = courses.get(i).getSchool();
            CourseViewHolder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(v.getContext(), UniversityCourseDetailsUI.class);

                    in.putExtra("courseName", courseName);
                    in.putExtra("schoolName", schoolName);
                    v.getContext().startActivity(in);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}