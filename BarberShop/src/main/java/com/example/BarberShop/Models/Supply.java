package com.example.BarberShop.Models;

import com.fasterxml.jackson.databind.DatabindException;
import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "Заполните дату")
    private Date datesupply;
    @Positive(message = "Оъем партии не может быть меньше или равна 0")
    private int valuesupply;
    @Positive(message = "Цена не может быть меньше или равна 0")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="provider_id")
    private Provider provider;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="stock_id")
    private Stock stock;

    @ManyToMany
    @JoinTable(name="material_supply",
            joinColumns=@JoinColumn(name="supply_id"),
            inverseJoinColumns = @JoinColumn(name="material_id"))
    private Collection<Material> materials;

    public Collection<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Collection<Material> materials) {
        this.materials = materials;
    }

    public Supply(Date datesupply, int valuesupply, double price) {
        this.datesupply = datesupply;
        this.valuesupply = valuesupply;
        this.price = price;
    }

    public Supply( int valuesupply, double price) {
        this.datesupply = new Date( LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.valuesupply = valuesupply;
        this.price = price;
    }
    public Supply() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatesupply() {
        return datesupply;
    }

    public void setDatesupply(Date datesupply) {
        this.datesupply = datesupply;
    }

    public int getValuesupply() {
        return valuesupply;
    }

    public void setValuesupply(int valuesupply) {
        this.valuesupply = valuesupply;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
