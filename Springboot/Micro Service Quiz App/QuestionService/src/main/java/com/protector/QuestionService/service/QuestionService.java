package com.protector.QuestionService.service;

import com.protector.QuestionService.model.ApiResponse;
import com.protector.QuestionService.model.Question;
import com.protector.QuestionService.model.QuestionWrapper;
import com.protector.QuestionService.model.Response;
import com.protector.QuestionService.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<ApiResponse<List<Question>>> getAllQuestions() {
        try {
            return ResponseEntity.ok(ApiResponse.of("Questions fetched successfully", questionRepository.findAll()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(ApiResponse.of("Error while fetching questions", questionRepository.findAll()), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse<List<Question>>> getQuestionsByCategory(String category) {
        try {
            return  ResponseEntity.ok(ApiResponse.of("Question with category " + category + " fetched successfully", questionRepository.findByCategoryIgnoreCase(category)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(ApiResponse.of("Unable to fetch questions with category " + category, new ArrayList<>()), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<ApiResponse<Question>> addQuestion(Question question) {
        questionRepository.save(question);
        return new ResponseEntity<>(ApiResponse.of("success", question),HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse<List<Integer>>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionRepository.findRandomQuestionsByCategory(categoryName, numQuestions);
        return  ResponseEntity.ok(ApiResponse.of("Questions fetched successfully", questions));
    }

    public ResponseEntity<ApiResponse<List<QuestionWrapper>>> getQuestionsFromIds(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionIds){
            questions.add(questionRepository.findById(id).get());
        }

        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }

        return  ResponseEntity.ok(ApiResponse.of("Questions fetched successfully", wrappers));
    }

    public ResponseEntity<ApiResponse<Map<String, Object>>> getScore(List<Response> responses) {


        int score = 0;

        for(Response response : responses){
            Question question = questionRepository.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
                score++;
        }

        final Map<String, Object> response = new HashMap<>();
        response.putIfAbsent("score", score);

        return ResponseEntity.ok(ApiResponse.of("Score fetched successfully", response));
    }
}
