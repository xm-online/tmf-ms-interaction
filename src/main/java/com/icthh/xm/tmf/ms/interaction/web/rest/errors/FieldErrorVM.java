package com.icthh.xm.tmf.ms.interaction.web.rest.errors;

import lombok.Getter;

import java.io.Serializable;

public class FieldErrorVM implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private final String objectName;

    @Getter
    private final String field;

    @Getter
    private final String message;

    public FieldErrorVM(String dto, String field, String message) {
        this.objectName = dto;
        this.field = field;
        this.message = message;
    }
}
