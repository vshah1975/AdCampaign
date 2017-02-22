package com.comcast.admgmt.util;

import java.util.Calendar;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;


/**
 * This is a wrapper around the Log4J.
 *
 * @author Viral Shah
 *
 */
public class Logger
{
    private static Object lock = new Object();
    private static Logger logger = null;
    private static LoggerContext loggerContext = null;
    private static Calendar lastUpdatedCal = Calendar.getInstance();

    /**
     * This is the private constructor to the logger class. The constructor is
     * private since we do not want to allow anyone to create instances of
     * this class directly.
     */
    private Logger() {
        //do nothing for now.
    }

    public static Logger getInstance()
    {
        if (null == logger)
        {
            synchronized (lock)
            {
                if (null == logger)
                {
                    logger = new Logger();
                    loggerContext = (LoggerContext)LogManager.getContext();
                    configure();
                    lastUpdatedCal = Calendar.getInstance();
                }
            }
        }

        /** Re-read the prop files in case its been greater than configured time **/
        /** not leaving this to the config file for the moment **/
        if (lastUpdatedCal.getTimeInMillis() + 300*1000 < Calendar.getInstance().getTimeInMillis())
            configure();

        return logger;
    }

    /**
     * Configure log4j using log4j.properties file which must be in the
     * system classpath
     */
    private static void configure()
    {
        try
        {
            System.out.println("Original Config Name: " + loggerContext.getConfiguration().getName());

            java.io.InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.PROP_LOG4J_FILENAME);
            
            ConfigurationSource isource = new ConfigurationSource(is);           
            
            XmlConfiguration newConfig = new XmlConfiguration(isource);
            loggerContext.start(newConfig);
            
            System.out.println("CTX isReady: " + loggerContext.isStarted());
            
            Map<String, Appender> mapApps = loggerContext.getConfiguration().getAppenders();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Logs debug information to the log file.
     *
     * @param context the caller class.
     * @param message the message that needs to be logged.
     */
    @SuppressWarnings({ "rawtypes" })
    public void debug(Class context, String message) {
        loggerContext.getLogger(context.getName()).debug(message);
    }

    /**
     * Logs warn information to the log file.
     *
     * @param context the caller class.
     * @param message the message that needs to be logged.
     */
    @SuppressWarnings({ "rawtypes" })
    public void warn(Class context, String message) {
        loggerContext.getLogger(context.getName()).warn(message);
    }

    /**
     * Logs info information to the log file.
     *
     * @param context the caller class.
     * @param message the message that needs to be logged.
     */
    @SuppressWarnings({ "rawtypes" })
    public void info(Class context, String message) {
        loggerContext.getLogger(context.getName()).info(message);
    }

    /**
     * Logs error information to the log file.
     *
     * @param context the caller class.
     * @param message the message that needs to be logged.
     */
    @SuppressWarnings({ "rawtypes" })
    public void error(Class context, String message) {
        loggerContext.getLogger(context.getName()).error(message);
    }

    /**
     * Logs error information to the log file
     * and print stacktrace.
     *
     * @param context the caller class.
     * @param message the message that needs to be logged.
     */
    @SuppressWarnings({ "rawtypes" })
    public void error(Class context, String message, Throwable ex){
        loggerContext.getLogger(context.getName()).error(message, ex);
    }
}
