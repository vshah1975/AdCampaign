package test.com.comcast.admgmt.dao;

import com.comcast.admgmt.dao.AdCampaignDAO;
import com.comcast.admgmt.framework.model.AdInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Created on 2/15/17.
 */
public class AdCampaignDAOTest
{
    String addCampaignJsonSuccess = "{\"status\":\"Success\",\"statusCode\":\"200\"}";
    
    @Test
    public void createAdSuccessfully() throws Exception
    {
        /**
         * We will test a few additions to the ad list
         */
        AdCampaignDAO.clearCampaignStore();
        AdCampaignDAO.createAdCampaign("1234", 30, "This is a test");
        assertTrue("Created Ad Campaign", AdCampaignDAO.getActiveCampaign("1234").getDuration().intValue() == 30);
        AdCampaignDAO.createAdCampaign("432334", 50, "This is a Second Test");        
        assertTrue("Second Ad added", AdCampaignDAO.getActiveCampaign("432334").getDuration().intValue() == 50);
        AdCampaignDAO.createAdCampaign("23232323", 80, "This is a third test");
        assertTrue("Third Ad Campaign", AdCampaignDAO.getActiveCampaign("23232323").getDuration().intValue() == 80);
    }

    @Test
    public void getCampaignListForPartner() throws Exception
    {
        AdCampaignDAO.clearCampaignStore();
        AdCampaignDAO.createAdCampaign("1234", 30, "This is a test");
        Vector<AdInfo> adList = AdCampaignDAO.getCampaigns("1234");
        assertTrue("Campaign List Found Successfully", adList.size() > 0);
    }

    @Test
    public void getCampaignListForPartnerFailure() throws Exception
    {
        AdCampaignDAO.clearCampaignStore();
        AdCampaignDAO.createAdCampaign("1234", 30, "This is a test");
        Vector<AdInfo> adInfo = AdCampaignDAO.getCampaigns("4321");
        assertTrue("No Partner found", adInfo.size() == 0);
    }

    @Test
    public void getFullCampaignListSuccess() throws Exception
    {
        AdCampaignDAO.clearCampaignStore();
        AdCampaignDAO.createAdCampaign("123123", 30, "This is a test");
        AdCampaignDAO.createAdCampaign("234234", 50, "This is a Second Test");        
        AdCampaignDAO.createAdCampaign("345345", 70, "This is a third Test");        
        AdCampaignDAO.createAdCampaign("456456", 90, "This is a fourth Test");        
        AdCampaignDAO.createAdCampaign("567567", 110, "This is a fifth Test");        
        Vector<AdInfo> adInfo = AdCampaignDAO.getCampaigns();
        System.out.println(adInfo.size());
        assertTrue("Full Campaign List found", adInfo.size() == 5);
    }

}