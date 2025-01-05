package com.protector.QuizApp.controller;

import com.protector.QuizApp.model.ApiResponse;
import com.protector.QuizApp.model.QuestionWrapper;
import com.protector.QuizApp.model.Response;
import com.protector.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz/")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public  ResponseEntity<ApiResponse<?>> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public
    ResponseEntity<ApiResponse<List<QuestionWrapper>>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public
    ResponseEntity<ApiResponse<Map<String, Object>>> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }


}
