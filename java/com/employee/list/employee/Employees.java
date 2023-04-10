package com.employee.list.employee;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employees {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false, length = 64)
        private String password;

        @Column(nullable = false, length = 20)
        private String firstName;

        @Column(nullable = false, length = 20)
        private String lastName;

        @Column(nullable = false, length = 20)
        private String occupation;

        @Column(nullable = false, length = 20)
        private String adress;

        @Column(nullable = false, length = 20)
        private String salary;

        @Column(nullable = false, length = 20)
        private String mobilephone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
}
