package controller;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utsav.edufind.R;

import java.util.List;

import entity.PolytechnicCourse;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PolytechnicCourseViewHolder> {

    public static class PolytechnicCourseViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView polyCourseName;
        TextView polyCourseL1R4;

        PolytechnicCourseViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            polyCourseName = itemView.findViewById(R.id.polyCourse_name);
            polyCourseL1R4 = itemView.findViewById(R.id.polyCourse_L1R4);
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
        //polytechnicCourseViewHolder.polyCourseName.setText(polyCourses.get(i).getPolyCourseName());
        polytechnicCourseViewHolder.polyCourseL1R4.setText(String.valueOf(polyCourses.get(i).getL1R4()));
    }

    @Override
    public int getItemCount() {
        return polyCourses.size();
    }
}