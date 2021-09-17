package com.icthh.xm.tmf.ms.interaction.lep.keyresolver;

import static java.util.Optional.ofNullable;

import com.icthh.xm.commons.lep.AppendLepKeyResolver;
import com.icthh.xm.lep.api.LepManagerService;
import com.icthh.xm.lep.api.LepMethod;
import com.icthh.xm.lep.api.commons.SeparatorSegmentedLepKey;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class ProfileKeyResolver extends AppendLepKeyResolver {

    @Override
    protected String[] getAppendSegments(SeparatorSegmentedLepKey baseKey,
                                         LepMethod method,
                                         LepManagerService managerService) {
        String profile = getProfile(method);
        String translated = translateToLepConvention(profile);
        return new String[]{translated};
    }

    private String getProfile(LepMethod method) {
        HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return ofNullable(request.getHeader("Profile"))
            .or(() -> ofNullable(getParamValue(method, "profile", String.class)))
            .orElse(Strings.EMPTY);
    }

    @Override
    protected int getParamIndex(LepMethod method, final String paramName) {
        Objects.requireNonNull(paramName, "paramName can't be null");
        if (paramName.isEmpty()) {
            throw new IllegalArgumentException("paramName can't be blank");
        }

        List<String> paramNames = Arrays.asList(method.getMethodSignature().getParameterNames());
        int paramIndex = paramNames.indexOf(paramName);

        if (paramIndex == -1) {
            throw new IllegalStateException("Can't find parameter '" + paramName + "' for method: "
                + method.getMethodSignature().toString());
        }

        return paramIndex;
    }
}
