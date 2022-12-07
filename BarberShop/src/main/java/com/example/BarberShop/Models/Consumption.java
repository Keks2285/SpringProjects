package com.example.BarberShop.Models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Consumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateconsumption;

    private double valueconsumption;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="supply_id")
    private  Supply supply;

    public Consumption(Date dateconsumption, double valueconsumption) {
        this.dateconsumption = dateconsumption;
        this.valueconsumption = valueconsumption;
    }

    public Consumption() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateconsumption() {
        return dateconsumption;
    }

    public void setDateconsumption(Date dateconsumption) {
        this.dateconsumption = dateconsumption;
    }

    public double getValueconsumption() {
        return valueconsumption;
    }

    public void setValueconsumption(double valueconsumption) {
        this.valueconsumption = valueconsumption;
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }
}
