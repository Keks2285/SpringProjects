package com.example.BarberShop.Models;

import javax.persistence.*;
import java.sql.Date;
@Entity
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date datebegin;
    private Date dateend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employer_id")
    private Employer employer;

    public Vacation() {
    }

    public Vacation(Date datebegin, Date dateend, Employer employer) {
        this.datebegin = datebegin;
        this.dateend = dateend;
        this.employer = employer;
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

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
