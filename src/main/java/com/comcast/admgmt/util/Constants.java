package com.comcast.admgmt.util;

public final class Constants
{
    public static final String PROP_LOG4J_FILENAME     = "log4j-AdCampaignMgmt.xml";
    public static final String PROP_BASE_FILENAME      = "AdCampaignMgmt.properties";

    public static final String RESPONSETYPE_XML        = "xml";
    public static final String RESPONSETYPE_HTML       = "html";
    public static final String RESPONSETYPE_JSON       = "json";

    // String constants
    public static final char   X                       = 'x';
    public static final String EMPTY_STRING            = "";
    public static final String NULL                    = "null";
    public static final String Y                       = "Y";
    public static final String N                       = "N";
    public static final String TRUE                    = "true";
    public static final String FALSE                   = "false";

    // ERROR STRINGS
    public static final String ERROR_50                = "50";
    public static final String ERROR_100               = "100";
    public static final String ERROR_101               = "101";
    public static final String ERROR_201               = "201";

    public static final String COMP_USER_ID            = "compUserId";
    public static final String SUCCESSRESPONSE         = "successResponse";
    public static final String NORESULTFOUND           = "noResultFound";
    public static final String NO_PERMISSIONS_TO_USER  = "noPermissionsToUser";
    public static final String INVALIDINPUTPARAMETERS  = "invalidInputParameters";
    public static final String UNKNOWNERROR            = "unknownError";
    public static final String DBERROR                 = "dbError";
    public static final String VALIDACTIONRROR         = "validActionError";
    public static final String CUSTOMERROR             = "customError";
    public static final String FIELD_IS_REQUIRED       = "fieldIsRequired";
    public static final String UNABLE_TO_RETRIEVE_DATA = "unableToRetrieveData";

    // Constatns for date format which could be either
    // yyyy-MM-dd or yyyy-dd-mm
    public static final String yyyy_dd_MM              = "yyyy_dd_MM";
    public static final String yyyy_MM_dd              = "yyyy_MM_dd";

    // Validation Codes
    public static final String PARTNERID_NULL          = "partnerId.null";
    public static final String ADDURATION_NULL         = "adDuration.null";
    public static final String ADCONTENT_NULL          = "adContent.null";

    /**
     * Private constructor so no instances could be created.
     */
    private Constants()
    {

    }

}
