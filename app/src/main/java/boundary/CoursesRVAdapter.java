package boundary;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.util.TypedValue;
import android.util.DisplayMetrics;

import com.example.utsav.edufind.R;

import java.util.List;

import entity.PolytechnicCourse;
import entity.UniversityCourse;
import entity.Course;

/**
 * This class sets the course information in their respective View elements and populates the card view
 * of each course dynamically based on the size of the Course objects.
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class CoursesRVAdapter extends RecyclerView.Adapter<CoursesRVAdapter.CourseViewHolder> {
    /**
     * This class references the respective View widgets inside the layout through its id.
     */
    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView CourseName;
        TextView InstitutionName;
        TextView CourseGrade;
        TextView CourseIntake;
        ImageView CourseWebsite;
        ImageView InstitutionLogo;
        TextView gradeTitle;

        CourseViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            CourseName = itemView.findViewById(R.id.Course_name);
            CourseGrade= itemView.findViewById(R.id.Course_Grade);
            InstitutionName = itemView.findViewById(R.id.Institution_name);
            CourseIntake = itemView.findViewById(R.id.Course_Intake);
            CourseWebsite = itemView.findViewById(R.id.Course_Website);
            InstitutionLogo = itemView.findViewById(R.id.Institution_Logo);
            gradeTitle = itemView.findViewById(R.id.Course_Grade_Title);

            //GIVE SHADOW AROUND CARD
            cv.setCardElevation(10);
            CardView.LayoutParams lp = new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT);
            DisplayMetrics dm = cv.getResources().getDisplayMetrics();
            lp.setMargins(convertDpToPx(5, dm), convertDpToPx(5, dm), convertDpToPx(5, dm), convertDpToPx(5, dm));
            cv.setLayoutParams(lp);
            cv.setContentPadding(0, 0, 0, 0);
        }
    }

    List<Course> courses;
    String interest;
    String specialisation;
    int postalCode;

    public CoursesRVAdapter(List<Course> courses, String interest, String specialisation, int postalCode){
        this.courses = courses;
        this.interest = interest;
        this.specialisation = specialisation;
        this.postalCode = postalCode;
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

    /**
     * This method checks the name of the polytechnic or university and sets the respective institution image
     * to its CourseViewHolder widget.
     * This method also dynamically sets the external link(URL) on the clickable website icon which redirects the user
     * to an external browser when clicked on.
     * This method also dynamically sets the course details on the clickable card view which redirects the user
     * to the respective course information page.
     * @param CourseViewHolder Holds all the View widgets in the SearchResultsUI page
     * @param i Allows accessibility from it inner onClick method to retrieve the correct course URL
     */
    @Override
    public void onBindViewHolder(CourseViewHolder CourseViewHolder, final int i) {
        if(courses.get(i) instanceof PolytechnicCourse) {
            switch (courses.get(i).getInstitution().getInstitution()) {
                case "Singapore Polytechnic": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.sp);
                    break;
                }
                case "Ngee Ann Polytechnic": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.np);
                    break;
                }
                case "Republic Polytechnic": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.rp);
                    break;
                }
                case "Nanyang Polytechnic": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.nyp);
                    break;
                }
                case "Temasek Polytechnic": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.tp);
                    break;
                }
                default:
            }
            CourseViewHolder.InstitutionName.setText(courses.get(i).getInstitution().getInstitution());
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
            final String institutionName = courses.get(i).getInstitution().getInstitution();
            final String courseWebsite = courses.get(i).getWebsite();
            final String schDescription = courses.get(i).getInstitution().getInstiDescription();
            final String courseDescription = courses.get(i).getCourseDescription();
            final String careerProspect = courses.get(i).getCareerProspect();
            final int courseGrade = ((PolytechnicCourse) courses.get(i)).getL1R4();
            final int courseIntake = courses.get(i).getIntake();
            final int instPostCode = courses.get(i).getInstitution().getPostalCode();
            CourseViewHolder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(v.getContext(), PolytechnicDetailsUI.class);
                    in.putExtra("courseName", courseName);
                    in.putExtra("institutionName", institutionName);
                    in.putExtra("courseWebsite", courseWebsite);
                    in.putExtra("institutionDescription", schDescription);
                    in.putExtra("courseDescription", courseDescription);
                    in.putExtra("courseGrade", courseGrade);
                    in.putExtra("courseIntake", courseIntake);
                    in.putExtra("postalCode", postalCode);
                    in.putExtra("instPostalCode", instPostCode);
                    in.putExtra("interest", interest);
                    in.putExtra("specialisation", specialisation);
                    in.putExtra("careerProspect", careerProspect);
                    v.getContext().startActivity(in);
                }
            });
        }
        else if (courses.get(i) instanceof UniversityCourse) {
            switch (courses.get(i).getInstitution().getInstitution()) {
                case "Singapore University of Technology and Design": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.sutd);
                    break;
                }
                case "Nanyang Technological University": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.ntu);
                    break;
                }
                case "Singapore Management University": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.smu);
                    break;
                }
                case "National University of Singapore": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.nus);
                    break;
                }
                case "Singapore Institute of Technology": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.sit);
                    break;
                }
                case "Digipen Institute of Technology Singapore": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.digipen);
                    break;
                }
                default:
            }
            CourseViewHolder.InstitutionName.setText(courses.get(i).getInstitution().getInstitution());
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
            final String institutionName = courses.get(i).getInstitution().getInstitution();
            final String courseWebsite = courses.get(i).getWebsite();
            final String schDescription = courses.get(i).getInstitution().getInstiDescription();
            final String courseDescription = courses.get(i).getCourseDescription();
            final String careerProspect = courses.get(i).getCareerProspect();
            final double courseGrade = ((UniversityCourse) courses.get(i)).getGradePointAverage();
            final int courseIntake = courses.get(i).getIntake();
            final int instPostCode = courses.get(i).getInstitution().getPostalCode();
            CourseViewHolder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(v.getContext(), UniversityCourseDetailsUI.class);
                    in.putExtra("courseName", courseName);
                    in.putExtra("institutionName", institutionName);
                    in.putExtra("courseWebsite", courseWebsite);
                    in.putExtra("institutionDescription", schDescription);
                    in.putExtra("courseDescription", courseDescription);
                    in.putExtra("courseGrade", courseGrade);
                    in.putExtra("courseIntake", courseIntake);
                    in.putExtra("postalCode", postalCode);
                    in.putExtra("instPostalCode", instPostCode);
                    in.putExtra("careerProspect", careerProspect);
                    v.getContext().startActivity(in);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    private static int convertDpToPx(int dp, DisplayMetrics displayMetrics) {
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return Math.round(pixels);
    }
}