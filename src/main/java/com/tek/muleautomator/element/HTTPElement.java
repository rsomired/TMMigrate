package com.tek.muleautomator.element;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class HTTPElement {
	
	public static class HTTPSendResponseActivity {
		private static String CONFIG_name, CONFIG_desc, CONFIG_connectionPath, activityType;
		private boolean CONFIG_closeConnection, CONFIG_flushResponse;
		private HashMap<String,String> inputHeaders;
		private HashMap<String,String> inputDynamicHeaders;
		private String INPUT_content;
		private INPUT_CONTENT_TYPE contentType;
		
		enum INPUT_CONTENT_TYPE{
			ASCII, BINARY
		};
		
		public static String getCONFIG_connectionPath() {
			return CONFIG_connectionPath;
		}

		public static void setCONFIG_connectionPath(String cONFIG_connectionPath) {
			CONFIG_connectionPath = cONFIG_connectionPath;
		}

		public HTTPSendResponseActivity(Node targetNode){
			this.inputHeaders=new HashMap<>();
			Element rootActivityElement = (Element)targetNode;
			HTTPSendResponseActivity.CONFIG_desc="Sends a response to a previously received HTTP request. This activity is used in conjunction with the HTTP Receiver process starter or the Wait for HTTP Request activity";
			HTTPSendResponseActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
			this.CONFIG_closeConnection=rootActivityElement.getElementsByTagName("closeConnection").getLength()>0?Boolean.parseBoolean(rootActivityElement.getElementsByTagName("closeConnection").item(0).getTextContent()):false;
			this.CONFIG_flushResponse=false;
			if(rootActivityElement.getElementsByTagName("asciiContent").getLength()>0){
				this.contentType=INPUT_CONTENT_TYPE.ASCII;
				this.INPUT_content=rootActivityElement.getElementsByTagName("asciiContent").item(0).getTextContent();
			} else if (rootActivityElement.getElementsByTagName("binaryContent").getLength()>0){
				this.contentType=INPUT_CONTENT_TYPE.BINARY;
				this.INPUT_content=rootActivityElement.getElementsByTagName("binaryContent").item(0).getTextContent();
			}
		}

		public static String getCONFIG_name() {
			return CONFIG_name;
		}

		public static void setCONFIG_name(String cONFIG_name) {
			CONFIG_name = cONFIG_name;
		}

		public static String getCONFIG_desc() {
			return CONFIG_desc;
		}

		public static void setCONFIG_desc(String cONFIG_desc) {
			CONFIG_desc = cONFIG_desc;
		}

		public static String getActivityType() {
			return activityType;
		}

		public static void setActivityType(String activityType) {
			HTTPSendResponseActivity.activityType = activityType;
		}

		public boolean isCONFIG_closeConnection() {
			return CONFIG_closeConnection;
		}

		public void setCONFIG_closeConnection(boolean cONFIG_closeConnection) {
			CONFIG_closeConnection = cONFIG_closeConnection;
		}

		public boolean isCONFIG_flushResponse() {
			return CONFIG_flushResponse;
		}

		public void setCONFIG_flushResponse(boolean cONFIG_flushResponse) {
			CONFIG_flushResponse = cONFIG_flushResponse;
		}

		public HashMap<String, String> getInputHeaders() {
			return inputHeaders;
		}

		public void setInputHeaders(HashMap<String, String> inputHeaders) {
			this.inputHeaders = inputHeaders;
		}

		public HashMap<String, String> getInputDynamicHeaders() {
			return inputDynamicHeaders;
		}

		public void setInputDynamicHeaders(HashMap<String, String> inputDynamicHeaders) {
			this.inputDynamicHeaders = inputDynamicHeaders;
		}

		public String getINPUT_content() {
			return INPUT_content;
		}

		public void setINPUT_content(String iNPUT_content) {
			INPUT_content = iNPUT_content;
		}

		public INPUT_CONTENT_TYPE getContentType() {
			return contentType;
		}

		public void setContentType(INPUT_CONTENT_TYPE contentType) {
			this.contentType = contentType;
		}
		
	}
	
	public static class HTTPReceiverActivity  
	 {
	  private static String CONFIG_description;
	  private static String CONFIG_connectionName;
	  private static String CONFIG_activityType;
	  private String  CONFIG_outputMode;
	  private String CONFIG_defaultEncoding;
	  private String CONFIG_parsePostMethodData;
	  private String CONFIG_parameters;
	  private String CONFIG_HTTPAuthentication;
	  private String MISC_sequencingKey;
	  private String MISC_customId;
	  public HTTPReceiverActivity(Node targetNode)
	  {
	HTTPReceiverActivity.CONFIG_description="Starts a process based on the receipt of a HTTP request";
	   Element rootActivityElement = (Element)targetNode;
	   HTTPReceiverActivity.CONFIG_activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
	   String con=rootActivityElement.getElementsByTagName("sharedChannel").item(0).getTextContent();
	   HTTPReceiverActivity.CONFIG_connectionName=con.substring(con.lastIndexOf("/")+1, con.lastIndexOf("."));
	   this.CONFIG_outputMode=rootActivityElement.getElementsByTagName("outputMode").item(0).getTextContent();
	   this.CONFIG_defaultEncoding=rootActivityElement.getElementsByTagName("defaultEncoding").item(0).getTextContent();
	 }
	  public static String getDescription() {
	   return CONFIG_description;
	  }

	  public static String getConnectionName() {
	   return CONFIG_connectionName;
	  }

	  public static String getActivityType() {
	   return CONFIG_activityType;
	  }
	public static String getCONFIG_description() {
		return CONFIG_description;
	}
	public static void setCONFIG_description(String cONFIG_description) {
		CONFIG_description = cONFIG_description;
	}
	public static String getCONFIG_connectionPath() {
		return CONFIG_connectionName;
	}
	public static void setCONFIG_connectionPath(String cONFIG_connectionPath) {
		CONFIG_connectionName = cONFIG_connectionPath;
	}
	public static String getCONFIG_activityType() {
		return CONFIG_activityType;
	}
	public static void setCONFIG_activityType(String cONFIG_activityType) {
		CONFIG_activityType = cONFIG_activityType;
	}
	public String getCONFIG_outputMode() {
		return CONFIG_outputMode;
	}
	public void setCONFIG_outputMode(String cONFIG_outputMode) {
		CONFIG_outputMode = cONFIG_outputMode;
	}
	public String getCONFIG_defaultEncoding() {
		return CONFIG_defaultEncoding;
	}
	public void setCONFIG_defaultEncoding(String cONFIG_defaultEncoding) {
		CONFIG_defaultEncoding = cONFIG_defaultEncoding;
	}
	public String getCONFIG_parsePostMethodData() {
		return CONFIG_parsePostMethodData;
	}
	public void setCONFIG_parsePostMethodData(String cONFIG_parsePostMethodData) {
		CONFIG_parsePostMethodData = cONFIG_parsePostMethodData;
	}
	public String getCONFIG_parameters() {
		return CONFIG_parameters;
	}
	public void setCONFIG_parameters(String cONFIG_parameters) {
		CONFIG_parameters = cONFIG_parameters;
	}
	public String getCONFIG_HTTPAuthentication() {
		return CONFIG_HTTPAuthentication;
	}
	public void setCONFIG_HTTPAuthentication(String cONFIG_HTTPAuthentication) {
		CONFIG_HTTPAuthentication = cONFIG_HTTPAuthentication;
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
	  
	}

	public static class HTTPWaitForRequest {
		private static String CONFIG_name, CONFIG_description, CONFIG_connectionPath,CONFIG_activityType;
		private static Map<String, String> CONFIG_params;
		private static String CONFIG_authentication;
		
		private int processTimeout;
		private String key;
		
		private String OUTPUT_Method, OUTPUT_RequestURI, OUTPUT_HTTPVersion, OUTPUT_Command, OUTPUT_QueryString, OUTPUT_Header;
		private String OUTPUT_Port;
		private String OUTPUT_protocol;
		private Map<String, String> OUTPUT_headers, OUTPUT_parameters;
		
		public HTTPWaitForRequest(Node targetNode){
			 Element rootActivityElement = (Element)targetNode;
			 this.key="defaultKey";
			 HTTPWaitForRequest.CONFIG_activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
			 //HTTPWaitForRequest.CONFIG_connectionPath=rootActivityElement.getElementsByTagName("sharedChannel").item(0).getTextContent();
			 HTTPWaitForRequest.CONFIG_description="Waits for an incoming HTTP request in a process definition. The process instance suspends until the incoming HTTP request is received.";
			 this.processTimeout=Integer.valueOf(rootActivityElement.getElementsByTagName("processTimeout").item(0).getTextContent());
		}
	}
	
	public static class HTTPSendRequestActivity
	{
	 private static String CONFIG_description, CONFIG_connectionName;
	 private static String CONFIG_connectionPath;
	 private static String CONFIG_activityType;
	 private int CONFIG_port;
	 private  String CONFIG_host;
	 private Map<String, String> CONFIG_parameters;
	 private  String CONFIG_authentication;//in sender
	 private String OUTPUT_Header, OUTPUT_asciiContent;
	 private Map<String, String> OUTPUT_Headers;
	public HTTPSendRequestActivity(Node targetNode)
	{
	 this.CONFIG_parameters=new LinkedHashMap<>();
	 HTTPSendRequestActivity.CONFIG_description="The Send HTTP Request activity sends a HTTP request to a webserver";
	 Element rootActivityElement = (Element)targetNode;
	 HTTPSendRequestActivity.CONFIG_activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
	 try{
		 HTTPSendRequestActivity.CONFIG_connectionPath=rootActivityElement.getElementsByTagName("sharedChannel").item(0).getTextContent();
		 String con=rootActivityElement.getElementsByTagName("sharedChannel").item(0).getTextContent();
		HTTPSendRequestActivity.CONFIG_connectionName=con.substring(con.lastIndexOf("/")+1, con.lastIndexOf("."));
		   
	 } catch( Exception E){
		 HTTPSendRequestActivity.CONFIG_connectionName="HTTP Request Configuration";
	 }
	 this.CONFIG_host=(rootActivityElement.getElementsByTagName("host").getLength()>0)?rootActivityElement.getElementsByTagName("host").item(0).getTextContent():"";
	 try{
		 this.CONFIG_port=rootActivityElement.getElementsByTagName("serverport").getLength()>0?Integer.parseInt(rootActivityElement.getElementsByTagName("serverport").item(0).getTextContent()):80;
	 } catch(Exception E){
		 this.CONFIG_port=8081;
	 }
	 //this.CONFIG_authentication=((Element)rootActivityElement.getElementsByTagName("Authorization").item(0)).getChildNodes().item(0).getAttributes().getNamedItem("select").getNodeValue();
	 NodeList paramTag=rootActivityElement.getElementsByTagName("parameters");
	 if(paramTag.getLength()>0){
		 NodeList params=paramTag.item(0).getChildNodes();
		 for(int i=0;i<params.getLength();++i){
			 if(params.item(i).getNodeType()==Node.ELEMENT_NODE){	 
				 this.CONFIG_parameters.put(params.item(i).getNodeName(), ((Element)params.item(i)).getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue());
			 }
			}
	 }
	}
	public static String getCONFIG_connectionName() {
		return CONFIG_connectionName;
	}
	public static void setCONFIG_connectionName(String cONFIG_connectionName) {
		CONFIG_connectionName = cONFIG_connectionName;
	}
	public static String getDescription() {
	 return CONFIG_description;
	}

	public static String getConnectionPath() {
	 return CONFIG_connectionPath;
	}

	public static String getActivityType() {
	 return CONFIG_activityType;
	}
	public  String getHost() {
	 return CONFIG_host;
	}
	public   Map<String, String> getParameters() {
	 return CONFIG_parameters;
	}
	public  String getAuthentication() {
	 return CONFIG_authentication;
	}
	public  int getPort() {
	 return CONFIG_port;
	}
	public static String getCONFIG_description() {
		return CONFIG_description;
	}
	public static String getCONFIG_connectionPath() {
		return CONFIG_connectionPath;
	}
	public static String getCONFIG_activityType() {
		return CONFIG_activityType;
	}
	public int getCONFIG_port() {
		return CONFIG_port;
	}
	public String getCONFIG_host() {
		return CONFIG_host;
	}
	public Map<String, String> getCONFIG_parameters() {
		return CONFIG_parameters;
	}
	public String getCONFIG_authentication() {
		return CONFIG_authentication;
	}
	public static void setCONFIG_description(String cONFIG_description) {
		CONFIG_description = cONFIG_description;
	}
	public static void setCONFIG_connectionPath(String cONFIG_connectionPath) {
		CONFIG_connectionPath = cONFIG_connectionPath;
	}
	public static void setCONFIG_activityType(String cONFIG_activityType) {
		CONFIG_activityType = cONFIG_activityType;
	}
	public void setCONFIG_port(int cONFIG_port) {
		CONFIG_port = cONFIG_port;
	}
	public void setCONFIG_host(String cONFIG_host) {
		CONFIG_host = cONFIG_host;
	}
	public void setCONFIG_parameters(Map<String, String> cONFIG_parameters) {
		CONFIG_parameters = cONFIG_parameters;
	}
	public void setCONFIG_authentication(String cONFIG_authentication) {
		CONFIG_authentication = cONFIG_authentication;
	}
	
	}
	
	
}