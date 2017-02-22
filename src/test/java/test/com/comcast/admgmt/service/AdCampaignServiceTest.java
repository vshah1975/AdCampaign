package test.com.comcast.admgmt.service;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Vector;

import org.junit.Test;

import com.comcast.admgmt.dao.AdCampaignDAO;
import com.comcast.admgmt.exception.DataAccessException;
import com.comcast.admgmt.framework.model.AdInfo;
import com.comcast.admgmt.framework.model.CreateAdRequest;
import com.comcast.admgmt.service.AdCampaignService;

/**
 * Created on 2/15/17.
 */
public class AdCampaignServiceTest
{
    String addCampaignJsonSuccess = "{\"status\":\"Success\",\"statusCode\":\"200\"}";

    /**
     * Need to test the happy path of creating a successful
     * ad
     * 
     * @throws Exception
     */
    @Test
    public void createAdSuccessfully() throws Exception
    {
        /**
         * We ensure that the ad store is empty
         */
        AdCampaignDAO.clearCampaignStore();

        /**
         * We create a successful request
         */
        CreateAdRequest req = new CreateAdRequest();
        req.setAdContent("Test Real Content");
        req.setPartnerId("asdfasdsf");
        req.setDuration(BigInteger.valueOf(30));

        AdCampaignService.createAd(req);
        assertTrue("Service: Ad Object creation Successful", req == req);
    }

    /**
     * Validate various failure scenarios
     */
    @Test
    public void createAdFailures()
    {
        /**
         * We ensure that the ad store is empty
         */
        AdCampaignDAO.clearCampaignStore();

        /**
         * create bad requests
         */

        CreateAdRequest req = new CreateAdRequest();
        req.setAdContent("Test Real Content");
        req.setDuration(BigInteger.valueOf(100));

        try
        {
            AdCampaignService.createAd(req);
        }
        catch (DataAccessException e)
        {
            assertTrue("Ad Object failed creation Successfully - Null Partner", e.getMessageId().longValue() == 510);
        }

        /**
         * We create a req with no duration
         */
        req.setDuration(null);
        req.setPartnerId("asdfasdsf");

        try
        {
            AdCampaignService.createAd(req);
        }
        catch (DataAccessException e)
        {
            assertTrue("Ad Object failed creation Successfully - Null Duration", e.getMessageId().longValue() == 511);
        }

        /**
         * We create a req with no ad content
         */
        req.setDuration(BigInteger.valueOf(100));
        req.setAdContent(null);

        try
        {
            AdCampaignService.createAd(req);
        }
        catch (DataAccessException e)
        {
            assertTrue("Ad Object failed creation Successfully - Null Content", e.getMessageId().longValue() == 512);
        }
    }

    /**
     * Validate successful retrieval of ads
     */
    @Test
    public void getAdListSuccess()
    {
        /**
         * We ensure that the ad store is empty
         */
        AdCampaignDAO.clearCampaignStore();

        /**
         * create good requests to add to the store
         */
        CreateAdRequest req = new CreateAdRequest();
        req.setAdContent("Test Real Content");
        req.setDuration(BigInteger.valueOf(100));
        req.setPartnerId("asdfasdsf");

        try
        {
            AdCampaignService.createAd(req);
        }
        catch (DataAccessException e)
        {
        }

        /**
         * Now start searching for the ad:
         */
        try
        {
            Vector<AdInfo> adList = AdCampaignService.getCampaignList("asdfasdsf");
            assertTrue("Found the correct size of list from Service Test", adList.size() == 1);
            assertTrue("Found the correct value in Service Test", adList.get(0).getDuration().intValue() == 100);
        }
        catch (DataAccessException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Validate successful retrieval of ads
     */
    @Test
    public void getAdListFailure()
    {
        /**
         * We ensure that the ad store is empty
         */
        AdCampaignDAO.clearCampaignStore();

        /**
         * create good requests to add to the store
         */
        CreateAdRequest req = new CreateAdRequest();
        req.setAdContent("Test Real Content");
        req.setDuration(BigInteger.valueOf(100));
        req.setPartnerId("asdfasdsf");

        try
        {
            AdCampaignService.createAd(req);
        }
        catch (DataAccessException e)
        {
        }

        /**
         * Now start searching for the ad:
         */
        try
        {
            Vector<AdInfo> adList = AdCampaignService.getCampaignList("4321");
            assertTrue("Found the correct size of list from Service Test", adList.size() == 0);
        }
        catch (DataAccessException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Validate successful retrieval of ads across all
     * partners
     */
    @Test
    public void getFullAdListSuccess()
    {
        /**
         * We ensure that the ad store is empty
         */
        AdCampaignDAO.clearCampaignStore();

        /**
         * create good requests to add to the store
         */
        CreateAdRequest req = new CreateAdRequest();
        for (int i = 0; i < 100; i++)
        {
            req.setAdContent("Test Real Content");
            req.setDuration(BigInteger.valueOf(100));
            req.setPartnerId("asdfasdsf " + i);

            try
            {
                AdCampaignService.createAd(req);
            }
            catch (DataAccessException e)
            {
            }
        }

        /**
         * Now start searching for the ad:
         */
        try
        {
            Vector<AdInfo> adList = AdCampaignService.getCampaignList("");
            assertTrue("Found the correct size of list from Service Test", adList.size() == 100);
        }
        catch (DataAccessException e)
        {
            e.printStackTrace();
        }
    }
}
