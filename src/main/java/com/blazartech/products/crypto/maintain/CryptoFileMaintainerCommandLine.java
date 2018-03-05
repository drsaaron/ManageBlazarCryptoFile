/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.maintain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author AAR1069
 */
@Component
public class CryptoFileMaintainerCommandLine extends BaseCryptoFileMaintainer {

    private static final Logger logger = LoggerFactory.getLogger(CryptoFileMaintainerCommandLine.class);

    @Override
    protected void maintainCryptoFile(String... args) {
        String userID = args[0];
        String resourceID = args[1];
        String password = args[2];

        getCryptoFile().updatePassword(userID, resourceID, password);

        logger.info("password updated");
    }

    @Override
    protected String getName() {
        return "command line maintainer";
    }

    @Override
    public boolean isApplicableMaintainer(String... args) {
        // this is applicable if there are multiple command line arguments
        return (args.length > 0);
    }

}
