/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.maintain;

import com.blazartech.products.crypto.BlazarCryptoFileKey;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
public class CryptoFileMaintainerList extends BaseCryptoFileMaintainer {

    private static final Logger logger = LoggerFactory.getLogger(CryptoFileMaintainerList.class);
    
    @Override
    protected void maintainCryptoFile(String... args) {
        logger.info("listing crypto file");
        
        Collection<BlazarCryptoFileKey> keys = getCryptoFile().getKeys();
        keys.forEach((key) -> {
            String password = getCryptoFile().getPassword(key.getUserID(), key.getResource());
            logger.info("user: " + key.getUserID() + ", resource: " + key.getResource() + ", password = " + password);
        });
    }

    @Override
    protected String getName() {
        return "lister";
    }

    @Override
    public boolean isApplicableMaintainer(String... args) {
        return args.length == 1 && args[0].equals("list");
    }
    
}
