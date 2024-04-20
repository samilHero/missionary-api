package com.samill.missionary_backend.terms.domain.eums;


import com.samill.missionary_backend.common.domain.enums.EnumModel;

public enum TermsType implements EnumModel {
    USING_OF_SERVICE("서비스이용약관"),
    PROCESSING_POLICY_OF_PRIVATE_INFO("개인정보처리방침"),

    USING_OF_PRIVATE_INFO("개인정보수집및이용동의"),

    OFFERING_PRIVATE_INFO_TO_THIRD_PARTY("개인정보 제3자제공동의");


    private String value;

    TermsType(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
