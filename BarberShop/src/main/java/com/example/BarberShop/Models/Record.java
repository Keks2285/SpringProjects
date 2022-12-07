package com.example.BarberShop.Models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date daterecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="service_id")
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private  Client client;

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employer_id")
    private Employer employer;

    public Record(Date daterecord, Service service, Client client, Employer employer) {
        this.daterecord = daterecord;
        this.service = service;
        this.client = client;
        this.employer = employer;
    }

    public Record() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDaterecord() {
        return daterecord;
    }

    public void setDaterecord(Date daterecord) {
        this.daterecord = daterecord;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
