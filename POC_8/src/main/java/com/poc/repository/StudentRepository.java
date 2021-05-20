package com.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	@Query(value = "SELECT s from Student s WHERE CONCAT(s.id,'') LIKE %?1%")
	public List<Student> search(String studentId);

}
