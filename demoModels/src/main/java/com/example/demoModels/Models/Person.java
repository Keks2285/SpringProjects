package com.example.demoModels.Models;
import javax.persistence.*;
import javax.swing.text.StyledEditorKit;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String firstname;
    private String lastname;
    private String middlename;
    private String login;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Person(String firstname, String lastname, String middlename, String loginr) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.login = login;
    }

    public Person() {
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @OneToOne(optional = true, cascade =CascadeType.ALL)
    @JoinColumn(name="passport_id")
    private Passport passport;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Car> cars;

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }

    public Collection<Computer> getComputers() {
        return computers;
    }

    public void setComputers(Collection<Computer> computers) {
        this.computers = computers;
    }

    @ManyToMany
    @JoinTable(name="person_computer",
                joinColumns=@JoinColumn(name="person_id"),
                inverseJoinColumns = @JoinColumn(name="computer_id"))
    private Collection<Computer> computers;
}
