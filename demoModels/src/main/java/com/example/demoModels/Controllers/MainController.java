package com.example.demoModels.Controllers;

import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demoModels.Repo.EmployeRepository;
import com.example.demoModels.Models.Employe;
import com.example.demoModels.Repo.ProductRepository;
import com.example.demoModels.Models.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
//@PreAuthorize("hasAnyAuthority('ADMIN')")
public class MainController {
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/main")
    public String employerMain(Model model)
    {
        Iterable<Employe> employers = employeRepository.findAll();
        model.addAttribute("employers",employers);
        return "MainPage";
    }








}

