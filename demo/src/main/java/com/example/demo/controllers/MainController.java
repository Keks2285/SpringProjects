package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/title")
    public String greeting(Model model) {
        model.addAttribute("title", "Главная страница");
        return "Home";
    }
    @GetMapping("/calcByGet")
    public String calcGet(@RequestParam(value="action_type") int act,
                          @RequestParam(value="numberA") double A,
                          @RequestParam(value="numberB") double B,Model model) {
        double answer =0;
        switch(act){
            case(1): answer=A+B;
                     break;
            case(2): answer=A-B;
                     break;
            case(3): answer=A*B;
                break;
            case(4): answer=A/B;
                break;
        }

        model.addAttribute("answer", answer);
        return "Home";
    }
    @PostMapping("/calcByPost")
    public String calcPost(@RequestParam(value="action_type") int act,
                           @RequestParam(value="numberA") double A,
                           @RequestParam(value="numberB") double B,Model model) {
        double answer =0;
        switch(act){
            case(1): answer=A+B;
                break;
            case(2): answer=A-B;
                break;
            case(3): answer=A*B;
                break;
            case(4): answer=A/B;
                break;
        }

        model.addAttribute("answer", answer);
        return "Home";
    }

}