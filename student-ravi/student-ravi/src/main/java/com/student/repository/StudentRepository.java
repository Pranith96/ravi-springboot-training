package com.student.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.student.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	//Optional<List<Student>> findByName(String name);

//	@Query("select s from Student s where s.name=:studentName")
//	Optional<List<Student>> getByName(String studentName);
	
//	@Query("select s from Student s where s.name=?1")
//	Optional<List<Student>> getByName(String studentName);
	
	@Query(value = "select * from student_table where student_name=:studentName", nativeQuery = true)
	Optional<List<Student>> getByName(String studentName);
	
	//Optional<Student> findByLongIdAndPassword(String loginId, String password);
	
//	@Query("select s from Student s where s.longId=:loginId and s.password=:password")
//	Optional<Student> getByLongIdAndPassword(String loginId, String password);
	
//	@Query("select s from Student s where s.longId=?1 and s.password=?2")
//	Optional<Student> getByLongIdAndPassword(String loginId, String password);

	@Query(value = "select * from student_table where login_id=?1 and password=?2", nativeQuery = true)
	Optional<Student> getByLongIdAndPassword(String loginId, String password);

	@Modifying
	@Query("update Student s set s.name=:name where s.studentId=:studentId")
	void updateName(String name, Integer studentId);

}
