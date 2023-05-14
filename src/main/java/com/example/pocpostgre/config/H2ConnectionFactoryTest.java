package com.example.pocpostgre.config;

import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.h2.H2ConnectionConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class H2ConnectionFactoryTest implements DBConnectionFactory{

    @Override
    public ConnectionFactory getConnectionFactory(String uri, String username, String password, String host, Integer port, String db) {
        System.out.println("uri: " + uri);
        System.out.println("username: " + username);
        System.out.println("password: " + password);

        return new H2ConnectionFactory(H2ConnectionConfiguration.builder()
                .url(uri)
                .username(username)
                .password(password)
                .build());
    }
}
