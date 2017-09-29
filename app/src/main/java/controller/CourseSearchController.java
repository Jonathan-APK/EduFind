package controller;

import entity.Course;
import entity.PolytechnicCourse;

import java.util.ArrayList;

/**
 * Created by boonleng94 on 27/9/2017.
 */

public class CourseSearchController {

    public boolean validInterests(String interest, String specialization) {
        boolean valid = false;

        //Check both fields are not empty
        if (!(interest.isEmpty()) && !(specialization.isEmpty())) {
            valid = true;
        }

        return valid;
    }


    public boolean validatePostalCode(int postalCode) {
        boolean valid = false;

        //Check field is not empty
        if (postalCode != 0) {
            //Check field contains 6 digits
            if (String.valueOf(postalCode).length() == 6) {
                valid = true;
            }
        }

        return valid;
    }

    public ArrayList<PolytechnicCourse> search(String interest, String specialization, int L1R4, int postalCode) {
        ArrayList<PolytechnicCourse> courseList = new ArrayList<PolytechnicCourse>();
        ArrayList<PolytechnicCourse> sortedCourseList = new ArrayList<PolytechnicCourse>();
        PolytechnicCourse temp = new PolytechnicCourse();

        //Retrieve list of courses
        courseList = CourseController.retrieveListOfPolyCourses();

        for (int i = 0; i < courseList.size(); i++) {
            temp = courseList.get(i);
            //Filter by interests and specialization
            if (temp.getInterest().equals(interest) && temp.getSpecialization().equals(specialization)) {
                //Filter by L1R4
                if (L1R4 <= temp.getL1R4()) {
                    sortedCourseList.add(courseList.get(i));
                }
            }
        }

        //Sort by distance
        DistanceCalculation d1 = new DistanceCalculation();

        for (int i = 0; i < sortedCourseList.size(); i++) {
            temp = sortedCourseList.get(i);
            d1.execute(Integer.toString(postalCode), Integer.toString(temp.getInstitution());
            try {
                double distance = d1.get();
                //Log.d("distance", String.valueOf(distance));
            } catch (Exception e) {
                //Log.d("distance_error", e.toString());
            }
        }
        return sortedCourseList;
    }
}
