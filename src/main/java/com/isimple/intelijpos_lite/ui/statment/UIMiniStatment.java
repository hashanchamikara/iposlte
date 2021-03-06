/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isimple.intelijpos_lite.ui.statment;

import com.isimple.intelijpos_lite.controller.ReportManager;
import com.isimple.intelijpos_lite.controller.SalesManager;
import com.isimple.intelijpos_lite.models.Invoice;
import com.isimple.intelijpos_lite.models.User;
import com.isimple.intelijpos_lite.ui.main.UIHome;
import com.isimple.intelijpos_lite.util.Const;
import com.isimple.intelijpos_lite.util.Utilities;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dumidu
 */
public class UIMiniStatment extends javax.swing.JPanel {

    private final UIHome home;

    /**
     * Creates new form UIMiniStatment
     *
     * @param home
     */
    public UIMiniStatment(UIHome home) {
        initComponents();
        this.home = home;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mDateStart = new com.toedter.calendar.JDateChooser();
        mDateEnd = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        mBtnGenerate = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        mTotal = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        mBtnPrint = new javax.swing.JButton();

        jLabel1.setText("Start");

        mDateStart.setDate(new java.util.Date());

        mDateEnd.setDate(new java.util.Date());

        jLabel2.setText("End");

        mBtnGenerate.setText("Generate");
        mBtnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mBtnGenerateActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel3.setText("Mini Statment");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Date", "Invoice", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        mTotal.setEditable(false);
        mTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        mTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mTotal.setText("0.00");
        mTotal.setDisabledTextColor(new java.awt.Color(1, 1, 1));
        mTotal.setEnabled(false);
        mTotal.setFocusable(false);
        mTotal.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel5.setText("Total");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        mBtnPrint.setText("Print");
        mBtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mBtnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(mDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(mDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mBtnGenerate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mBtnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {mDateEnd, mDateStart});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
                    .addComponent(mDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(mDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(mBtnGenerate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mBtnPrint, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mBtnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mBtnGenerateActionPerformed
        generateReport();
    }//GEN-LAST:event_mBtnGenerateActionPerformed

    private void mBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mBtnPrintActionPerformed
        printReport();
    }//GEN-LAST:event_mBtnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton mBtnGenerate;
    private javax.swing.JButton mBtnPrint;
    private com.toedter.calendar.JDateChooser mDateEnd;
    private com.toedter.calendar.JDateChooser mDateStart;
    private javax.swing.JFormattedTextField mTotal;
    // End of variables declaration//GEN-END:variables

    private void generateReport() {
        Utilities.clear(jTable1);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        List<Invoice> invoices = SalesManager.getInstance().getSales(mDateStart.getDate(), mDateEnd.getDate());
        int i = 1;
        double total = 0;
        for (Invoice invoice : invoices) {
            Object[] os = new Object[4];
            os[0] = i;
            os[1] = invoice.getDate();
            os[2] = invoice.getId();
            os[3] = invoice.getValue();
            total += invoice.getValue();
            model.addRow(os);
            i++;
        }

        mTotal.setValue(total);
    }

    private void printReport() {
        if (jTable1.getRowCount() > 0) {
            Map<String, Object> parms = new HashMap<>();
            Date start = mDateStart.getDate();
            start.setHours(00);
            start.setMinutes(00);
            start.setSeconds(00);

            Date end = mDateEnd.getDate();
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);

            parms.put("startTime", start.toString());
            parms.put("endTime", end.toString());
            parms.put("Amount", Const.CURRANCY + mTotal.getText());
            User user = home.getUser();
            parms.put("user", user.getFname() + " " + user.getLname());

            List<Map<String, Object>> data = new ArrayList<>();
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("index", jTable1.getValueAt(i, 0).toString());
                map.put("date", jTable1.getValueAt(i, 1));
                map.put("invoice", jTable1.getValueAt(i, 2).toString());
                map.put("value", Const.CURRANCY+new DecimalFormat("#,###.00")
                        .format(Double.parseDouble(jTable1.getValueAt(i, 3).toString())));
                data.add(map);
            }

            try {
                if (!ReportManager.getInstance().printMiniStatment(parms, data.toArray())) {
                    JOptionPane.showMessageDialog(this, "Unfortunatly, Cannot issue statment.",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
