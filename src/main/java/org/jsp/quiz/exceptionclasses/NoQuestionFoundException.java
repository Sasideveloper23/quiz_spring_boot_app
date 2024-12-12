package org.jsp.quiz.exceptionclasses;
import lombok.Builder;

@Builder
public class NoQuestionFoundException extends RuntimeException{
	
	public String message;
	public NoQuestionFoundException() {
		
	}
	public NoQuestionFoundException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		return  this.message;
	}
	
}
