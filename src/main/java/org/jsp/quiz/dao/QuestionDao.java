package org.jsp.quiz.dao;
import java.util.List;
import java.util.Optional;

import org.jsp.quiz.entity.Question;

public interface QuestionDao {

	Question saveQuestion(Question question);

	List<Question> findAllQuestions();

	List<Question> findAllActiveQuestions();

	List<Question> takeQuiz();

	Optional<Question> findQuestionById(int id);

	
	

}
