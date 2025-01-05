package com.protector.SpringbootWebApp1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public String  home(){
        System.out.println("home method called");
        return "index";
    }

    @GetMapping("/add")
    public ModelAndView add(@RequestParam("num1") int num, int num2, ModelAndView modelAndView){
        int result = num + num2;
        modelAndView.addObject("result", result);
        modelAndView.setViewName("result");
        return modelAndView;
    }

    @GetMapping("/addAlien")
    public String addAlien(@ModelAttribute() Alien alien, ModelAndView modelAndView){
//        modelAndView.addObject("alien", alien);
//        modelAndView.setViewName("result");
//        return modelAndView;
        return  "result";
    }
}
