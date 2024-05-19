package com.samill.missionary_backend.common.util;

import com.samill.missionary_backend.common.dto.MaskingType;
import org.springframework.stereotype.Component;

@Component
public class MaskingUtil {
    public static String mask(MaskingType type, String value) {
        String str = "";
        switch (type) {
            case IDENTIFICATION_NUMBER:
                str = getIdentificationNumberMask(value);
                break;
            default:
                break;
        }
        return str;
    }

    private static String getIdentificationNumberMask(String identificationNumber) {
        String last_6_char_pattern = "(.{6}$)";
        return identificationNumber.replaceAll(last_6_char_pattern, "******");
    }
}