package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Role;
import com.example.demoModels.Models.User;
import com.example.demoModels.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String userView(Model model){
        model.addAttribute("user_list", userRepository.findAll());
        return"Users/index";
    }
    @GetMapping("/{id}")
    public String detailView(@PathVariable long id, Model model){
        model.addAttribute("user_object", userRepository.findById(id).orElseThrow());
        //model.addAttribute("roles", Role.values());
        return"users/info";
    }

    @GetMapping("/{id}/update")
    public String updateView(@PathVariable long id, Model model){
        model.addAttribute("user_object", userRepository.findById(id).orElseThrow());
        model.addAttribute("roles", Role.values());
        return"users/userUpd";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable long id,
                         @RequestParam String username,
                         @RequestParam(name="roles[]", required = false) String[] roles,
                         Model model){
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        user.getRoles().clear();
        if (roles!=null){
            for(String role: roles){
                user.getRoles().add(Role.valueOf(role));
            }
        }
        userRepository.save(user);
        return"redirect:/admin/";
    }
}
