package com.p2p.condominium.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerConstant {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "20";
    public static final String MEDIA_TYPE_JSON = "application/json";
    public static final String STATUS_OK = "200";
    public static final String STATUS_OK_DESCRIPTION = "Successful operation";

    /**
     *  STACKHOLDER
     */
    public static final String STACKHOLDER = "Stackholder";
    public static final String STACKHOLDER_DESCRIPTION = "Stackholder Transactions Operations";
    public static final String STACKHOLDER_OPERATION_LIST_SUMMARY = "Get stackholders";
    public static final String STACKHOLDER_OPERATION_LIST_DESCRIPTION = "Returns list of registered stackholders in paginated form";
}
