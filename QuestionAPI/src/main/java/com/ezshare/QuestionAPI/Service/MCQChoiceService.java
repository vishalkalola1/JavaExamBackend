package com.ezshare.QuestionAPI.Service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.web.bind.annotation.RequestBody;

import com.ezshare.QuestionAPI.DTO.MCQChoiceDTO;
import com.ezshare.QuestionAPI.DTO.QuestionDTO;
import com.ezshare.Questionnaire.datamodel.Answer;
import com.ezshare.Questionnaire.datamodel.MCQChoice;
import com.ezshare.Questionnaire.datamodel.Question;
import com.ezshare.Questionnaire.services.MCQChoiceDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Path("/choice")
@JsonIgnoreProperties("error")
public class MCQChoiceService {
	
	
	@Inject
	MCQChoiceDAO mcqChoiceDAO;
	
	@POST
	@Path("/addChoice")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response createChoices(@RequestBody List<MCQChoiceDTO> listDTO) {
		
		for (MCQChoiceDTO mcqChoiceDTO : listDTO) {
			
			MCQChoice choice = new MCQChoice();
			choice.setChoice(mcqChoiceDTO.getChoice());
			
			Question question = new Question();
			question.setId(mcqChoiceDTO.getQuestion().getId());
			question.setTitle(mcqChoiceDTO.getQuestion().getTitle());
			question.setType(mcqChoiceDTO.getQuestion().getType());
			question.setExam(mcqChoiceDTO.getQuestion().getExam());
			
			choice.setQuestion(question);
			choice.setValid(mcqChoiceDTO.isValid());
			
			mcqChoiceDAO.create(choice);
			
			mcqChoiceDTO.setId(choice.getId());
		}
		return Response.status(Status.OK).entity(listDTO).build();
	}
	
	@GET
	@Path("/choices/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getAnswer(@PathParam("id") long questionID) {
		List<MCQChoice> choices = mcqChoiceDAO.getByOtherColumnId(questionID, "question");
		Response response = Response.ok(choices).build();
		return response;
	}
}
