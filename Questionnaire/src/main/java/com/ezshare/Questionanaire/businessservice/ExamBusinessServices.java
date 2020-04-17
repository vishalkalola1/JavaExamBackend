package com.ezshare.Questionanaire.businessservice;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.ezshare.Questionnaire.datamodel.Answer;
import com.ezshare.Questionnaire.datamodel.Exam;
import com.ezshare.Questionnaire.datamodel.MCQAnswer;
import com.ezshare.Questionnaire.datamodel.MCQChoice;
import com.ezshare.Questionnaire.datamodel.Question;
import com.ezshare.Questionnaire.datamodel.Student;
import com.ezshare.Questionnaire.exceptions.ExamBusinessException;
import com.ezshare.Questionnaire.services.AnswerDAO;
import com.ezshare.Questionnaire.services.ExamDAO;
import com.ezshare.Questionnaire.services.MCQAnswerDAO;
import com.ezshare.Questionnaire.services.MCQChoiceDAO;
import com.ezshare.Questionnaire.services.QuestionDAO;
import com.ezshare.Questionnaire.services.StudentDAO;

public class ExamBusinessServices {
	
	@Inject
	StudentDAO userDAO;
	
	@Inject
	QuestionDAO questionDAO;
	
	@Inject
	AnswerDAO answerDAO;
	
	@Inject
	MCQChoiceDAO mcqChoiceDAO;
	
	@Inject
	MCQAnswerDAO mcqAnswerDAO;
	
	@Inject
	ExamDAO examDAO;
	
	@Transactional(value = TxType.REQUIRED)
	public void answerQuestion(Student user, Question question, Answer answer, Exam exam) throws ExamBusinessException {
		
		
		if (user == null || question == null || answer == null || exam == null) {
			throw new NullPointerException();
		}
		
		if (questionDAO.getById(question.getId()) == null) {
			throw new ExamBusinessException("Question does not exist");
		}
		
		if (userDAO.getById(user.getName()) == null) {
			throw new ExamBusinessException("User does not exist");
		}
		
		if (examDAO.getById(exam.getId()) == null) {
			throw new ExamBusinessException("exam does not exist");
		}
		
		answer.setStudent(user);
		answer.setQuestion(question);
		answer.setExam(exam);
		
		answerDAO.create(answer);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void answerToMCQ(Student user, MCQChoice choice, Exam exam, MCQAnswer mcqAnswer) throws ExamBusinessException {
		
		if (user == null || choice == null || exam == null || mcqAnswer == null) {
			throw new NullPointerException();
		}
		
		if (userDAO.getById(user.getName()) == null) {
			throw new ExamBusinessException("User does not exist");
		}
		
		if (mcqChoiceDAO.getById(choice.getId()) != null) {
			throw new ExamBusinessException("choice already exist");
		}
		
		if (examDAO.getById(exam.getId()) == null) {
			throw new ExamBusinessException("exam does not exist");
		}
		
		mcqAnswer.setStudent(user);
		mcqAnswer.setChoice(choice);
		mcqAnswer.setExam(exam);
		
		mcqAnswerDAO.create(mcqAnswer);
		
	}
}
