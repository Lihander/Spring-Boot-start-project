package com.example.hello.controller;

import com.example.hello.domain.Role;
import com.example.hello.domain.User;
import com.example.hello.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final UserRepo userRepo;

    @Autowired
    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration(){return "registration";}

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        if (user == null) {
            model.put("message", "Fail registration! Unknown error!");
            return "registration";
        }

        if (userRepo.findByUsername(user.getUsername()) != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }

}
