package controller;

import android.content.Context;

import com.example.utsav.edufind.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import entity.Course;
import entity.Institution;
import entity.UniversityCourse;

/**
 * Created by darks on 26-Oct-17.
 */

public class UniImplementation implements DataStoreInterface{

    private static BufferedReader br = null;
    private static String line;
    private static String delimiter = "\\^";
    private static String[] tempArray;
    private Context context;
    private static InputStream stream;

    public UniImplementation(Context context){
        this.context = context;
    }

    @Override
    public ArrayList<Object> retrieveList() {
        ArrayList<Object> uniList = new ArrayList<Object>();
        stream = context.getResources().openRawResource(R.raw.university);
        try {
            br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                Course uni = CourseFactory.createCourse("uni");
                tempArray = line.split(delimiter);
                Institution institute = new Institution(tempArray[0], tempArray[11], Integer.parseInt(tempArray[5].replaceAll("\\s+","")));
                uni.setInstitution(institute);
                uni.setSchool(tempArray[1]);
                uni.setCourseName(tempArray[2]);
                uni.setInterest(tempArray[3]);
                uni.setspecialisation(tempArray[4]);
                ((UniversityCourse)uni).setGradePointAverage(Double.parseDouble(tempArray[6]));
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
}
