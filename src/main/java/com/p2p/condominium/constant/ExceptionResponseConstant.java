package com.p2p.condominium.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ExceptionResponseConstant {
    public static final String STATUS = "Http request status";
    public static final String TIMESTAMP = "Error date time";
    public static final String MESSAGES = "List of error messages";
    public static final String MESSAGES_FIELD = "Field that showed the error";
    public static final String MESSAGES_ERROR = "Error message";
}
