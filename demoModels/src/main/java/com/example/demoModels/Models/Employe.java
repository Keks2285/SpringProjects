package com.example.demoModels.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.text.StyledEditorKit;

@Entity
public class Employe  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname, lastname, middlename;
    private int age;
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
