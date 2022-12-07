package com.example.BarberShop.Models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateincome;
    private double valueincome;


    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="record_id")
    private  Record record;

    public Income(Date dateincome, double valueincome) {
        this.dateincome = dateincome;
        this.valueincome = valueincome*0.87;
    }

    public Income(Record record) {
        this.record = record;
        this.dateincome = record.getDaterecord();
        this.valueincome = record.getService().getPrice()*0.87  ;
    }

    public Income() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateincome() {
        return dateincome;
    }

    public void setDateincome(Date dateincome) {
        this.dateincome = dateincome;
    }

    public double getValueincome() {
        return valueincome;
    }

    public void setValueincome(double valueincome) {
        this.valueincome = valueincome;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
