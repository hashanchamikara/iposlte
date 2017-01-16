/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isimple.intelijpos_lite.app;

import com.isimple.intelijpos_lite.ui.main.UIHome;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Hashan Chamikara
 * @email hashanchamikara@googlemail.com
 * @contact 0094 778 62 57 57
 * @copyright Best Life IT
 */
public class Application {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            Config.getProperty(Config.LOCATION);
            UIHome uiHome = new UIHome();
            uiHome.setVisible(true);
        });
    }

}
