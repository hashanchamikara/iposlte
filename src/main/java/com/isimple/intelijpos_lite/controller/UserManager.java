/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isimple.intelijpos_lite.controller;

import com.avaje.ebean.EbeanServer;
import com.isimple.intelijpos_lite.models.User;
import com.isimple.intelijpos_lite.util.DatabaseUtil;
import java.util.List;

/**
 *
 * @author Hashan Chamikara
 * @email hashanchamikara@googlemail.com
 * @contact 0094 778 62 57 57
 * @copyright Best Life IT
 */
public class UserManager {

    private UserManager() {
    }

    public static UserManager getInstance() {
        return UserManagerHolder.INSTANCE;
    }

    private static class UserManagerHolder {

        private static final UserManager INSTANCE = new UserManager();
    }

    public User login(String email, String passwd) {
        EbeanServer session = DatabaseUtil.getServer();
        List<User> list = session.createQuery(User.class).where()
                .eq("email", email)
                .eq("password", passwd)
                .eq("status", 1)
                .setMaxRows(1).findList();
        User user = !list.isEmpty() ? list.get(0) : null;
        return user;
    }
}
