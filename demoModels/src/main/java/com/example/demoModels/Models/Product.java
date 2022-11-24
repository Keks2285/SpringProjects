package com.example.demoModels.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Date;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[а-яА-Яa-zA-Z]+$", message = "Разрешены только буквы")
    private String nameproduct;
    @Positive(message = "Цена должна быть больше 0")
    private double price;
    @PositiveOrZero(message = "Количество не должно быть отрицательным")
    private int value;
    @Pattern(regexp = "^[а-яА-Яa-zA-Z]+$", message = "Разрешены только буквы")
    private String country;
    private String description;

    @PastOrPresent(message = "Нельзя задать будущую дату ")
    private Date creationdate;

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Product(String nameproduct, double price, int value, String country, String description, Date creationdate) {
        this.nameproduct = nameproduct;
        this.price = price;
        this.value = value;
        this.country = country;
        this.description = description;
        this.creationdate=creationdate;
    }
    public Product(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
