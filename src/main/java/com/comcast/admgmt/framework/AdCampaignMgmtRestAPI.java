package com.comcast.admgmt.framework;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.comcast.admgmt.exception.DataAccessException;
import com.comcast.admgmt.framework.model.AdInfo;
import com.comcast.admgmt.framework.model.AdList;
import com.comcast.admgmt.framework.model.CreateAdRequest;
import com.comcast.admgmt.framework.model.GetAdListRequest;
import com.comcast.admgmt.model.ResponseObject;
import com.comcast.admgmt.service.AdCampaignService;
import com.comcast.admgmt.util.ConfigurationManager;
import com.comcast.admgmt.util.Constants;
import com.comcast.admgmt.util.Logger;
import com.comcast.admgmt.util.MyNullKeySerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Path("/services")
public class AdCampaignMgmtRestAPI
{
    private static Logger      logger = Logger.getInstance();
    ResponseObject             responseObj;

    @Context
    private HttpServletRequest httpServletRequest;

    /**
     * Validate User POST Method
     * 
     * @param validateUserRequest
     * @return
     * @throws TascException
     */
    @POST
    @Produces(
    { MediaType.APPLICATION_JSON })
    @Consumes(
    { MediaType.APPLICATION_JSON })

    @Path("/ad")
    public Response createAd(CreateAdRequest createAdRequest) throws DataAccessException
    {
        logger.debug(this.getClass(), "createAd: Start of method execution.");

        responseObj = new ResponseObject();
        try
        {
            AdCampaignService.createAd(createAdRequest);
        }
        catch (DataAccessException e)
        {
            responseObj.setStatusCode(e.getMessageId().toString());
            responseObj.setStatus(e.getMessageText());

            return this.writeResponse(responseObj);
        }
        logger.debug(this.getClass(), "createAd: End of method execution.");

        return executeSuccessResponse(responseObj);
    }

    @GET
    @Produces(
    { MediaType.APPLICATION_JSON })
    @Path("/ad/{partnerId}")
    public Response getCampaigns(@PathParam("partnerId") String partnerId) throws DataAccessException
    {
        logger.debug(this.getClass(), "getCampaigns: Start of method execution.");
        responseObj = new ResponseObject();
        try
        {
            Vector<AdInfo> campaignList = AdCampaignService.getCampaignList(partnerId);
            AdList adList = new AdList();
            adList.getAdInfo().addAll(campaignList);
            responseObj.setAdList(adList);
        }
        catch (DataAccessException e)
        {
            responseObj.setStatusCode(e.getMessageId().toString());
            responseObj.setStatus(e.getMessageText());
            return this.writeResponse(responseObj);
        }
        logger.debug(this.getClass(), "getCampaigns: End of method execution.");

        return executeSuccessResponse(responseObj);
    }

    @GET
    @Produces(
    { MediaType.APPLICATION_JSON })
    @Path("/ad")
    public Response getAllCampaigns() throws DataAccessException
    {
        logger.debug(this.getClass(), "getAllCampaigns: Start of method execution.");
        responseObj = new ResponseObject();
        try
        {
            Vector<AdInfo> campaignList = AdCampaignService.getCampaignList("");
            AdList adList = new AdList();
            adList.getAdInfo().addAll(campaignList);
            responseObj.setAdList(adList);
        }
        catch (DataAccessException e)
        {
            responseObj.setStatusCode(e.getMessageId().toString());
            responseObj.setStatus(e.getMessageText());
            return this.writeResponse(responseObj);
        }
        logger.debug(this.getClass(), "getAllCampaigns: End of method execution.");

        return executeSuccessResponse(responseObj);
    }

    /**
     * 
     * @param objToBeReturned
     * @return
     */
    private Response writeResponse(Object objToBeReturned)
    {
        logger.debug(this.getClass(), "writeResponse: starting to generate response");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(dateFormat);

        mapper.getSerializerProvider().setNullKeySerializer(new MyNullKeySerializer());

        String response = "";

        try
        {
            response = mapper.writeValueAsString(objToBeReturned);
        }
        catch (JsonMappingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ResponseBuilder builder = Response.ok(response);
        builder.type(MediaType.APPLICATION_JSON);

        logger.debug(this.getClass(), "writeResponse: Finished generating response");

        return builder.build();
    }

    /**
     * Add success response & return the same.
     * 
     * @param responseObject
     * @return
     */
    private Response executeSuccessResponse(ResponseObject responseObject)
    {
        StringTokenizer strTok = new StringTokenizer(ConfigurationManager.getProperty(Constants.SUCCESSRESPONSE), "|");
        responseObject.setStatusCode(strTok.nextToken());
        responseObject.setStatus(strTok.nextToken());

        return this.writeResponse(responseObject);
    }
}
