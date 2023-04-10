package com.employee.list.service;

import com.employee.list.employee.CustomUserDetails;
import com.employee.list.employee.Employees;
import com.employee.list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employees employees = repo.findByEmail(email);
        if (employees == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(employees);
    }
}
