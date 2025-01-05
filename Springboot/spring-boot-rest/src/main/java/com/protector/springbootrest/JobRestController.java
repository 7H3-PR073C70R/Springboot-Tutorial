package com.protector.springbootrest;
import com.protector.springbootrest.model.JobPost;
import com.protector.springbootrest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:8081")
public class JobRestController {
    private JobService jobService;

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(path="job-posts", produces = {"application/json"})
    public List<JobPost> getAllJobPosts() {
       return jobService.getAllJobPosts();
    }

    @GetMapping("job-post/{postId}")
    public Map<String, Object> getJobPost(@PathVariable("postId") int postId) {
        final Map<String, Object> resultMap = new HashMap<>();
        resultMap.putIfAbsent("status", 200);
        resultMap.putIfAbsent("description", "Job fetched successfully");
        resultMap.putIfAbsent("data", jobService.getJobPost(postId));
        return resultMap;
    }

    @PostMapping(path="job-post", consumes = {"application/json"})
    public Map<String, Object> addJobPost(@RequestBody JobPost jobPost) {
        jobService.addJobPost(jobPost);
        final Map<String, Object> resultMap = new HashMap<>();
        resultMap.putIfAbsent("status", 200);
        resultMap.putIfAbsent("description", "Job Post with post id " + jobPost.getPostId() + " added successfully");
        resultMap.putIfAbsent("data", jobService.getJobPost(jobPost.getPostId()));
        return resultMap;
    }

    @PutMapping("job-post")
    public Map<String, Object> updateJobPost(@RequestBody JobPost jobPost) {
        final boolean result = jobService.updateJobPost(jobPost);
        final Map<String, Object> resultMap = new HashMap<>();
        resultMap.putIfAbsent("status", 200);
        resultMap.putIfAbsent("description", result ? "Job Post with post id " + jobPost.getPostId() + " updated successfully" : "Record not found");
        if(result)
            resultMap.putIfAbsent("data", jobService.getJobPost(jobPost.getPostId()));
        return resultMap;
    }

    @DeleteMapping("job-post/{postId}")
    public Map<String, Object> deleteJobPost(@PathVariable("postId") int postId) {
        final boolean result = jobService.deleteJobPost(postId);
        final Map<String, Object> resultMap = new HashMap<>();
        resultMap.putIfAbsent("status", 200);
        resultMap.putIfAbsent("description", result ?  "Job Post Deleted Successfully" : "Record not found");
        return resultMap;
    }

    @GetMapping("load")
    public String load() {
        jobService.load();
        return  "Success";
    }

    @GetMapping("job-posts/keyword/{keyword}")
    public  Map<String, Object> searchByKeyword(@PathVariable("keyword") String keyword) {
        final Map<String, Object> resultMap = new HashMap<>();
        resultMap.putIfAbsent("status", 200);
        resultMap.putIfAbsent("description", "Job fetched successfully");
        resultMap.putIfAbsent("data", jobService.searchByKeyword(keyword));
        return resultMap;
    }
}
