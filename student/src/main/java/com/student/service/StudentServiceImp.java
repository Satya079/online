package com.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.dao.CourseDao;
import com.student.dto.CourseDto;
import com.student.entity.Student;
import com.student.repository.StudentRepository;


import com.student.exception.ResourceNotFoundException;

@Service
public class StudentServiceImp implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseDao dao;
	
	public Student saveDetails(Student student)
	{
		return studentRepository.save(student);
	}
	
	public Student searchById(long id)
	{
	    Student student = studentRepository.findByStudentId(id).get();
	    
	    if(student==null)
	    {
	    	throw new ResourceNotFoundException("Student not found for Id"+id);
	    }
	    return student;
	}

	
	
	 @Override
	    public String delete(long id) {
	        Student student = searchById(id);
	        studentRepository.delete(student);
	        return "Student deleted successfully";
	    }

	 @Override
	    public Student update(Student student) {
	        searchById(student.getStudentId()); // Check if student exists
	        return studentRepository.saveAndFlush(student);
	    }
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}
	
	
	public String registerForCourse(long studentId,long courseId)
	{
		if(searchById(studentId)==null)
		{
			throw new ResourceNotFoundException("Student not found for Id");
		}
		if(dao.searchByCourseId(courseId)==null)
		{
			throw new ResourceNotFoundException("Course not found for Id");
		}
		
		CourseDto course = dao.searchByCourseId(courseId);
		
		if (course.getMaxStudent() >= course.getMaxStudent()) {
			throw new IllegalStateException("Course is already full!");
		}
 
		course.setMaxStudent(course.getMaxStudent() - 1);
        dao.update(course);
		// Set the course for the student
	    searchById(studentId).setCourseId((int)courseId);
		// Update the student in the database
		update( searchById(studentId));
		
		return "registrated successfully";
	}

	public int getScore(long id) {
		
		return searchById(id).getScore();
	}

	public List<Student> getAllPass() {
		List<Student> st=studentRepository.findAll();
		List<Student> pass = new ArrayList<>();
		for(Student x:st) {
			if(x.getScore()>=1) {
				pass.add(x);
			}
		}
		return pass;
	}

	public List<Student> getAllFail() {
		List<Student> st=studentRepository.findAll();
		List<Student> fail = new ArrayList<>();
		for(Student x:st) {
			if(x.getScore()<1) {
				fail.add(x);
			}
		}
		return fail;	}
	
	

}
