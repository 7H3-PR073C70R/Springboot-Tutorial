package com.protector.JobApp;

import com.protector.JobApp.model.JobPost;
import com.protector.JobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JobController {

    private JobService jobService;

    @GetMapping({"/", "home"})
    public String home() {
        System.out.println("Here");
        return "home";
    }

    @GetMapping("addjob")
    public String addJob() {
        return "addjob";
    }

    @GetMapping("viewalljobs")
    public String allJobs(ModelAndView modelAndView, Model model) {
        model.addAttribute("jobPosts", jobService.getAllJobPosts());
        return "viewalljobs";
    }

    @PostMapping("handleForm")
    public String handleForm(JobPost jobPost, ModelAndView modelAndView) {
        jobService.addJobPost(jobPost);
        return "success";
    }

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }
}
