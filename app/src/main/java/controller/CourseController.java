package controller;

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

    public static ArrayList<PolytechnicCourse> retrieveListOfPolyCourses() {

        ArrayList<PolytechnicCourse> polyList = new ArrayList<PolytechnicCourse>();
        PolytechnicCourse poly = new PolytechnicCourse();
        Institution institute  = new Institution();

        String csvFile = "/Users/darks/Desktop/poly.csv";
        BufferedReader br = null;
        String line = "";
        String delimiter = "^";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] tempPoly = line.split(delimiter);

                poly.setInstitution(tempPoly[0]);
                poly.setSchool(tempPoly[0]);
                poly.setCourseName(tempPoly[0]);
                poly.setInterest(tempPoly[0]);
                poly.setSpecialization(tempPoly[0]);
                poly.setL1R4(tempPoly[0]);
                poly.setEducationLevel(tempPoly[0]);
                poly.setIntake(tempPoly[0]);
                poly.setWebsite(tempPoly[0]);
                poly.setCourseDescription(tempPoly[0]);

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
        return list;
    }

    public static ArrayList<UniversityCourse> retrieveListOfUniCourses() {
        ArrayList<UniversityCourse> uniList = new ArrayList<UniversityCourse>();

        //call ur IO shit here

        return list;
    }

    public static ArrayList<Institution> retrieveListOfInstitution() {
        ArrayList<Institution> schList = new ArrayList<Institution>();

        //call ur IO shit here

        return list;
    }
}
