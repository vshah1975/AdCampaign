package com.comcast.admgmt.model;

import com.comcast.admgmt.framework.model.AdList;

/**
 * @author USER
 *
 */
public class ResponseObject
{
    String status;
    String statusCode;

    AdList adList;

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }

    public AdList getAdList()
    {
        return adList;
    }

    public void setAdList(AdList adList)
    {
        this.adList = adList;
    }

}
