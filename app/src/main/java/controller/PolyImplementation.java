package controller;

import android.content.Context;

import com.example.utsav.edufind.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

import entity.Course;
import entity.Institution;
import entity.PolytechnicCourse;

/**
 * Created by darks on 26-Oct-17.
 */

public class PolyImplementation implements DataStoreInterface{

    private static BufferedReader br = null;
    private static String line;
    private static String delimiter = "\\^";
    private static String[] tempArray;
    private Context context;
    private static InputStream stream;

    public PolyImplementation(Context context){
        this.context = context;
    }

    @Override
    public ArrayList<Object> retrieveList() {
        ArrayList<Object> polyList = new ArrayList<Object>();
        stream = context.getResources().openRawResource(R.raw.polytechnic);
        //Create IO object using IO factory
        //Pass the IO object to the Course Factory as parameter
        //Retrieve the list using IO

        try {
            br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                Course poly = CourseFactory.createCourse("poly");
                tempArray = line.split(delimiter);
                Institution institute = new Institution(tempArray[0], tempArray[12], Integer.parseInt(tempArray[5]));
                poly.setInstitution(institute);
                poly.setSchool(tempArray[1]);
                poly.setCourseName(tempArray[2]);
                poly.setInterest(tempArray[3]);
                poly.setSpecialization(tempArray[4]);
                ((PolytechnicCourse)poly).setL1R4(Integer.parseInt(tempArray[6]));
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


}
