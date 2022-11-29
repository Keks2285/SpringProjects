package com.example.demoModels.Models;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.text.StyledEditorKit;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="passport")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    private String series;
    private Date datecreation;
    @OneToOne(optional = true, mappedBy = "passport",cascade =CascadeType.ALL)
    private Person owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Passport(String number, String series, Date datecreation, Person owner) {
        this.number = number;
        this.series = series;
        this.datecreation = datecreation;
        this.owner = owner;
    }
    public Passport() {
    }


}
