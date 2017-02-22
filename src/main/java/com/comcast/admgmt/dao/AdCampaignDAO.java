package com.comcast.admgmt.dao;

import java.math.BigInteger;
import java.util.Vector;

import com.comcast.admgmt.dbutil.CampaignStore;
import com.comcast.admgmt.exception.DataAccessException;
import com.comcast.admgmt.framework.model.AdInfo;
import com.comcast.admgmt.util.Logger;

/**
 * In order to add a new campaign to the data store, a few
 * methods need to be managed that allow for read and write
 * access. This class is to manage all the read/write to the
 * data store.
 * 
 * @author vshah
 *
 */
public class AdCampaignDAO
{
    private static Logger logger = Logger.getInstance();

    /**
     * 
     * @param partnerId
     * @param duration
     * @param adContent
     */
    public static void createAdCampaign(String partnerId, int duration, String adContent) throws DataAccessException
    {
        logger.debug(AdCampaignDAO.class, "createAdCampaign: Starting to create campaign for Partner: " + partnerId);

        /**
         * Create an AdInfo object
         */
        AdInfo ad = new AdInfo();
        ad.setAdContent(adContent);
        ad.setDuration(BigInteger.valueOf(duration));
        ad.setPartnerId(partnerId);

        CampaignStore store = CampaignStore.getInstance();
        store.createCampaign(ad);

        logger.debug(AdCampaignDAO.class, "createAdCampaign: Finished creating campaign for Partner: " + partnerId);
    }

    /**
     * 
     * @param partnerId
     */
    public static AdInfo getActiveCampaign(String partnerId)
    {
        logger.debug(AdCampaignDAO.class,
                "getActiveCampaign: Starting to get the active campaign for Partner: " + partnerId);

        CampaignStore store = CampaignStore.getInstance();
        AdInfo ad = store.getActiveCampaign(partnerId);

        logger.debug(AdCampaignDAO.class, "getActiveCampaign: Found Ad: " + ad);

        logger.debug(AdCampaignDAO.class,
                "getActiveCampaign: Finished finding the active campaign for Partner: " + partnerId);
        return ad;
    }

    /**
     * 
     * @param partnerId
     */
    public static Vector<AdInfo> getCampaigns(String partnerId)
    {
        logger.debug(AdCampaignDAO.class, "getCampaigns: Starting to get list of campaigns for Partner: " + partnerId);

        CampaignStore store = CampaignStore.getInstance();
        Vector<AdInfo> adList = store.getAllCampaigns(partnerId);

        logger.debug(AdCampaignDAO.class, "getCampaigns: List of Campaigns: " + adList);

        logger.debug(AdCampaignDAO.class, "getCampaigns: Finished finding list of campaigns for Partner: " + partnerId);
        return adList;
    }

    public static Vector<AdInfo> getCampaigns()
    {
        logger.debug(AdCampaignDAO.class, "getCampaigns: Starting to get the full list of campaigns");

        CampaignStore store = CampaignStore.getInstance();
        Vector<AdInfo> adList = store.getAllCampaigns();

        logger.debug(AdCampaignDAO.class, "getCampaigns: List of Campaigns: " + adList);

        logger.debug(AdCampaignDAO.class, "getCampaigns: Finished finding the full list of campaigns");
        return adList;
    }
    
    public static void clearCampaignStore()
    {
        logger.debug(AdCampaignDAO.class, "clearCampaignStore: Starting to empty campaigns");

        CampaignStore store = CampaignStore.getInstance();
        store.emptyCampaigns();

        logger.debug(AdCampaignDAO.class, "clearCampaignStore: Finished emptying campaigns");
        
    }
}
