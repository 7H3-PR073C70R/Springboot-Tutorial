package com.protector.QuizService.service;

import com.protector.QuizService.feign.QuestionInterface;
import com.protector.QuizService.model.*;
import com.protector.QuizService.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionInterface questionInterface;

    public ResponseEntity<ApiResponse<?>> createQuiz(String category, int numQ, String title) {

      try {
          List<Integer> questionIds = questionInterface.getQuestionsForQuiz(category, numQ).getBody().getData();

          Quiz quiz = new Quiz();
          quiz.setTitle(title);
          quiz.setQuestionIds(questionIds);
          quizRepository.save(quiz);

          return new ResponseEntity<>(ApiResponse.of("Success", null), HttpStatus.CREATED);
      } catch (Exception e) {
          return new ResponseEntity<>(ApiResponse.of(e.getMessage(), null), HttpStatus.BAD_REQUEST);
      }

    }

    public ResponseEntity<ApiResponse<List<QuestionWrapper>>> getQuizQuestions(Integer id) {
       try {
           Optional<Quiz> quiz = quizRepository.findById(id);
           List<QuestionWrapper> questionsForUser = questionInterface.getQuestionsFromId(quiz.get().getQuestionIds()).getBody().getData();

           return  ResponseEntity.ok(ApiResponse.of("Success", questionsForUser));
       } catch (Exception e) {
           return new ResponseEntity<>(ApiResponse.of(e.getMessage(), null), HttpStatus.BAD_REQUEST);
       }

    }

    public ResponseEntity<ApiResponse<Map<String, Object>>> calculateResult(Integer id, List<Response> responses) {
       try {
            Quiz quiz = quizRepository.findById(id).get();
            int score = (Integer) questionInterface.getScore(responses).getBody().getData().get("score");

            final Map<String, Object> response = new HashMap<>();
            response.putIfAbsent("score", score);

            return ResponseEntity.ok(ApiResponse.of("Score fetched successfully", response));
        } catch (Exception e) {
           return new ResponseEntity<>(ApiResponse.of(e.getMessage(), null), HttpStatus.BAD_REQUEST);
       }
    }

}
