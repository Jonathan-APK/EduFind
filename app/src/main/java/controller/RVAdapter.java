package controller;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import com.example.utsav.edufind.R;
import java.util.List;
import entity.PolytechnicCourse;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CourseViewHolder> {

    public static class CourseViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView CourseName;
        TextView CourseL1R4;
        TextView SchoolName;
        TextView CourseIntake;
        ImageView CourseWebsite;
        ImageView SchoolLogo;

        CourseViewHolder(View itemView) {
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

    List<PolytechnicCourse> courses;

    public RVAdapter(List<PolytechnicCourse> courses){
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
    public void onBindViewHolder(CourseViewHolder CourseViewHolder, int i) {
        switch(courses.get(i).getSchool()){

            case "Singapore Polytechnic":
                {
                CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.sp);
                    break;
                }

            case "Ngee Ann Polytechnic":
                {
                CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.np);
                    break;
                }

            case "Republic Polytechnic":
                {
                CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.rp);
                    break;
                }

            case "Nanyang Polytechnic":
                {
                CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.nyp);
                    break;
                }

            case "Temasek Polytechnic":
                {
                CourseViewHolder.SchoolLogo.setImageResource(R.mipmap.tp);
                    break;
                }

            default:
        }
        CourseViewHolder.SchoolName.setText(courses.get(i).getSchool());
        CourseViewHolder.CourseName.setText(courses.get(i).getCourseName());
        CourseViewHolder.CourseL1R4.setText(String.valueOf(courses.get(i).getL1R4()));
        CourseViewHolder.CourseIntake.setText(String.valueOf(courses.get(i).getIntake()));
        CourseViewHolder.CourseWebsite.setImageResource(R.drawable.website);

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}