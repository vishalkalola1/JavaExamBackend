package com.ezshare.Questionnaire.test;

import java.util.UUID;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ezshare.Questionanaire.businessservice.ExamBusinessServices;
import com.ezshare.Questionnaire.datamodel.Answer;
import com.ezshare.Questionnaire.datamodel.Exam;
import com.ezshare.Questionnaire.datamodel.Question;
import com.ezshare.Questionnaire.datamodel.Student;
import com.ezshare.Questionnaire.exceptions.ExamBusinessException;
import com.ezshare.Questionnaire.services.ExamDAO;
import com.ezshare.Questionnaire.services.QuestionDAO;
import com.ezshare.Questionnaire.services.StudentDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationcontext.xml")
public class TestExamServies {
	
	@Inject
	ExamBusinessServices exambusinessservices;
	
	@Inject
	StudentDAO studentDAO;
	
	@Inject
	QuestionDAO questionDAO;
	
	@Inject
	ExamDAO examDAO;
	
	@Test
	public void test() {
		
		Student student = new Student();
		student.setName(UUID.randomUUID().toString());
		studentDAO.create(student);
		
		Question question = new Question();
		question.setTitle("What is Java");
		questionDAO.create(question);
		
		
		Exam exam = new Exam();
		exam.setTitle("Java Assignment");
		examDAO.create(exam);
		
		
		Answer answer = new Answer();
		
		try {
			exambusinessservices.answerQuestion(student, question, answer, exam);
		} catch (ExamBusinessException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
