package factory;

import entity.Course;
import entity.PolytechnicCourse;
import entity.UniversityCourse;

/**
 * This class is use to instantiate PolytechnicCourse or UniversityCourse objects on behalf of the caller
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-29
 */
public class CourseFactory {

    /**
     * Instantiate PolytechnicCourse or UniversityCourse based on parameter
     * @param course type of course to instantiate
     * @return Course course object
     */
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
