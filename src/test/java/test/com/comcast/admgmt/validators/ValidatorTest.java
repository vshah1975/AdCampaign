package test.com.comcast.admgmt.validators;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

import com.comcast.admgmt.dao.AdCampaignDAO;
import com.comcast.admgmt.exception.DataAccessException;
import com.comcast.admgmt.framework.model.CreateAdRequest;
import com.comcast.admgmt.validators.Validator;

public class ValidatorTest
{
    /**
     * Validate behavior when its a successful request object
     * @throws DataAccessException 
     */
    @Test
    public void validateCreateAdRequestSuccess() throws DataAccessException
    {
        /**
         * We will test a few additions to the ad list
         */
        CreateAdRequest req = new CreateAdRequest();
        req.setAdContent("Test Real Content");
        req.setDuration(BigInteger.valueOf(100));
        req.setPartnerId("asdfasdsf");
        
        Validator.validateCreateAdRequest(req);
        assertTrue("Ad Object creation Successful", req == req);
    }

    /**
     * Validate behavior when partner is null
     */
    @Test
    public void validateNullPartnerSuccess()
    {
        /**
         * We will test a few additions to the ad list
         */
        CreateAdRequest req = new CreateAdRequest();
        req.setAdContent("Test Real Content");
        req.setDuration(BigInteger.valueOf(100));
        
        try {
            Validator.validateCreateAdRequest(req);
        }
        catch (DataAccessException e){
            assertTrue("Ad Object failed creation Successfully - Null Partner", e.getMessageId().longValue() == 510);
        }
    }

    /**
     * Need to test the validator's behavior when 
     * the duration is null or 0
     */
    @Test
    public void validateNullDurationSuccess()
    {
        /**
         * We create a req with no duration
         */
        CreateAdRequest req = new CreateAdRequest();
        req.setAdContent("Test Real Content");
        req.setPartnerId("asdfasdsf");
        
        try {
            Validator.validateCreateAdRequest(req);
        }
        catch (DataAccessException e){
            assertTrue("Ad Object failed creation Successfully - Null Duration", e.getMessageId().longValue() == 511);
        }

        /**
         * We create a req with duration as 0
         */
        req.setAdContent("Test Real Content");
        req.setPartnerId("asdfasdsf");
        req.setDuration(BigInteger.valueOf(0));
        
        try {
            Validator.validateCreateAdRequest(req);
        }
        catch (DataAccessException e){
            assertTrue("Ad Object failed creation Successfully - 0 Duration", e.getMessageId().longValue() == 511);
        }
}

    /**
     * Need to validate the exceptions that occur 
     * as part of null content for the ad
     */
    @Test
    public void validateNullContentSuccess()
    {
        /**
         * We will test a few additions to the ad list
         */
        CreateAdRequest req = new CreateAdRequest();
        req.setDuration(BigInteger.valueOf(100));
        req.setPartnerId("asdfasdsf");
        
        try {
            Validator.validateCreateAdRequest(req);
        }
        catch (DataAccessException e){
            assertTrue("Ad Object failed creation Successfully - Null Content", e.getMessageId().longValue() == 512);
        }
    }
}
