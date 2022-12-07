package com.example.BarberShop.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    @Pattern(regexp = "^[а-яА-ЯA-Za-z]+$", message = "Разрешены только буквы")
    private  String nameprovider;
    @NotBlank(message = "Адрес не может быть пустым")
    private String address;
    @Size(min=12,max=12, message = "инн должен быть 12 символов")
    @NotBlank(message = "ИНН не может быть пустым")
    @Pattern(regexp = "^[0-9]+$", message = "Разрешены только цифры")
    private String inn;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Supply> supplies;

    public Provider() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameprovider() {
        return nameprovider;
    }

    public void setNameprovider(String nameprovider) {
        this.nameprovider = nameprovider;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Provider(String nameprovider, String address, String inn) {
        this.nameprovider = nameprovider;
        this.address = address;
        this.inn = inn;
    }
}
