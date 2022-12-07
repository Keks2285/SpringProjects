package com.example.BarberShop.Controllers;

import com.example.BarberShop.Models.Employer;
import com.example.BarberShop.Models.SickLeave;
import com.example.BarberShop.Models.Vacation;
import com.example.BarberShop.Repo.EmployerRepository;
import com.example.BarberShop.Repo.SickLeaveRepository;
import com.example.BarberShop.Repo.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class VacationController {

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'KADR')")
    @GetMapping("/vacations")
    public String MainPageGet(Model model)
    {
        Vacation vacation = new Vacation();
        Iterable<Vacation> vacations = vacationRepository.findAll();
        Iterable<Employer> employers = employerRepository.findAll();
        model.addAttribute("employers",employers);
        model.addAttribute("vacation",vacation);
        model.addAttribute("vacations",vacations);
        return "VacationsPages/VacationsList";
    }


    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'KADR')")
    @PostMapping("/vacations/add")
    public String sickadd(@Valid Vacation vacation, BindingResult bindingResult,
                          @RequestParam long employerid, Model model)
    {
        if(bindingResult.hasErrors()){ return "redirect:/vacations";}
        Employer employer = employerRepository.findById(employerid).orElseThrow();
        vacation.setEmployer(employer);
        vacationRepository.save(vacation);
        return "redirect:/vacations";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'KADR')")
    @PostMapping("/vacations/remove/{id}")
    public String sickadd(
            @PathVariable(value = "id") long id, Model model)
    {
        Vacation vacation = vacationRepository.findById(id).orElseThrow();
        vacationRepository.delete(vacation);
        return "redirect:/vacations";
    }
}
