package com.protector.QuizApp.controller;

import com.protector.QuizApp.model.ApiResponse;
import com.protector.QuizApp.model.Question;
import com.protector.QuizApp.model.QuestionWrapper;
import com.protector.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question/")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    private ResponseEntity<ApiResponse<List<Question>>> getAllQuestion() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<ApiResponse<List<Question>>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<ApiResponse<Question>> addQuestion(@RequestBody Question question){
        return  questionService.addQuestion(question);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<ApiResponse<List<QuestionWrapper>>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
//        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }
}
