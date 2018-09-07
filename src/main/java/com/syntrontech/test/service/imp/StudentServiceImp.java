package com.syntrontech.test.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntrontech.test.aop.StudentRepositoryAOP;
import com.syntrontech.test.exception.client.NotFoundException;
import com.syntrontech.test.exception.client.ObjectHasExistedException;
import com.syntrontech.test.exception.client.ObjectNotExistedException;
import com.syntrontech.test.model.Student;
import com.syntrontech.test.model.common.ModelStatus;
import com.syntrontech.test.repository.StudentRepository;
import com.syntrontech.test.restful.to.StudentTO;
import com.syntrontech.test.restful.vo.CreateStudentVO;
import com.syntrontech.test.restful.vo.UpdateStudentVO;
import com.syntrontech.test.service.StudentService;

@Service
public class StudentServiceImp implements StudentService {

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImp.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public StudentTO createStudent(CreateStudentVO vo, String createBy, Date createTime) 
			throws ObjectHasExistedException {
		if(getExistedStudentById(vo.getStudentId()).isPresent()){
			throw new ObjectHasExistedException("studentId");
		}
		
		Student student = new Student();
		student.setId(vo.getStudentId());
		student.setName(vo.getStudentName());
		student.setStatus(vo.getStatus());
		student.setCreateBy(createBy);
		student.setCreateTime(createTime);
		student.setUpdateBy(createBy);
		student.setUpdateTime(createTime);
		
		Student newStudent = studentRepository.save(student);
		logger.debug("update sutdent successfully");
		return convertFromStudent(newStudent);
	}

	@Override
	public StudentTO updateStudent(String id, UpdateStudentVO vo, String updateBy, Date updateTime) 
			throws NotFoundException {
		Student student = getExistedStudentById(id).orElseThrow(() -> new NotFoundException());
		student.setName(vo.getStudentName());
		student.setStatus(vo.getStatus());
		student.setUpdateBy(updateBy);
		student.setUpdateTime(updateTime);
		
		Student newStudent = studentRepository.save(student);
		logger.debug("update sutdent successfully");
		return convertFromStudent(newStudent);
	}

	@Override
	public void deleteStudents(Set<String> ids, String updateBy, Date updateTime) 
			throws ObjectNotExistedException {
		for(String id : ids){
			deleteStudent(id, updateBy, updateTime);
		}
	}
	
	protected void deleteStudent(String id, String updateBy, Date updateTime) 
			throws ObjectNotExistedException{
		Student student = getExistedStudentById(id).orElseThrow(() -> new ObjectNotExistedException("studentIds"));
		student.setStatus(ModelStatus.DELETED);
		student.setUpdateBy(updateBy);
		student.setUpdateTime(updateTime);
		
		studentRepository.save(student);
		logger.debug("update sutdent successfully");
	}

	@Override
	public StudentTO getStudentById(String id) throws NotFoundException {
		Student student = getExistedStudentById(id).orElseThrow(() -> new NotFoundException());
		
		return convertFromStudent(student);
	}
	
	protected Optional<Student> getExistedStudentById(String id){
		return studentRepository.findByIdAndStatusNot(id, ModelStatus.DELETED);
	}

	@Override
	public List<StudentTO> findAllStudents() {
		List<Student> students = studentRepository.findByStatusNot(ModelStatus.DELETED);
		
		return students.stream()
						.map(s -> convertFromStudent(s))
						.collect(Collectors.toList());
	}
	
	protected StudentTO convertFromStudent(Student student){
		StudentTO to = new StudentTO();
		to.setStudentId(student.getId());
		to.setStudentName(student.getName());
		to.setStatus(student.getStatus());
		
		return to;
	}
}
