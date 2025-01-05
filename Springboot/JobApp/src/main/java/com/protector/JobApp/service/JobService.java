package com.protector.JobApp.service;

import com.protector.JobApp.model.JobPost;
import com.protector.JobApp.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private JobRepository jobRepository;

    public void addJobPost(JobPost jobPost){
        jobRepository.addJobPost(jobPost);
    }

    public List<JobPost> getAllJobPosts(){
        return  jobRepository.getAllJobPosts();
    }

    @Autowired
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
}
