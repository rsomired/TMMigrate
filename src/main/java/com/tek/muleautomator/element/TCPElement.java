package com.tek.muleautomator.element;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
public class TCPElement {
public static class TCPConnectionActivity{
	 private String CONFIG_description;
	  private  String activityType;
	  private static String CONFIG_name,CONFIG_host,CONFIG_port;
	  private static String Adv_enableDNSLookup;
	 public TCPConnectionActivity(Node target){
		 Element rootActivityElement=(Element)target;
	      this.CONFIG_description="The TCP Connection is a shared configuration resource that specifies the connection information for the TCP server. This resource is used when a process definition acts as a TCP client connecting to a remote server or when a process definition acts as a TCP server accepting incoming TCP connections.";
	      this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
	 }
	public String getCONFIG_description() {
		return CONFIG_description;
	}
	public void setCONFIG_description(String cONFIG_description) {
		CONFIG_description = cONFIG_description;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public static String getCONFIG_name() {
		return CONFIG_name;
	}
	public static void setCONFIG_name(String cONFIG_name) {
		CONFIG_name = cONFIG_name;
	}
	public static String getCONFIG_host() {
		return CONFIG_host;
	}
	public static void setCONFIG_host(String cONFIG_host) {
		CONFIG_host = cONFIG_host;
	}
	public static String getCONFIG_port() {
		return CONFIG_port;
	}
	public static void setCONFIG_port(String config_port) {
		CONFIG_port = config_port;
	}
	public static String getAdv_enableDNSLookup() {
		return Adv_enableDNSLookup;
	}
	public static void setAdv_enableDNSLookup(String adv_enableDNSLookup) {
		Adv_enableDNSLookup = adv_enableDNSLookup;
	}
	 
}
public static class TCPOpenConnectionActivity{
	 private String CONFIG_description;
	  private  String activityType;
	  private static String CONFIG_name,CONFIG_TCPConnection,CONFIG_localHost;
	  private static String In_connection,In_host,In_port;
	  private static boolean Out_connection;
	  public TCPOpenConnectionActivity(Node target){
		  Element rootActivityElement=(Element)target;
	      this.CONFIG_description="The TCP Open Connection activity opens a connection to a TCP server.After establishing the connection, the activity places a handle to the open connection in the connectionKey output element. This connection key can be used by subsequent activities in the process definition to read data from, write data to, or close the connection.";
	     this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();  
	  }
	public String getCONFIG_description() {
		return CONFIG_description;
	}
	public void setCONFIG_description(String cONFIG_description) {
		CONFIG_description = cONFIG_description;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public static String getCONFIG_name() {
		return CONFIG_name;
	}
	public static void setCONFIG_name(String cONFIG_name) {
		CONFIG_name = cONFIG_name;
	}
	public static String getCONFIG_TCPConnection() {
		return CONFIG_TCPConnection;
	}
	public static void setCONFIG_TCPConnection(String cONFIG_TCPConnection) {
		CONFIG_TCPConnection = cONFIG_TCPConnection;
	}
	public static String getCONFIG_localHost() {
		return CONFIG_localHost;
	}
	public static void setCONFIG_localHost(String cONFIG_localHost) {
		CONFIG_localHost = cONFIG_localHost;
	}
	public static String getIn_connection() {
		return In_connection;
	}
	public static void setIn_connection(String in_connection) {
		In_connection = in_connection;
	}
	public static String getIn_host() {
		return In_host;
	}
	public static void setIn_host(String in_host) {
		In_host = in_host;
	}
	public static String getIn_port() {
		return In_port;
	}
	public static void setIn_port(String in_port) {
		In_port = in_port;
	}
	public static boolean isOut_connection() {
		return Out_connection;
	}
	public static void setOut_connection(boolean out_connection) {
		Out_connection = out_connection;
	}
	  
}
}
