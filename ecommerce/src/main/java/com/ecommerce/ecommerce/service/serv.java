/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.UserDetailss.UserDetailss;
import com.ecommerce.ecommerce.model.LoginUser;
import com.ecommerce.ecommerce.repository.repositiryLogin;
//import com.ecommerce.ecommerce.repository.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ELCOT
 */
@Service
public class serv implements UserDetailsService{
@Autowired 
repositiryLogin repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser lu= repo.findByUsername(username);
       if(lu==null)
       {
           System.out.println("LoginUser =="+lu);
           throw new UsernameNotFoundException(username);
       }
       return new UserDetailss(lu);
    }
    
}
