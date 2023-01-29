package com.springrest.springrest.services;
import java.util.ArrayList;
 import com.springrest.springrest.entities.Course;

public interface CourseService {
	
	public ArrayList<Course>getCourses();
	public Course getCourse(long id);
	public Course addCourse(Course course);
	public Course updateCourse(Course course);
	public void deleteCourse(long id);

}
