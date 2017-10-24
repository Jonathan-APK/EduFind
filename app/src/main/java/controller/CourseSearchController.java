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

    //Tested to be working. Waiting to fill pk's data. Does search, filter and sort properly
    public ArrayList<Course> search(String interest, String specialization, int L1R4, int postalCode) {
        ArrayList<PolytechnicCourse> courseList = new ArrayList<PolytechnicCourse>();
        ArrayList<PolytechnicCourse> filteredCourseList = new ArrayList<PolytechnicCourse>();
        ArrayList<Course> sortedCourseList = new ArrayList<Course>();
        ArrayList<Institution> institutionList = new ArrayList<Institution>();
        ArrayList<Institution> sortedInstitutionList = new ArrayList<Institution>();
        ArrayList<Double> sortedDistanceList = new ArrayList<Double>();
        PolytechnicCourse temp = new PolytechnicCourse();

        //Retrieve full list of polytechnic courses
        //courseList = CourseController.retrieveListOfPolyCourses();
        Institution int1 = new Institution("Nanyang Polytechnic", "NYP Desc", 569830);
        Institution int2 = new Institution("Singapore Polytechnic", "SP Desc", 139651);
        PolytechnicCourse pc1 = new PolytechnicCourse("Diploma in Food Science", "APPLIED SCIENCES", "Food Science", "website1", "School of Health Science", "Diploma", "Desc", int1, 10, 14);
        PolytechnicCourse pc2 = new PolytechnicCourse("Diploma in Culinary", "APPLIED SCIENCES", "Food Science", "website1", "School of Arts", "Diploma", "Desc", int2, 10, 12);
        PolytechnicCourse pc3 = new PolytechnicCourse("Diploma in Culinary Arts", "APPLIED SCIENCES", "Food Science", "website1", "School of Health Science", "Diploma", "Desc", int1, 10, 20);
        PolytechnicCourse pc4 = new PolytechnicCourse("Diploma in Biomedical Science", "APPLIED SCIENCES", "Biomedical", "website1", "Singapore Polytechnic", "Diploma", "Desc", int1, 10, 16);
        courseList.add(pc1);
        courseList.add(pc2);
        courseList.add(pc3);
        courseList.add(pc4);

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
