package com.rohit.domain;

import javax.persistence.Entity;

@Entity

public class Employee extends AbstractEntity<Long> {

    private String firstName;

    private String lastName;

    @Deprecated
    public Employee() {
        //required by hibernate
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
