package com.samill.missionary_backend.church.church.dto;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

public record GetChurchesCursor(
        @NonNull String id,
        @NonNull String name
) {

    public String value() {
        return StringUtils.rightPad(name, 20, getCharPad()).concat(name);
    }

    public char getCharPad() {
        return '0';
    }

}
