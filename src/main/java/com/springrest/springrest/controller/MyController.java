package com.springrest.springrest.controller;
import com.springrest.springrest.Helper.FileUploadHelper;
import com.springrest.springrest.entities.Course;

import com.springrest.springrest.services.CourseService;
import com.springrest.springrest.services.CourseServiceImpl;
import org.springframework.http.MediaType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MyController {
	
	@GetMapping("/home")
	@CrossOrigin(origins = "https://localhost:3000")
	
	public String home()
	{
		return "This is home page";
	}
	 
	
	@PostMapping("/sendEmail")
	@CrossOrigin(origins = "https://localhost:3000")
	
	public ResponseEntity<String> handleEmail(@RequestParam String email)
	{
		if(email==null || email.isEmpty())
		{
			System.out.println(email);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid Email Address");
		}
		
		String emailRegex= "^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern=Pattern.compile(emailRegex);
		if(pattern.matcher(email).matches())
		{
			System.out.println(email);
			 return ResponseEntity.ok("Valid Email Address");
		}
		System.out.println(email);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid Email Address");

		
	}
	
	
		
    @Autowired
	public FileUploadHelper helper;
	
	@CrossOrigin(origins = "https://localhost:3000")
	@RequestMapping(value="/upload", method = RequestMethod.POST    ) 
	@ResponseBody

	public ResponseEntity <String> fileUpload(@RequestParam("file111") MultipartFile file ) 
	{

		try {
		
		
		if(file.isEmpty())
		{
			System.out.println("Please also pass File here");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain a file");
			
		}
	
		if(!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
		{
			System.out.println("only Excel File are allowed");
		return ResponseEntity.ok("Sorry!! Only Excel File can be uploaded in server");
		
		
		}
		
		if( file.getSize()  > 2000006  )
			{
		    return ResponseEntity.ok("Size cannot exceed 2 mb!!");

		}
		
		
		
			
		
		
//		System.out.println("Your File Name:"+ file.getOriginalFilename());
//		System.out.println("Your File Size:" +  file.getSize());
//		System.out.println(file.getName());
		
		
		else {
			
			      if(helper.uploadFile(file))
			     {
			    System.out.println("Your file is successfully upladed");
			    System.out.println("Type:"+ file.getContentType());
			    System.out.println("Your File size  :" + file.getSize());
			    return ResponseEntity.ok("Congrats!!File has been Successfully uploaded in the server");
			     }
			
			    else {	  
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain a file");
			      } 

		}
		
		}catch(Exception e) {
			e.printStackTrace();
		     }
		return ResponseEntity.ok("working....");
		
		
		}   //end of function
	
	
		
	
	@Autowired
	public CourseService obj;
	
	
	@GetMapping("/courses")
	@CrossOrigin(origins = "https://localhost:3000")
	public ArrayList<Course> getCourses()
	{
		return obj.getCourses();
	}
	
	@GetMapping("/courses/{id}")
	@CrossOrigin(origins = "https://localhost:3000")
	
	public String getCourse(@PathVariable String id)
	{
		Course p=obj.getCourse(Long.parseLong(id));
		System.out.println("course=" +p );
		return p.getTitle();
		
		
	}
	
	@PostMapping("/courses")
	@CrossOrigin(origins = "https://localhost:3000")

	public Course addCourse(@RequestBody  Course course )
	{
	
		  return obj.addCourse(course);
	}
	
	@PutMapping("/courses")
	public Course updateCourse(@RequestBody Course course)
	{
		return obj.updateCourse(course);
	}
	
	@DeleteMapping("/courses/{id}") 
	@CrossOrigin(origins = "https://localhost:3000")
	
	public  ResponseEntity<HttpStatus> deleteCourse(@PathVariable String id)
	{
		try
		{
			obj.deleteCourse(Long.parseLong(id));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	

}
