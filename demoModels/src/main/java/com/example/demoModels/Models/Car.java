package com.example.demoModels.Models;
import javax.persistence.*;
import javax.swing.text.StyledEditorKit;
import javax.validation.constraints.*;

@Entity
@Table(name="car")
public class Car {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mark;

    private String nomer;

    private String model;

    private int year;

    public Person getOwner() {

        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="person_id")
    private Person owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
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

    public Car(String mark, String nomer, String model, int year) {
        this.mark = mark;
        this.nomer = nomer;
        this.model = model;
        this.year = year;
    }

    public Car() {
    }
}
