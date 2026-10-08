package com.rechargesys.backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    private final Environment env;

    public DataSourceConfig(Environment env) {
        this.env = env;
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        String databaseUrl = env.getProperty("DATABASE_URL");
        String finalUrl = env.getProperty("spring.datasource.url");
        String finalUser = env.getProperty("spring.datasource.username");
        String finalPassword = env.getProperty("spring.datasource.password");

        // If DATABASE_URL is set in environment (common in Railway/Heroku/Neon)
        if (databaseUrl != null && !databaseUrl.trim().isEmpty() && !databaseUrl.startsWith("jdbc:")) {
            try {
                URI uri = new URI(databaseUrl.trim());

                if (uri.getUserInfo() != null) {
                    String[] userParts = uri.getUserInfo().split(":", 2);
                    finalUser = userParts[0];
                    if (userParts.length > 1) {
                        finalPassword = userParts[1];
                    }
                }

                String host = uri.getHost();
                int port = uri.getPort() == -1 ? 5432 : uri.getPort();
                String path = uri.getPath();
                String query = uri.getQuery();

                String sslParam = (query != null && !query.isEmpty()) ? "?" + query : "?sslmode=require";
                if (!sslParam.contains("sslmode")) {
                    sslParam += "&sslmode=require";
                }

                finalUrl = "jdbc:postgresql://" + host + ":" + port + path + sslParam;
                log.info("Configured DataSource from DATABASE_URL for host: {}", host);
            } catch (Exception ex) {
                log.warn("Could not parse DATABASE_URL as URI, using default settings: {}", ex.getMessage());
            }
        }

        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(finalUrl)
                .username(finalUser)
                .password(finalPassword)
                .build();
    }
}
