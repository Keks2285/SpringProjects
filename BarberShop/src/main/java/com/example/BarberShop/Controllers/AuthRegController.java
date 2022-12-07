package com.example.BarberShop.Controllers;

import com.example.BarberShop.Models.Client;
import com.example.BarberShop.Models.Role;
import com.example.BarberShop.Models.User;
import com.example.BarberShop.Repo.ClientRepository;
import com.example.BarberShop.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class AuthRegController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    private String RegView(){return "Auth/Registration";}

    @PostMapping("/registration")
    private String Reg(User user, Client client, Model model){
        User user_from_db = userRepository.findByEmail(user.getEmail());
        if(user_from_db!=null){
            model.addAttribute("message","Пользователь уже зарегестрирован, авторизуйтесь");
            return "Auth/Registration";
        }

        //client.setFirstname(firstname);
        //client.setLastname(lastname);
        //client.setMiddlename(middlename);
        //client.setPhone(phone);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.CLIENT));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        client.setUser(user);
        userRepository.save(user);
        clientRepository.save(client);
        return "redirect:/login"; //html file
    }

}
