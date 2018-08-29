package com.syntrontech.test.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.syntrontech.test.exception.client.NotFoundException;
import com.syntrontech.test.exception.client.ObjectHasExistedException;
import com.syntrontech.test.exception.client.ObjectNotExistedException;
import com.syntrontech.test.restful.to.StudentTO;
import com.syntrontech.test.restful.vo.CreateStudentVO;
import com.syntrontech.test.restful.vo.UpdateStudentVO;

public interface StudentService {

	StudentTO createStudent(CreateStudentVO vo, String createBy, Date createTime)throws ObjectHasExistedException;
	StudentTO updateStudent(String id, UpdateStudentVO vo, String updateBy, Date updateTime) throws NotFoundException;
	void deleteStudents(Set<String> ids, String updateBy, Date updateTime)throws ObjectNotExistedException;
	StudentTO getStudentById(String id) throws NotFoundException;
	List<StudentTO> findAllStudents();
}
