package org.jsp.quiz.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.quiz.entity.Question;
import org.jsp.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDaoimpl {
	@Autowired
	private QuestionRepository repository;
	
	public Question saveQuestion(Question question) {
		return repository.save(question);
	}
	public List<Question> findAllQuestions(){
		return repository.findAll();
	}
	public Optional<Question> findQuestionById(int id){
		return repository.findById(id);
	}
	public List<Question> findAllActiveQuestions(){
		return repository.findAllActiveQuestions();
	}
	public List<Question> takeQuiz(){
		return repository.findRandomQuestions(PageRequest.of(0, 10));
	}
}
