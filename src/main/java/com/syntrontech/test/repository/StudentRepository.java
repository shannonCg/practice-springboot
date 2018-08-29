package com.syntrontech.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.syntrontech.test.model.Student;
import com.syntrontech.test.model.common.ModelStatus;

public interface StudentRepository extends CrudRepository<Student, Long> {

	Optional<Student> findByIdAndStatusNot(String id, ModelStatus status);

	List<Student> findByStatusNot(ModelStatus status);
	
}
