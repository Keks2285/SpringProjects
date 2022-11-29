package com.example.demoModels.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.text.StyledEditorKit;
import javax.validation.constraints.*;

@Entity
public class Employe  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @Size(min=2, max=20, message ="Фамилия не может быть меньше 2 и не больше 20")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String firstname;
    @Size(min=2, max=20, message ="Имя не может быть меньше 2 и не больше 20")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String lastname;
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String middlename;
    @Min(value = 18,message = "Возраст не может быть меньше 18")
    private int age;
    @Min(value=15000,message = "Оклад не может быть меньше 15000")
    private double oklad;
    private boolean isalive;

    public boolean isIsalive() {
        return isalive;
    }

    public void setIsalive(boolean isalive) {
        this.isalive = isalive;
    }

    public Employe(String firstname, String lastname, String middlename, int age, double oklad, boolean isalive) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.age = age;
        this.oklad = oklad;
        this.isalive = isalive;
    }

    public Employe(){
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setOklad(double oklad) {
        this.oklad = oklad;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public int getAge() {
        return age;
    }

    public double getOklad() {
        return oklad;
    }
}
