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
import entity.PolytechnicCourse;

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
        ArrayList<Object> polyList = new ArrayList<>();
        stream = context.getResources().openRawResource(R.raw.polytechnic);
        try {
            br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                Course poly = CourseFactory.createCourse("poly");
                tempArray = line.split(delimiter);
                Institution institute = new Institution(tempArray[0], tempArray[12], Integer.parseInt(tempArray[5]));
                poly.setInstitution(institute);
                poly.setCourseName(tempArray[2]);
                poly.setInterest(tempArray[3]);
                poly.setSpecialisation(tempArray[4]);
                ((PolytechnicCourse)poly).setL1R4(Integer.parseInt(tempArray[6]));
                poly.setIntake(Integer.parseInt(tempArray[8]));
                poly.setWebsite(tempArray[9]);
                poly.setCourseDescription(tempArray[10]);
                poly.setCareerProspect(tempArray[11]);
                polyList.add(poly);
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
        return polyList;
    }


}
