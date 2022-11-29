package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Role;
import com.example.demoModels.Models.User;
import com.example.demoModels.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    private String RegView(){

        return "regis"; //html file
    }

    @PostMapping("/registration")
    private String Reg(User user, Model model){

        User user_from_db = userRepository.findByUsername(user.getUsername());
        if(user_from_db!=null){
            model.addAttribute("message","Пользователь уже зарегестрирован, авторизуйтесь");
            return "regis";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login"; //html file
    }
}
