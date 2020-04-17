package com.ezshare.Questionnaire.exceptions;

import com.ezshare.Questionanaire.businessservice.BusinessExceptions;

@SuppressWarnings("serial")
public class ExamBusinessException extends BusinessExceptions {
	
	public ExamBusinessException(String exception) {
		super(exception);
	}
}
