package org.jsp.quiz.exceptionclasses;
import lombok.Builder;

@Builder
public class InvalidQuestionIdException extends RuntimeException {
	
	private String message;
	public InvalidQuestionIdException(String message) {
		this.message=message;
	}
	@Override
	public String getMessage() {
		return this.message;
	}
	public InvalidQuestionIdException(){
		
	}
}