package com.student.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.dto.StudentDto;
import com.student.entity.Status;
import com.student.entity.Student;
import com.student.exceptions.StudentNotFoundException;
import com.student.repository.StudentRepository;


@Primary
@Service(value = "service1")
@Transactional
@Profile(value = { "dev", "qa", "prod", "local" })
public class StudentServiceImpl implements StudentService {

	Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	StudentRepository repository;

	@Override
	public String addStudent(Student student) {
		student.getAddress().setStudent(student);
		student.setStudentStatus(Status.ACTIVE);
		log.info("Before calling repo");
		Student response = repository.save(student);
		log.info("after calling repo");
		if (response == null) {
			return "Data is not saved";
		}
		return "Data is Saved successfully";
	}

	@Override
	public List<Student> getAllStudents() {
		log.info("Before calling repo for find all");
		List<Student> response = repository.findAll();
		log.info("after calling repo for find all");
		if (response == null || response.isEmpty()) {
			throw new RuntimeException("Data is Empty");
		}
		return response;
	}

	@Override
	public StudentDto getStudentById(Integer studentId) {
		log.info("Before calling repo for find by id");
		Optional<Student> response = repository.findById(studentId);
		log.info("after calling repo for find by id: {}" ,studentId);
		if (!response.isPresent()) {
			throw new StudentNotFoundException("Data is not found");
		}

//		StudentDto dto = new StudentDto();
//		dto.setName(response.get().getName());
//		dto.setEmail(response.get().getEmail());
//		dto.setMobileNumber(response.get().getMobileNumber());
		
		StudentDto dto = StudentDto.builder()
				.name(response.get().getName())
				.email(response.get().getEmail())
				.mobileNumber(response.get().getMobileNumber())
				.build();
		return dto;
	}

	@Override
	public List<Student> getStudentByName(String name) {
		// Optional<List<Student>> response = repository.findByName(name);
		Optional<List<Student>> response = repository.getByName(name);
		if (!response.isPresent()) {
			throw new StudentNotFoundException("Data is not found for given name");
		}
		return response.get();
	}

	@Override
	public Student getStudentByLoginIdAndPassword(String loginId, String password) {
		// Optional<Student> response =
		// repository.findByLongIdAndPassword(loginId,password);
		Optional<Student> response = repository.getByLongIdAndPassword(loginId, password);
		if (!response.isPresent()) {
			throw new StudentNotFoundException("Data is not found for loginId and password");
		}
		return response.get();
	}

	/*
	 * @Override public String UpdateStudentName(String name, Integer studentId) {
	 * Optional<Student> response = repository.findById(studentId); if
	 * (!response.isPresent()) { throw new RuntimeException("Data is not found"); }
	 * 
	 * if (!name.isEmpty() || name != null) { response.get().setName(name); }
	 * repository.save(response.get());
	 * 
	 * return "Data updated succesfully"; }
	 */

	@Transactional
	@Override
	public String updateStudentName(String name, Integer studentId) {
		repository.updateName(name, studentId);

		return "Data updated succesfully";
	}

	@Override
	public String deleteStudentById(Integer studentId) {
		Optional<Student> response = repository.findById(studentId);
		if (!response.isPresent()) {
			throw new StudentNotFoundException("Data is not found");
		}
		repository.delete(response.get());
		// repository.deleteById(studentId);
		return "Deleted Succesfully";
	}
}
