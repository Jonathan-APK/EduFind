package controller;

import com.example.utsav.edufind.R;

import android.content.Context;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import entity.Course;
import entity.PolytechnicCourse;
import entity.UniversityCourse;
import entity.Institution;


public class CourseController {
    private static String csvFile;
    private static BufferedReader br = null;
    private static String line;
    private static String delimiter = "\\^";
    private static String[] tempArray;
    private Context context;
    private static InputStream stream;

    public CourseController(Context context) {
        this.context = context;
    }

    public ArrayList<Course> retrieveListOfPolyCourses() {
        ArrayList<Course> polyList = new ArrayList<Course>();
        stream = context.getResources().openRawResource(R.raw.polytechnic);
        //Create IO object using IO factory
        //Pass the IO object to the Course Factory as parameter
        //Retrieve the list using IO

        try {
            br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                PolytechnicCourse poly = new PolytechnicCourse();
                tempArray = line.split(delimiter);
                Institution institute = new Institution(tempArray[0], tempArray[12], Integer.parseInt(tempArray[5]));
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

    public ArrayList<Course> retrieveListOfUniCourses() {
        ArrayList<Course> uniList = new ArrayList<Course>();
        stream = context.getResources().openRawResource(R.raw.university);
        try {
            br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                UniversityCourse uni = new UniversityCourse();
                tempArray = line.split(delimiter);
                Institution institute = new Institution(tempArray[0], tempArray[11], Integer.parseInt(tempArray[5].replaceAll("\\s+","")));
                uni.setInstitution(institute);
                uni.setSchool(tempArray[1]);
                uni.setCourseName(tempArray[2]);
                uni.setInterest(tempArray[3]);
                uni.setSpecialization(tempArray[4]);
                uni.setGradePointAverage(Double.parseDouble(tempArray[6]));
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

    /*public static ArrayList<Institution> retrieveListOfInstitution() {
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
    }*/
}
