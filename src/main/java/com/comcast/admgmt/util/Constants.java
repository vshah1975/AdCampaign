package com.comcast.admgmt.util;

public final class Constants
{
    public static final String PROP_LOG4J_FILENAME     = "log4j-AdCampaignMgmt.xml";
    public static final String PROP_BASE_FILENAME      = "AdCampaignMgmt.properties";

    public static final String RESPONSETYPE_XML        = "xml";
    public static final String RESPONSETYPE_HTML       = "html";
    public static final String RESPONSETYPE_JSON       = "json";

    // Validation Codes
    public static final String SUCCESSRESPONSE         = "successResponse";
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
