package com.example.BarberShop.Models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.sql.Date;

public enum Role implements GrantedAuthority {
ADMIN, BUHGALTER, STOCKER, CLIENT,KADR;

    @Override
    public String getAuthority() {
        return name();
    }

}
