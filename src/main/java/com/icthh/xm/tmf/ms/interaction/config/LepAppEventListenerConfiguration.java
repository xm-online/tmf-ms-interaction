package com.icthh.xm.tmf.ms.interaction.config;

import com.icthh.xm.commons.config.client.service.TenantConfigService;
import com.icthh.xm.commons.lep.commons.CommonsService;
import com.icthh.xm.commons.permission.service.PermissionCheckService;
import com.icthh.xm.tmf.ms.interaction.lep.XmMsLepProcessingApplicationListener;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * The {@link LepAppEventListenerConfiguration} class.
 */
@Configuration
public class LepAppEventListenerConfiguration {

    @Bean
    XmMsLepProcessingApplicationListener buildLepProcessingApplicationListener(
        TenantConfigService tenantConfigService,
        @Qualifier("loadBalancedRestTemplate") RestTemplate restTemplate,
        CommonsService commonsService,
        JdbcTemplate jdbcTemplate,
        PermissionCheckService permissionCheckService,
        MeterRegistry meterRegistry
    ) {
        return new XmMsLepProcessingApplicationListener(
            tenantConfigService,
            restTemplate,
            jdbcTemplate,
            commonsService,
            permissionCheckService,
            meterRegistry
        );
    }

}
