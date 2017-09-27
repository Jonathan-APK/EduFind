package controller;

import entity.Course;
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

    public ArrayList<Course> search(String interest, String specialization, int L1R4, int postalCode) {
        ArrayList<Course> courseList = new ArrayList<Course>();

        //Retrieve list of courses
        //Filter by L1R4
        //Filter by interests and specialization
        //Sort by distance

        return courseList;
    }
}
