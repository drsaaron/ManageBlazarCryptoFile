/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.maintain;

import com.blazartech.products.crypto.maintain.gui.MainWindow;
import java.awt.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author AAR1069
 */
@Component
public class CryptoFileMaintainerGUI extends BaseCryptoFileMaintainer {

    private static final Logger logger = LoggerFactory.getLogger(CryptoFileMaintainerGUI.class);

    @Autowired
    private MainWindow mainWindow;
    
    @Override
    protected void maintainCryptoFile(String... args) {
        EventQueue.invokeLater(() -> {
            mainWindow.setVisible(true);
        });
    }

    @Override
    protected String getName() {
        return "GUI";
    }

    @Override
    public boolean isApplicableMaintainer(String... args) {
        // this is applicable when there are no command line arguments
        return args.length == 0;
    }
    
    
}
