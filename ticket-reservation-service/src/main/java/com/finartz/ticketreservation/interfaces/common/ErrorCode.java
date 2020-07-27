package com.finartz.ticketreservation.interfaces.common;

public enum ErrorCode {
    FLIGHTIDNOTVALID("TRS1000"),
    CVVNUMBERNOTVALID("TRS1001"),
    CREDITCARDEXPIREDATENOTVALID("TRS1002"),
    CREDITCARDNUMBERNOTVALID("TRS1003"),
    IDENTITYNUMBERNOTVALID("TRS1004"),
    NAMENOTVALID("TRS1005"),
    SURNAMENOTVALID("TRS1006");


    private String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }
}