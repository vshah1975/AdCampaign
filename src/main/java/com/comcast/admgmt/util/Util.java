package com.comcast.admgmt.util;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;

import com.comcast.admgmt.exception.DataAccessException;
import com.comcast.admgmt.exception.DataAccessException.Message;

public class Util
{
    private static Logger logger = Logger.getInstance();

    /**
     * This method is used to make strings null safe.
     *
     * @param test
     *            String
     * @return String
     */
    public static String nullToEmptyString(String test)
    {
        if (test == null)
            return "";
        else
            return test.trim();
    }

    /**
     * 
     * @param rootNode
     * @return
     */
    public static String serializeXML(Object rootNode)
    {
        try
        {
            JAXBContext context = JAXBContext.newInstance(rootNode.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false); // pretty
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-1"); // specify
                                                                       // encoding
            StringWriter writer = new StringWriter();
            // output xml to outputstream.
            marshaller.marshal(rootNode, writer);

            return writer.toString();
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 
     * @param constantName
     * @throws ReportingException
     */
    public static void executeDataAccessException(String constantName) throws DataAccessException
    {
        executeDataAccessException(constantName, null);
    }

    /**
     * 
     * @param constantName
     * @param statusMsg
     * @throws TascException
     * @throws ReportingException
     */
    public static void executeDataAccessException(String constantName, String statusMsg) throws DataAccessException
    {
        StringTokenizer strTok = new StringTokenizer(ConfigurationManager.getProperty(constantName), "|");
        String errorCode = strTok.nextToken();
        String errorMsg = strTok.nextToken();
        if (StringUtils.isNotBlank(statusMsg))
            errorMsg += statusMsg;
        logger.error(Util.class, "executeDataAccessException: Error occurred: " + errorMsg);
        throw new DataAccessException(new Message(errorCode, errorMsg, Message.FAULT_CODE_SERVER));
    }
}
