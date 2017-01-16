/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isimple.intelijpos_lite.util;

import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dumidu
 */
public class Utilities {

    public static void clear(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        while (model.getRowCount() >= 1) {
            model.removeRow(0);
        }
    }

    public static void openDrower() {
        try {
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            if (service != null) {
                final byte[] openCD = {27, 112, 0, 60, 120};
                InputStream is = new ByteArrayInputStream(openCD);
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                Doc doc = new SimpleDoc(is, flavor, null);
                DocPrintJob job = service.createPrintJob();
                job.print(doc, null);
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moveUP(JTable jTable) {
        if (jTable.getRowCount() > 0) {
            if (jTable.getSelectedRow() >= 0) {
                int row = jTable.getSelectedRow();
                if (row != 0) {
                    row = row - 1;
                }
                jTable.setRowSelectionInterval(row, row);
                jTable.scrollRectToVisible(jTable.getCellRect(jTable.getSelectedRow(), 0, true));
            }
        }
    }

    public static void moveDOWN(JTable jTable) {
        if (jTable.getRowCount() > 0) {
            if (jTable.getSelectedRow() != jTable.getRowCount() - 1) {
                jTable.setRowSelectionInterval(jTable.getSelectedRow() + 1, jTable.getSelectedRow() + 1);
                jTable.scrollRectToVisible(jTable.getCellRect(jTable.getSelectedRow(), 0, true));
            }
        }
    }

    public static void tone() {
        Toolkit.getDefaultToolkit().beep();
    }
}
