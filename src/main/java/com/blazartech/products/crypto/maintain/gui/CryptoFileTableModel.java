/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.maintain.gui;

import com.blazartech.products.crypto.BlazarCryptoFile;
import com.blazartech.products.crypto.BlazarCryptoFileKey;
import java.util.Collection;
import javax.swing.table.AbstractTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author AAR1069
 */
@Component
public class CryptoFileTableModel extends AbstractTableModel {

    @Autowired
    private BlazarCryptoFile cryptoFile;
    
    private Collection<BlazarCryptoFileKey> getKeys() {
        return cryptoFile.getKeys();
    }
    
    @Override
    public int getRowCount() {
        return getKeys().size();
    }

    private static final String[] columns = { "User ID", "Resource", "Password" };
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BlazarCryptoFileKey keys[] = new BlazarCryptoFileKey[getRowCount()];
        getKeys().toArray(keys);
        
        BlazarCryptoFileKey key = keys[rowIndex];
        
        switch (columnIndex) {
            case 0 -> {
                return key.getUserID();
            }
            case 1 -> {
                return key.getResource();
            }
            case 2 -> {
                return cryptoFile.getPassword(key.getUserID(), key.getResource());
            }
        }
        
        // should never get here.
        return "";
    }
    
}
