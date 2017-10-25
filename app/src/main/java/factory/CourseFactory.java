package factory;

import java.util.ArrayList;

import entity.Course;
import entity.PolytechnicCourse;
import entity.UniversityCourse;

public class CourseFactory {
    private ArrayList<Course> courseList;
    private Course temp;

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public Course getTemp() {
        return temp;
    }
}
