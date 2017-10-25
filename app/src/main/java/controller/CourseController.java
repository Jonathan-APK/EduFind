package controller;

import java.net.URL;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import entity.PolytechnicCourse;
import entity.UniversityCourse;
import entity.Institution;

/**
 * Created by boonleng94 on 27/9/2017.
 */

public class CourseController {
    private static Institution institute;
    private static String csvFile;
    private static BufferedReader br = null;
    private static String line;
    private static String delimiter = "^";
    private static String[] tempArray;
    private static ArrayList<UniversityCourse> uniList;
    private static ArrayList<PolytechnicCourse> polyList;
    private static PolytechnicCourse poly;
    private static UniversityCourse uni;
    private static String polyFile = "C:/Users/boonleng94/AndroidStudioProjects/EduFind/app/src/main/data/Polytechnic.csv";
    private static String uniFile = "C:/Users/boonleng94/AndroidStudioProjects/EduFind/app/src/main/data/University.csv";

    public static ArrayList<PolytechnicCourse> retrieveListOfPolyCourses() {
        polyList = new ArrayList<PolytechnicCourse>();
        poly = new PolytechnicCourse();

        //Create IO object using IO factory
        //Pass the IO object to the Course Factory as parameter
        //Retrieve the list using IO

        csvFile = polyFile;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                tempArray = line.split(delimiter);

                institute = new Institution(tempArray[0], tempArray[12], Integer.parseInt(tempArray[5]));

                poly.setInstitution(institute);
                poly.setSchool(tempArray[1]);
                poly.setCourseName(tempArray[2]);
                poly.setInterest(tempArray[3]);
                poly.setSpecialization(tempArray[4]);
                poly.setL1R4(Integer.parseInt(tempArray[6]));
                poly.setEducationLevel(tempArray[7]);
                poly.setIntake(Integer.parseInt(tempArray[8]));
                poly.setWebsite(tempArray[9]);
                poly.setCourseDescription(tempArray[10]);

                polyList.add(poly);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return polyList;
    }

    public static ArrayList<UniversityCourse> retrieveListOfUniCourses() {

        uniList = new ArrayList<UniversityCourse>();
        uni = new UniversityCourse();

        csvFile = uniFile;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                tempArray = line.split(delimiter);

                institute = new Institution(tempArray[0], tempArray[12], Integer.parseInt(tempArray[5]));

                uni.setInstitution(institute);
                uni.setSchool(tempArray[1]);
                uni.setCourseName(tempArray[2]);
                uni.setInterest(tempArray[3]);
                uni.setSpecialization(tempArray[4]);
                uni.setGradePointAverage(Integer.parseInt(tempArray[6]));
                uni.setEducationLevel(tempArray[7]);
                uni.setIntake(Integer.parseInt(tempArray[8]));
                uni.setWebsite(tempArray[9]);
                uni.setCourseDescription(tempArray[10]);

                uniList.add(uni);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return uniList;
    }

    public static ArrayList<Institution> retrieveListOfInstitution() {
        ArrayList<Institution> schList = new ArrayList<Institution>();
        uniList = retrieveListOfUniCourses();
        polyList = retrieveListOfPolyCourses();
        boolean alreadyAdded = false;

        for (int i = 0; i < uniList.size(); i++) {

            if (schList.isEmpty()) {
                institute = uniList.get(i).getInstitution();
                schList.add(institute);
            } else {

                for (int j = 0; j < schList.size(); j++) {

                    if (uniList.get(i).getInstitution().getInstitution().equals(schList.get(j).getInstitution())) {
                        alreadyAdded = true;
                        break;
                    }

                }

                if (alreadyAdded = false) {
                    schList.add(institute);
                } else
                    alreadyAdded = false;

            }

        }


        for (int i = 0; i < polyList.size(); i++) {

            if (schList.isEmpty()) {

                institute = polyList.get(i).getInstitution();
                schList.add(institute);

            } else {

                for (int j = 0; j < schList.size(); j++) {

                    if (polyList.get(i).getInstitution().getInstitution().equals(schList.get(j).getInstitution())) {
                        alreadyAdded = true;
                        break;
                    }

                }

                if (alreadyAdded = false) {
                    schList.add(institute);
                } else
                    alreadyAdded = false;

            }

        }
        return schList;
    }
}
