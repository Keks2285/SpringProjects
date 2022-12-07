package com.example.BarberShop.Models;

import javax.persistence.*;

import javax.validation.constraints.*;
import java.util.Collection;

@Entity
public class Employer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=20, message ="Фамилия не может быть меньше 2 и не больше 20")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    @NotBlank(message = "Фамилия не может быть пустым")
    private String firstname;
    @Size(min=2, max=20, message ="Имя не может быть меньше 2 и не больше 20")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    @NotBlank(message = "Имя не может быть пустым")
    private String lastname;
    @Size(min=2, max=20, message ="Отчество не может быть меньше 2 и не больше 20")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String middlename;
    @Pattern(regexp = "^[0-9]+$", message = "Разрешены только цифры")
    private String inn;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="post_id")
    private  Post post;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Record> records;

    public String getInn() {
        return inn;
    }

    public Employer(String firstname, String lastname, String middlename, String inn) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.inn = inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Vacation> vacations;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<TaxReport> taxReports;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<SickLeave> sickleaves;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Collection<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(Collection<Vacation> vacations) {
        this.vacations = vacations;
    }

    public Collection<SickLeave> getSickleaves() {
        return sickleaves;
    }

    public void setSickleaves(Collection<SickLeave> sickleaves) {
        this.sickleaves = sickleaves;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getINN() {
        return inn;
    }

    public void setINN(String inn) {
        this.inn = inn;
    }

    public Employer(Long id, String firstname, String lastname, String middlename, String inn, User user) {
        this.id = user.getId();
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.inn = inn;
        this.user = user;
    }

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public Employer() {
    }

}
