package com.example.BarberShop.Controllers;

import com.example.BarberShop.Models.Role;
import com.example.BarberShop.Models.User;
import com.example.BarberShop.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;


    @GetMapping("/home")
    public String MainPageGet(Model model)
    {

        return "HomePage";
    }



    @GetMapping("/info")
    public String InfoPageGet(Model model)
    {
        return "Info";
    }




}
