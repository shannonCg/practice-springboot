package com.syntrontech.test.restful.resource;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.syntrontech.test.exception.client.NotFoundException;
import com.syntrontech.test.exception.client.ObjectHasExistedException;
import com.syntrontech.test.exception.client.ObjectNotExistedException;
import com.syntrontech.test.restful.to.StudentTO;
import com.syntrontech.test.restful.vo.CreateStudentVO;
import com.syntrontech.test.restful.vo.DeleteStudentVO;
import com.syntrontech.test.restful.vo.UpdateStudentVO;
import com.syntrontech.test.service.StudentService;

@Path("/student")
public class StudentResource {

	@Autowired
	private StudentService studentService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createStudent(CreateStudentVO studentVO) throws ObjectHasExistedException {

		StudentTO student = studentService.createStudent(studentVO, "systemAdmin", Calendar.getInstance().getTime());

		return Response.status(Response.Status.CREATED)
						.type(MediaType.APPLICATION_JSON)
						.entity(student)
						.build();
	}

	@PUT
	@Path("/{studentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStudent(UpdateStudentVO studentVO, @PathParam("studentId") String studentId) throws NotFoundException {

		StudentTO student = studentService.updateStudent(studentId, studentVO, "systemAdmin", Calendar.getInstance().getTime());

		return Response.status(Response.Status.OK)
						.type(MediaType.APPLICATION_JSON)
						.entity(student)
						.build();
	}

	@DELETE
	public Response deleteStudent(@BeanParam DeleteStudentVO studentVO) throws ObjectNotExistedException {

		studentService.deleteStudents(studentVO.getStudentIds(), "systemAdmin", Calendar.getInstance().getTime());

		return Response.status(Response.Status.NO_CONTENT)
						.build();
	}

	@GET
	@Path("/{studentId}")
	public Response getStudentById(@PathParam("studentId") String studentId) throws NotFoundException {

		StudentTO student = studentService.getStudentById(studentId);

		return Response.status(Response.Status.OK)
						.type(MediaType.APPLICATION_JSON)
						.entity(student)
						.build();
	}

	@GET
	public Response findAllStudents() {

		List<StudentTO> students = studentService.findAllStudents();

		return Response.status(Response.Status.OK)
						.type(MediaType.APPLICATION_JSON)
						.entity(students)
						.build();
	}
}
