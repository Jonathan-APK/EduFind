package controller;

import android.content.Context;

import factory.DataStoreFactory;
import entity.Course;
import entity.PolytechnicCourse;
import entity.Institution;
import entity.UniversityCourse;
import strategy.DataStoreInterface;

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
     * @param specialisation The specialisation specified by the user
     * @param L1R4 The L1R4 grade of the user
     * @param postalCode The postal code entered by the user
     */
    public ArrayList<Course> search(String interest, String specialisation, int L1R4, int postalCode) {
        ArrayList<Course> courseList;
        ArrayList<Course> filteredCourseList = new ArrayList<>();
        ArrayList<Course> sortedCourseList = new ArrayList<>();
        ArrayList<Institution> institutionList = new ArrayList<>();
        ArrayList<Institution> sortedInstitutionList = new ArrayList<>();
        ArrayList<Double> sortedDistanceList = new ArrayList<>();
        Course temp;

        //Retrieve full list of polytechnic courses and university courses
        DataStoreInterface di = DataStoreFactory.createDatastore("poly",current);
        courseList = (ArrayList<Course>)(Object)di.retrieveList();
        di = DataStoreFactory.createDatastore("uni",current);
        courseList.addAll((ArrayList<Course>)(Object)di.retrieveList());

        //Filter list according to inputs
        for (int i = 0; i < courseList.size(); i++) {
            temp = courseList.get(i);
            //Filter by interests and specialisation
            if (temp.getInterest().toLowerCase().equals(interest.toLowerCase()) && temp.getSpecialisation().toLowerCase().contains(specialisation.toLowerCase())) {
                //Filter by L1R4 (for poly)
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
            for (int j = 0; j < institutionList.size(); j++) {
                if (!(institutionList.get(j).getInstitution().equals(temp.getInstitution().getInstitution()))) {
                    institutionList.add(temp.getInstitution());
                }
            }
        }

        //Get list of distances from the institutions
        for (int i = 0; i < institutionList.size(); i++) {
            DistanceCalculator d1 = new DistanceCalculator();
            d1.execute(String.valueOf(postalCode), String.valueOf(institutionList.get(i).getPostalCode()));
            try {
               // Thread.currentThread().sleep(10000);
                double distance = d1.get();
                sortedDistanceList.add(distance);
            } catch (Exception e) {
            }
        }

        //Sort the distances in ascending order
        Collections.sort(sortedDistanceList);

        //Only sort by distance if GoogleMapsAPI working correctly
        if (sortedDistanceList.size() != 0) {
            //Sort the institutions using sorted distances list
            for (int i = 0; i < sortedDistanceList.size(); i++) {
                for (int j = 0; j < institutionList.size(); j++) {
                    DistanceCalculator d1 = new DistanceCalculator();
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
        }
        else {
            //No distance sorting
            for (int j = 0; j < institutionList.size(); j++) {
                sortedInstitutionList.add(institutionList.get(j));
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
