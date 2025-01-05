package com.protector.StudentApp.model;

public class ApiResponse<T> {

    private String description;
    private T data;

    private ApiResponse(Builder<T> builder) {
        this.description = builder.description;
        this.data = builder.data;
    }

    // Getters and Setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Static factory method for convenience
    public static <T> ApiResponse<T> of(String description, T data) {
        return new ApiResponse<>(new Builder<T>().description(description).data(data));
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }

    // Builder class for named arguments
    public static class Builder<T> {
        private String description;
        private T data;

        public Builder<T> description(String description) {
            this.description = description;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse<>(this);
        }
    }
}

