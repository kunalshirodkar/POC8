package com.poc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poc.model.Student;
import com.poc.repository.StudentRepository;



@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	
	public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.studentRepository.findAll(pageable);
	}

	
	public Student saveStudent(final Student student) {
		return studentRepository.save(student);
	}

	
	public void deleteUserById(Long id) {
		studentRepository.deleteById(id);
	}


	public Student findById(Long id) {
		Optional<Student> optional = studentRepository.findById(id);
		Student student = null;
		if (optional.isPresent()) {
			student = optional.get();
		} else {
			throw new RuntimeException("Student not found for id: " + id);
		}
		return student;
	}


	public List<Student> findAll() {
		return studentRepository.findAll();
	}


	public List<Student> getAllStudents(String studentId) {
		if(studentId != null) {
			return studentRepository.search(studentId);
		}
		return studentRepository.findAll();
	}


}
