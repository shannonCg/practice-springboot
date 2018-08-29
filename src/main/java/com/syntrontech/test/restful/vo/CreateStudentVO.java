package com.syntrontech.test.restful.vo;


import com.syntrontech.test.model.common.ModelStatus;
import com.syntrontech.test.restful.common.Status;

public class CreateStudentVO {

	private String studentId;
	
	private String studentName;
	
	private Status status;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public ModelStatus getStatus() {
		return ModelStatus.valueOf(status.name());
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
