package com.alpha.ioc.persist.rdb;

import java.sql.Connection;

public interface DataSource {
    Connection getConnection();
    String getTableName();
}
