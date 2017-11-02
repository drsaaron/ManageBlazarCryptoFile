/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.maintain;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author AAR1069
 */
@SpringBootApplication
@ComponentScan("com.blazartech")
public class Main {
    
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(Main.class).headless(false).run(args);
    }
}
