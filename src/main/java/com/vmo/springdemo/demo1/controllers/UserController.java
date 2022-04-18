package com.vmo.springdemo.demo1.controllers;

import com.vmo.springdemo.demo1.models.Transfer;
import com.vmo.springdemo.demo1.models.User;
import com.vmo.springdemo.demo1.repository.UserRepository;
import com.vmo.springdemo.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
    @GetMapping("/transfer")
    public Optional<User> transferMoneyPage(Principal principal){

        return userService.findByName(principal.getName());
    }
    @PostMapping("/transfer")
    public String transferMoney(@ModelAttribute(name = "personal") User personal,
                                @ModelAttribute(name = "transferfrom") Transfer transferForm){
        Transfer form = transferForm;

        userService.transfer(form.getSenderName(),
                form.getReceiverName(),
                form.getAmount());
        return "Transfer complte";
    }
}
