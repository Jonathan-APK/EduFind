package strategy;

import android.content.Context;

import com.example.utsav.edufind.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import factory.CourseFactory;
import entity.Course;
import entity.Institution;
import entity.UniversityCourse;

/**
 * This class is use to manage the I/O operation of the University csv file
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-29
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

    /**
     * Retrieve all university information from csv and store in a Arraylist of object type
     * @return ArrayList<Object> Arraylist containing all information from the university csv file
     */
    @Override
    public ArrayList<Object> retrieveList() {
        ArrayList<Object> uniList = new ArrayList<>();
        stream = context.getResources().openRawResource(R.raw.university);
        try {
            br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                Course uni = CourseFactory.createCourse("uni");
                tempArray = line.split(delimiter);
                Institution institute = new Institution(tempArray[0], tempArray[12], Integer.parseInt(tempArray[5].replaceAll("\\s+","")));
                uni.setInstitution(institute);
                uni.setCourseName(tempArray[2]);
                uni.setInterest(tempArray[3]);
                uni.setSpecialisation(tempArray[4]);
                ((UniversityCourse)uni).setGradePointAverage(Double.parseDouble(tempArray[6]));
                uni.setIntake(Integer.parseInt(tempArray[8]));
                uni.setWebsite(tempArray[9]);
                uni.setCourseDescription(tempArray[10]);
                uni.setCareerProspect(tempArray[11]);
                uniList.add(uni);
            }
        } catch (Exception e) {
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
