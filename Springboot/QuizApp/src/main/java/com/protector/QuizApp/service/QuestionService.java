package com.protector.QuizApp.service;

import com.protector.QuizApp.model.ApiResponse;
import com.protector.QuizApp.model.Question;
import com.protector.QuizApp.model.QuestionWrapper;
import com.protector.QuizApp.model.Response;
import com.protector.QuizApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public ResponseEntity<ApiResponse<List<QuestionWrapper>>> getQuestionsFromId(List<Integer> questionIds) {
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
}
