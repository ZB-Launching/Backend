package com.example.zblunchrecommend.enums;

public enum ChooseType {
    PREFERENCES("선호", "점심 메뉴를 추천해줘. \n"
            + "가격: 15,000원 이하 \n"
            + "%s하는 음식: %s \n"
            + "종류: %s 종류의 음식"),
    NOT_PREFERENCES("비선호", "점심 메뉴를 추천해줘. \n"
            + "가격: 15,000원 이하 \n"
            + "%s하는 음식: %s \n"),
    RANDOM("랜덤", "점심 메뉴를 추천해줘. \n"
            + "가격: 15,000원 이하 \n"
            + "%s한 음식");
    private String value;
    private String contentFormat;

    ChooseType(String value, String contentFormat) {
        this.value = value;
        this.contentFormat = contentFormat;
    }
    public String getValue() {
        return value;
    }
    public String getContentFormat() {
        return contentFormat;
    }
}
