package com.springrest.springrest.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.dao.CourseDao;
import java.util.*;

@Service

public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDao coursedao;
	
	
	@Override
	public ArrayList<Course> getCourses()
	{
		return (ArrayList)coursedao.findAll();
	}
	
	
	public Course getCourse(long id)
	{
		
		Course course=coursedao.getById(id);
		System.out.println(course);
		return course;
	}
	
	public Course addCourse(Course c)
	{
		coursedao.save(c);
		return c;
	}
	
	public Course updateCourse(Course c)
	{
		coursedao.save(c);
		return c;
	}
	
	public void deleteCourse(long id)
	{
		Course entity=coursedao.getOne(id);
		coursedao.delete(entity);
	}
	
}
	
	