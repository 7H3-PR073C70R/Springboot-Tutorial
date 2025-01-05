package com.protector.QuizService.feign;

import com.protector.QuizService.model.ApiResponse;
import com.protector.QuizService.model.QuestionWrapper;
import com.protector.QuizService.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "QUESTIONSERVICE")
public interface QuestionInterface {
    @GetMapping("/api/question/generate")
    public ResponseEntity<ApiResponse<List<Integer>>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions );

    @PostMapping("/api/question/getQuestions")
    public ResponseEntity<ApiResponse<List<QuestionWrapper>>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("/api/question/getScore")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getScore(@RequestBody List<Response> responses);
}
