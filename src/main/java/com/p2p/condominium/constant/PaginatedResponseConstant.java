package com.p2p.condominium.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaginatedResponseConstant {
    public static final String LIST = "List with the content returned from the query";
    public static final String PAGE = "Current page";
    public static final String SIZE = "Current page size";
    public static final String TOTAL_RECORDS = "Total number of records";
    public static final String MAX_PAGE = "Maximum number of pages";
    public static final String LAST = "Check if it is last page";
}
