package com.syntrontech.test.restful.vo;

import java.util.Set;

import javax.ws.rs.QueryParam;

public class DeleteStudentVO {
	
	@QueryParam("studentIds")
	Set<String> studentIds;

	public Set<String> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(Set<String> studentIds) {
		this.studentIds = studentIds;
	}

}
