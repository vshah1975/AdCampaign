package com.comcast.admgmt.service;

import java.util.Vector;

import com.comcast.admgmt.dao.AdCampaignDAO;
import com.comcast.admgmt.exception.DataAccessException;
import com.comcast.admgmt.exception.DataAccessException.Message;
import com.comcast.admgmt.framework.model.AdInfo;
import com.comcast.admgmt.framework.model.CreateAdRequest;
import com.comcast.admgmt.framework.model.GetAdListRequest;
import com.comcast.admgmt.util.Logger;
import com.comcast.admgmt.util.Util;
import com.comcast.admgmt.validators.Validator;

/**
 * This class is responsible for being the orchestrator of
 * business functionality. It exposes methods that allow
 * creation and querying of ad campaigns.
 * 
 * @author vshah
 */
public class AdCampaignService
{
    private static Logger logger = Logger.getInstance();

    /**
     * In order to create an ad, we must have the ability
     * to: 1. Validate the request 2. Figure out if the
     * partner has other active campaigns 3. If all
     * succeeds, create a campaign.
     * 
     * In order to do all those steps, we have created a
     * method that provides the necessary abstraction.
     * 
     * @param createAdRequest
     * @throws DataAccessException
     */
    public static void createAd(CreateAdRequest createAdRequest) throws DataAccessException
    {
        logger.debug(AdCampaignService.class, "createAd: Starting to create an Ad");

        /**
         * first things first - we need to validate if the
         * request has all the correct parameters. If not,
         * then do not create an ad campaign for the
         * partner.
         */
        Validator.validateCreateAdRequest(createAdRequest);

        /**
         * Now check if the partner has any other active
         * campaigns
         */
        if (AdCampaignDAO.getActiveCampaign(createAdRequest.getPartnerId()) != null)
            throw new DataAccessException(Message.ERROR_ACTIVE_CAMPAIGN_EXISTS);

        /**
         * Create a new campaign
         */
        logger.debug(AdCampaignService.class, "createAd: Total Ads before adding: "
                + AdCampaignDAO.getCampaigns(createAdRequest.getPartnerId()).size());

        AdCampaignDAO.createAdCampaign(createAdRequest.getPartnerId(), createAdRequest.getDuration().intValue(),
                createAdRequest.getAdContent());

        logger.debug(AdCampaignService.class, "createAd: Ad Created. Total Ads: "
                + AdCampaignDAO.getCampaigns(createAdRequest.getPartnerId()).size());

        logger.debug(AdCampaignService.class, "createAd: Finished creating an Ad");
    }

    /**
     * Method to retrieve campaigns by partner
     * 
     * @param partnerId
     * @return
     * @throws DataAccessException
     */
    public static Vector<AdInfo> getCampaignList(String partnerId) throws DataAccessException
    {
        logger.debug(AdCampaignService.class, "getCampaignList: Starting to get ad List");

        /**
         * Get campaign List
         */
        Vector<AdInfo> campaignList = null;
        if (Util.nullToEmptyString(partnerId).equalsIgnoreCase(""))
            campaignList = AdCampaignDAO.getCampaigns();
        else
            campaignList = AdCampaignDAO.getCampaigns(partnerId);

        logger.debug(AdCampaignService.class, "getCampaignList: Ad Created. Total Ads: " + campaignList.size());

        logger.debug(AdCampaignService.class, "getCampaignList: Finished creating an Ad");
        return campaignList;
    }
}
