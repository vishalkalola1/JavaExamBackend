package com.ezshare.QuestionAPI.Service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.web.bind.annotation.RequestBody;

import com.ezshare.QuestionAPI.DTO.QuestionDTO;
import com.ezshare.QuestionAPI.Error.GeneralError;
import com.ezshare.Questionnaire.datamodel.Exam;
import com.ezshare.Questionnaire.datamodel.MCQChoice;
import com.ezshare.Questionnaire.datamodel.Question;
import com.ezshare.Questionnaire.services.ExamDAO;
import com.ezshare.Questionnaire.services.MCQChoiceDAO;
import com.ezshare.Questionnaire.services.QuestionDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Path("/Questions")
public class QuestionService {

	@Inject
	QuestionDAO questionDAO;
	
	@Inject
	ExamDAO examDAO;
	
	@Inject
	MCQChoiceDAO mcqChoiceDAO;

	@POST
	@Path("/createQuestion")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response createQuestion(@RequestBody QuestionDTO questionDTO) {

		if (questionDTO.getTitle() == null) {
			return Response.ok(new GeneralError("Question is empty")).build();
		}

		Exam exam = new Exam();
		exam.setId(questionDTO.getExam().getId());
		exam.setTitle(questionDTO.getExam().getTitle());

		Question question = new Question();
		question.setTitle(questionDTO.getTitle());
		question.setExam(exam);
		question.setType(questionDTO.getType());

		questionDAO.create(question);

		questionDTO.setId(question.getId());
		return Response.status(Status.OK).entity(questionDTO).build();
	}

	@GET
	@Path("/getAllQuestion")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getAllQuestion() {
		List<Question> list = questionDAO.getAll();
		return Response.status(Status.OK).entity(list).build();
	}
	
	@POST
	@Path("/deleteQuestion")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response deleteQuestion(@RequestBody QuestionDTO questionDTO) {

		if (questionDTO.getId() == null) {
			return Response.ok(new GeneralError("Question is empty")).build();
		}
		
		if (questionDTO.getType().equalsIgnoreCase("mcq")) {
			mcqChoiceDAO.deleteChild(questionDTO.getId(), "question");
		}
		
		questionDAO.delete(questionDTO.getId());
		return Response.status(Status.OK).entity(questionDTO).build();
	}
}
