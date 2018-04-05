package com.tek.muleautomator.element;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.tek.muleautomator.util.MuleAutomatorConstants;
public class JMSElement {
	 public static class JMSQueueEventSource
	 {//done other2nd msg-selector
		public static String getActivityType() {
			return activityType;
		}
		public static void setActivityType(String activityType) {
			JMSQueueEventSource.activityType = activityType;
		}
		public static String getCONFIG_description() {
			return CONFIG_description;
		}
		public static void setCONFIG_description(String cONFIG_description) {
			CONFIG_description = cONFIG_description;
		}
		public String getCONFIG_JMSConnection() {
			return CONFIG_JMSConnection;
		}
		public void setCONFIG_JMSConnection(String cONFIG_JMSConnection) {
			CONFIG_JMSConnection = cONFIG_JMSConnection;
		}
		public String getCONFIG_destinationQueue() {
			return CONFIG_destinationQueue;
		}
		public void setCONFIG_destinationQueue(String cONFIG_destinationQueue) {
			CONFIG_destinationQueue = cONFIG_destinationQueue;
		}
		public String getCONFIG_messageType() {
			return CONFIG_messageType;
		}
		public void setCONFIG_messageType(String cONFIG_messageType) {
			CONFIG_messageType = cONFIG_messageType;
		}
		public String getCONFIG_acknowledgeMode() {
			return CONFIG_acknowledgeMode;
		}
		public void setCONFIG_acknowledgeMode(String cONFIG_acknowledgeMode) {
			CONFIG_acknowledgeMode = cONFIG_acknowledgeMode;
		}
		public int getCONFIG_maxSessions() {
			return CONFIG_maxSessions;
		}
		public void setCONFIG_maxSessions(int cONFIG_maxSessions) {
			CONFIG_maxSessions = cONFIG_maxSessions;
		}
		public String getMISC_sequencingKey() {
			return MISC_sequencingKey;
		}
		public void setMISC_sequencingKey(String mISC_sequencingKey) {
			MISC_sequencingKey = mISC_sequencingKey;
		}
		public String getMISC_customId() {
			return MISC_customId;
		}
		public void setMISC_customId(String mISC_customId) {
			MISC_customId = mISC_customId;
		}
		public String getADV_messageSelector() {
			return ADV_messageSelector;
		}
		public void setADV_messageSelector(String aDV_messageSelector) {
			ADV_messageSelector = aDV_messageSelector;
		}
		public String getADV_JMSApplicationProperties() {
			return ADV_JMSApplicationProperties;
		}
		public void setADV_JMSApplicationProperties(String aDV_JMSApplicationProperties) {
			ADV_JMSApplicationProperties = aDV_JMSApplicationProperties;
		}
		public String getADV_precreateTransactions() {
			return ADV_precreateTransactions;
		}
		public void setADV_precreateTransactions(String aDV_precreateTransactions) {
			ADV_precreateTransactions = aDV_precreateTransactions;
		}
		public String getADV_XATransactionManagerReference() {
			return ADV_XATransactionManagerReference;
		}
		public void setADV_XATransactionManagerReference(String aDV_XATransactionManagerReference) {
			ADV_XATransactionManagerReference = aDV_XATransactionManagerReference;
		}
		public int getADV_receiverTimeout() {
			return ADV_receiverTimeout;
		}
		public void setADV_receiverTimeout(int aDV_receiverTimeout) {
			ADV_receiverTimeout = aDV_receiverTimeout;
		}
		public Map<String, String> getOUT_JMSHeaders() {
			return OUT_JMSHeaders;
		}
		public void setOUT_JMSHeaders(Map<String, String> oUT_JMSHeaders) {
			OUT_JMSHeaders = oUT_JMSHeaders;
		}
		public Map<String, String> getOUT_JMSProperties() {
			return OUT_JMSProperties;
		}
		public void setOUT_JMSProperties(Map<String, String> oUT_JMSProperties) {
			OUT_JMSProperties = oUT_JMSProperties;
		}
		public Map<String, String> getOUT_otherProperties() {
			return OUT_otherProperties;
		}
		public void setOUT_otherProperties(Map<String, String> oUT_otherProperties) {
			OUT_otherProperties = oUT_otherProperties;
		}
		public Map<String, String> getOUT_body() {
			return OUT_body;
		}
		public void setOUT_body(Map<String, String> oUT_body) {
			OUT_body = oUT_body;
		}
		public Map<String, String> getOUT_dynamicProperties() {
			return OUT_dynamicProperties;
		}
		public void setOUT_dynamicProperties(Map<String, String> oUT_dynamicProperties) {
			OUT_dynamicProperties = oUT_dynamicProperties;
		}
		private static String activityType;
	    private static String CONFIG_description;
	    private String CONFIG_JMSConnection,CONFIG_destinationQueue,CONFIG_messageType,CONFIG_acknowledgeMode;
	    private int CONFIG_maxSessions;
	    private String MISC_sequencingKey,MISC_customId;
	    private String ADV_messageSelector, ADV_JMSApplicationProperties, ADV_precreateTransactions, ADV_XATransactionManagerReference;	 
	    private int ADV_receiverTimeout; 
	    private Map<String, String> OUT_JMSHeaders,OUT_JMSProperties,OUT_otherProperties,OUT_body,OUT_dynamicProperties;
	    public JMSQueueEventSource(Node node){
        	Element rootActivityElement = (Element)node;
        	this.CONFIG_description="This actvity will be used to receive jms messages from jms/tibco ems servers";
        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	this.CONFIG_messageType=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("PermittedMessageType").item(0).getTextContent());
        	//this.MISC_sequencingKey=rootActivityElement.getElementsByTagName("_sequenceKey").item(0).getTextContent();
        	String x=rootActivityElement.getElementsByTagName("ConnectionReference").item(0).getTextContent();
            this.CONFIG_JMSConnection=x.substring(x.lastIndexOf("/")+1,x.lastIndexOf("."));
            this.CONFIG_acknowledgeMode=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("acknowledgeMode").item(0).getTextContent());
       	  //this.CONFIG_maxSessions=rootActivityElement.getElementsByTagName("JMSExpiration").getLength()>0?Integer.parseInt(rootActivityElement.getElementsByTagName("timeout").item(0).getTextContent()):10;
     	 //this.ADV_JMSApplicationProperties=rootActivityElement.getElementsByTagName("ApplicationProperties").item(0).getTextContent();
       	  try{
       		  this.CONFIG_destinationQueue=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("destination").item(0).getTextContent());
       	  } catch (Exception E){
       		  this.CONFIG_destinationQueue="";
       	  }
	 }
	    
	 }
	 public static class JMSQueueSendActivity
	 {//done
		    private static String activityType;
		    private static String CONFIG_description;
		    private String CONFIG_JMSConnection,CONFIG_destinationQueue,CONFIG_messageType;
		    private String ADV_replyToQueue,ADV_deliveryMode,ADV_Type,ADV_JMSApplicationProperties,ADV_overrideTransactionBehavior;
		    private int ADV_JMSExpiration,ADV_priority;
		    private String INP_destinationQueue,INP_replyToQueue,INP_JMSPriority,INP_JMSDeliveryMode,INP_JMSCorrelationID,INP_JMSType,INP_body;
		    private int INP_JMSExpiration;
		    private Map<String,String>INP_JMSProperties,INP_otherProperties,INP_dynamicProperties;
		    private String OUT_messageID;
		    private String bodyValue;
		    public JMSQueueSendActivity(Node node){
	        	Element rootActivityElement = (Element)node;
	        	this.CONFIG_description="This is used to send jms messages to jms/tibco ems servers this is not a blocking activity i.e it will not wait for response from consumer";
	        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
	        	try{
	        		this.CONFIG_destinationQueue=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("destination").item(0).getTextContent());
	        	} catch (Exception E){
	        		this.CONFIG_destinationQueue="";
	        	}
	        	this.CONFIG_messageType=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("PermittedMessageType").item(0).getTextContent());
	        	//this.INP_JMSType=rootActivityElement.getElementsByTagName("conditionType").item(0).getTextContent();
	        	try{
	        		String x=rootActivityElement.getElementsByTagName("ConnectionReference").item(0).getTextContent();
	        		this.CONFIG_JMSConnection=x.substring(x.lastIndexOf("/")+1,x.lastIndexOf("."));
		            
	        	} catch (Exception E){
	        		this.CONFIG_JMSConnection="";
	        	}
	            this.ADV_deliveryMode=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("JMSDeliveryMode").item(0).getTextContent());
	       	    this.ADV_JMSExpiration=rootActivityElement.getElementsByTagName("JMSExpiration").getLength()>0?Integer.parseInt(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("JMSExpiration").item(0).getTextContent())):10;
		        this.ADV_priority=rootActivityElement.getElementsByTagName("JMSPriority").getLength()>0?Integer.parseInt(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("JMSPriority").item(0).getTextContent())):10;
		        try{
		        	this.CONFIG_destinationQueue=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("destination").item(0).getTextContent());
		        } catch (Exception E){
		        	this.CONFIG_destinationQueue="";
		        }
		        Element bodyTag=(Element)rootActivityElement.getElementsByTagName("Body").item(0);
	        	try{
	        		this.bodyValue=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression((((Element)bodyTag.getElementsByTagName("xsl:value-of").item(0)).getAttribute("select")));
	        	} catch (Exception E){
	        		this.bodyValue="";
	        	}

		    }
			public String getBodyValue() {
				return bodyValue;
			}
			public void setBodyValue(String bodyValue) {
				this.bodyValue = bodyValue;
			}
			public static String getActivityType() {
				return activityType;
			}
			public static void setActivityType(String activityType) {
				JMSQueueSendActivity.activityType = activityType;
			}
			public static String getCONFIG_description() {
				return CONFIG_description;
			}
			public static void setCONFIG_description(String cONFIG_description) {
				CONFIG_description = cONFIG_description;
			}
			public String getCONFIG_JMSConnection() {
				return CONFIG_JMSConnection;
			}
			public void setCONFIG_JMSConnection(String cONFIG_JMSConnection) {
				CONFIG_JMSConnection = cONFIG_JMSConnection;
			}
			public String getCONFIG_destinationQueue() {
				return CONFIG_destinationQueue;
			}
			public void setCONFIG_destinationQueue(String cONFIG_destinationQueue) {
				CONFIG_destinationQueue = cONFIG_destinationQueue;
			}
			public String getCONFIG_messageType() {
				return CONFIG_messageType;
			}
			public void setCONFIG_messageType(String cONFIG_messageType) {
				CONFIG_messageType = cONFIG_messageType;
			}
			public String getADV_replyToQueue() {
				return ADV_replyToQueue;
			}
			public void setADV_replyToQueue(String aDV_replyToQueue) {
				ADV_replyToQueue = aDV_replyToQueue;
			}
			public String getADV_deliveryMode() {
				return ADV_deliveryMode;
			}
			public void setADV_deliveryMode(String aDV_deliveryMode) {
				ADV_deliveryMode = aDV_deliveryMode;
			}
			public String getADV_Type() {
				return ADV_Type;
			}
			public void setADV_Type(String aDV_Type) {
				ADV_Type = aDV_Type;
			}
			public String getADV_JMSApplicationProperties() {
				return ADV_JMSApplicationProperties;
			}
			public void setADV_JMSApplicationProperties(String aDV_JMSApplicationProperties) {
				ADV_JMSApplicationProperties = aDV_JMSApplicationProperties;
			}
			public String getADV_overrideTransactionBehavior() {
				return ADV_overrideTransactionBehavior;
			}
			public void setADV_overrideTransactionBehavior(String aDV_overrideTransactionBehavior) {
				ADV_overrideTransactionBehavior = aDV_overrideTransactionBehavior;
			}
			public int getADV_JMSExpiration() {
				return ADV_JMSExpiration;
			}
			public void setADV_JMSExpiration(int aDV_JMSExpiration) {
				ADV_JMSExpiration = aDV_JMSExpiration;
			}
			public int getADV_priority() {
				return ADV_priority;
			}
			public void setADV_priority(int aDV_priority) {
				ADV_priority = aDV_priority;
			}
			public String getINP_destinationQueue() {
				return INP_destinationQueue;
			}
			public void setINP_destinationQueue(String iNP_destinationQueue) {
				INP_destinationQueue = iNP_destinationQueue;
			}
			public String getINP_replyToQueue() {
				return INP_replyToQueue;
			}
			public void setINP_replyToQueue(String iNP_replyToQueue) {
				INP_replyToQueue = iNP_replyToQueue;
			}
			public String getINP_JMSPriority() {
				return INP_JMSPriority;
			}
			public void setINP_JMSPriority(String iNP_JMSPriority) {
				INP_JMSPriority = iNP_JMSPriority;
			}
			public String getINP_JMSDeliveryMode() {
				return INP_JMSDeliveryMode;
			}
			public void setINP_JMSDeliveryMode(String iNP_JMSDeliveryMode) {
				INP_JMSDeliveryMode = iNP_JMSDeliveryMode;
			}
			public String getINP_JMSCorrelationID() {
				return INP_JMSCorrelationID;
			}
			public void setINP_JMSCorrelationID(String iNP_JMSCorrelationID) {
				INP_JMSCorrelationID = iNP_JMSCorrelationID;
			}
			public String getINP_JMSType() {
				return INP_JMSType;
			}
			public void setINP_JMSType(String iNP_JMSType) {
				INP_JMSType = iNP_JMSType;
			}
			public String getINP_Body() {
				return INP_body;
			}
			public void setINP_Body(String iNP_Body) {
				INP_body = iNP_Body;
			}
			public int getINP_JMSExpiration() {
				return INP_JMSExpiration;
			}
			public void setINP_JMSExpiration(int iNP_JMSExpiration) {
				INP_JMSExpiration = iNP_JMSExpiration;
			}
			public Map<String, String> getINP_JMSProperties() {
				return INP_JMSProperties;
			}
			public void setINP_JMSProperties(Map<String, String> iNP_JMSProperties) {
				INP_JMSProperties = iNP_JMSProperties;
			}
			public Map<String, String> getINP_otherProperties() {
				return INP_otherProperties;
			}
			public void setINP_otherProperties(Map<String, String> iNP_otherProperties) {
				INP_otherProperties = iNP_otherProperties;
			}
			public Map<String, String> getINP_dynamicProperties() {
				return INP_dynamicProperties;
			}
			public void setINP_dynamicProperties(Map<String, String> iNP_dynamicProperties) {
				INP_dynamicProperties = iNP_dynamicProperties;
			}
			public String getOUT_messageID() {
				return OUT_messageID;
			}
			public void setOUT_messageID(String oUT_messageID) {
				OUT_messageID = oUT_messageID;
			}
		    
	 }
	 public static class JMSQueueRequestReplyActivity
	 { //done 
		 private static String activityType;
		    private static String CONFIG_description;
		    private String CONFIG_JMSConnection,CONFIG_destinationQueue,CONFIG_messageType;
		    private String ADV_deliveryMode,ADV_Type,ADV_JMSApplicationProperties;
		    private int ADV_JMSExpiration,ADV_priority;
		    private String INP_destinationQueue,INP_JMSPriority,INP_JMSCorrelationID,INP_JMSType,INP_replyToDestination,INP_body; 
		    private int INP_requestTimeout,INP_JMSExpiration;
		    private Map<String,String>INP_dynamicProperties;
		    private String bodyValue;
		    public String getBodyValue() {
				return bodyValue;
			}
			public void setBodyValue(String bodyValue) {
				this.bodyValue = bodyValue;
			}
			private Map<String, String> OUT_JMSHeaders,OUT_JMSProperties,OUT_otherProperties,OUT_body,OUT_dynamicProperties;
		    public JMSQueueRequestReplyActivity(Node node){
	        	Element rootActivityElement = (Element)node;
	        	this.CONFIG_description="This activity is used to send request and wait for response,in request reply scenarios we use this activity";
	        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
	        	 this.CONFIG_messageType=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("PermittedMessageType").item(0).getTextContent());
	        	 String x=rootActivityElement.getElementsByTagName("ConnectionReference").item(0).getTextContent();
	             this.CONFIG_JMSConnection=x.substring(x.lastIndexOf("/")+1,x.lastIndexOf("."));
	             this.ADV_JMSExpiration=rootActivityElement.getElementsByTagName("JMSExpiration").getLength()>0?Integer.parseInt(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("JMSExpiration").item(0).getTextContent())):10;
	        	 this.ADV_deliveryMode=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("JMSDeliveryMode").item(0).getTextContent());
	        	 this.CONFIG_destinationQueue=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("destination").item(0).getTextContent());
	        	 this.ADV_priority=rootActivityElement.getElementsByTagName("JMSPriority").getLength()>0?Integer.parseInt(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("JMSPriority").item(0).getTextContent())):10;
	        	 //this.INP_JMSType=rootActivityElement.getElementsByTagName("conditionType").item(0).getTextContent();
	        	 Element cIdTag=(Element)rootActivityElement.getElementsByTagName("JMSCorrelationID").item(0);
	        	 String value=((Element)cIdTag.getElementsByTagName("xsl:value-of").item(0)).getAttribute("select");
	        	 Element bodyTag=(Element)rootActivityElement.getElementsByTagName("Body").item(0);
	        	 this.bodyValue=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(((Element)bodyTag.getElementsByTagName("xsl:value-of").item(0)).getAttribute("select"));
		 }
			public static String getActivityType() {
				return activityType;
			}
			public static void setActivityType(String activityType) {
				JMSQueueRequestReplyActivity.activityType = activityType;
			}
			public static String getCONFIG_description() {
				return CONFIG_description;
			}
			public static void setCONFIG_description(String cONFIG_description) {
				CONFIG_description = cONFIG_description;
			}
			public String getCONFIG_JMSConnection() {
				return CONFIG_JMSConnection;
			}
			public void setCONFIG_JMSConnection(String cONFIG_JMSConnection) {
				CONFIG_JMSConnection = cONFIG_JMSConnection;
			}
			public String getCONFIG_destinationQueue() {
				return CONFIG_destinationQueue;
			}
			public void setCONFIG_destinationQueue(String cONFIG_destinationQueue) {
				CONFIG_destinationQueue = cONFIG_destinationQueue;
			}
			public String getCONFIG_messageType() {
				return CONFIG_messageType;
			}
			public void setCONFIG_messageType(String cONFIG_messageType) {
				CONFIG_messageType = cONFIG_messageType;
			}
			public String getADV_deliveryMode() {
				return ADV_deliveryMode;
			}
			public void setADV_deliveryMode(String aDV_deliveryMode) {
				ADV_deliveryMode = aDV_deliveryMode;
			}
			public String getADV_Type() {
				return ADV_Type;
			}
			public void setADV_Type(String aDV_Type) {
				ADV_Type = aDV_Type;
			}
			public String getADV_JMSApplicationProperties() {
				return ADV_JMSApplicationProperties;
			}
			public void setADV_JMSApplicationProperties(String aDV_JMSApplicationProperties) {
				ADV_JMSApplicationProperties = aDV_JMSApplicationProperties;
			}
			public int getADV_JMSExpiration() {
				return ADV_JMSExpiration;
			}
			public void setADV_JMSExpiration(int aDV_JMSExpiration) {
				ADV_JMSExpiration = aDV_JMSExpiration;
			}
			public int getADV_priority() {
				return ADV_priority;
			}
			public void setADV_priority(int aDV_priority) {
				ADV_priority = aDV_priority;
			}
			public String getINP_destinationQueue() {
				return INP_destinationQueue;
			}
			public void setINP_destinationQueue(String iNP_destinationQueue) {
				INP_destinationQueue = iNP_destinationQueue;
			}
			public String getINP_JMSPriority() {
				return INP_JMSPriority;
			}
			public void setINP_JMSPriority(String iNP_JMSPriority) {
				INP_JMSPriority = iNP_JMSPriority;
			}
			public String getINP_JMSCorrelationID() {
				return INP_JMSCorrelationID;
			}
			public void setINP_JMSCorrelationID(String iNP_JMSCorrelationID) {
				INP_JMSCorrelationID = iNP_JMSCorrelationID;
			}
			public String getINP_JMSType() {
				return INP_JMSType;
			}
			public void setINP_JMSType(String iNP_JMSType) {
				INP_JMSType = iNP_JMSType;
			}
			public String getINP_replyToDestination() {
				return INP_replyToDestination;
			}
			public void setINP_replyToDestination(String iNP_replyToDestination) {
				INP_replyToDestination = iNP_replyToDestination;
			}
			public String getINP_body() {
				return INP_body;
			}
			public void setINP_body(String iNP_body) {
				INP_body = iNP_body;
			}
			public int getINP_requestTimeout() {
				return INP_requestTimeout;
			}
			public void setINP_requestTimeout(int iNP_requestTimeout) {
				INP_requestTimeout = iNP_requestTimeout;
			}
			public int getINP_JMSExpiration() {
				return INP_JMSExpiration;
			}
			public void setINP_JMSExpiration(int iNP_JMSExpiration) {
				INP_JMSExpiration = iNP_JMSExpiration;
			}
			public Map<String, String> getINP_dynamicProperties() {
				return INP_dynamicProperties;
			}
			public void setINP_dynamicProperties(Map<String, String> iNP_dynamicProperties) {
				INP_dynamicProperties = iNP_dynamicProperties;
			}
			public Map<String, String> getOUT_JMSHeaders() {
				return OUT_JMSHeaders;
			}
			public void setOUT_JMSHeaders(Map<String, String> oUT_JMSHeaders) {
				OUT_JMSHeaders = oUT_JMSHeaders;
			}
			public Map<String, String> getOUT_JMSProperties() {
				return OUT_JMSProperties;
			}
			public void setOUT_JMSProperties(Map<String, String> oUT_JMSProperties) {
				OUT_JMSProperties = oUT_JMSProperties;
			}
			public Map<String, String> getOUT_otherProperties() {
				return OUT_otherProperties;
			}
			public void setOUT_otherProperties(Map<String, String> oUT_otherProperties) {
				OUT_otherProperties = oUT_otherProperties;
			}
			public Map<String, String> getOUT_body() {
				return OUT_body;
			}
			public void setOUT_body(Map<String, String> oUT_body) {
				OUT_body = oUT_body;
			}
			public Map<String, String> getOUT_dynamicProperties() {
				return OUT_dynamicProperties;
			}
			public void setOUT_dynamicProperties(Map<String, String> oUT_dynamicProperties) {
				OUT_dynamicProperties = oUT_dynamicProperties;
			}
		    
	 }
	 public static class JMSTopicPublishActivity
	 {//done 
		 private static String activityType;
		    private static String CONFIG_description;
		    private String CONFIG_JMSConnection,CONFIG_destinationTopic,CONFIG_messageType;
		    private String ADV_replyToTopic,ADV_deliveryMode,ADV_Type,ADV_JMSApplicationProperties,ADV_overrideTransactionBehavior;
		    private int ADV_JMSExpiration,ADV_priority;
		    private String INP_JMSExpiration;
		    private String INP_destinationTopic,INP_replyToTopic,INP_JMSPriority,INP_JMSDeliveryMode,INP_JMSCorrelationID,INP_JMSType,bodyValue;
		    private Map<String,String>INP_JMSProperties,INP_otherProperties,INP_dynamicProperties;
		    private String OUT_messageID;
		    public JMSTopicPublishActivity(Node node){
	        	Element rootActivityElement = (Element)node;
	        	this.CONFIG_description="This is used to send jms messages to jms/tibco ems servers this is not a blocking activity i.e it will not wait for response from consumer";
	        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
	        	this.CONFIG_messageType=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression((rootActivityElement.getElementsByTagName("PermittedMessageType")).item(0).getTextContent());
	        	String x=rootActivityElement.getElementsByTagName("ConnectionReference").item(0).getTextContent();
	            this.CONFIG_JMSConnection=x.substring(x.lastIndexOf("/")+1,x.lastIndexOf("."));
	            this.CONFIG_destinationTopic=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("destination").item(0).getTextContent());
	       	    this.ADV_deliveryMode=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("JMSDeliveryMode").item(0).getTextContent());
	       	    try{
	       	    	this.bodyValue=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue());
	       	    } catch (Exception E){
	       	    	this.bodyValue = "";
	       	    }
	       	 this.ADV_JMSExpiration=rootActivityElement.getElementsByTagName("JMSExpiration").getLength()>0?Integer.parseInt(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("JMSExpiration").item(0).getTextContent())):10;
		        this.ADV_priority=rootActivityElement.getElementsByTagName("JMSPriority").getLength()>0?Integer.parseInt(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("JMSPriority").item(0).getTextContent())):10;
		 }
			public static String getActivityType() {
				return activityType;
			}
			public static void setActivityType(String activityType) {
				JMSTopicPublishActivity.activityType = activityType;
			}
			public static String getCONFIG_description() {
				return CONFIG_description;
			}
			public static void setCONFIG_description(String cONFIG_description) {
				CONFIG_description = cONFIG_description;
			}
			public String getCONFIG_JMSConnection() {
				return CONFIG_JMSConnection;
			}
			public void setCONFIG_JMSConnection(String cONFIG_JMSConnection) {
				CONFIG_JMSConnection = cONFIG_JMSConnection;
			}
			public String getCONFIG_destinationTopic() {
				return CONFIG_destinationTopic;
			}
			public void setCONFIG_destinationTopic(String cONFIG_destinationTopic) {
				CONFIG_destinationTopic = cONFIG_destinationTopic;
			}
			public String getCONFIG_messageType() {
				return CONFIG_messageType;
			}
			public void setCONFIG_messageType(String cONFIG_messageType) {
				CONFIG_messageType = cONFIG_messageType;
			}
			public String getADV_replyToTopic() {
				return ADV_replyToTopic;
			}
			public void setADV_replyToTopic(String aDV_replyToTopic) {
				ADV_replyToTopic = aDV_replyToTopic;
			}
			public String getADV_DeliveryMode() {
				return ADV_deliveryMode;
			}
			public void setADV_DeliveryMode(String aDV_DeliveryMode) {
				ADV_deliveryMode = aDV_DeliveryMode;
			}
			public String getADV_Type() {
				return ADV_Type;
			}
			public void setADV_Type(String aDV_Type) {
				ADV_Type = aDV_Type;
			}
			public String getADV_JMSApplicationProperties() {
				return ADV_JMSApplicationProperties;
			}
			public void setADV_JMSApplicationProperties(String aDV_JMSApplicationProperties) {
				ADV_JMSApplicationProperties = aDV_JMSApplicationProperties;
			}
			public String getADV_overrideTransactionBehavior() {
				return ADV_overrideTransactionBehavior;
			}
			public void setADV_overrideTransactionBehavior(String aDV_overrideTransactionBehavior) {
				ADV_overrideTransactionBehavior = aDV_overrideTransactionBehavior;
			}
			public int getADV_JMSExpiration() {
				return ADV_JMSExpiration;
			}
			public void setADV_JMSExpiration(int aDV_JMSExpiration) {
				ADV_JMSExpiration = aDV_JMSExpiration;
			}
			public int getADV_priority() {
				return ADV_priority;
			}
			public void setADV_priority(int aDV_priority) {
				ADV_priority = aDV_priority;
			}
			public String getINP_JMSExpiration() {
				return INP_JMSExpiration;
			}
			public void setINP_JMSExpiration(String iNP_JMSExpiration) {
				INP_JMSExpiration = iNP_JMSExpiration;
			}
			public String getINP_destinationTopic() {
				return INP_destinationTopic;
			}
			public void setINP_destinationTopic(String iNP_destinationTopic) {
				INP_destinationTopic = iNP_destinationTopic;
			}
			public String getINP_replyToTopic() {
				return INP_replyToTopic;
			}
			public void setINP_replyToTopic(String iNP_replyToTopic) {
				INP_replyToTopic = iNP_replyToTopic;
			}
			public String getINP_JMSPriority() {
				return INP_JMSPriority;
			}
			public void setINP_JMSPriority(String iNP_JMSPriority) {
				INP_JMSPriority = iNP_JMSPriority;
			}
			public String getINP_JMSDeliveryMode() {
				return INP_JMSDeliveryMode;
			}
			public void setINP_JMSDeliveryMode(String iNP_JMSDeliveryMode) {
				INP_JMSDeliveryMode = iNP_JMSDeliveryMode;
			}
			public String getINP_JMSCorrelationID() {
				return INP_JMSCorrelationID;
			}
			public void setINP_JMSCorrelationID(String iNP_JMSCorrelationID) {
				INP_JMSCorrelationID = iNP_JMSCorrelationID;
			}
			public String getINP_JMSType() {
				return INP_JMSType;
			}
			public void setINP_JMSType(String iNP_JMSType) {
				INP_JMSType = iNP_JMSType;
			}
			public String getINP_body() {
				return bodyValue;
			}
			public void setINP_body(String iNP_body) {
				bodyValue = iNP_body;
			}
			public Map<String, String> getINP_JMSProperties() {
				return INP_JMSProperties;
			}
			public void setINP_JMSProperties(Map<String, String> iNP_JMSProperties) {
				INP_JMSProperties = iNP_JMSProperties;
			}
			public Map<String, String> getINP_otherProperties() {
				return INP_otherProperties;
			}
			public void setINP_otherProperties(Map<String, String> iNP_otherProperties) {
				INP_otherProperties = iNP_otherProperties;
			}
			public Map<String, String> getINP_dynamicProperties() {
				return INP_dynamicProperties;
			}
			public void setINP_dynamicProperties(Map<String, String> iNP_dynamicProperties) {
				INP_dynamicProperties = iNP_dynamicProperties;
			}
			public String getOUT_messageID() {
				return OUT_messageID;
			}
			public void setOUT_messageID(String oUT_messageID) {
				OUT_messageID = oUT_messageID;
			}
		    
	 }
	 public static class JMSTopicRequestReplyActivity
	 {
		 private static String activityType;
		    private static String CONFIG_description;
		    private String CONFIG_JMSConnection,CONFIG_destinationTopic,CONFIG_messageType;
		    private int ADV_JMSExpiration,ADV_priority;
		    private String ADV_DeliveryMode,ADV_Type,ADV_JMSApplicationProperties;
		    private String INP_JMSExpiration,INP_requestTimeout;
		    private String  INP_destinationTopic,INP_JMSPriority,INP_JMSDeliveryMode,INP_JMSCorrelationID,INP_JMSType,INP_replyToTopic,INP_body;
		    private Map<String,String>INP_JMSProperties,INP_otherProperties,INP_dynamicProperties;
		    private Map<String, String> OUT_JMSHeaders,OUT_JMSProperties,OUT_otherProperties,OUT_body,OUT_dynamicProperties;
		    public JMSTopicRequestReplyActivity(Node node){
	        	Element rootActivityElement = (Element)node;
	        	this.CONFIG_description="This activity is used to send request and wait for response.In request reply scenarios we use this activity";
	        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
	        	this.CONFIG_JMSConnection="JMS Connection";
		    }
			public static String getActivityType() {
				return activityType;
			}
			public static void setActivityType(String activityType) {
				JMSTopicRequestReplyActivity.activityType = activityType;
			}
			public static String getCONFIG_description() {
				return CONFIG_description;
			}
			public static void setCONFIG_description(String cONFIG_description) {
				CONFIG_description = cONFIG_description;
			}
			public String getCONFIG_JMSConnection() {
				return CONFIG_JMSConnection;
			}
			public void setCONFIG_JMSConnection(String cONFIG_JMSConnection) {
				CONFIG_JMSConnection = cONFIG_JMSConnection;
			}
			public String getCONFIG_destinationTopic() {
				return CONFIG_destinationTopic;
			}
			public void setCONFIG_destinationTopic(String cONFIG_destinationTopic) {
				CONFIG_destinationTopic = cONFIG_destinationTopic;
			}
			public String getCONFIG_messageType() {
				return CONFIG_messageType;
			}
			public void setCONFIG_messageType(String cONFIG_messageType) {
				CONFIG_messageType = cONFIG_messageType;
			}
			public int getADV_JMSExpiration() {
				return ADV_JMSExpiration;
			}
			public void setADV_JMSExpiration(int aDV_JMSExpiration) {
				ADV_JMSExpiration = aDV_JMSExpiration;
			}
			public int getADV_priority() {
				return ADV_priority;
			}
			public void setADV_priority(int aDV_priority) {
				ADV_priority = aDV_priority;
			}
			public String getADV_DeliveryMode() {
				return ADV_DeliveryMode;
			}
			public void setADV_DeliveryMode(String aDV_DeliveryMode) {
				ADV_DeliveryMode = aDV_DeliveryMode;
			}
			public String getADV_Type() {
				return ADV_Type;
			}
			public void setADV_Type(String aDV_Type) {
				ADV_Type = aDV_Type;
			}
			public String getADV_JMSApplicationProperties() {
				return ADV_JMSApplicationProperties;
			}
			public void setADV_JMSApplicationProperties(String aDV_JMSApplicationProperties) {
				ADV_JMSApplicationProperties = aDV_JMSApplicationProperties;
			}
			public String getINP_JMSExpiration() {
				return INP_JMSExpiration;
			}
			public void setINP_JMSExpiration(String iNP_JMSExpiration) {
				INP_JMSExpiration = iNP_JMSExpiration;
			}
			public String getINP_requestTimeout() {
				return INP_requestTimeout;
			}
			public void setINP_requestTimeout(String iNP_requestTimeout) {
				INP_requestTimeout = iNP_requestTimeout;
			}
			public String getINP_destinationTopic() {
				return INP_destinationTopic;
			}
			public void setINP_destinationTopic(String iNP_destinationTopic) {
				INP_destinationTopic = iNP_destinationTopic;
			}
			public String getINP_JMSPriority() {
				return INP_JMSPriority;
			}
			public void setINP_JMSPriority(String iNP_JMSPriority) {
				INP_JMSPriority = iNP_JMSPriority;
			}
			public String getINP_JMSDeliveryMode() {
				return INP_JMSDeliveryMode;
			}
			public void setINP_JMSDeliveryMode(String iNP_JMSDeliveryMode) {
				INP_JMSDeliveryMode = iNP_JMSDeliveryMode;
			}
			public String getINP_JMSCorrelationID() {
				return INP_JMSCorrelationID;
			}
			public void setINP_JMSCorrelationID(String iNP_JMSCorrelationID) {
				INP_JMSCorrelationID = iNP_JMSCorrelationID;
			}
			public String getINP_JMSType() {
				return INP_JMSType;
			}
			public void setINP_JMSType(String iNP_JMSType) {
				INP_JMSType = iNP_JMSType;
			}
			public String getINP_replyToTopic() {
				return INP_replyToTopic;
			}
			public void setINP_replyToTopic(String iNP_replyToTopic) {
				INP_replyToTopic = iNP_replyToTopic;
			}
			public String getINP_body() {
				return INP_body;
			}
			public void setINP_body(String iNP_body) {
				INP_body = iNP_body;
			}
			public Map<String, String> getINP_JMSProperties() {
				return INP_JMSProperties;
			}
			public void setINP_JMSProperties(Map<String, String> iNP_JMSProperties) {
				INP_JMSProperties = iNP_JMSProperties;
			}
			public Map<String, String> getINP_otherProperties() {
				return INP_otherProperties;
			}
			public void setINP_otherProperties(Map<String, String> iNP_otherProperties) {
				INP_otherProperties = iNP_otherProperties;
			}
			public Map<String, String> getINP_dynamicProperties() {
				return INP_dynamicProperties;
			}
			public void setINP_dynamicProperties(Map<String, String> iNP_dynamicProperties) {
				INP_dynamicProperties = iNP_dynamicProperties;
			}
			public Map<String, String> getOUT_JMSHeaders() {
				return OUT_JMSHeaders;
			}
			public void setOUT_JMSHeaders(Map<String, String> oUT_JMSHeaders) {
				OUT_JMSHeaders = oUT_JMSHeaders;
			}
			public Map<String, String> getOUT_JMSProperties() {
				return OUT_JMSProperties;
			}
			public void setOUT_JMSProperties(Map<String, String> oUT_JMSProperties) {
				OUT_JMSProperties = oUT_JMSProperties;
			}
			public Map<String, String> getOUT_otherProperties() {
				return OUT_otherProperties;
			}
			public void setOUT_otherProperties(Map<String, String> oUT_otherProperties) {
				OUT_otherProperties = oUT_otherProperties;
			}
			public Map<String, String> getOUT_body() {
				return OUT_body;
			}
			public void setOUT_body(Map<String, String> oUT_body) {
				OUT_body = oUT_body;
			}
			public Map<String, String> getOUT_dynamicProperties() {
				return OUT_dynamicProperties;
			}
			public void setOUT_dynamicProperties(Map<String, String> oUT_dynamicProperties) {
				OUT_dynamicProperties = oUT_dynamicProperties;
			}
		    
	 }
	 public static class JMSTopicSignalInActivity
	 {
		    private static String activityType;
		    private static String CONFIG_description;
		    private String CONFIG_JMSConnection,CONFIG_acknowledgeMode,CONFIG_destinationTopic,CONFIG_messageType,CONFIG_durableSubscription,CONFIG_subscriptionName,CONFIG_SuppressLocalMessages; 
		    private String ADV_messageSelector, ADV_JMSApplicationProperties;
		    private int ADV_receiverTimeout; 
		    private String INP_key;
		    private int INP_processTimeout;
		    public JMSTopicSignalInActivity(Node node){
	        	Element rootActivityElement = (Element)node;
	        	this.CONFIG_description="This acitivity is used in scenarios where we have asyncronous pattern.This actvity will wait for messages on a topic";
	        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
		 }
			public static String getActivityType() {
				return activityType;
			}
			public static void setActivityType(String activityType) {
				JMSTopicSignalInActivity.activityType = activityType;
			}
			public static String getCONFIG_description() {
				return CONFIG_description;
			}
			public static void setCONFIG_description(String cONFIG_description) {
				CONFIG_description = cONFIG_description;
			}
			public String getCONFIG_JMSConnection() {
				return CONFIG_JMSConnection;
			}
			public void setCONFIG_JMSConnection(String cONFIG_JMSConnection) {
				CONFIG_JMSConnection = cONFIG_JMSConnection;
			}
			public String getCONFIG_acknowledgeMode() {
				return CONFIG_acknowledgeMode;
			}
			public void setCONFIG_acknowledgeMode(String cONFIG_acknowledgeMode) {
				CONFIG_acknowledgeMode = cONFIG_acknowledgeMode;
			}
			public String getCONFIG_destinationTopic() {
				return CONFIG_destinationTopic;
			}
			public void setCONFIG_destinationTopic(String cONFIG_destinationTopic) {
				CONFIG_destinationTopic = cONFIG_destinationTopic;
			}
			public String getCONFIG_messageType() {
				return CONFIG_messageType;
			}
			public void setCONFIG_messageType(String cONFIG_messageType) {
				CONFIG_messageType = cONFIG_messageType;
			}
			public String getCONFIG_durableSubscription() {
				return CONFIG_durableSubscription;
			}
			public void setCONFIG_durableSubscription(String cONFIG_durableSubscription) {
				CONFIG_durableSubscription = cONFIG_durableSubscription;
			}
			public String getCONFIG_subscriptionName() {
				return CONFIG_subscriptionName;
			}
			public void setCONFIG_subscriptionName(String cONFIG_subscriptionName) {
				CONFIG_subscriptionName = cONFIG_subscriptionName;
			}
			public String getCONFIG_SuppressLocalMessages() {
				return CONFIG_SuppressLocalMessages;
			}
			public void setCONFIG_SuppressLocalMessages(String cONFIG_SuppressLocalMessages) {
				CONFIG_SuppressLocalMessages = cONFIG_SuppressLocalMessages;
			}
			public String getADV_messageSelector() {
				return ADV_messageSelector;
			}
			public void setADV_messageSelector(String aDV_messageSelector) {
				ADV_messageSelector = aDV_messageSelector;
			}
			public String getADV_JMSApplicationProperties() {
				return ADV_JMSApplicationProperties;
			}
			public void setADV_JMSApplicationProperties(String aDV_JMSApplicationProperties) {
				ADV_JMSApplicationProperties = aDV_JMSApplicationProperties;
			}
			public int getADV_receiverTimeout() {
				return ADV_receiverTimeout;
			}
			public void setADV_receiverTimeout(int aDV_receiverTimeout) {
				ADV_receiverTimeout = aDV_receiverTimeout;
			}
			public String getINP_key() {
				return INP_key;
			}
			public void setINP_key(String iNP_key) {
				INP_key = iNP_key;
			}
			public int getINP_processTimeout() {
				return INP_processTimeout;
			}
			public void setINP_processTimeout(int iNP_processTimeout) {
				INP_processTimeout = iNP_processTimeout;
			}
		    
	 }
	 public static class JMSQueueSignalInActivity
	 {
		 private static String activityType;
		    private static String CONFIG_description;
		    private String CONFIG_JMSConnection,CONFIG_destinationQueue,CONFIG_messageType,CONFIG_acknowledgeMode;
		    private String ADV_messageSelector, ADV_JMSApplicationProperties;
		    private int ADV_receiverTimeout; 
		    private String INP_key;
		    private int INP_processTimeout;
		    private Map<String, String> OUT_JMSHeaders,OUT_JMSProperties,OUT_otherProperties,OUT_body,OUT_dynamicProperties;
		    public JMSQueueSignalInActivity(Node node){
	        	Element rootActivityElement = (Element)node;
	        	this.CONFIG_description="This acitiviyt is used in scenarios where we have asyncronous pattern.This actvity will wait for messages on a queue";
	        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
		 }
			public static String getActivityType() {
				return activityType;
			}
			public static void setActivityType(String activityType) {
				JMSQueueSignalInActivity.activityType = activityType;
			}
			public static String getCONFIG_description() {
				return CONFIG_description;
			}
			public static void setCONFIG_description(String cONFIG_description) {
				CONFIG_description = cONFIG_description;
			}
			public String getCONFIG_JMSConnection() {
				return CONFIG_JMSConnection;
			}
			public void setCONFIG_JMSConnection(String cONFIG_JMSConnection) {
				CONFIG_JMSConnection = cONFIG_JMSConnection;
			}
			public String getCONFIG_destinationQueue() {
				return CONFIG_destinationQueue;
			}
			public void setCONFIG_destinationQueue(String cONFIG_destinationQueue) {
				CONFIG_destinationQueue = cONFIG_destinationQueue;
			}
			public String getCONFIG_messageType() {
				return CONFIG_messageType;
			}
			public void setCONFIG_messageType(String cONFIG_messageType) {
				CONFIG_messageType = cONFIG_messageType;
			}
			public String getCONFIG_acknowledgeMode() {
				return CONFIG_acknowledgeMode;
			}
			public void setCONFIG_acknowledgeMode(String cONFIG_acknowledgeMode) {
				CONFIG_acknowledgeMode = cONFIG_acknowledgeMode;
			}
			public String getADV_messageSelector() {
				return ADV_messageSelector;
			}
			public void setADV_messageSelector(String aDV_messageSelector) {
				ADV_messageSelector = aDV_messageSelector;
			}
			public String getADV_JMSApplicationProperties() {
				return ADV_JMSApplicationProperties;
			}
			public void setADV_JMSApplicationProperties(String aDV_JMSApplicationProperties) {
				ADV_JMSApplicationProperties = aDV_JMSApplicationProperties;
			}
			public int getADV_receiverTimeout() {
				return ADV_receiverTimeout;
			}
			public void setADV_receiverTimeout(int aDV_receiverTimeout) {
				ADV_receiverTimeout = aDV_receiverTimeout;
			}
			public String getINP_key() {
				return INP_key;
			}
			public void setINP_key(String iNP_key) {
				INP_key = iNP_key;
			}
			public int getINP_processTimeout() {
				return INP_processTimeout;
			}
			public void setINP_processTimeout(int iNP_processTimeout) {
				INP_processTimeout = iNP_processTimeout;
			}
			public Map<String, String> getOUT_JMSHeaders() {
				return OUT_JMSHeaders;
			}
			public void setOUT_JMSHeaders(Map<String, String> oUT_JMSHeaders) {
				OUT_JMSHeaders = oUT_JMSHeaders;
			}
			public Map<String, String> getOUT_JMSProperties() {
				return OUT_JMSProperties;
			}
			public void setOUT_JMSProperties(Map<String, String> oUT_JMSProperties) {
				OUT_JMSProperties = oUT_JMSProperties;
			}
			public Map<String, String> getOUT_otherProperties() {
				return OUT_otherProperties;
			}
			public void setOUT_otherProperties(Map<String, String> oUT_otherProperties) {
				OUT_otherProperties = oUT_otherProperties;
			}
			public Map<String, String> getOUT_body() {
				return OUT_body;
			}
			public void setOUT_body(Map<String, String> oUT_body) {
				OUT_body = oUT_body;
			}
			public Map<String, String> getOUT_dynamicProperties() {
				return OUT_dynamicProperties;
			}
			public void setOUT_dynamicProperties(Map<String, String> oUT_dynamicProperties) {
				OUT_dynamicProperties = oUT_dynamicProperties;
			}
		    
	 }
	 public static class JMSQueueGetMessageActivity
	 {
		    private static String activityType;
		    private static String CONFIG_description;
		    private String CONFIG_JMSConnection,CONFIG_destinationQueue,CONFIG_messageType,CONFIG_acknowledgeMode;
		    private String ADV_messageSelector, ADV_JMSApplicationProperties;
		    private String ADV_destinationQueue,ADV_selector;
		    private int ADV_timeout;
		    private Map<String, String> OUT_JMSHeaders,OUT_JMSProperties,OUT_otherProperties,OUT_body,OUT_dynamicProperties;
		    public JMSQueueGetMessageActivity(Node node){
	        	Element rootActivityElement = (Element)node;
	        	this.CONFIG_description="This activity is used in scenarios where we need to pull messages from a queue on demand";
	        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
		 }
			public static String getActivityType() {
				return activityType;
			}
			public static void setActivityType(String activityType) {
				JMSQueueGetMessageActivity.activityType = activityType;
			}
			public static String getCONFIG_description() {
				return CONFIG_description;
			}
			public static void setCONFIG_description(String cONFIG_description) {
				CONFIG_description = cONFIG_description;
			}
			public String getCONFIG_JMSConnection() {
				return CONFIG_JMSConnection;
			}
			public void setCONFIG_JMSConnection(String cONFIG_JMSConnection) {
				CONFIG_JMSConnection = cONFIG_JMSConnection;
			}
			public String getCONFIG_destinationQueue() {
				return CONFIG_destinationQueue;
			}
			public void setCONFIG_destinationQueue(String cONFIG_destinationQueue) {
				CONFIG_destinationQueue = cONFIG_destinationQueue;
			}
			public String getCONFIG_messageType() {
				return CONFIG_messageType;
			}
			public void setCONFIG_messageType(String cONFIG_messageType) {
				CONFIG_messageType = cONFIG_messageType;
			}
			public String getCONFIG_acknowledgeMode() {
				return CONFIG_acknowledgeMode;
			}
			public void setCONFIG_acknowledgeMode(String cONFIG_acknowledgeMode) {
				CONFIG_acknowledgeMode = cONFIG_acknowledgeMode;
			}
			public String getADV_messageSelector() {
				return ADV_messageSelector;
			}
			public void setADV_messageSelector(String aDV_messageSelector) {
				ADV_messageSelector = aDV_messageSelector;
			}
			public String getADV_JMSApplicationProperties() {
				return ADV_JMSApplicationProperties;
			}
			public void setADV_JMSApplicationProperties(String aDV_JMSApplicationProperties) {
				ADV_JMSApplicationProperties = aDV_JMSApplicationProperties;
			}
			public String getADV_destinationQueue() {
				return ADV_destinationQueue;
			}
			public void setADV_destinationQueue(String aDV_destinationQueue) {
				ADV_destinationQueue = aDV_destinationQueue;
			}
			public String getADV_selector() {
				return ADV_selector;
			}
			public void setADV_selector(String aDV_selector) {
				ADV_selector = aDV_selector;
			}
			public int getADV_timeout() {
				return ADV_timeout;
			}
			public void setADV_timeout(int aDV_timeout) {
				ADV_timeout = aDV_timeout;
			}
			public Map<String, String> getOUT_JMSHeaders() {
				return OUT_JMSHeaders;
			}
			public void setOUT_JMSHeaders(Map<String, String> oUT_JMSHeaders) {
				OUT_JMSHeaders = oUT_JMSHeaders;
			}
			public Map<String, String> getOUT_JMSProperties() {
				return OUT_JMSProperties;
			}
			public void setOUT_JMSProperties(Map<String, String> oUT_JMSProperties) {
				OUT_JMSProperties = oUT_JMSProperties;
			}
			public Map<String, String> getOUT_otherProperties() {
				return OUT_otherProperties;
			}
			public void setOUT_otherProperties(Map<String, String> oUT_otherProperties) {
				OUT_otherProperties = oUT_otherProperties;
			}
			public Map<String, String> getOUT_body() {
				return OUT_body;
			}
			public void setOUT_body(Map<String, String> oUT_body) {
				OUT_body = oUT_body;
			}
			public Map<String, String> getOUT_dynamicProperties() {
				return OUT_dynamicProperties;
			}
			public void setOUT_dynamicProperties(Map<String, String> oUT_dynamicProperties) {
				OUT_dynamicProperties = oUT_dynamicProperties;
			}
		    
	 }
	 public static class JMSReplyActivity
	 { // done 
		    private static String activityType;
		    private static String CONFIG_description; 
		    private String CONFIG_messageType,CONFIG_replyTo;
		    private String ADV_deliveryMode,ADV_Type,ADV_JMSApplicationProperties;
		    private int ADV_JMSExpiration,ADV_priority;
		    private int INP_JMSExpiration;
		    private String INP_JMSPriority,INP_JMSDeliveryMode,INP_JMSCorrelationID,INP_JMSType,INP_body;
		    private Map<String,String>INP_JMSProperties,INP_otherProperties,INP_dynamicProperties;
		    private Map<String, String> OUT_JMSHeaders,OUT_JMSProperties,OUT_otherProperties,OUT_body,OUT_dynamicProperties;
		    public JMSReplyActivity(Node node){
	        Element rootActivityElement = (Element)node;
	        this.CONFIG_description="This activity is used while impelementing synchronous pattern,inorder to send response back to requestor";
	        this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
	        this.CONFIG_messageType=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("PermittedMessageType").item(0).getTextContent());
	        this.ADV_deliveryMode=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("JMSDeliveryMode").item(0).getTextContent());
	        Element cIdTag=(Element)rootActivityElement.getElementsByTagName("JMSCorrelationID").item(0);
       	    String value=((Element)cIdTag.getElementsByTagName("xsl:value-of").item(0)).getAttribute("select");
       	    Element bodyTag=(Element)rootActivityElement.getElementsByTagName("JMSCorrelationID").item(0);
       	    String bodyValue=((Element)cIdTag.getElementsByTagName("xsl:value-of").item(0)).getAttribute("select");
	        this.ADV_JMSExpiration=rootActivityElement.getElementsByTagName("JMSExpiration").getLength()>0?Integer.parseInt(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("JMSExpiration").item(0).getTextContent())):10;
	        this.ADV_priority=rootActivityElement.getElementsByTagName("JMSPriority").getLength()>0?Integer.parseInt(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("JMSPriority").item(0).getTextContent())):10;
		    }
			public static String getActivityType() {
				return activityType;
			}
			public static void setActivityType(String activityType) {
				JMSReplyActivity.activityType = activityType;
			}
			public static String getCONFIG_description() {
				return CONFIG_description;
			}
			public static void setCONFIG_description(String cONFIG_description) {
				CONFIG_description = cONFIG_description;
			}
			public String getCONFIG_messageType() {
				return CONFIG_messageType;
			}
			public void setCONFIG_messageType(String cONFIG_messageType) {
				CONFIG_messageType = cONFIG_messageType;
			}
			public String getCONFIG_replyTo() {
				return CONFIG_replyTo;
			}
			public void setCONFIG_replyTo(String cONFIG_replyTo) {
				CONFIG_replyTo = cONFIG_replyTo;
			}
			public String getADV_deliveryMode() {
				return ADV_deliveryMode;
			}
			public void setADV_deliveryMode(String aDV_deliveryMode) {
				ADV_deliveryMode = aDV_deliveryMode;
			}
			public String getADV_Type() {
				return ADV_Type;
			}
			public void setADV_Type(String ADV_Type) {
				ADV_Type = ADV_Type;
			}
			public String getADV_JMSApplicationProperties() {
				return ADV_JMSApplicationProperties;
			}
			public void setADV_JMSApplicationProperties(String aDV_JMSApplicationProperties) {
				ADV_JMSApplicationProperties = aDV_JMSApplicationProperties;
			}
			public int getADV_JMSExpiration() {
				return ADV_JMSExpiration;
			}
			public void setADV_JMSExpiration(int aDV_JMSExpiration) {
				ADV_JMSExpiration = aDV_JMSExpiration;
			}
			public int getADV_priority() {
				return ADV_priority;
			}
			public void setADV_priority(int aDV_priority) {
				ADV_priority = aDV_priority;
			}
			public int getINP_JMSExpiration() {
				return INP_JMSExpiration;
			}
			public void setINP_JMSExpiration(int iNP_JMSExpiration) {
				INP_JMSExpiration = iNP_JMSExpiration;
			}
			public String getINP_JMSPriority() {
				return INP_JMSPriority;
			}
			public void setINP_JMSPriority(String iNP_JMSPriority) {
				INP_JMSPriority = iNP_JMSPriority;
			}
			public String getINP_JMSDeliveryMode() {
				return INP_JMSDeliveryMode;
			}
			public void setINP_JMSDeliveryMode(String iNP_JMSDeliveryMode) {
				INP_JMSDeliveryMode = iNP_JMSDeliveryMode;
			}
			public String getINP_JMSCorrelationID() {
				return INP_JMSCorrelationID;
			}
			public void setINP_JMSCorrelationID(String iNP_JMSCorrelationID) {
				INP_JMSCorrelationID = iNP_JMSCorrelationID;
			}
			public String getINP_JMSType() {
				return INP_JMSType;
			}
			public void setINP_JMSType(String iNP_JMSType) {
				INP_JMSType = iNP_JMSType;
			}
			public String getINP_body() {
				return INP_body;
			}
			public void setINP_body(String iNP_body) {
				INP_body = iNP_body;
			}
			public Map<String, String> getINP_JMSProperties() {
				return INP_JMSProperties;
			}
			public void setINP_JMSProperties(Map<String, String> iNP_JMSProperties) {
				INP_JMSProperties = iNP_JMSProperties;
			}
			public Map<String, String> getINP_otherProperties() {
				return INP_otherProperties;
			}
			public void setINP_otherProperties(Map<String, String> iNP_otherProperties) {
				INP_otherProperties = iNP_otherProperties;
			}
			public Map<String, String> getINP_dynamicProperties() {
				return INP_dynamicProperties;
			}
			public void setINP_dynamicProperties(Map<String, String> iNP_dynamicProperties) {
				INP_dynamicProperties = iNP_dynamicProperties;
			}
			public Map<String, String> getOUT_JMSHeaders() {
				return OUT_JMSHeaders;
			}
			public void setOUT_JMSHeaders(Map<String, String> oUT_JMSHeaders) {
				OUT_JMSHeaders = oUT_JMSHeaders;
			}
			public Map<String, String> getOUT_JMSProperties() {
				return OUT_JMSProperties;
			}
			public void setOUT_JMSProperties(Map<String, String> oUT_JMSProperties) {
				OUT_JMSProperties = oUT_JMSProperties;
			}
			public Map<String, String> getOUT_otherProperties() {
				return OUT_otherProperties;
			}
			public void setOUT_otherProperties(Map<String, String> oUT_otherProperties) {
				OUT_otherProperties = oUT_otherProperties;
			}
			public Map<String, String> getOUT_body() {
				return OUT_body;
			}
			public void setOUT_body(Map<String, String> oUT_body) {
				OUT_body = oUT_body;
			}
			public Map<String, String> getOUT_dynamicProperties() {
				return OUT_dynamicProperties;
			}
			public void setOUT_dynamicProperties(Map<String, String> oUT_dynamicProperties) {
				OUT_dynamicProperties = oUT_dynamicProperties;
			}
		    
	 }
}


