package com.protector.QuestionService.controller;

import com.protector.QuestionService.model.ApiResponse;
import com.protector.QuestionService.model.Question;
import com.protector.QuestionService.model.QuestionWrapper;
import com.protector.QuestionService.model.Response;
import com.protector.QuestionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    private ResponseEntity<ApiResponse<List<Question>>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    private ResponseEntity<ApiResponse<Question>> addQuestion(@RequestBody Question question){
        return  questionService.addQuestion(question);
    }

    @GetMapping("generate")
    private ResponseEntity<ApiResponse<List<Integer>>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions ){
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    @PostMapping("getQuestions")
    private ResponseEntity<ApiResponse<List<QuestionWrapper>>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
//        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromIds(questionIds);
    }

    @PostMapping("getScore")
    private ResponseEntity<ApiResponse<Map<String, Object>>> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }
}
