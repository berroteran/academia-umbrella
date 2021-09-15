package com.berroteran.academia.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Customer implements Serializable {
    private Long id;
    private String name;
    //private Address address;
    //private Collection<Order> orders = new HashSet();
    //private Set<PhoneNumber> phones = new HashSet();

    // No-arg constructor
    public Customer() {}

    @Id // property access is used
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}