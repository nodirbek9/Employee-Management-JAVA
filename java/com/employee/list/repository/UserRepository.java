package com.employee.list.repository;

import com.employee.list.employee.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Employees, Long> {


    @Query("SELECT u FROM Employees u WHERE u.email = ?1")
    Employees findByEmail(String email);
}
