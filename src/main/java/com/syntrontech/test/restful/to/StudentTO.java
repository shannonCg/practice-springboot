package com.syntrontech.test.restful.to;


import com.syntrontech.test.model.common.ModelStatus;
import com.syntrontech.test.restful.common.Status;

public class StudentTO {

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(ModelStatus status) {
		this.status = Status.valueOf(status.name());
	}
	
	public String toString(){
		return "StudentTO"+"{"
				+"studentId="+studentId+","
				+"studentName="+studentName+","
				+"status="+status.name()
				+"}";
	}
}
