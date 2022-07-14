package com.p2p.condominium.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ControllerConstant {

    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String ID = "id";
    public static final String PAGE_DESCRIPTION = "Request page number";
    public static final String SIZE_DESCRIPTION = "Request page size";
    public static final String ID_DESCRIPTION = "Request id";
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "20";
    public static final String MEDIA_TYPE_JSON = "application/json";
    public static final String STATUS_OK = "200";
    public static final String STATUS_OK_DESCRIPTION = "Successful operation";
    public static final String STATUS_CREATED = "201";
    public static final String STATUS_CREATED_DESCRIPTION = "Entity created";
    public static final String STATUS_ACCEPTED = "202";
    public static final String STATUS_ACCEPTED_DESCRIPTION = "Entity accepted";
    public static final String STATUS_NO_CONTENT = "204";
    public static final String STATUS_NO_CONTENT_DESCRIPTION = "No content";
    public static final String STATUS_BAD_REQUEST = "400";
    public static final String STATUS_BAD_REQUEST_DESCRIPTION = "Bad request";
    public static final String STATUS_NOT_FOUND = "404";
    public static final String STATUS_NOT_FOUND_DESCRIPTION = "Entity not found";

    /**
     *  STACKHOLDER
     */
    public static final String STACKHOLDER = "Stackholder";
    public static final String STACKHOLDER_DESCRIPTION = "Stackholder Transactions Operations";
    public static final String STACKHOLDER_OPERATION_LIST_SUMMARY = "Get stackholders";
    public static final String STACKHOLDER_OPERATION_LIST_DESCRIPTION = "Returns list of registered stackholders in paginated form";
    public static final String STACKHOLDER_OPERATION_GET_ID_SUMMARY = "Get single stackholder";
    public static final String STACKHOLDER_OPERATION_GET_ID_DESCRIPTION = "Returns unique stackholder by id";
    public static final String STACKHOLDER_OPERATION_DELETE_SUMMARY = "Delete single stackholder";
    public static final String STACKHOLDER_OPERATION_DELETE_DESCRIPTION = "Exclude unique stackholder by id if it has not been linked to any operation";
    public static final String STACKHOLDER_OPERATION_POST_SUMMARY = "Create stackholder";
    public static final String STACKHOLDER_OPERATION_POST_DESCRIPTION = "Create stackholder if it doesn't exist";
    public static final String STACKHOLDER_OPERATION_PUT_SUMMARY = "Update stackholder";
    public static final String STACKHOLDER_OPERATION_PUT_DESCRIPTION = "Update stackholder if it exists";
}
