/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.maintain;

import org.springframework.boot.CommandLineRunner;

/**
 *
 * @author AAR1069
 */
public interface CryptoFileMaintainer extends CommandLineRunner {
    
    /**
     * is this runner applicable given the command line arguments?
     * @param args
     * @return 
     */
    public boolean isApplicableMaintainer(String... args);
}
