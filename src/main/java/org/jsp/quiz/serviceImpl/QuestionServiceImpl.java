package org.jsp.quiz.serviceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jsp.quiz.dao.QuestionDao;
import org.jsp.quiz.dto.QuestionDto;
import org.jsp.quiz.dto.QuizResponse;
import org.jsp.quiz.entity.Question;
import org.jsp.quiz.exceptionclasses.InvalidQuestionIdException;
import org.jsp.quiz.exceptionclasses.NoQuestionFoundException;
import org.jsp.quiz.service.QuestionService;
import org.jsp.quiz.structure.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionDao dao;
	@Override
	public ResponseEntity<?> saveQuestion(Question question) {
		question=dao.saveQuestion(question);
//		ResponseStructure<Question> structure=new ResponseStructure<>();
//		structure.setHttpStatus(HttpStatus.OK.value());
//		structure.setMessage("Question Saved Successfully");
//		structure.setBody(question);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Questions saved Successfully").body(question).build());
	}
	@Override
	public ResponseEntity<?> findAllQuestions() {
//		List<Question> questions=dao.findAllQuestions();
		List<Question> questions=dao.findAllActiveQuestions();
		List<QuestionDto> dtolist= new ArrayList<>();
		for(Question q:questions) {
			dtolist.add(new QuestionDto(q.getTitle(),q.getA(),q.getB(),q.getC(),q.getD()));
		}
		if(dtolist.isEmpty())
			throw NoQuestionFoundException.builder().message("No Question Found In The Database").build();   //telling builder to build the object
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("All Questions Founded").body(dtolist).build());
	}
	@Override
	public ResponseEntity<?> findQuestionById(int id) {
		Optional<Question> optional=dao.findQuestionById(id);
		if(optional.isEmpty()) {
			throw InvalidQuestionIdException.builder().message("Invalid Question Id").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Question Found successfully").body(optional.get()).build());
	}
	@Override
	public ResponseEntity<?> submitQuiz(List<QuizResponse> quizResponses) {
		int c=0;
		for(QuizResponse qr:quizResponses) {
			Optional<Question> q= dao.findQuestionById(qr.getId());
				Question question= q.get();
				if(question.getAns().equalsIgnoreCase(qr.getAns())) {
					c++;
				}
			}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Submitted Successfully").body("Your Score is:"+c).build());
	}
	@Override
	public ResponseEntity<?> takeQuiz() {
		List<Question> questions=dao.takeQuiz();
		if(questions.isEmpty())
			throw NoQuestionFoundException.builder().message("No Question Found In The Database").build(); 
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("All Questions Founded").body(questions).build());
	}
	
}