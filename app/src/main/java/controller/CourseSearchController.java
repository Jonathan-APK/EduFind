package controller;

import entity.Course;
import entity.PolytechnicCourse;
import entity.Institution;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by boonleng94 on 27/9/2017.
 */

public class CourseSearchController {

    public boolean validateInterest(String interest, String specialization) {
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

    /*public ArrayList<PolytechnicCourse> search(String interest, String specialization, int L1R4, int postalCode) {
        ArrayList<PolytechnicCourse> courseList = new ArrayList<PolytechnicCourse>();
        ArrayList<PolytechnicCourse> filteredCourseList = new ArrayList<PolytechnicCourse>();
        ArrayList<PolytechnicCourse> sortedCourseList = new ArrayList<PolytechnicCourse>();
        ArrayList<Institution> institutionList = new ArrayList<Institution>();
        ArrayList<Institution> sortedInstitutionList = new ArrayList<Institution>();
        ArrayList<Double> sortedDistanceList = new ArrayList<Double>();
        PolytechnicCourse temp = new PolytechnicCourse();
        DistanceCalculation d1 = new DistanceCalculation();

        //Retrieve full list of polytechnic courses
        courseList = CourseController.retrieveListOfPolyCourses();

        //Filter list according to inputs
        for (int i = 0; i < courseList.size(); i++) {
            temp = courseList.get(i);
            //Filter by interests and specialization
            if (temp.getInterest().equals(interest) && temp.getSpecialization().equals(specialization)) {
                //Filter by L1R4
                if (L1R4 <= temp.getL1R4()) {
                    filteredCourseList.add(courseList.get(i));
                }
            }
        }

        //Get list of unique institutions after filter
        for (int i = 0; i < filteredCourseList.size(); i++) {
            temp = filteredCourseList.get(i);
            if (!(institutionList.contains(temp.getInstitution()))) {
                institutionList.add(temp.getInstitution());
            }
        }

        //Get list of distances from the institutions
        for (int i = 0; i < institutionList.size(); i++) {
            d1.execute(Integer.toString(postalCode), Integer.toString(institutionList.get(i).getPostalCode()));
            try {
                sortedDistanceList.add(d1.get());
                //Log.d("distance", String.valueOf(distance));
            } catch (Exception e) {
                //Log.d("distance_error", e.toString());
            }
        }

        //Sort the distances in ascending order
        Collections.sort(sortedDistanceList);

        //Sort the institutions using sorted distances list
        for (int i = 0; i < sortedDistanceList.size(); i++) {
            for (int j = 0; j < institutionList.size(); j++) {
                d1.execute(Integer.toString(postalCode), Integer.toString(institutionList.get(j).getPostalCode()));
                try {
                    double distance = d1.get();

                    if (distance == sortedDistanceList.get(i)) {
                        sortedInstitutionList.add(institutionList.get(j));
                    }
                    //Log.d("distance", String.valueOf(distance));
                } catch (Exception e) {
                    //Log.d("distance_error", e.toString());
                }
            }
        }

        //Sort the courses in ascending distance orders using sorted institutions list
        for (int i = 0; i < sortedInstitutionList.size(); i++) {
            for (int j = 0; j < filteredCourseList.size(); j++) {
                temp = filteredCourseList.get(j);
                if (temp.getInstitution().equals(sortedInstitutionList.get(i))) {
                    sortedCourseList.add(temp);
                }
            }
        }
        return sortedCourseList;
    }*/

    public ArrayList<Course> search(String interest, String specialization, int L1R4, int postalCode) {
        ArrayList<PolytechnicCourse> courseList = new ArrayList<PolytechnicCourse>();
        ArrayList<PolytechnicCourse> filteredCourseList = new ArrayList<PolytechnicCourse>();
        ArrayList<Course> sortedCourseList = new ArrayList<Course>();
        ArrayList<Institution> institutionList = new ArrayList<Institution>();
        ArrayList<Institution> sortedInstitutionList = new ArrayList<Institution>();
        ArrayList<Double> sortedDistanceList = new ArrayList<Double>();
        PolytechnicCourse temp = new PolytechnicCourse();
        DistanceCalculation d1 = new DistanceCalculation();

        //Retrieve full list of polytechnic courses
        courseList = CourseController.retrieveListOfPolyCourses();

        //Filter list according to inputs
        for (int i = 0; i < courseList.size(); i++) {
            temp = courseList.get(i);
            //Filter by interests and specialization
            if (temp.getInterest().equals(interest) && temp.getSpecialization().equals(specialization)) {
                //Filter by L1R4
                if (L1R4 <= temp.getL1R4()) {
                    filteredCourseList.add(courseList.get(i));
                }
            }
        }

        //Get list of unique institutions after filter
        for (int i = 0; i < filteredCourseList.size(); i++) {
            temp = filteredCourseList.get(i);
            if (!(institutionList.contains(temp.getInstitution()))) {
                institutionList.add(temp.getInstitution());
            }
        }

        //Get list of distances from the institutions
        for (int i = 0; i < institutionList.size(); i++) {
            d1.execute(Integer.toString(postalCode), Integer.toString(institutionList.get(i).getPostalCode()));
            try {
                sortedDistanceList.add(d1.get());
                //Log.d("distance", String.valueOf(distance));
            } catch (Exception e) {
                //Log.d("distance_error", e.toString());
            }
        }

        //Sort the distances in ascending order
        Collections.sort(sortedDistanceList);

        //Sort the institutions using sorted distances list
        for (int i = 0; i < sortedDistanceList.size(); i++) {
            for (int j = 0; j < institutionList.size(); j++) {
                d1.execute(Integer.toString(postalCode), Integer.toString(institutionList.get(j).getPostalCode()));
                try {
                    double distance = d1.get();

                    if (distance == sortedDistanceList.get(i)) {
                        sortedInstitutionList.add(institutionList.get(j));
                    }
                    //Log.d("distance", String.valueOf(distance));
                } catch (Exception e) {
                    //Log.d("distance_error", e.toString());
                }
            }
        }

        //Sort the courses in ascending distance orders using sorted institutions list
        for (int i = 0; i < sortedInstitutionList.size(); i++) {
            for (int j = 0; j < filteredCourseList.size(); j++) {
                temp = filteredCourseList.get(j);
                if (temp.getInstitution().equals(sortedInstitutionList.get(i))) {
                    sortedCourseList.add(temp);
                }
            }
        }
        return sortedCourseList;
    }
}
