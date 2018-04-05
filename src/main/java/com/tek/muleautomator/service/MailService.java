package com.tek.muleautomator.service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tek.muleautomator.element.MailElement;
import com.tek.muleautomator.element.MailElement.SendMailActivity;
import com.tek.muleautomator.util.MuleConfigConnection;
public class MailService {
  public void receiveMail(String muleConfigPath,MailElement.ReceiveMailActivity receiveMailActivity, Element flow){
	  try {
		   Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
		   Element connecterRef=doc.createElement("pop3:connector");
		   connecterRef.setAttribute("name", "POP3");
		   connecterRef.setAttribute("validateConnections", "true");
		   connecterRef.setAttribute("doc:name", "POP3");
		   doc.getFirstChild().appendChild(connecterRef);
		   Element el=(Element)doc.getFirstChild();
		   el.setAttribute("xmlns:pop3", "http://www.mulesoft.org/schema/mule/pop3");
		     
		   Element pop3 = doc.createElement("pop3:inbound-endpoint");
		   pop3.setAttribute("xsi:schemaLocation", "http://www.mulesoft.org/schema/mule/pop3 http://www.mulesoft.org/schema/mule/pop3/current/mule-pop3.xsd");
		   pop3.setAttribute("host",receiveMailActivity.getCONFIG_host());
		   pop3.setAttribute("port", "995");
		   pop3.setAttribute("user",receiveMailActivity.getCONFIG_userName());
		   pop3.setAttribute("connector-ref", "POP3");
		   pop3.setAttribute("password",receiveMailActivity.getCONFIG_password() );
		   pop3.setAttribute("responseTimeout", "10000");
		   pop3.setAttribute("doc:name", "POP3");
		   flow.appendChild(pop3);
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
  }
  public void sendMail(String muleConfigPath,SendMailActivity sendMailActivity, Element flow){
	  try {
		   Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
		   Element connecterRef=doc.createElement("smtp:connector");
		   connecterRef.setAttribute("name", "smtp1");
		   connecterRef.setAttribute("validateConnections", "true");
		   connecterRef.setAttribute("doc:name", "SMTP");
		   doc.getFirstChild().appendChild(connecterRef);
		   Element el=(Element)doc.getFirstChild();
		   el.setAttribute("xmlns:smtp", "http://www.mulesoft.org/schema/mule/smtp");
		     
		   Element smtp = doc.createElement("smtp:outbound-endpoint");
		   smtp.setAttribute("xsi:schemaLocation", "http://www.mulesoft.org/schema/mule/smtp http://www.mulesoft.org/schema/mule/smtp/current/mule-smtp.xsd");
		   smtp.setAttribute("host",sendMailActivity.getCONFIG_host());
		   smtp.setAttribute("port", "465");
		   smtp.setAttribute("user","not-available");
		   smtp.setAttribute("connector-ref", "smtp");
		   smtp.setAttribute("password","not-available");
		   smtp.setAttribute("to", sendMailActivity.getIn_to());
		   smtp.setAttribute("from", sendMailActivity.getIn_from());
		   smtp.setAttribute("subject", sendMailActivity.getIn_subject());
		   smtp.setAttribute("cc", sendMailActivity.getIn_cc());
		   smtp.setAttribute("responseTimeout", "10000");
		   smtp.setAttribute("doc:name", "SMTP");
		   flow.appendChild(smtp);
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
  }
}
