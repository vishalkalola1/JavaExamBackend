package com.ezshare.QuestionAPI.Service;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.web.bind.annotation.RequestBody;

import com.ezshare.QuestionAPI.DTO.UserDTO;
import com.ezshare.QuestionAPI.Error.GeneralError;
import com.ezshare.Questionnaire.datamodel.Student;
import com.ezshare.Questionnaire.services.StudentDAO;

@Path("/user")
public class UserService {
	
	@Inject
	StudentDAO studentDAO;
	
	@POST
	@Path("/createUser")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response create(@RequestBody UserDTO user) {
		
		if (user.getEmail() == null || user.getName() == null || user.getType() == null) {
			return Response.ok(new GeneralError("Email and Name is empty")).build();
		}
		
		Student student = new Student();
		student.setEmail(user.getEmail());
		student.setName(user.getName());
		student.setType(user.getType());
		
		if (studentDAO.getById(user.getName()) == null) {
			try {
				studentDAO.create(student);
				return Response.status(Status.OK).entity(user).build();
			} catch (Exception e) {
				return Response.ok(new GeneralError(e.getLocalizedMessage())).build();
			}
		} else {
			return Response.ok(new GeneralError("User already register")).build();
		}
	}
	
	@POST
	@Path("/authenticateUser")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response authenticate(@RequestBody UserDTO user) {
		if (user.getEmail() == null || user.getName() == null) {
			return Response.ok(new GeneralError("Email and Name is empty")).build();
		}
		
		Student student = studentDAO.getById(user.getName());
		if (student != null) {
			if(student.getName().equalsIgnoreCase(user.getName()) && student.getEmail().equalsIgnoreCase(user.getEmail())) {
				user.setType(student.getType());
				return Response.status(Status.OK).entity(user).build();
			}else {
				return Response.ok(new GeneralError("User not register. Please register first.")).build();
			}
		} else {
			return Response.ok(new GeneralError("User not register. Please register first.")).build();
		}
	}
}
