package com.employee.list.controller;

import com.employee.list.employee.Employees;
import com.employee.list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/add")
    public String showSingupForm(Model model) {
        model.addAttribute("employees", new Employees());
        return "add-employee";
    }
    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @PostMapping("/process_register")
    public String processRegistration(Employees employees) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(employees.getPassword());
        employees.setPassword(encodedPassword);
        userRepository.save(employees);
        return "list_employees";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
        Iterable<Employees> employees = userRepository.findAll();
        model.addAttribute("employees", employees);
        return "list_employees";
    }
    @GetMapping("/list_users/{id}/edit") //PathVariable используется чтобы брать {id} адресс
    public String employeeEdit(@PathVariable(value = "id") long id,  Model model){
        if (!userRepository.existsById(id)){ //existsById это функция возврашает нам true если обект найдено
            return "redirect:/list_users";
        }
        Optional<Employees> employees =  userRepository.findById(id);
        ArrayList<Employees> res = new ArrayList<>();
        employees.ifPresent(res::add);
        model.addAttribute("employees", res);
        return "employee_edit";
    }
    @PostMapping("/list_users/{id}/edit") //@PostMapping Получаем данные из поста
    public String employeeUpdate(@PathVariable(value = "id") long id, @RequestParam String email,
                                 @RequestParam String firstName, @RequestParam String lastName,
                                 @RequestParam String occupation, @RequestParam String salary,
                                 @RequestParam String mobilephone, @RequestParam String adress, Model model){
        Employees employees = userRepository.findById(id).orElseThrow();// orElseThrow это метод который выбрасывает на следуюший id если оно не найдено
        employees.setEmail(email);
        employees.setLastName(lastName);
        employees.setFirstName(firstName);
        employees.setAdress(adress);
        employees.setOccupation(occupation);
        employees.setSalary(salary);
        employees.setMobilephone(mobilephone);
        userRepository.save(employees);

        return "redirect:/list_users"; //redirect переадрисовывает на указзанный адрес
    }

    @PostMapping("/list_users/{id}/remove") //@PostMapping Получаем данные из поста
    public String employerDelete(@PathVariable(value = "id") long id, Model model) {
        Employees post = userRepository.findById(id).orElseThrow();// orElseThrow это метод который выбрасывает на следуюший id если оно не найдено
        userRepository.delete(post);
        return "redirect:/list_users"; //redirect переадрисовывает на указзанный адрес
    }
}
