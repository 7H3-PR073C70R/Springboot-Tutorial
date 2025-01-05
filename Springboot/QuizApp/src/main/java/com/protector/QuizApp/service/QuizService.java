package com.protector.QuizApp.service;

import com.protector.QuizApp.model.*;
import com.protector.QuizApp.repository.QuestionRepository;
import com.protector.QuizApp.repository.QuizRepository;
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
    QuestionRepository questionRepository;

    public ResponseEntity<ApiResponse<?>> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>(ApiResponse.of("Success", null), HttpStatus.CREATED);

    }

    public ResponseEntity<ApiResponse<List<QuestionWrapper>>> getQuizQuestions(Integer id) {
       try {
           Optional<Quiz> quiz = quizRepository.findById(id);
           List<Question> questionsFromDB = quiz.get().getQuestions();
           List<QuestionWrapper> questionsForUser = new ArrayList<>();

           for(Question q : questionsFromDB){
               QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
               questionsForUser.add(qw);
           }

           return  ResponseEntity.ok(ApiResponse.of("Success", questionsForUser));
       } catch (Exception e) {
           return new ResponseEntity<>(ApiResponse.of(e.getMessage(), null), HttpStatus.BAD_REQUEST);
       }

    }

    public ResponseEntity<ApiResponse<Map<String, Object>>> calculateResult(Integer id, List<Response> responses) {
       try {
            Quiz quiz = quizRepository.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            int score = 0;
            int index = 0;
            for (Response response : responses) {
                if (response.getResponse().equals(questions.get(index).getRightAnswer()))
                    score++;

                index++;
            }

            final Map<String, Object> response = new HashMap<>();
            response.putIfAbsent("score", score);

            return ResponseEntity.ok(ApiResponse.of("Score fetched successfully", response));
        } catch (Exception e) {
           return new ResponseEntity<>(ApiResponse.of(e.getMessage(), null), HttpStatus.BAD_REQUEST);
       }
    }

}
