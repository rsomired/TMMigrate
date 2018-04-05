package com.tek.muleautomator.element;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.tek.muleautomator.util.MuleAutomatorConstants;
public class MailElement {
  public static class ReceiveMailActivity
  {
     private String CONFIG_description;
  private  String activityType;
  private static boolean CONFIG_deleteMail;
  private static String CONFIG_host,CONFIG_userName,CONFIG_password,CONFIG_SSL,CONFIG_upgradeConfiguration;
  private static int CONFIG_pollingInterval;
  private static String ADV_provideRawMessage,ADV_writetoFile,ADV_directory,ADV_createNonExistingDirectories,ADV_thresholdMessageDataSize;
  private static String Misc_sequencingKey,Misc_customId;
  private static String Out_from,Out_to,Out_cc,Out_replyTo,Out_subject,Out_sentData;
  public ReceiveMailActivity(Node target)
  {
   Element rootActivityElement=(Element)target;
      this.CONFIG_description="The Receive Mail process starter polls a POP3 mail server for new mail. When new mail is detected and retrieved, the Receive Mail process starter starts a new process for the process definition it resides in and passes the mail data to the next activity in the process flow.. ";
      this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
      this.CONFIG_host=rootActivityElement.getElementsByTagName("host").item(0).getTextContent();
      this.CONFIG_userName=rootActivityElement.getElementsByTagName("username").item(0).getTextContent();
      this.CONFIG_pollingInterval=Integer.parseInt(rootActivityElement.getElementsByTagName("pollinginterval").item(0).getTextContent());
   	  this.CONFIG_password=rootActivityElement.getElementsByTagName("password").item(0).getTextContent();
  	  this.CONFIG_deleteMail=rootActivityElement.getElementsByTagName("deletemail").getLength()>0?Boolean.parseBoolean(rootActivityElement.getElementsByTagName("deletemail").item(0).getTextContent()):true;
  	  
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
public static boolean isCONFIG_deleteMail() {
	return CONFIG_deleteMail;
}
public static void setCONFIG_deleteMail(boolean cONFIG_deleteMail) {
	CONFIG_deleteMail = cONFIG_deleteMail;
}
public static String getCONFIG_host() {
	return CONFIG_host;
}
public static void setCONFIG_host(String cONFIG_host) {
	CONFIG_host = cONFIG_host;
}
public static String getCONFIG_userName() {
	return CONFIG_userName;
}
public static void setCONFIG_userName(String cONFIG_userName) {
	CONFIG_userName = cONFIG_userName;
}
public static String getCONFIG_password() {
	return CONFIG_password;
}
public static void setCONFIG_password(String cONFIG_password) {
	CONFIG_password = cONFIG_password;
}
public static String getCONFIG_SSL() {
	return CONFIG_SSL;
}
public static void setCONFIG_SSL(String cONFIG_SSL) {
	CONFIG_SSL = cONFIG_SSL;
}
public static String getCONFIG_upgradeConfiguration() {
	return CONFIG_upgradeConfiguration;
}
public static void setCONFIG_upgradeConfiguration(String cONFIG_upgradeConfiguration) {
	CONFIG_upgradeConfiguration = cONFIG_upgradeConfiguration;
}
public static int getCONFIG_pollingInterval() {
	return CONFIG_pollingInterval;
}
public static void setCONFIG_pollingInterval(int cONFIG_pollingInterval) {
	CONFIG_pollingInterval = cONFIG_pollingInterval;
}
public static String getADV_provideRawMessage() {
	return ADV_provideRawMessage;
}
public static void setADV_provideRawMessage(String aDV_provideRawMessage) {
	ADV_provideRawMessage = aDV_provideRawMessage;
}
public static String getADV_writetoFile() {
	return ADV_writetoFile;
}
public static void setADV_writetoFile(String aDV_writetoFile) {
	ADV_writetoFile = aDV_writetoFile;
}
public static String getADV_directory() {
	return ADV_directory;
}
public static void setADV_directory(String aDV_directory) {
	ADV_directory = aDV_directory;
}
public static String getADV_createNonExistingDirectories() {
	return ADV_createNonExistingDirectories;
}
public static void setADV_createNonExistingDirectories(String aDV_createNonExistingDirectories) {
	ADV_createNonExistingDirectories = aDV_createNonExistingDirectories;
}
public static String getADV_thresholdMessageDataSize() {
	return ADV_thresholdMessageDataSize;
}
public static void setADV_thresholdMessageDataSize(String aDV_thresholdMessageDataSize) {
	ADV_thresholdMessageDataSize = aDV_thresholdMessageDataSize;
}
public static String getMisc_sequencingKey() {
	return Misc_sequencingKey;
}
public static void setMisc_sequencingKey(String misc_sequencingKey) {
	Misc_sequencingKey = misc_sequencingKey;
}
public static String getMisc_customId() {
	return Misc_customId;
}
public static void setMisc_customId(String misc_customId) {
	Misc_customId = misc_customId;
}
public static String getOut_from() {
	return Out_from;
}
public static void setOut_from(String out_from) {
	Out_from = out_from;
}
public static String getOut_to() {
	return Out_to;
}
public static void setOut_to(String out_to) {
	Out_to = out_to;
}
public static String getOut_cc() {
	return Out_cc;
}
public static void setOut_cc(String out_cc) {
	Out_cc = out_cc;
}
public static String getOut_replyTo() {
	return Out_replyTo;
}
public static void setOut_replyTo(String out_replyTo) {
	Out_replyTo = out_replyTo;
}
public static String getOut_subject() {
	return Out_subject;
}
public static void setOut_subject(String out_subject) {
	Out_subject = out_subject;
}
public static String getOut_sentData() {
	return Out_sentData;
}
public static void setOut_sentData(String out_sentData) {
	Out_sentData = out_sentData;
}
  
  }
  public static class SendMailActivity
  {
   private String CONFIG_description;
  private String activityType;
  private static String CONFIG_name,CONFIG_host,CONFIG_authenticate,CONFIG_userName,CONFIG_password,CONFIG_upgradeConfiguration;
  private static String Adv_allowNonStandardEmailIds;
  private static String In_userName,In_password,In_from,In_to,In_cc,In_bcc,In_replyTo,In_subject,In_sentData,In_bodyText;
  public SendMailActivity(Node target){ 
   Element rootActivityElement=(Element)target;
   this.CONFIG_description="The Send Mail activity sends an email by way of a SMTP server.";
   this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
 		this.CONFIG_host=rootActivityElement.getElementsByTagName("host").item(0).getTextContent();
	Element fromElement=(Element)rootActivityElement.getElementsByTagName("from").item(0);
	if(fromElement!=null)
		this.In_from=fromElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
	this.In_from=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.In_from);	
	Element toElement=(Element)rootActivityElement.getElementsByTagName("to").item(0);
		if(toElement!=null)
			this.In_to=fromElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
		this.In_to=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.In_to);
		Element subjectElement=(Element)rootActivityElement.getElementsByTagName("subject").item(0);
		if(subjectElement!=null)
			this.In_subject=subjectElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
		this.In_subject=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.In_subject);
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
public static String getCONFIG_authenticate() {
	return CONFIG_authenticate;
}
public static void setCONFIG_authenticate(String cONFIG_authenticate) {
	CONFIG_authenticate = cONFIG_authenticate;
}
public static String getCONFIG_userName() {
	return CONFIG_userName;
}
public static void setCONFIG_userName(String cONFIG_userName) {
	CONFIG_userName = cONFIG_userName;
}
public static String getCONFIG_password() {
	return CONFIG_password;
}
public static void setCONFIG_password(String cONFIG_password) {
	CONFIG_password = cONFIG_password;
}
public static String getCONFIG_upgradeConfiguration() {
	return CONFIG_upgradeConfiguration;
}
public static void setCONFIG_upgradeConfiguration(String cONFIG_upgradeConfiguration) {
	CONFIG_upgradeConfiguration = cONFIG_upgradeConfiguration;
}
public static String getAdv_allowNonStandardEmailIds() {
	return Adv_allowNonStandardEmailIds;
}
public static void setAdv_allowNonStandardEmailIds(String adv_allowNonStandardEmailIds) {
	Adv_allowNonStandardEmailIds = adv_allowNonStandardEmailIds;
}
public static String getIn_userName() {
	return In_userName;
}
public static void setIn_userName(String in_userName) {
	In_userName = in_userName;
}
public static String getIn_password() {
	return In_password;
}
public static void setIn_password(String in_password) {
	In_password = in_password;
}
public static String getIn_from() {
	return In_from;
}
public static void setIn_from(String in_from) {
	In_from = in_from;
}
public static String getIn_to() {
	return In_to;
}
public static void setIn_to(String in_to) {
	In_to = in_to;
}
public static String getIn_cc() {
	return In_cc;
}
public static void setIn_cc(String in_cc) {
	In_cc = in_cc;
}
public static String getIn_bcc() {
	return In_bcc;
}
public static void setIn_bcc(String in_bcc) {
	In_bcc = in_bcc;
}
public static String getIn_replyTo() {
	return In_replyTo;
}
public static void setIn_replyTo(String in_replyTo) {
	In_replyTo = in_replyTo;
}
public static String getIn_subject() {
	return In_subject;
}
public static void setIn_subject(String in_subject) {
	In_subject = in_subject;
}
public static String getIn_sentData() {
	return In_sentData;
}
public static void setIn_sentData(String in_sentData) {
	In_sentData = in_sentData;
}
public static String getIn_bodyText() {
	return In_bodyText;
}
public static void setIn_bodyText(String in_bodyText) {
	In_bodyText = in_bodyText;
}
  
  }
}