/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author desktop
 */
@RestController
public class Controller {
    @Autowired
    UserRepository userRepository;
    
    @RequestMapping(value = "/api")
    public String home() {
        //userRepository.
        return "Hello Service";
    }
    
}
