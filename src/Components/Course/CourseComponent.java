/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University
 */
package Components.Course;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CourseComponent {
    protected ArrayList<Course> vCourse;

    public CourseComponent(String sCourseFileName) throws FileNotFoundException, IOException { 	
        BufferedReader bufferedReader  = new BufferedReader(new FileReader(sCourseFileName));       
        this.vCourse  = new ArrayList<Course>();
        while (bufferedReader.ready()) {
            String courseInfo = bufferedReader.readLine();
            if(!courseInfo.equals("")) this.vCourse.add(new Course(courseInfo));
        }    
        bufferedReader.close();
    }
    public ArrayList<Course> getCourseList() { return this.vCourse; }
    public Course getCourse(String courseId) {
    	for (int i = 0; i < vCourse.size(); i++) {
			Course course = (Course) this.vCourse.get(i);
			if (course.match(courseId)) return course;
		}
		return null;
	}
    public boolean isRegisteredCourse(String courseId) {
        for (int i = 0; i < this.vCourse.size(); i++) {
            if (((Course) this.vCourse.get(i)).match(courseId)) return true;
        }
        return false;
    }
	public boolean deleteCourse(String cID) {
		for (int i = 0; i < vCourse.size(); i++) {
			Course course = (Course) this.vCourse.get(i);
			if (course.match(cID))
				if (this.vCourse.remove(course)) return true;
			else return true;
		}
		return false;
	}
	
}
