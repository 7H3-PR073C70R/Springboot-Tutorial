package com.protector.QuizService.model;

import lombok.Data;

@Data
public class QuizDTO {
    private String categoryName;
    private int numQuestions;
    private String title;

    public QuizDTO() {

    }
    public QuizDTO(String categoryName, int numQuestions, String title) {
        this.categoryName = categoryName;
        this.numQuestions = numQuestions;
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(Integer numQuestions) {
        this.numQuestions = numQuestions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "QuizDTO{" +
                "categoryName='" + categoryName + '\'' +
                ", numQuestions=" + numQuestions +
                ", title='" + title + '\'' +
                '}';
    }
}
