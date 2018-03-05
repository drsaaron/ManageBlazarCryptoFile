/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.maintain;

import com.blazartech.products.crypto.BlazarCryptoFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * base class for all maintainers.  There are multiple ways of maintaining the 
 * crypto file, each being implemented in a class that will implement the CryptoFileMaintainer
 * interface, and presumably extend this class.  
 * 
 * @author AAR1069
 */
public abstract class BaseCryptoFileMaintainer implements CryptoFileMaintainer {

    private static final Logger logger = LoggerFactory.getLogger(BaseCryptoFileMaintainer.class);
    
    /**
     * a method to implement the actual maintenance.
     * 
     * @param args 
     */
    protected abstract void maintainCryptoFile(String... args);
    
    /**
     * get a name for this maintainer.
     * 
     * @return 
     */
    protected abstract String getName();
    
    /**
     * determine if this maintainer is applicable, and if so execute.
     * 
     * @param strings
     * @throws Exception 
     */
    @Override
    public void run(String... strings) throws Exception {
        // determine if this maintainer is applicable.
        logger.info("checking applicability of " + getName());
        if (isApplicableMaintainer(strings)) {
            logger.info("maintainer is applicable, execute");
            maintainCryptoFile(strings);
        } else {
            logger.info(getName() + " not applicable");
        }
    }
    
    @Autowired
    private BlazarCryptoFile cryptoFile;
    
    public BlazarCryptoFile getCryptoFile() {
        return cryptoFile;
    }
}
