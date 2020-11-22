/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Book;
import com.ecommerce.ecommerce.model.Customer;
import com.ecommerce.ecommerce.model.LoginUser;
import com.ecommerce.ecommerce.repository.repositiryLogin;
import com.ecommerce.ecommerce.repository.repository;
import com.ecommerce.ecommerce.repository.repositoryBooking;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author ELCOT
 */
@Controller
public class controller {
    @Autowired
    repositoryBooking rb;
    @Autowired
    repository repo;
    @Autowired
    repositiryLogin rl;
    PasswordEncoder passwordEncoder;
@Autowired
    public controller(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    @RequestMapping("/")
    public String home()
    {
        
        return "home";
    }
    @RequestMapping("/hotel")
    public String hotel()
    {
        return "addhotel";
    }
    @PostMapping("/addhotels")
    public String add(@RequestParam("id") int id,@RequestParam("name")String name,@RequestParam("state") String state,@RequestParam("city")String city,@RequestParam("phonenumber")String phoneNumber,@RequestParam("image")MultipartFile image)
    {
        Customer us=new Customer();
        us.setId(id);
        us.setName(name);
        us.setState(state);
        us.setCity(city);
        us.setPhoneNumber(phoneNumber);
        try
        {
            us.setImage(Base64.getEncoder().encodeToString(image.getBytes())); 
        }
       catch(IOException e)
       {
           System.out.println("image Exception --------------------- "+e);
       }
        repo.insert(us);
return "home";
    }
    @GetMapping("/display")
    public String view(Model model)
    {
        List<Customer>us=repo.findAll();
        //System.out.println(us+"  ........................  ....................................");
        model.addAttribute("us", us);
        return "view";
    }
    @GetMapping("/search")
    public String search(@RequestParam("search")String city,Model model)
    {
        System.out.println(city+" ,,,,,,,,,,,,,,,,,,,,,,,, ");
       List<Customer>searchs= repo.findByCity(city);
       //repo.findByCity(city);
        System.out.println(searchs+" .....................e... ");
        model.addAttribute("city", searchs);
        return "search";
    }
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
    @RequestMapping("/logout")
    public String logout()
    {
       return "home";
    }
    @RequestMapping("/createAccount")
    public String createAccount()
    {
        return "createLogin";
    }
    @RequestMapping("/AddAccount")
    public String addAccount(@RequestParam("id") int id,@RequestParam("username") String username,@RequestParam("password") String password)
    {
        LoginUser lu=new LoginUser();
        UserDetails ud=   User.builder()
                .username(username)
                .password(password)
                .roles("ADMIN")
                .build();
        System.out.println("......... "+ud.getPassword());
        lu.setId(id);
        lu.setUsername(ud.getUsername());
        lu.setPassword(passwordEncoder.encode(ud.getPassword()));
        rl.insert(lu);
        return "login";
    }
    @RequestMapping("/book")
    public String book()
    {
        return "book";
    }
    @PostMapping("/booked")
    public String booked(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("email")String email,@RequestParam("arrival_date")String arrival_date,@RequestParam("departure_date")String departure_date,@RequestParam("no_of_person")String no_of_person,@RequestParam("no_of_room")String no_of_room)
    {
        Book bk=new Book();
        bk.setId(id);
        bk.setName(name);
        bk.setArrival_date(arrival_date);
        bk.setDeparture_date(departure_date);
        bk.setNo_of_person(no_of_person);
        bk.setNo_of_room(no_of_room);
        rb.insert(bk);
        return "Booksucess";
    }
}
