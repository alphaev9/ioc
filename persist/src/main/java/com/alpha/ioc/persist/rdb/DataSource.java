package com.alpha.ioc.persist.rdb;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private String dbConfig;

    public void setDbConfig(String dbConfig) {
        this.dbConfig = dbConfig;
    }

    public Connection getConnection() {
        InputStream resource = getClass().getClassLoader().getResourceAsStream(dbConfig);
        Properties properties = new Properties();
        try {
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
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

    public String getTableName() {
        InputStream resource = getClass().getClassLoader().getResourceAsStream(dbConfig);
        Properties properties = new Properties();
        try {
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("table");
    }
}
