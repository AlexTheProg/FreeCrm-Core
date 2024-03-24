package com.axc.persistence;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatasourceConfiguration {

    @Bean
    @ConfigurationProperties("master-datasource")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("master-datasource.configuration")
    public HikariDataSource masterDataSource(DataSourceProperties masterDataSourceProperties) {
        return masterDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("readonly-datasource")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("slave.datasource.configuration")
    public HikariDataSource slaveDataSource(DataSourceProperties slaveDataSourceProperties) {
        return slaveDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public AbstractRoutingDataSource routingDataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                return TransactionSynchronizationManager
                        .isCurrentTransactionReadOnly() ?
                        DataSourceType.READ_ONLY :
                        DataSourceType.READ_WRITE;
            }
        };

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.READ_WRITE, masterDataSource);
        dataSourceMap.put(DataSourceType.READ_ONLY, slaveDataSource);

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }
}
