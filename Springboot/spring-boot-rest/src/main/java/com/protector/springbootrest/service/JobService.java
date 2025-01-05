package com.protector.springbootrest.service;


import com.protector.springbootrest.model.JobPost;
import com.protector.springbootrest.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    private JobRepository jobRepository;

    public void addJobPost(JobPost jobPost){
        jobRepository.save(jobPost);
    }

    public List<JobPost> getAllJobPosts(){
        return  jobRepository.findAll();
    }

    public JobPost getJobPost(int jobPostId) {
        return  jobRepository.findById(jobPostId).orElse(new JobPost());
    }

    public boolean updateJobPost(JobPost jobPost) {
        if(getJobPost(jobPost.getPostId()).getPostId() == 0) {
            return false;
        }
        jobRepository.save(jobPost);
        return true;
    }

    public boolean deleteJobPost(int jobPostId) {
        if(getJobPost(jobPostId).getPostId() == 0) {
            return false;
        }
        jobRepository.delete(getJobPost(jobPostId));
        return true;
    }

    public void load() {
        // arrayList to store store JobPost objects
        List<JobPost> jobs =
                new ArrayList<>(List.of(
                        new JobPost(1, "Flutter Engineer", "Massive growth opportunity for a skilled Flutter Engineer", 4, List.of("Flutter", "Dart", "Git")),
                        new JobPost(2, "Software Engineer", "Exciting opportunity for a skilled software engineer.", 3, List.of("Java", "Spring", "SQL")),
                        new JobPost(3, "Data Scientist", "Join our data science team and work on cutting-edge projects.", 5, List.of("Python", "Machine Learning", "TensorFlow")),
                        new JobPost(4, "Frontend Developer", "Create amazing user interfaces with our talented frontend team.", 2, List.of("JavaScript", "React", "CSS")),
                        new JobPost(5, "Network Engineer", "Design and maintain our robust network infrastructure.", 4, List.of("Cisco", "Routing", "Firewalls")),
                        new JobPost(6, "UX Designer", "Shape the user experience with your creative design skills.", 3, List.of("UI/UX Design", "Adobe XD", "Prototyping"))

                ));

        jobRepository.saveAll(jobs);

    }

    public List<JobPost> searchByKeyword(String keyword) {
        return jobRepository.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
    }

    @Autowired
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
}
