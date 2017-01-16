/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isimple.intelijpos_lite.controller;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Transaction;
import com.isimple.intelijpos_lite.models.Item;
import com.isimple.intelijpos_lite.models.Stock;
import com.isimple.intelijpos_lite.util.Const;
import com.isimple.intelijpos_lite.util.DatabaseUtil;
import java.util.List;

/**
 *
 * @author dumidu
 */
public class ItemManager {

    private ItemManager() {
    }

    public static ItemManager getInstance() {
        return ItemManagerHolder.INSTANCE;
    }

    public boolean saveItem(Item item, Stock stock) {
        EbeanServer session = DatabaseUtil.getServer();
        Transaction transaction = session.beginTransaction();
        boolean flag = false;
        try {
            session.save(item);
            session.save(stock);
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

    public List<Item> searchItem(String text) {
        EbeanServer session = DatabaseUtil.getServer();
        List list = session.createQuery(Item.class).where(Expr.and(
                Expr.or(
                        Expr.like("barcode", "%" + text),
                        Expr.or(Expr.like("reference", "%" + text),
                                Expr.like("name", "%" + text + "%"))), Expr.eq("status", Const.ITEM_ACTIVE)))
                .setMaxRows(20).findList();

        return list;
    }

    public List<Item> getAllItem() {
        EbeanServer session = DatabaseUtil.getServer();
        List list = session.createQuery(Item.class).where(Expr.eq("status", Const.ITEM_ACTIVE)).findList();

        return list;
    }

    public List<Item> searchItemName(String text) {
        EbeanServer session = DatabaseUtil.getServer();
        List list = session.createQuery(Item.class).where(
                Expr.and(
                        Expr.like("name", "%" + text + "%"),
                        Expr.eq("status", Const.ITEM_ACTIVE)))
                .setMaxRows(10).findList();
        return list;
    }

    public List<Item> searchItemBarcode(String text) {
        EbeanServer session = DatabaseUtil.getServer();
        List list = session.createQuery(Item.class).where(
                Expr.and(
                        Expr.like("barcode", "%" + text + "%"),
                        Expr.eq("status", Const.ITEM_ACTIVE)))
                .setMaxRows(10).findList();

        return list;
    }

    public Item getItemByBarcode(String barcode) {
        System.err.println("Barcode " + barcode);
        EbeanServer session = DatabaseUtil.getServer();
        Item item = (Item) session.createQuery(Item.class).where(
                Expr.and(
                        Expr.eq("barcode", barcode),
                        Expr.eq("status", Const.ITEM_ACTIVE))).setMaxRows(1).findUnique();

        return item;
    }

    public Item getItemByRefcode(String barcode) {
        System.err.println("Refcode " + barcode);
        EbeanServer session = DatabaseUtil.getServer();
        Item item = (Item) session.createQuery(Item.class).where(
                Expr.and(
                        Expr.eq("reference", barcode),
                        Expr.eq("status", Const.ITEM_ACTIVE))).setMaxRows(1).findUnique();
        return item;
    }

    public List<Stock> getStocks(Item item) {
        EbeanServer session = DatabaseUtil.getServer();
        List list = session.createQuery(Stock.class).where(Expr.eq("item", item)).findList();
        return list;
    }

    public List<Stock> getStocks(Item item, int minQty, int maxQty) {
        EbeanServer session = DatabaseUtil.getServer();
        List list = session.createQuery(Stock.class).where(
                Expr.and(
                        Expr.eq("item", item),
                        Expr.between("qty", minQty, maxQty))).findList();
        return list;
    }

    public Stock getItemStock(String barcode, String rprice) {
        EbeanServer session = DatabaseUtil.getServer();
        Item item = (Item) session.createQuery(Item.class).where(
                Expr.and(
                        Expr.eq("barcode", barcode),
                        Expr.eq("status", Const.ITEM_ACTIVE))).setMaxRows(1).findUnique();
        Stock stock = (Stock) session.createQuery(Stock.class).where(Expr.and(Expr.eq("item", item),
                Expr.eq("rprice", Double.parseDouble(rprice)))).setMaxRows(1).findUnique();

        return stock;
    }

    private static class ItemManagerHolder {

        private static final ItemManager INSTANCE = new ItemManager();
    }

}
