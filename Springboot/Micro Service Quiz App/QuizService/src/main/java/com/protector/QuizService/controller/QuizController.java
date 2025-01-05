package com.protector.QuizService.controller;

import com.protector.QuizService.model.ApiResponse;
import com.protector.QuizService.model.QuestionWrapper;
import com.protector.QuizService.model.QuizDTO;
import com.protector.QuizService.model.Response;
import com.protector.QuizService.service.QuizService;
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
    public  ResponseEntity<ApiResponse<?>> createQuiz(@RequestBody QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO.getCategoryName(), quizDTO.getNumQuestions(), quizDTO.getTitle());
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
