package controller;

import entity.Course;
import entity.PolytechnicCourse;
import entity.UniversityCourse;

/**
 * Created by darks on 26-Oct-17.
 */

public class CourseFactory {


    public static Course createCourse(String course){

        Course cc = null;

        if(course.equalsIgnoreCase("poly")){
            cc = new PolytechnicCourse();
        }
        else if(course.equalsIgnoreCase("uni")){
            cc = new UniversityCourse();
        }
        return cc;
    }
}
