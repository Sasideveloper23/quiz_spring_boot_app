package org.jsp.quiz.structure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class ResponseStructure<T> {
	private int httpStatus;
	private String message;
	private T body;
}
