package test.com.comcast.admgmt.framework;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.transport.local.LocalConduit;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.comcast.admgmt.dao.AdCampaignDAO;
import com.comcast.admgmt.exception.DataAccessException;
import com.comcast.admgmt.framework.AdCampaignMgmtRestAPI;
import com.comcast.admgmt.framework.model.CreateAdRequest;
import com.comcast.admgmt.model.ResponseObject;
import com.comcast.admgmt.util.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class AdCampaignMgmtRestAPITest
{
    private static Logger       logger           = Logger.getInstance();
    ResponseObject              responseObj;

    private final static String ENDPOINT_ADDRESS = "local://AdCampaignMgmt";
    private static Server       server;
    private WebClient           client;
    private static List<Object> providers        = new ArrayList<Object>();

    @BeforeClass
    public static void initialize() throws Exception
    {
        startServer();
    }

    private static void startServer() throws Exception
    {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(AdCampaignMgmtRestAPI.class);
        providers.add(new JacksonJaxbJsonProvider());
        // add custom providers if any
        sf.setProviders(providers);

        sf.setResourceProvider(AdCampaignMgmtRestAPI.class,
                new SingletonResourceProvider(new AdCampaignMgmtRestAPI(), true));
        sf.setAddress(ENDPOINT_ADDRESS);

        server = sf.create();
    }

    @AfterClass
    public static void destroy() throws Exception
    {
        server.stop();
        server.destroy();
    }

    @Before
    public void setUp() throws Exception
    {
        client = WebClient.create(ENDPOINT_ADDRESS, providers);
        WebClient.getConfig(client).getRequestContext().put(LocalConduit.DIRECT_DISPATCH, Boolean.TRUE);
    }

    @After
    public void tearDown() throws Exception
    {
        client.close();
    }

    /**
     * Validate User POST Method - Valid Ad creation
     * 
     * @param validateUserRequest
     * @return
     * @throws TascException
     */
    @Test
    public void createAdSuccess()
    {
        /**
         * We ensure that the ad store is empty
         */
        AdCampaignDAO.clearCampaignStore();

        /**
         * create good requests to add to the store
         */
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON);
        client.path("/services/ad");
        Response resp = client
                .post("{\"partner_id\":\"123123\",\"duration\":20,\"ad_content\":\"This is a new test\"}");
        assertTrue("Create Ad: Got a success", resp.getStatus() == 200);
    }

    /**
     * Failed Ad creation
     * 
     * @param validateUserRequest
     * @return
     * @throws TascException
     */
    @Test
    public void createAdFailureByPartner()
    {
        /**
         * We ensure that the ad store is empty
         */
        AdCampaignDAO.clearCampaignStore();

        /**
         * create good requests to add to the store
         */
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON);
        client.path("/services/ad");
        Response resp = client.post("{\"duration\":20,\"ad_content\":\"This is a new test\"}");
        assertTrue("Create Ad: Got a failure", resp.readEntity(String.class)
                .equalsIgnoreCase("{\"status\":\"Partner id cannot be blank\",\"statusCode\":\"510\"}"));
    }

    /**
     * Failed Ad creation
     * 
     * @param validateUserRequest
     * @return
     * @throws TascException
     */
    @Test
    public void createAdFailureByContent()
    {
        /**
         * We ensure that the ad store is empty
         */
        AdCampaignDAO.clearCampaignStore();

        /**
         * create good requests to add to the store
         */
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON);
        client.path("/services/ad");
        Response resp = client.post("{\"partner_id\":\"123123\",\"duration\":20}");
        assertTrue("Create Ad: Got a failure", resp.readEntity(String.class)
                .equalsIgnoreCase("{\"status\":\"Content cannot be blank\",\"statusCode\":\"512\"}"));
    }

    /**
     * Failed Ad creation
     * 
     * @param validateUserRequest
     * @return
     * @throws TascException
     */
    @Test
    public void createAdFailureByDuration()
    {
        /**
         * We ensure that the ad store is empty
         */
        AdCampaignDAO.clearCampaignStore();

        /**
         * create good requests to add to the store
         */
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON);
        client.path("/services/ad");
        Response resp = client.post("{\"partner_id\":\"123123\",\"ad_content\":\"This is a new test\"}");
        assertTrue("Create Ad: Got a failure", resp.readEntity(String.class)
                .equalsIgnoreCase("{\"status\":\"Ad Duration cannot be blank\",\"statusCode\":\"511\"}"));
    }

    @Test
    public void getCampaigns() throws JsonProcessingException, IOException
    {
        /**
         * We ensure that the ad store is empty
         */
        AdCampaignDAO.clearCampaignStore();

        /**
         * create good requests to add to the store
         */
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON);
        client.path("/services/ad");
        Response resp = client
                .post("{\"partner_id\":\"123123\",\"duration\":20,\"ad_content\":\"This is a new test\"}");
        assertTrue("Create Ad: Got a success", resp.getStatus() == 200);

        client.replacePath("/services/ad/123123");
        resp = client.get();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(resp.readEntity(String.class));
        
        assertTrue("Read the list of active campaigns", node.get("status").asText().equalsIgnoreCase("Success"));
    }

    @Test
    public void getCampaignsForWrongPartner() throws JsonProcessingException, IOException
    {
        /**
         * We ensure that the ad store is empty
         */
        AdCampaignDAO.clearCampaignStore();

        /**
         * create good requests to add to the store
         */
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON);
        client.path("/services/ad");
        Response resp = client
                .post("{\"partner_id\":\"123123\",\"duration\":20,\"ad_content\":\"This is a new test\"}");
        assertTrue("Create Ad: Got a success", resp.getStatus() == 200);

        client.replacePath("/services/ad/asdasd");
        resp = client.get();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(resp.readEntity(String.class));
        
        assertTrue("Read the list of active campaigns", node.get("status").asText().equalsIgnoreCase("Success"));
    }

    @Test
    public void getAllCampaigns() throws JsonProcessingException, IOException
    {
        /**
         * We ensure that the ad store is empty
         */
        AdCampaignDAO.clearCampaignStore();

        /**
         * create good requests to add to the store
         */
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON);
        client.path("/services/ad");
        Response resp = client.post("{\"partner_id\":\"123123\",\"duration\":20,\"ad_content\":\"This is a new test\"}");
        assertTrue("Create Ad: Got a success", resp.getStatus() == 200);
        resp = client.post("{\"partner_id\":\"asdfasdf\",\"duration\":20,\"ad_content\":\"This is a new test\"}");
        assertTrue("Create Ad: Got a success", resp.getStatus() == 200);

        client.replacePath("/services/ad");
        resp = client.get();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(resp.readEntity(String.class));
        
        assertTrue("Read the list of active campaigns", node.get("status").asText().equalsIgnoreCase("Success"));
    }
}
