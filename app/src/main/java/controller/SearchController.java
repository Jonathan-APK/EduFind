package controller;

import android.content.Context;

import entity.Course;
import entity.PolytechnicCourse;
import entity.Institution;
import entity.UniversityCourse;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class takes in the search parameters entered by the user to do search, filtering and sorting on the data
 * of the available courses provided by the csv files from the CourseController
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class SearchController {
    private Context current;

    public SearchController(Context current) {
        this.current = current;
    }

    /**
     * This method checks that the postalCode passed is valid (6 digits only)
     * @param postalCode The postal code entered by the user
     */
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

    /**
     * This method uses the search parameters entered by the user to search, filter, and sort the results according to distance
     * @param interest The interest specified by the user
     * @param specialization The specialization specified by the user
     * @param L1R4 The L1R4 grade of the user
     * @param postalCode The postal code entered by the user
     */
    public ArrayList<Course> search(String interest, String specialization, int L1R4, int postalCode) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        ArrayList<Course> filteredCourseList = new ArrayList<Course>();
        ArrayList<Course> sortedCourseList = new ArrayList<Course>();
        ArrayList<Institution> institutionList = new ArrayList<Institution>();
        ArrayList<Institution> sortedInstitutionList = new ArrayList<Institution>();
        ArrayList<Double> sortedDistanceList = new ArrayList<Double>();
        Course temp = new Course();

        /*//Retrieve full list of polytechnic courses and university courses
        CourseController cc = new CourseController(current);
        courseList = cc.retrieveListOfPolyCourses();
        courseList.addAll(cc.retrieveListOfUniCourses());*/

        //PK FACTORY TEST /Retrieve full list of polytechnic courses and university courses
        DataStoreInterface di = DataStoreFactory.getDatastore("poly",current);
        courseList = (ArrayList<Course>)(Object)di.retrieveList();
        di = DataStoreFactory.getDatastore("uni",current);
        courseList.addAll((ArrayList<Course>)(Object)di.retrieveList());

        //Filter list according to inputs
        for (int i = 0; i < courseList.size(); i++) {
            temp = courseList.get(i);
            //Filter by interests and specialization
            if (temp.getInterest().toLowerCase().equals(interest.toLowerCase()) && temp.getSpecialization().toLowerCase().contains(specialization.toLowerCase())) {
                //Filter by L1R4
                if (temp instanceof PolytechnicCourse) {
                    if (L1R4 <= ((PolytechnicCourse) temp).getL1R4()) {
                        filteredCourseList.add(courseList.get(i));
                    }
                } else if (temp instanceof UniversityCourse) {
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
            DistanceCalculation d1 = new DistanceCalculation();
            d1.execute(String.valueOf(postalCode), String.valueOf(institutionList.get(i).getPostalCode()));
            try {
                double distance = d1.get();
                sortedDistanceList.add(distance);
            } catch (Exception e) {
            }
        }

        //Sort the distances in ascending order
        Collections.sort(sortedDistanceList);

        //Sort the institutions using sorted distances list
        for (int i = 0; i < sortedDistanceList.size(); i++) {
            for (int j = 0; j < institutionList.size(); j++) {
                DistanceCalculation d1 = new DistanceCalculation();
                d1.execute(String.valueOf(postalCode), String.valueOf(institutionList.get(j).getPostalCode()));
                try {
                    double distance = d1.get();
                    if (distance == sortedDistanceList.get(i)) {
                        sortedInstitutionList.add(institutionList.get(j));
                    }
                } catch (Exception e) {
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
