package com.icthh.xm.tmf.ms.interaction.lep;

import static com.icthh.xm.tmf.ms.interaction.lep.LepXmAccountMsConstants.BINDING_KEY_COMMONS;
import static com.icthh.xm.tmf.ms.interaction.lep.LepXmAccountMsConstants.BINDING_KEY_METER_REGISTRY;
import static com.icthh.xm.tmf.ms.interaction.lep.LepXmAccountMsConstants.BINDING_KEY_SERVICES;
import static com.icthh.xm.tmf.ms.interaction.lep.LepXmAccountMsConstants.BINDING_KEY_TEMPLATES;
import static com.icthh.xm.tmf.ms.interaction.lep.LepXmAccountMsConstants.BINDING_SUB_KEY_PERMISSION_SERVICE;
import static com.icthh.xm.tmf.ms.interaction.lep.LepXmAccountMsConstants.BINDING_SUB_KEY_SERVICE_TENANT_CONFIG_SERICE;
import static com.icthh.xm.tmf.ms.interaction.lep.LepXmAccountMsConstants.BINDING_SUB_KEY_TEMPLATE_JDBC;
import static com.icthh.xm.tmf.ms.interaction.lep.LepXmAccountMsConstants.BINDING_SUB_KEY_TEMPLATE_REST;

import com.icthh.xm.commons.config.client.service.TenantConfigService;
import com.icthh.xm.commons.lep.commons.CommonsExecutor;
import com.icthh.xm.commons.lep.commons.CommonsService;
import com.icthh.xm.commons.lep.spring.SpringLepProcessingApplicationListener;
import com.icthh.xm.commons.permission.service.PermissionCheckService;
import com.icthh.xm.lep.api.ScopedContext;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * The {@link XmMsLepProcessingApplicationListener} class.
 */
@RequiredArgsConstructor
public class XmMsLepProcessingApplicationListener extends SpringLepProcessingApplicationListener {


    private final TenantConfigService tenantConfigService;

    private final RestTemplate restTemplate;
    private final JdbcTemplate jdbcTemplate;

    private final CommonsService commonsService;
    private final PermissionCheckService permissionCheckService;

    private final MeterRegistry meterRegistry;

    @Override
    protected void bindExecutionContext(ScopedContext executionContext) {
        // services
        Map<String, Object> services = new HashMap<>();
        services.put(BINDING_SUB_KEY_SERVICE_TENANT_CONFIG_SERICE, tenantConfigService);
        services.put(BINDING_SUB_KEY_PERMISSION_SERVICE, permissionCheckService);

        executionContext.setValue(BINDING_KEY_COMMONS, new CommonsExecutor(commonsService));
        executionContext.setValue(BINDING_KEY_SERVICES, services);
        executionContext.setValue(BINDING_KEY_METER_REGISTRY, meterRegistry);

        // templates
        Map<String, Object> templates = new HashMap<>();
        templates.put(BINDING_SUB_KEY_TEMPLATE_REST, restTemplate);
        templates.put(BINDING_SUB_KEY_TEMPLATE_JDBC, jdbcTemplate);

        executionContext.setValue(BINDING_KEY_TEMPLATES, templates);
    }

}
