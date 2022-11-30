package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Car;
import com.example.demoModels.Models.Computer;
import com.example.demoModels.Models.Passport;
import com.example.demoModels.Models.Person;
import com.example.demoModels.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class RelationController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private ComputerRepository computerRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("passport/add")
    public String createPassportGet(Model model) {

        return "RelationsPages/PassportPage";
    }

    @PostMapping("passport/add")
    public String createPassportPost(Passport passport) {
        passportRepository.save(passport);
        return "RelationsPages/PassportPage";
    }
    @GetMapping("car/add")
    public String createCarGet(Model model) {
        Iterable<Person> persons = personRepository.findAll();
        model.addAttribute("persons",persons);
        return "RelationsPages/CarPage";
    }

    @PostMapping("car/add")
    public String createCarPost(Car car, @RequestParam String firstname) {
        car.setOwner(personRepository.findByFirstname(firstname));
        carRepository.save(car);
        return "redirect:/main";
    }

    @GetMapping("person/add")
    public String createPersonGet(Model model) {
        Iterable<Passport> passports = passportRepository.findAll();
           ArrayList<Passport> passportArrayList = new ArrayList<>();
           for (Passport pass: passports){
               if(pass.getOwner()==null){
                   passportArrayList.add(pass);
               }
        }
        model.addAttribute("passports", passportArrayList);
        return "RelationsPages/PersonPage";
    }
    @PostMapping("person/add")
    public String createPersonPost(Person person, @RequestParam String number) {
        person.setPassport(passportRepository.findByNumber(number));
        personRepository.save(person);
        return "redirect:/main";
    }


    @GetMapping("computer/add")
    public String createComputerGet(Model model) {
        return "RelationsPages/ComputerPage";
    }
    @PostMapping("computer/add")
    public String createCompputerPost(Computer computer) {
        computerRepository.save(computer);
        return "redirect:/main";
    }


    @GetMapping("computer_person/add")
    public String createComputer_PersonGet(Model model) {
        Iterable<Person> persons = personRepository.findAll();
        Iterable<Computer> computers = computerRepository.findAll();
        model.addAttribute("persons", persons);
        model.addAttribute("computers", computers);
        return "RelationsPages/Computer_Person";
    }
    @PostMapping("computer_person/add")
    public String createComputer_PersonPost(@RequestParam String login, @RequestParam String model) {
        Person user = personRepository.findByLogin(login);
        Computer pc = computerRepository.findByModel(model);
        user.getComputers().add(pc);
        personRepository.save(user);
        // computerRepository.save(computer);
        return "redirect:/main";
    }
}
