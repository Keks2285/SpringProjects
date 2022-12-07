package com.example.BarberShop.Controllers;

import com.example.BarberShop.Models.*;
import com.example.BarberShop.Repo.EmployerRepository;
import com.example.BarberShop.Repo.PostRepository;
import com.example.BarberShop.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployerController {
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'KADR','CLIENT')")
    @GetMapping("/employer")
    public String MainPageGet(Model model)
    {

        Iterable<Employer> employers = employerRepository.findAll();
        model.addAttribute("employers",employers);
        return "EmployerPages/EmployerList";
    }


    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'KADR','CLIENT')")
    @GetMapping("/employer/add")
    public String addEmployer ( Employer employer){
        return "EmployerPages/EmployerAdd";
    }

    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'KADR','CLIENT')")
    @PostMapping("/employer/add")
    public String addEmployer (
                               @Valid Employer employer,
                               BindingResult bindingResult,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model){

        if(bindingResult.hasErrors()){
            return "EmployerPages/EmployerAdd";
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        switch (role){
            case "Администратор":
                user.setRoles(Collections.singleton(Role.ADMIN));
                employer.setPost(postRepository.findById(1l).orElseThrow());

                break;
            case "Бухгалтер":
                user.setRoles(Collections.singleton(Role.BUHGALTER));
                employer.setPost(postRepository.findById(2l).orElseThrow());
                break;
            case "Сотрудник отдела кадров":
                user.setRoles(Collections.singleton(Role.KADR));
                employer.setPost(postRepository.findById(3l).orElseThrow());
                break;
            case "Кладовщик":
                user.setRoles(Collections.singleton(Role.STOCKER));
                employer.setPost(postRepository.findById(4l).orElseThrow());
                break;
        }
        User user_from_db = userRepository.findByEmail(user.getEmail());
        if(user_from_db!=null){
            model.addAttribute("message","Сотрудник с такой почтой уже есть");
            return "EmployerPages/EmployerAdd";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user= userRepository.save(user);
        employer.setUser(user);

        employerRepository.save(employer);
        return "redirect:/employer";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'KADR','CLIENT')")
    @PostMapping("/employer/{id}")
    public String editemployer( @Valid Employer employer,
                                BindingResult bindingResult,
                                @PathVariable(value = "id") long id,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String role,
                                Model model)
    {
        if(bindingResult.hasErrors()) {
            return "EmployerPages/EmployerEdit";
        }

        User user = employerRepository.findById(id).orElseThrow().getUser();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
       // return "redirect:/employer";
        Employer employerUser = user.getEmployer();
        employerUser.setFirstname(employer.getFirstname());
        employerUser.setLastname(employer.getLastname());
        employerUser.setMiddlename(employer.getMiddlename());
        employerUser.setINN(employer.getINN());

        //user.setEmail(email);
        //user.setPassword(password);
        switch (role){
            case "Администратор":
                user.setRoles(Collections.singleton(Role.ADMIN));
                employerUser.setPost(postRepository.findById(1l).orElseThrow());

                break;
            case "Бухгалтер":
                user.setRoles(Collections.singleton(Role.BUHGALTER));
                employerUser.setPost(postRepository.findById(2l).orElseThrow());
                break;
            case "Сотрудник отдела кадров":
                user.setRoles(Collections.singleton(Role.KADR));
                employerUser.setPost(postRepository.findById(3l).orElseThrow());
                break;
            case "Кладовщик":
                user.setRoles(Collections.singleton(Role.STOCKER));
                employerUser.setPost(postRepository.findById(4l).orElseThrow());
                break;
        }

//
//        employerRepository.save(employerUser);
        //User user_from_db = userRepository.findByEmail(user.getEmail());
        //if(user_from_db!=null){
        //    model.addAttribute("message","Сотрудник с такой почтой уже есть");
        //    return "EmployerPages/EmployerEdit";
        //}
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setEmployer(employer);
        //user= userRepository.save(user);
        //employerUser.setUser(user);
        // user.setEmployer(employerUser);
  //  user = userRepository.save(user);
        employerRepository.save(employerUser);
        return "redirect:/employer";
        //model.addAttribute("materials", materialRepository.findAll());
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'KADR','CLIENT')")
    @GetMapping("/employer/{id}")
    public String editemployer( @PathVariable(value = "id") long id, Model model)
    {

        Employer employer = employerRepository.findById(id).orElseThrow();
        model.addAttribute("employer", employer);
        //model.addAttribute("materials", materialRepository.findAll());
        return "EmployerPages/EmployerEdit";
    }




}
