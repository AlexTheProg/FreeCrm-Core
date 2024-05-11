package com.axc.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@TestConfiguration
@Slf4j
public class IntegrationTestConfiguration {

    @Bean(destroyMethod = "close")
    public PostgreSQLContainer<?> postgreSQLContainer() {
        var container = new PostgreSQLContainer<>("postgres");
        container.start();

        log.info("Postgresql container started on {}", container.getCurrentContainerInfo().getConfig().getHostName());

        return container;
    }
}
