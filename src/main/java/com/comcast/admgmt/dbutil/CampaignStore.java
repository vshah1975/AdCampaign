package com.comcast.admgmt.dbutil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import com.comcast.admgmt.framework.model.AdInfo;
import com.comcast.admgmt.util.Logger;

public class CampaignStore
{
    private static CampaignStore store     = new CampaignStore();
    private static Logger logger    = Logger.getInstance();

    private static ConcurrentHashMap<String, Vector<AdInfo>> campaigns;
    
    private CampaignStore()
    {
        campaigns = new ConcurrentHashMap<String, Vector<AdInfo>>();
    }
    
    public static CampaignStore getInstance() 
    {
        return store;
    }

    /**
     * Retrieve the active campaign for the partner
     * 
     * @param partnerId
     * @return
     */
    public static AdInfo getActiveCampaign(String partnerId)
    {
        Vector<AdInfo> adList = campaigns.get(partnerId);

        if (adList != null)
        {
            for (AdInfo ad : adList)
                try
                {
                    logger.debug(CampaignStore.class, "getActiveCampaign: Campaign Exp Time: "+(new SimpleDateFormat("MM-dd-yyyy HH:mm:ss")).parse(ad.getAdExpiration()).getTime() + " Current Time: "+Calendar.getInstance().getTimeInMillis());
                    if ((new SimpleDateFormat("MM-dd-yyyy HH:mm:ss")).parse(ad.getAdExpiration()).getTime() > Calendar.getInstance().getTimeInMillis())
                        return ad;
                }
                catch (ParseException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return null;
    }

    /**
     * Retrieve the active campaign for the partner
     * 
     * @param partnerId
     * @return
     */
    public static Vector<AdInfo> getAllCampaigns(String partnerId)
    {
        Vector<AdInfo> adList = campaigns.get(partnerId) ;
        
        if (adList == null)
            adList = new Vector<AdInfo>();
        
        return adList;
    }

    /**
     * Retrieve campaigns across all partners
     * 
     * @return
     */
    public static Vector<AdInfo> getAllCampaigns()
    {
        Vector<AdInfo> adList = new Vector<AdInfo>();

        for (Entry<String, Vector<AdInfo>> entry : campaigns.entrySet())
        {
            Vector<AdInfo> list = entry.getValue();
            adList.addAll(list);
        }

        return adList;
    }

    /**
     * Add a new campaign to the pool
     * 
     * @param adinfo
     */
    public static void createCampaign(AdInfo adinfo)
    {
        String partnerId = adinfo.getPartnerId();

        if (campaigns.get(partnerId) == null)
            campaigns.put(partnerId, new Vector<AdInfo>());

        /**
         * We need to ensure that the expiration time is
         * setup correctly.
         */
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.setTimeInMillis((expirationDate.getTimeInMillis() + adinfo.getDuration().intValue() * 1000));

        adinfo.setAdExpiration((new SimpleDateFormat("MM-dd-yyyy HH:mm:ss")).format(expirationDate.getTime()));
        campaigns.get(partnerId).add(adinfo);
    }
    
    public static void emptyCampaigns()
    {
        campaigns.clear();
    }
}
