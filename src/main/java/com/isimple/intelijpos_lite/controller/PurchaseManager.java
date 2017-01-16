/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isimple.intelijpos_lite.controller;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Expr;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.Transaction;
import com.isimple.intelijpos_lite.models.Grn;
import com.isimple.intelijpos_lite.models.GrnDetail;
import com.isimple.intelijpos_lite.models.Item;
import com.isimple.intelijpos_lite.models.Stock;
import com.isimple.intelijpos_lite.models.Supplier;
import com.isimple.intelijpos_lite.util.Const;
import com.isimple.intelijpos_lite.util.DatabaseUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dumidu
 */
public class PurchaseManager {

    private PurchaseManager() {
    }

    public static PurchaseManager getInstance() {
        return GRNManagerHolder.INSTANCE;
    }

    public boolean saveGRN(Grn grn, Map<Item, Stock> mITEMS, int type) {
        EbeanServer session = DatabaseUtil.getServer();
        Transaction transaction = session.beginTransaction();
        boolean flag = false;
        try {
            session.save(grn);
            mITEMS.entrySet().stream().map((entrySet) -> {
                Item key = entrySet.getKey();
                Stock value = entrySet.getValue();
                GrnDetail detail = new GrnDetail();
                detail.setGrn(grn);
                detail.setItem(key);
                detail.setCost(value.getCost());
                detail.setWprice(value.getWprice());
                detail.setRprice(value.getRprice());
                detail.setQty(value.getQty());
                return detail;
            }).forEach((detail) -> {
                session.save(detail);
            });

            if (type == Const.TYPE_UPDATE) {
                for (Stock stock : mITEMS.values()) {
                    Collection<Stock> findList = session.createQuery(Stock.class)
                            .where(Expr.eq("item", stock.getItem())).findList();
                    if (findList.isEmpty()) {
                        session.save(stock);
                    } else {
                        int qty = 0;
                        for (Stock s : findList) {
                            qty += s.getQty();
                            session.delete(s);
                        }
                        stock.setQty(stock.getQty() + qty);
                        session.save(stock);
                    }
                }
            } else {
                for (Stock stock : mITEMS.values()) {
                    Collection<Stock> findList = session.createQuery(Stock.class)
                            .where(Expr.eq("item", stock.getItem())).findList();
                    if (findList.isEmpty()) {
                        session.save(stock);
                    } else {
                        for (Stock s : findList) {
                            if (s.getRprice() == stock.getRprice() && s.getWprice() == stock.getWprice()) {
                                s.setQty(s.getQty() + stock.getQty());
                                session.update(s);
                                break;
                            } else {
                                session.save(s);
                            }
                        }
                    }
                }
            }
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            transaction.rollback();
            System.err.println("Exception : " + e.getMessage());
        } finally {
            transaction.end();
        }
        return flag;
    }

    private static class GRNManagerHolder {

        private static final PurchaseManager INSTANCE = new PurchaseManager();
    }

    public List<Supplier> searchSupplier(String text) {
        EbeanServer session = DatabaseUtil.getServer();
        List findList = session.createQuery(Supplier.class).where(Expr.and(
                Expr.like("name", "%" + text + "%"),
                Expr.eq("status", Const.SUPPLIER_ACTIVE))).setMaxRows(10).findList();
        return findList;
    }

    public boolean saveSupplier(Supplier supplier) {
        EbeanServer session = DatabaseUtil.getServer();
        Transaction transaction = session.beginTransaction();
        boolean flag = false;
        try {
            session.save(supplier);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            transaction.end();
        }
        return flag;
    }

    public String getGenerateProductNumber() {
        EbeanServer session = DatabaseUtil.getServer();
        SqlRow row = session.createSqlQuery("SELECT MAX(`barcode`) AS `barcode` FROM `item`").setMaxRows(1).findUnique();
        int i = Integer.parseInt(row != null ? row.getString("barcode") : "0");
        i++;
        return String.format("%07d", i);
    }

    public List<String> getCategories(String keyword) {
        EbeanServer session = DatabaseUtil.getServer();
        SqlQuery query = session.createSqlQuery("SELECT `category` FROM `item` WHERE `category` LIKE ? GROUP BY `category`");
        query.setParameter(1, "%" + keyword + "%");
        List<SqlRow> list = query.findList();
        List<String> strings = new ArrayList<>();
        list.stream().forEach((row) -> {
            strings.add(row.getString("category"));
        });
        return strings;
    }

    public List<String> getUnits(String keyword) {
        EbeanServer session = DatabaseUtil.getServer();
        SqlQuery query = session.createSqlQuery("SELECT `unit` FROM `item` WHERE `unit` LIKE ? GROUP BY `unit`");
        query.setParameter(1, "%" + keyword + "%");
        List<SqlRow> list = query.findList();
        List<String> strings = new ArrayList<>();
        list.stream().forEach((row) -> {
            strings.add(row.getString("unit"));
        });
        return strings;
    }

}
