package com.icthh.xm.tmf.ms.interaction.config;

import io.github.jhipster.config.JHipsterConstants;
import io.github.jhipster.config.liquibase.AsyncSpringLiquibase;
import javax.sql.DataSource;

import io.github.jhipster.config.liquibase.SpringLiquibaseUtil;
import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.core.task.TaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class LiquibaseConfiguration {

    private final Environment env;

    @Bean
    public SpringLiquibase liquibase(@Qualifier("taskExecutor") Executor executor,
                                     @LiquibaseDataSource ObjectProvider<DataSource> liquibaseDataSource, LiquibaseProperties liquibaseProperties,
                                     ObjectProvider<DataSource> dataSource, DataSourceProperties dataSourceProperties) {

        // If you don't want Liquibase to start asynchronously, substitute by this:
        // SpringLiquibase liquibase = SpringLiquibaseUtil.createSpringLiquibase(liquibaseDataSource.getIfAvailable(),
        // liquibaseProperties, dataSource.getIfUnique(), dataSourceProperties);
        SpringLiquibase liquibase = SpringLiquibaseUtil.createAsyncSpringLiquibase(this.env, executor,
            liquibaseDataSource.getIfAvailable(), liquibaseProperties, dataSource.getIfUnique(), dataSourceProperties);
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setChangeLogParameters(liquibaseProperties.getParameters());
        if (env.acceptsProfiles(Profiles.of(JHipsterConstants.SPRING_PROFILE_NO_LIQUIBASE))) {
            liquibase.setShouldRun(false);
        } else {
            liquibase.setShouldRun(liquibaseProperties.isEnabled());
            log.debug("Configuring Liquibase");
        }
        return liquibase;
    }
}
