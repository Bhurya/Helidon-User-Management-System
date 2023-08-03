package com.helidon.ums.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
@Access(AccessType.PROPERTY)
@NamedQueries({
        @NamedQuery(name = "findById", query = "select r from Role r where r.id = :id"),
        @NamedQuery(name = "findByRole", query = "select r from Role r where r.role = :role")
})
public class Role {

    private  Long id;

    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
