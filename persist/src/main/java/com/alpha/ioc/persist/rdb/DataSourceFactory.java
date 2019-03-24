package com.alpha.ioc.persist.rdb;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceFactory {
    static public DataSource buildDataSorceFromProperties(String config) {
        InputStream resource = DataSourceFactory.class.getClassLoader().getResourceAsStream(config);
        Properties properties = new Properties();
        try {
            properties.load(resource);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            return new DataSource() {
                @Override
                public Connection getConnection() {
                    try {
                        Class.forName(driver);
                        return DriverManager.getConnection(url, user, password);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                public String getTableName() {
                    return properties.getProperty("table");
                }
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
