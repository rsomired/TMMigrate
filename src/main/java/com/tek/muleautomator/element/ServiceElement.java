package com.tek.muleautomator.element;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
public class ServiceElement {
	
	
public static class InvokePartnerActivity{
 private String CONFIG_partner;
 private String CONFIG_operation;
 private String CONFIG_partnerConfig;
 private Map<String, String> IN_inputMessage;
 private String wsdlFileName;
 public String getCONFIG_partnerConfig() {
	return CONFIG_partnerConfig;
}
public void setCONFIG_partnerConfig(String cONFIG_partnerConfig) {
	CONFIG_partnerConfig = cONFIG_partnerConfig;
}
private Map<String, String> OUT_outputMessage;
 private static String activityType;
    private static String CONFIG_description;
    public InvokePartnerActivity(Node node){
     Element rootActivityElement = (Element)node;
     this.CONFIG_description="The Invoke Partner activity can be used in a BusinessWorks Process Definition to invoke external services over SOAP.";
     this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
     this.CONFIG_partner=rootActivityElement.getElementsByTagName("partner").item(0).getTextContent();
     this.CONFIG_operation=rootActivityElement.getElementsByTagName("operation").item(0).getTextContent();
     
     Element partnerElement=(Element)((Element)rootActivityElement.getParentNode()).getElementsByTagName("pd:partners").item(0);
     String x=partnerElement.getElementsByTagName("partnerConfig").item(0).getTextContent();
     this.CONFIG_partnerConfig = x.substring(x.lastIndexOf("/")+1, x.lastIndexOf("#")).split("\\.")[0];
     
     String wsdlPath=partnerElement.getElementsByTagName("partnerInterfaceLocation").item(0).getTextContent();
     wsdlFileName=wsdlPath.substring(wsdlPath.lastIndexOf("/"), wsdlPath.length());
    }
 public String getWsdlFileName() {
		return wsdlFileName;
	}
	public void setWsdlFileName(String wsdlFileName) {
		this.wsdlFileName = wsdlFileName;
	}
public String getCONFIG_partner() {
  return CONFIG_partner;
 }
 public void setCONFIG_partner(String cONFIG_partner) {
  CONFIG_partner = cONFIG_partner;
 }
 public String getCONFIG_operation() {
  return CONFIG_operation;
 }
 public void setCONFIG_operation(String cONFIG_operation) {
  CONFIG_operation = cONFIG_operation;
 }
 public Map<String, String> getIN_inputMessage() {
  return IN_inputMessage;
 }
 public void setIN_inputMessage(Map<String, String> iN_inputMessage) {
  IN_inputMessage = iN_inputMessage;
 }
 public Map<String, String> getOUT_outputMessage() {
  return OUT_outputMessage;
 }
 public void setOUT_outputMessage(Map<String, String> oUT_outputMessage) {
  OUT_outputMessage = oUT_outputMessage;
 }
 public static String getActivityType() {
  return activityType;
 }
 public static void setActivityType(String activityType) {
  InvokePartnerActivity.activityType = activityType;
 }
 public static String getCONFIG_description() {
  return CONFIG_description;
 }
 public static void setCONFIG_description(String cONFIG_description) {
  CONFIG_description = cONFIG_description;
 }
}


public static class ReceivePartnerNotification{
 private static String activityType;
    private static String CONFIG_description;
    private String CONFIG_partner;
 //Node CONFIG_parent;
 private String CONFIG_operation;
 private Map<String, String> OUT_outputMessage;
 private Map<String, String> MISC_sequencingKey,MISC_customId;
 public ReceivePartnerNotification(Node node){
     Element rootActivityElement = (Element)node;
     this.CONFIG_description="The Receive Partner Notification activity can be used in an ActiveMatrix BusinessWorks Process Definition to invoke notification services over SOAP. The notification service sends a message that is received by the invoker.";
     this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
     this.CONFIG_partner=rootActivityElement.getElementsByTagName("partner").item(0).getTextContent();
     this.CONFIG_operation=rootActivityElement.getElementsByTagName("operation").item(0).getTextContent();
 }
 public static String getActivityType() {
  return activityType;
 }
 public static void setActivityType(String activityType) {
  ReceivePartnerNotification.activityType = activityType;
 }
 public static String getCONFIG_description() {
  return CONFIG_description;
 }
 public static void setCONFIG_description(String cONFIG_description) {
  CONFIG_description = cONFIG_description;
 }
 public String getCONFIG_partner() {
  return CONFIG_partner;
 }
 public void setCONFIG_partner(String cONFIG_partner) {
  CONFIG_partner = cONFIG_partner;
 }
 public String getCONFIG_operation() {
  return CONFIG_operation;
 }
 public void setCONFIG_operation(String cONFIG_operation) {
  CONFIG_operation = cONFIG_operation;
 }
 public Map<String, String> getOUT_outputMessage() {
  return OUT_outputMessage;
 }
 public void setOUT_outputMessage(Map<String, String> oUT_outputMessage) {
  OUT_outputMessage = oUT_outputMessage;
 }
 public Map<String, String> getMISC_sequencingKey() {
  return MISC_sequencingKey;
 }
 public void setMISC_sequencingKey(Map<String, String> mISC_sequencingKey) {
  MISC_sequencingKey = mISC_sequencingKey;
 }
 public Map<String, String> getMISC_customId() {
  return MISC_customId;
 }
 public void setMISC_customId(Map<String, String> mISC_customId) {
  MISC_customId = mISC_customId;
 }
}
}