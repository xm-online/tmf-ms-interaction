package com.icthh.xm.tmf.ms.interaction.lep.keyresolver;

import com.icthh.xm.lep.api.LepMethod;
import com.icthh.xm.lep.api.MethodSignature;
import com.icthh.xm.tmf.ms.interaction.web.rest.PartyInteractionDelegate;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProfileKeyResolverUnitTest {

    private static final String PROFILE_PARAM_NAME = "profile";
    private static final String RETRIEVE_METHOD_NAME = "retrievePartyInteraction";
    private static final String RETRIEVE_LIST_METHOD_NAME = "retrievePartyInteractions";

    @Test
    void shouldGetParamNameForMethodsWithDifferentParamIndex() {

        Method[] methods = PartyInteractionDelegate.class.getMethods();

        MethodSignature methodSignature = mock(MethodSignature.class);
        LepMethod lepMethod = mock(LepMethod.class);

        when(lepMethod.getMethodSignature()).thenReturn(methodSignature);
        when(methodSignature.getParameterNames())
            .thenReturn(getParametersFor(RETRIEVE_METHOD_NAME, methods))
            .thenReturn(getParametersFor(RETRIEVE_LIST_METHOD_NAME, methods));

        ProfileKeyResolver resolver = new ProfileKeyResolver();
        int retrieveProfileIdx = resolver.getParamIndex(lepMethod, PROFILE_PARAM_NAME);
        int retrieveListProfileIdx = resolver.getParamIndex(lepMethod, PROFILE_PARAM_NAME);

        assertEquals(1, retrieveProfileIdx);
        assertEquals(0, retrieveListProfileIdx);
    }

    private String[] getParametersFor(final String methodName, final Method[] methods) {
        return Arrays.stream(methods)
            .filter(method -> method.getName().equals(methodName))
            .findFirst()
            .map(Executable::getParameters)
            .map(Arrays::stream)
            .map(params -> params.map(Parameter::getName).toArray(String[]::new))
            .orElseThrow();
    }
}
