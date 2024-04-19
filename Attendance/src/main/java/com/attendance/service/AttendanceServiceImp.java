package com.attendance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.dao.StudentDao;
import com.attendance.entity.Attendance;
import com.attendance.repository.AttendanceRepository;
import com.attendance.exception.ResourceNotFoundException;
@Service
public class AttendanceServiceImp implements AttendanceService{
	@Autowired
	private AttendanceRepository attendanceRepository;
	@Autowired
	private StudentDao dao;

	@Override
	public Attendance saveDetail(Attendance attendance) {
	
		
		if(dao.SearchByStudent(attendance.getStudentId())==null)
		{
			throw new ResourceNotFoundException("Attendance not found for StudentId");

		}
		   
		attendance.setDate(LocalDate.now().toString());
		
		return attendanceRepository.save(attendance);
	}

	@Override
	public Attendance update(Attendance attendance) {
		if(!attendanceRepository.findById(attendance.getId()).isPresent())
		{
			throw new ResourceNotFoundException("Attendance not found for Id");

		}
		return attendanceRepository.save(attendance);
	}
   
	public List<String> getStatus(boolean status)
	{
		List<String> names= new ArrayList<>();
		
		 List<Attendance> findAll = attendanceRepository.findAll();
		 
		 for(Attendance a:findAll)
		 {
			 if(a.getStatus())
			 {
				 if(dao.SearchByStudent(a.getStudentId())==null){}
				 else
				 {
					 names.add(dao.SearchByStudent(a.getStudentId()).getFirstName());
				 }
			 }
		 }
		
		return names;
	}
}
