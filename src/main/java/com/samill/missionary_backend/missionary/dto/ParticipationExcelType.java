package com.samill.missionary_backend.missionary.dto;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ParticipationExcelType {
    NAME("이름"),
    USER_ID("User ID"),
    BIRTHDATE("생년월일"),
    CHURCH("연계교회"),
    LEADER_NAME("팀장"),
    APPLY_FEE("선교비"),
    IS_PAID("입금여부"),
    IDENTIFICATION_NUMBER("주민등록번호"),
    APPLY_DATE("신청일");

    private final String text;

    public static String[] getNames() {
        return Arrays.stream(ParticipationExcelType.values())
            .map(ParticipationExcelType::getText)
            .toArray(String[]::new);
    }
}
