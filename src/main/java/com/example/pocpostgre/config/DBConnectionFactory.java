package com.example.pocpostgre.config;

import io.r2dbc.spi.ConnectionFactory;

public interface DBConnectionFactory {
    ConnectionFactory getConnectionFactory(String uri, String username, String password, String host, Integer port, String db);
}
