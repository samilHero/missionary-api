package com.samill.missionary_backend.common.util;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.samill.missionary_backend.common.dto.MaskingType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotationsInside
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@JsonSerialize(using = MaskingPropertySerializer.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaskRequired {
    MaskingType type();
}
