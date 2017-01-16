package com.isimple.intelijpos_lite.util;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;

/**
 *
 * @author Hashan Chamikara
 */
public class DatabaseUtil {

    private static final EbeanServer ebeanServer;

    static {
        DataSourceConfig ds = new DataSourceConfig();
        ds.setDriver("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/iposlitedb");
        ds.setUsername("root");
        ds.setPassword("");
        ServerConfig sc = new ServerConfig();
        sc.setName("defult");
        sc.setDataSourceConfig(ds);
        sc.setDefaultServer(true);
        ebeanServer = EbeanServerFactory.create(sc);
    }
    
    public static EbeanServer getServer(){
        return ebeanServer;
    }
}
