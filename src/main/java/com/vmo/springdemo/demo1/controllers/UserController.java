package com.vmo.springdemo.demo1.controllers;

import com.vmo.springdemo.demo1.models.Transfer;
import com.vmo.springdemo.demo1.models.User;
import com.vmo.springdemo.demo1.repository.UserRepository;
import com.vmo.springdemo.demo1.service.BillService;
import com.vmo.springdemo.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    BillService billService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
    @GetMapping("/transfer")
    public String transferMoneyPage(Principal principal){
        User user = userService.findByName(principal.getName()).get();
        int coin = (int) user.getCoin();
        return "User "+principal.getName()+" has "+coin+" coin";
    }
    @PostMapping("/transfer")
    public String transferMoney(Principal principal,@RequestBody Transfer transferForm){
        Transfer form = transferForm;
        String username = principal.getName() ;
        User user = userService.findByName(principal.getName()).get();
        int coin = (int) user.getCoin();
        System.out.println(username);
        userService.transfer(username,
                form.getReceiverName(),
                form.getAmount());
//                form.getSenderName(),

        return "Transfer "+form.getAmount()
                + " coin complete from "+username
                +" to "+form.getReceiverName()+" "+coin;
    }


}
