package com.example.BarberShop.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=20, message ="Название не может быть меньше 2 и не больше 20")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    @NotBlank(message = "Название не может быть пустым")
    private String name;
    @PositiveOrZero(message = "Количество не может быть отрицательным")
    private int valuematerials;

    @ManyToMany
    @JoinTable(name="material_supply",
            joinColumns=@JoinColumn(name="material_id"),
            inverseJoinColumns = @JoinColumn(name="supply_id"))
    private Collection<Supply> supplies;

    public Material() {
    }



    public Material(String name, int valuematerials) {
        this.name = name;
        this.valuematerials = valuematerials;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValuematerials() {
        return valuematerials;
    }

    public void setValuematerials(Integer valuematerials) {
        this.valuematerials = valuematerials;
    }

    public Collection<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(Collection<Supply> supplies) {
        this.supplies = supplies;
    }
}
