/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isimple.intelijpos_lite.controller;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Transaction;
import com.isimple.intelijpos_lite.models.Invoice;
import com.isimple.intelijpos_lite.models.InvoiceDetail;
import com.isimple.intelijpos_lite.models.Item;
import com.isimple.intelijpos_lite.models.Receipt;
import com.isimple.intelijpos_lite.models.Stock;
import com.isimple.intelijpos_lite.util.DatabaseUtil;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dumidu
 */
public class SalesManager {

    private SalesManager() {
    }

    public static SalesManager getInstance() {
        return SalesManagerHolder.INSTANCE;
    }

    private static class SalesManagerHolder {

        private static final SalesManager INSTANCE = new SalesManager();
    }

    public Integer saveInvoice(Invoice invoice, List<InvoiceDetail> details, List<Receipt> receipts) {
        EbeanServer session = DatabaseUtil.getServer();
        Transaction transaction = session.beginTransaction();
//        ItemManager itemManager = ItemManager.getInstance();
        try {
            session.save(invoice);
            for (InvoiceDetail detail : details) {
                detail.setInvoice(invoice);
//                Item item = detail.getItem();
                session.save(detail);
//                List<Stock> stocks = itemManager.getStocks(item);
//                if (!stocks.isEmpty()) {
//                    int qty = detail.getQty();
//                    for (Stock stock : stocks) {
//                        if (stock.getQty() <= qty) {
//                            qty -= stock.getQty();
//                            stock.setQty(0);
//                        } else {
//                            stock.setQty(stock.getQty() - qty);
//                        }
//                        session.update(stock);
//                    }
//                }
            }
            for (Receipt receipt : receipts) {
                receipt.setInvoice(invoice);
                session.save(receipt);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            transaction.end();
        }
        return invoice.getId();
    }

    public List<Invoice> getSales(Date startDate, Date endDate) {
        startDate.setHours(00);
        startDate.setMinutes(00);
        startDate.setSeconds(00);

        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        EbeanServer session = DatabaseUtil.getServer();
        List invoices = session.createQuery(Invoice.class).where(Expr.between("date", startDate, endDate)).findList();
        return invoices;
    }
}
