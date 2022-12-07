package com.example.BarberShop.Models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // @NotBlank(message = "заполните почту")
   // @Email(message = "Неправильный формат почты")
    private String email;
    //@NotBlank(message = "заполните пароль")
    //@Size(min=4,max=20, message ="пароль не может быть меньше 4 и больше 20 символов" )
    private String password;
    private boolean active;

    @OneToOne(optional = true, mappedBy = "user",cascade =CascadeType.ALL)
    private Client client;

    @OneToOne(optional = true, mappedBy = "user",cascade =CascadeType.ALL)
    private Employer employer;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(String email, String password, boolean active, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        active=true;
    }
}
