package com.comcast.admgmt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Properties;


/**
 * The Utility class to read the configurations from the specified properties
 * file.
 *
 */
public class ConfigurationManager
{

    /**
     * The logger.
     */
    private static Logger logger = Logger.getInstance();
    /**
     * The properties.
     */
    private static Properties  propSys = new Properties();
    /**
	 *
	 */
    private final static String strConfigFileName = Constants.PROP_BASE_FILENAME;

    /**
     * Maintains a timer to see when the config file was last read. Will not
     * attempt a read if last read was less than 10 seconds.
     */
    private static long lastCallTime      = 0;
    /**
     * Maintains the last modified time stamp of the properties file. Will not
     * read if the last modified timestamp is just the same as the previous
     * read.
     */
    private static long  lastModified      = 0;

    /**
     * Loads the proerties.
     *
     * @param fileProperty
     *            The file containing properties.
     * @throws IOException
     * @throws IOException
     * @throws Exception
     *             If error happens while reading the file.
     */
    public static void load(final File fileProperty) throws IOException,
            FileNotFoundException
    {
        logger.debug(ConfigurationManager.class, "Reloading " + strConfigFileName);
        synchronized (propSys)
        {
            InputStream fisPropFile = null;
            try
            {
                fisPropFile = new FileInputStream(fileProperty);
                propSys.clear();
                propSys.load(fisPropFile);

            }
            catch (FileNotFoundException objFNF)
            {
                throw new FileNotFoundException("File not found" + objFNF.getMessage());
            }
            catch (IOException objIOE)
            {
                throw new IOException("Error reading the propery" + objIOE.getMessage());
            }
            finally
            {
                if (fisPropFile != null)
                {
                    try
                    {
                        fisPropFile.close();
                    }
                    catch (IOException objIOE)
                    {
                        logger.error(ConfigurationManager.class, "Error closing file : " + objIOE);
                    }
                }
            }
        }
    }

    /**
     * Gets a specific property value
     *
     * @param strKey
     *            The key
     * @return The value
     * @throws IOException
     * @throws FileNotFoundException
     * @throws URISyntaxException
     * @throws Exception
     *             If error occurs
     */
    public static String getProperty(String strKey)
    {
    	logger.debug(ConfigurationManager.class, "start : getProperty : "+ strKey);
        
        long timeNow = new Date().getTime();
        long timeInterval = timeNow - lastCallTime;

        if (timeInterval > 300000)
        {
            // > 5 minutes
            // Update the last read timestamp
            lastCallTime = timeNow;
            // Now check if the file has been modified

            URI uri;
            try
            {
               	logger.debug(ConfigurationManager.class, "loading file : "+ strKey);
                
                uri = Thread.currentThread().getContextClassLoader().getResource(strConfigFileName).toURI();
                
//               	logger.info(ConfigurationManager.class, "loading file : "+ strKey);

                File file = new File(uri);
                long currentTimeStamp = file.lastModified();
            	
                if (lastModified != currentTimeStamp)
                {
                    lastModified = currentTimeStamp;
                    try
                    {
                        load(file);
                    }
                    catch (FileNotFoundException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            catch (URISyntaxException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return Util.nullToEmptyString(propSys.getProperty(strKey, ""));
    }
}
