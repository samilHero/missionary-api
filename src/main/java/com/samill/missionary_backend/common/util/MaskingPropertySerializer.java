package com.samill.missionary_backend.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.samill.missionary_backend.common.dto.MaskingType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MaskingPropertySerializer extends StdSerializer<String> implements ContextualSerializer {
    MaskingType maskingType;

    public MaskingPropertySerializer() {
        super(String.class);
    }

    public MaskingPropertySerializer(MaskingType maskingType) {
        super(String.class);
        this.maskingType = maskingType;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(MaskingUtil.mask(maskingType, value));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        MaskingType maskingTypeValue = null;
        MaskRequired ann = null;
        if (property != null) {
            ann = property.getAnnotation(MaskRequired.class);
        }
        if (ann != null) {
            maskingTypeValue = ann.type();
        }
        return new MaskingPropertySerializer(maskingTypeValue);
    }
}
