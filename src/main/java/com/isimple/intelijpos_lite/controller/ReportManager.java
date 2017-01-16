/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isimple.intelijpos_lite.controller;

import com.isimple.intelijpos_lite.ui.barcode.UIBarcode;
import com.isimple.intelijpos_lite.util.Const;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author dumidu
 */
public class ReportManager {

    private ReportManager() {
    }

    public static ReportManager getInstance() {
        return ReportManagerHolder.INSTANCE;
    }

    public void previewBarcode(Collection<UIBarcode.BarcodeItem> values) throws Exception {
        DecimalFormat format = new DecimalFormat("#,###.00");
        List<Map<String, String>> maps = new ArrayList<>();
        for (UIBarcode.BarcodeItem value : values) {
            int qty = value.qty;
            if (qty % 2 != 0) {
                qty += 1;
            }
            for (int i = 0; i < qty / 2; i++) {
                Map<String, String> map = new HashMap<>();
                map.put("barcode", value.barcode);
                map.put("item", value.item);
                map.put("price", Const.CURRANCY + format.format(value.price));
                maps.add(map);
            }
        }
        JasperReport report = JasperCompileManager.compileReport(
                getClass().getResource("/com/isimple/intelijpos_lite/jrxml/rpt_barcode.jrxml").openStream());
        JasperPrint jasperPrint = JasperFillManager.fillReport(report,
                null, new JRMapArrayDataSource(maps.toArray()));
        JasperViewer.viewReport(jasperPrint, false);
    }

    private static class ReportManagerHolder {

        private static final ReportManager INSTANCE = new ReportManager();
    }

    public boolean printReceipt_tharmal(Map<String, Object> parms, Object[] array) throws Exception {
        JasperReport report = JasperCompileManager.compileReport(
                getClass().getResource("/com/isimple/intelijpos_lite/jrxml/rpt_receipt.jrxml").openStream());
        JasperPrint jasperPrint = JasperFillManager.fillReport(report,
                parms, new JRMapArrayDataSource(array));
        return JasperPrintManager.printReport(jasperPrint, false);
    }
    
    public boolean printReceipt_A6(Map<String, Object> parms, Object[] array) throws Exception {
        JasperReport report = JasperCompileManager.compileReport(
                getClass().getResource("/com/isimple/intelijpos_lite/jrxml/rpt_receipt_a6.jrxml").openStream());
        JasperPrint jasperPrint = JasperFillManager.fillReport(report,
                parms, new JRMapArrayDataSource(array));
        return JasperPrintManager.printReport(jasperPrint, false);
    }

    public boolean printMiniStatment(Map<String, Object> parms, Object[] array) throws Exception {
        JasperReport report = JasperCompileManager.compileReport(
                getClass().getResource("/com/isimple/intelijpos_lite/jrxml/rpt_min_statment.jrxml").openStream());
        JasperPrint jasperPrint = JasperFillManager.fillReport(report,
                parms, new JRMapArrayDataSource(array));
        return JasperPrintManager.printReport(jasperPrint, false);
    }

    public boolean printBarcode(Collection<UIBarcode.BarcodeItem> values) throws Exception {
        DecimalFormat format = new DecimalFormat("#,###.00");
        List<Map<String, String>> maps = new ArrayList<>();
        for (UIBarcode.BarcodeItem value : values) {
            int qty = value.qty;
            if (qty % 2 != 0) {
                qty += 1;
            }
            for (int i = 0; i < qty / 2; i++) {
                Map<String, String> map = new HashMap<>();
                map.put("barcode", value.barcode);
                map.put("item", value.item);
                map.put("price", Const.CURRANCY + format.format(value.price));
                maps.add(map);
            }
        }
        JasperReport report = JasperCompileManager.compileReport(
                getClass().getResource("/com/isimple/intelijpos_lite/jrxml/rpt_barcode.jrxml").openStream());
        JasperPrint jasperPrint = JasperFillManager.fillReport(report,
                null, new JRMapArrayDataSource(maps.toArray()));
        return JasperPrintManager.printReport(jasperPrint, true);
    }

}
