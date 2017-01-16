/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isimple.intelijpos_lite.app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hashan Chamikara
 * @email hashanchamikara@googlemail.com
 * @contact 0094 778 62 57 57
 * @copyright Best Life IT
 */
public final class Config {

    public static final String FILE_NAME = "config.ipos";
    public static final String LOCATION = "location";
    public static final String INVOICE = "invoice";

    private static final Properties PROPERTIES = new Properties();
    private static FileReader fileReader;

    private Config() {
    }

    public static String getProperty(String key) {
        if (fileReader == null) {
            try {
                fileReader = new FileReader(FILE_NAME);
                PROPERTIES.load(fileReader);
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.CONFIG, null, ex);
                try {
                    PROPERTIES.setProperty(LOCATION, "0");
                    PROPERTIES.setProperty(INVOICE, "INV");
                    PROPERTIES.store(new java.io.FileOutputStream(FILE_NAME), "intelijPOS - LTE v1.1 Configuration");
                } catch (FileNotFoundException ex1) {
                    Logger.getLogger(Config.class.getName()).log(Level.CONFIG, null, ex1);
                } catch (IOException ex1) {
                    Logger.getLogger(Config.class.getName()).log(Level.CONFIG, null, ex1);
                }
            } finally {
                try {
                    
                    PROPERTIES.load(fileReader);
                } catch (IOException ex) {
                    Logger.getLogger(Config.class.getName()).log(Level.CONFIG, null, ex);
                }
            }
        }
        return PROPERTIES.getProperty(key, "");
    }
}
