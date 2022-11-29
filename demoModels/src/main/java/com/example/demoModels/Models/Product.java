package com.example.demoModels.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @NotNull(message = "Название не должно быть пустым")
    @Pattern(regexp = "^[а-яА-Яa-zA-Z]+$", message = "Разрешены только буквы")
    @NotBlank(message = "Название не должно быть пустым")
    private String nameproduct;
   @Positive(message = "Цена должна быть больше 0")
   @NotNull(message = "Заполните цену")
    private double price;
    //@PositiveOrZero(message = "Количество не должно быть отрицательным")
    @Min(value = 1,message = "Количество должно быть больше 0")
    @NotNull(message = "Заполните цену")
    private int value;
    @Pattern(regexp = "^[а-яА-Яa-zA-Z]+$", message = "Разрешены только буквы")
    private String country;
    @NotBlank(message = "Описание не должно быть пустым")
    private String description;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "введите дату изготовления")
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
