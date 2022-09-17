package com.student.service;

import java.util.List;

import com.student.dto.StudentDto;
import com.student.entity.Student;

public interface StudentService {

	String addStudent(Student student);

	List<Student> getAllStudents();

	StudentDto getStudentById(Integer studentId);

	List<Student> getStudentByName(String name);

	Student getStudentByLoginIdAndPassword(String loginId, String password);

	String updateStudentName(String name, Integer studentId);

	String deleteStudentById(Integer studentId);

}
