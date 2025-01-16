/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.blazartech.products.crypto.maintain.gui;

import com.blazartech.products.crypto.BlazarCryptoFile;
import com.blazartech.products.crypto.BlazarCryptoFileKey;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author scott
 */
@ExtendWith(SpringExtension.class)
public class CryptoFileTableModelTest {

    private static final Logger logger = LoggerFactory.getLogger(CryptoFileTableModelTest.class);

    @TestConfiguration
    static class CryptoFileTableModelTestConfiguration {

        @Bean
        public CryptoFileTableModel instance() {
            return new CryptoFileTableModel();
        }
    }

    @Autowired
    private CryptoFileTableModel instance;
    
    @MockitoBean
    private BlazarCryptoFile cryptoFile;

    static class MockKey implements BlazarCryptoFileKey {

        private String userID;
        private String resource;

        @Override
        public String getResource() {
            return resource;
        }

        public void setResource(String server) {
            this.resource = server;
        }

        @Override
        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        @Override
        public int compareTo(BlazarCryptoFileKey o) {
            if (getUserID().compareTo(o.getUserID()) == 0) {
                return getResource().compareTo(o.getResource());
            } else {
                return getUserID().compareTo(o.getUserID());
            }
        }

        public MockKey(String userID, String resource) {
            this.userID = userID;
            this.resource = resource;
        }

        
    }

    public CryptoFileTableModelTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    private static final String RESOURCE_1 = "Resource1";
    private static final String USERID_1 = "UserID1";
    private static final String PW_1 = "PW1";
    
    private static final String RESOURCE_2 = "Resource2";
    private static final String USERID_2 = "UserID2";
    private static final String PW_2 = "PW2";
    
    @BeforeEach
    public void setUp() {
        MockKey key1 = new MockKey(USERID_1, RESOURCE_1);
        MockKey key2 = new MockKey(USERID_2, RESOURCE_2);
        List<BlazarCryptoFileKey> keys = List.of(key1, key2);
        
        Mockito.when(cryptoFile.getKeys())
                .thenReturn(keys);
        
        Mockito.when(cryptoFile.getPassword(USERID_1, RESOURCE_1))
                .thenReturn(PW_1);
        Mockito.when(cryptoFile.getPassword(USERID_2, RESOURCE_2))
                .thenReturn(PW_2);
    }

    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testGetValueAt() {
        logger.info("getValueAt");
        
        int rowIndex = 0;
        int columnIndex = 0;
        
        Object expResult = USERID_1;
        Object result = instance.getValueAt(rowIndex, columnIndex);
        assertEquals(expResult, result);
        
        expResult = RESOURCE_2;
        result = instance.getValueAt(1, 1);
        assertEquals(expResult, result);
        
        expResult = PW_2;
        result = instance.getValueAt(1, 2);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRowCount() {
        logger.info("getRowCount");
        
        int expResult = 2;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
    }
}
