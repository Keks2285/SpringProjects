package com.example.BarberShop.Models;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.sql.Date;

@Entity
public class TaxReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date datereport;
    private Date datebegin;

    private Date dateend;
    @Positive(message = "Продажи не могут быть меньше или равны 0")
    private double valuesells;
    double taxvalue;

    public Date getDatereport() {
        return datereport;
    }

    public void setDatereport(Date datereport) {
        this.datereport = datereport;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employer_id")
    private Employer employer;

    public TaxReport() {
    }

    public TaxReport(Date datereport,Date datebegin, Date dateend, double valuesells) {
        this.datereport=datereport;
        this.datebegin = datebegin;
        this.dateend = dateend;
        this.valuesells = valuesells;
        this.taxvalue = valuesells*0.13;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatebegin() {
        return datebegin;
    }

    public void setDatebegin(Date datebegin) {
        this.datebegin = datebegin;
    }

    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }

    public double getValuesells() {
        return valuesells;
    }

    public void setValuesells(double valuesells) {
        this.valuesells = valuesells;
    }

    public double getTaxvalue() {
        return taxvalue;
    }

    public void setTaxvalue(double taxvalue) {
        this.taxvalue = taxvalue;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
