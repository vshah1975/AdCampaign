package com.comcast.admgmt.validators;

import com.comcast.admgmt.exception.DataAccessException;
import com.comcast.admgmt.framework.model.CreateAdRequest;
import com.comcast.admgmt.util.Constants;
import com.comcast.admgmt.util.Logger;
import com.comcast.admgmt.util.Util;

public class Validator
{

    private static Logger logger = Logger.getInstance();

    public static void validateCreateAdRequest(CreateAdRequest createAdRequest) throws DataAccessException
    {

        logger.debug(Validator.class, "validateCreateAdRequest: Start of method execution");

        if (Util.nullToEmptyString(createAdRequest.getPartnerId()).equalsIgnoreCase(""))
        {
            Util.executeDataAccessException(Constants.PARTNERID_NULL);
        } else if (createAdRequest.getDuration() == null || createAdRequest.getDuration().intValue() < 1)
        {
            Util.executeDataAccessException(Constants.ADDURATION_NULL);
        } else if (Util.nullToEmptyString(createAdRequest.getAdContent()).equalsIgnoreCase(""))
        {
            Util.executeDataAccessException(Constants.ADCONTENT_NULL);
        }

        logger.debug(Validator.class, "validateCreateAdRequest: Validation was successful");
        logger.debug(Validator.class, "validateCreateAdRequest: End of method execution");
    }

}
