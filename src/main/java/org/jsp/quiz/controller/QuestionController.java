package org.jsp.quiz.controller;


import java.util.List;

import org.jsp.quiz.dto.QuizResponse;
import org.jsp.quiz.entity.Question;
import org.jsp.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionService service;
	
	@PostMapping
	public ResponseEntity<?> saveQuestion(@RequestBody Question question){
		return service.saveQuestion(question);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllQuestions(){
		return service.findAllQuestions();
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findQuestionById(@PathVariable int id){
		return service.findQuestionById(id);
	}
	@GetMapping("/take-quiz")
	public ResponseEntity<?> takeQuiz(){
		return service.takeQuiz();
	}
	
	@PostMapping("/submit-quiz")
	public ResponseEntity<?> submitQuiz(@RequestBody List<QuizResponse> quizResponse){
		return service.submitQuiz(quizResponse);
	}
}
