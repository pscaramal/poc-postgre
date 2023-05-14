package com.example.pocpostgre.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class R2DBCConfig extends AbstractR2dbcConfiguration {

    @Value("${spring.r2dbc.url}")
    private String url;

    @Value("${spring.r2dbc.username}")
    private String userName;

    @Value("${spring.r2dbc.password}")
    private String password;

    @Value("${spring.r2dbc.host}")
    private String host;

    @Value("${spring.r2dbc.port}")
    private Integer port;

    @Value("${spring.r2dbc.db}")
    private String db;

    private DBConnectionFactory dbConnectionFactory;

    @Override
    public ConnectionFactory connectionFactory() {
        return dbConnectionFactory
                .getConnectionFactory(url, userName, password, host, port, db);
    }
}
