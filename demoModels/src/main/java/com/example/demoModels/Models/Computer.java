package com.example.demoModels.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.text.StyledEditorKit;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="computer")
public class Computer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    public Computer(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Computer() {
    }
    private String model;

    private int year;

    @ManyToMany
    @JoinTable(name="person_computer",
            joinColumns=@JoinColumn(name="computer_id"),
            inverseJoinColumns = @JoinColumn(name="person_id"))
    private Collection<Person> persons;
}
