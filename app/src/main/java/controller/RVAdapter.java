package controller;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.utsav.edufind.AboutUs;
import com.example.utsav.edufind.BookmarksUI;
import com.example.utsav.edufind.NewSearchUI;
import com.example.utsav.edufind.R;
import com.example.utsav.edufind.SearchResultsUI;

import java.util.List;

import entity.PolytechnicCourse;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PolytechnicCourseViewHolder> {

    public static class PolytechnicCourseViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView CourseName;
        TextView CourseL1R4;
        TextView SchoolName;
        TextView CourseIntake;
        ImageView CourseWebsite;
        ImageView SchoolLogo;

        PolytechnicCourseViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            CourseName = itemView.findViewById(R.id.Course_name);
            CourseL1R4 = itemView.findViewById(R.id.Course_L1R4);
            SchoolName = itemView.findViewById(R.id.School_name);
            CourseIntake = itemView.findViewById(R.id.Course_Intake);
            CourseWebsite = itemView.findViewById(R.id.Course_Website);
            SchoolLogo = itemView.findViewById(R.id.School_Logo);
        }
    }

    List<PolytechnicCourse> polyCourses;

    public RVAdapter(List<PolytechnicCourse> polyCourses){
        this.polyCourses = polyCourses;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PolytechnicCourseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PolytechnicCourseViewHolder pvh = new PolytechnicCourseViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PolytechnicCourseViewHolder polytechnicCourseViewHolder, int i) {
        switch(polyCourses.get(i).getSchool()){

            case "Singapore Polytechnic":
                {
                polytechnicCourseViewHolder.SchoolLogo.setImageResource(R.mipmap.sp);
                    break;
                }

            case "Ngee Ann Polytechnic":
                {
                polytechnicCourseViewHolder.SchoolLogo.setImageResource(R.mipmap.np);
                    break;
                }

            case "Republic Polytechnic":
                {
                polytechnicCourseViewHolder.SchoolLogo.setImageResource(R.mipmap.rp);
                    break;
                }

            case "Nanyang Polytechnic":
                {
                polytechnicCourseViewHolder.SchoolLogo.setImageResource(R.mipmap.nyp);
                    break;
                }

            case "Temasek Polytechnic":
                {
                polytechnicCourseViewHolder.SchoolLogo.setImageResource(R.mipmap.tp);
                    break;
                }

            default:
        }
        polytechnicCourseViewHolder.SchoolName.setText(polyCourses.get(i).getSchool());
        polytechnicCourseViewHolder.CourseName.setText(polyCourses.get(i).getCourseName());
        polytechnicCourseViewHolder.CourseL1R4.setText(String.valueOf(polyCourses.get(i).getL1R4()));
        polytechnicCourseViewHolder.CourseIntake.setText(String.valueOf(polyCourses.get(i).getIntake()));
        polytechnicCourseViewHolder.CourseWebsite.setImageResource(R.drawable.website);

    }

    @Override
    public int getItemCount() {
        return polyCourses.size();
    }
}