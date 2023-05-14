package com.example.pocpostgre.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!local")
public class PostgreSQLConnectionFactory implements DBConnectionFactory{

    @Override
    public ConnectionFactory getConnectionFactory(String uri, String username, String password, String host, Integer port, String db) {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(host)
                        .port(port)
                        .username(username)
                        .password(password)
                        .database(db)
                        .build()
        );
    }
}
