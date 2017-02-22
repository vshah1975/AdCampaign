package com.comcast.admgmt.exception;

import java.math.BigInteger;

import com.comcast.admgmt.util.Logger;

@SuppressWarnings("serial")
public class DataAccessException extends Exception
{
	private static Logger logger = Logger.getInstance(  );

	private Message message;
	private String auditLogInfo = "";
	private boolean wasLogged = false;


	/**********************************************************************************
	 *
	 * @param message
	 * @param throwable
	 **********************************************************************************/
	public DataAccessException( Message message, Throwable throwable )
	{
		super( throwable );
		this.message = message;
	}


	/***********************************************************************************
	 *
	 * @param dvpException
	 * @param username
	 *********************************************************************************/
	public DataAccessException( Message message)
	{
		this.message = message;
	}

    /**
     * @return the auditLogInfo
     */
    public String getAuditLogInfo()
    {
        return auditLogInfo;
    }

    /**
     * @param auditLogInfo the auditLogInfo to set
     */
    public void setAuditLogInfo(String auditLogInfo)
    {
        this.auditLogInfo = auditLogInfo;
    }

    public void setExtraLogInfo( String extraLogInfo )
	{
	}
	
	public BigInteger getMessageId()
	{
		return this.message.getId();
	}

	public String getMessageText()
	{
		return this.message.getText();
	}

	public String getMessageFaultCode()
	{
		return this.message.getFaultCodeConstant();
	}

	/*************************************************************************************
	 *
	 *
	 ***********************************************************************************/
	public void log()
	{
		if( this.wasLogged )
			return;

		StringBuffer s = new StringBuffer( 80 );
		s.append( "id = " ).append( this.getMessageId()).append( " message = " ).append( this.getMessageText());

		if( super.getCause() != null )
			s.append( "root cause = " ).append( super.getCause() );

		logger.error(DataAccessException.class, s.toString());
		this.wasLogged = true;
	}


	/*****************************************************************************************
	 *
	 *****************************************************************************************/
	public static class Message
	{
		public static final String FAULT_CODE_CLIENT = "Client";
		public static final String FAULT_CODE_SERVER = "Server";

		public static final Message DB_FAILURE = new Message( "50", "System failure", FAULT_CODE_SERVER );
		public static final Message NO_RESULTS_FOUND = new Message( "100", "No results found", FAULT_CODE_CLIENT );
		public static final Message INVALID_QUERY_PARAMS = new Message( "101", "Inbound query parameters are invalid", FAULT_CODE_CLIENT );
		public static final Message ERROR_ACTIVE_CAMPAIGN_EXISTS = new Message("502", "There is already another active campaign", FAULT_CODE_SERVER);

		private BigInteger id;
		private String text;
		private String faultCodeConstant;


		/**************************************************************************************
		 *
		 * @param id
		 * @param text
		 * @param faultCodeConstant
		 *************************************************************************************/
		public Message( String id, String text, String faultCodeConstant )
		{
			this.id = new BigInteger( id );
			this.text = text;
			this.faultCodeConstant = faultCodeConstant;
		}


		public String getText()
		{
			return this.text;
		}


		public BigInteger getId()
		{
			return this.id;
		}


		public String getFaultCodeConstant()
		{
			return this.faultCodeConstant;
		}
	}
}
