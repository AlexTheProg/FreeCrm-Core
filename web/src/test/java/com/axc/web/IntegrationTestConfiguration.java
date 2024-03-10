package com.axc.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
@Slf4j
public class IntegrationTestConfiguration {

    @Bean(destroyMethod = "close")
    public PostgreSQLContainer<?> postgreSQLContainer() {
        var container = new PostgreSQLContainer<>("docker.io/postgres:15.2-alpine");
        container.start();

        log.info("Postgresql container started on {}", container.getCurrentContainerInfo().getConfig().getHostName());

        return container;
    }
}
