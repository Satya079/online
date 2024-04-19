package com.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.attendance.controller.AttendanceController;
import com.attendance.dto.StudentDto;
import com.attendance.entity.Attendance;
import com.attendance.exception.GlobalExceptionHandler;
import com.attendance.exception.ResourceNotFoundException;
import com.attendance.service.AttendanceServiceImp;

 class AttendanceControllerTests {
	 private Attendance attendance;
	 private StudentDto studentDto;
	 private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private AttendanceServiceImp attendanceServiceImp;

    @InjectMocks
    private AttendanceController attendanceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        attendance = new Attendance();
        studentDto = new StudentDto();
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
     void testSaveDetails() {
        int studentId = 123;
        Attendance attendance = new Attendance();
        when(attendanceServiceImp.saveDetail(any(Attendance.class))).thenReturn(attendance);

        Attendance savedAttendance = attendanceController.saveDetails(studentId);
        assertEquals(attendance, savedAttendance);
    }

    @Test
     void testUpdate() {
        Attendance attendance = new Attendance();
        when(attendanceServiceImp.update(any(Attendance.class))).thenReturn(attendance);

        Attendance updatedAttendance = attendanceController.update(attendance);
        assertEquals(attendance, updatedAttendance);
    }

    @Test
     void testGetStatus() {
        List<String> statusList = List.of("Present", "Absent");
        when(attendanceServiceImp.getStatus(true)).thenReturn(statusList);

        List<String> result = attendanceController.getStatus();
        assertEquals(statusList, result);
    }
    @Test
     void testSetAndGetId() {
        long id = 1;
        attendance.setId(id);
        assertEquals(id, attendance.getId());
    }

    @Test
    void testSetAndGetDate() {
        String date = "2024-04-19";
        attendance.setDate(date);
        assertEquals(date, attendance.getDate());
    }

    @Test
     void testSetAndGetStatus() {
        boolean status = true;
        attendance.setStatus(status);
        assertEquals(status, attendance.getStatus());
    }

    @Test
     void testSetAndGetStudentId() {
        int studentId = 123;
        attendance.setStudentId(studentId);
        assertEquals(studentId, attendance.getStudentId());
    }
    @Test
     void testSetAndGetStudentId1() {
        long studentId = 1;
        studentDto.setStudentId(studentId);
        assertEquals(studentId, studentDto.getStudentId());
    }

    @Test
     void testSetAndGetFirstName() {
        String firstName = "John";
        studentDto.setFirstName(firstName);
        assertEquals(firstName, studentDto.getFirstName());
    }

    @Test
     void testSetAndGetLastName() {
        String lastName = "Doe";
        studentDto.setLastName(lastName);
        assertEquals(lastName, studentDto.getLastName());
    }

    @Test
     void testSetAndGetDateOfBirth() {
        String dateOfBirth = "2000-01-01";
        studentDto.setDateOfBrith(dateOfBirth);
        assertEquals(dateOfBirth, studentDto.getDateOfBrith());
    }

    @Test
     void testSetAndGetContactNumber() {
        String contactNumber = "1234567890";
        studentDto.setContactNum(contactNumber);
        assertEquals(contactNumber, studentDto.getContactNum());
    }

    @Test
     void testSetAndGetPassword() {
        String password = "password123";
        studentDto.setPassword(password);
        assertEquals(password, studentDto.getPassword());
    }

    @Test
     void testSetAndGetEmail() {
        String email = "john.doe@example.com";
        studentDto.setEmail(email);
        assertEquals(email, studentDto.getEmail());
    }

    @Test
     void testHandleResourceNotFoundException() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Resource not found");
        ResponseEntity<Object> response = globalExceptionHandler.handleResourceNotFoundException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Resource not found", response.getBody());
    }

    @Test
     void testHandleGlobalException() {
        Exception ex = new Exception("Internal Server Error");
        ResponseEntity<Object> response = globalExceptionHandler.handleGlobalException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Student not found for id", response.getBody());
    }
    
}
