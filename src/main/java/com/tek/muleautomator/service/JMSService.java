package com.tek.muleautomator.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.tek.muleautomator.config.Connection;
import com.tek.muleautomator.config.HTTPConnection;
import com.tek.muleautomator.config.JMSConnection;
import com.tek.muleautomator.element.JMSElement.JMSQueueEventSource;
import com.tek.muleautomator.element.JMSElement.JMSQueueRequestReplyActivity;
import com.tek.muleautomator.element.JMSElement.JMSQueueSendActivity;
import com.tek.muleautomator.element.JMSElement.JMSReplyActivity;
import com.tek.muleautomator.element.JMSElement.JMSTopicPublishActivity;
import com.tek.muleautomator.element.JMSElement.JMSTopicRequestReplyActivity;
import com.tek.muleautomator.util.MuleAutomatorConstants;
import com.tek.muleautomator.util.MuleAutomatorUtil;
import com.tek.muleautomator.util.MuleConfigConnection;

public class JMSService {
	
	public void jmsConfiguration(String muleConfigPath, String conName) {	
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element muleTag = (Element) doc.getFirstChild();		
			ArrayList<JMSConnection> connections = new ArrayList<>();
			for (Map.Entry<String, Connection> entry : MuleAutomatorConstants.connectionConfigs.entrySet()) {
				if (entry.getValue().getConnectionType().equals("JMS")) {
					connections.add((JMSConnection) entry.getValue());
				}
			}
			Element jmsConfig=null;
			if(connections.size()>0){
				for(JMSConnection con: connections){
					//System.out.println(con+"  comparing:  "+conName);
					if(!con.CONNECTION_NAME.equals(conName))
						continue;
					if(con.IS_CONFIGURED)
						break;
					con.IS_CONFIGURED=true;
					jmsConfig = doc.createElement("jms:activemq-connector");
					jmsConfig.setAttribute("name", con.CONNECTION_NAME.replaceAll(" ", "_"));
					jmsConfig.setAttribute("brokerURL", con.PROVIDER_URL);
					jmsConfig.setAttribute("username", con.USERNAME);
					jmsConfig.setAttribute("password", con.PASSWORD);
					jmsConfig.setAttribute("validateConnections", "true");
					jmsConfig.setAttribute("doc:name", "Active MQ");
					jmsConfig.setAttribute("durable", "true");
					muleTag.appendChild(jmsConfig);
				}
			} else {
				System.err.println("No JMS-Shared configuration files found in "+MuleAutomatorConstants.TIBCO_PROJECT_ROOT_FOLDER+" \nUsing Default Values");
				jmsConfig = doc.createElement("jms:activemq-connector");
				jmsConfig.setAttribute("name", "Active_MQ");
				jmsConfig.setAttribute("brokerURL", "tcp://localhost:61616");
				jmsConfig.setAttribute("validateConnections", "true");
				jmsConfig.setAttribute("doc:name", "Active MQ");
				muleTag.appendChild(jmsConfig);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	public void jmsSubscribe(String muleConfigPath, Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			if(isJmsConfigRequired(muleConfigPath)){
				//jmsConfiguration(muleConfigPath, flow);
			}
			Element jmsOutBound=doc.createElement("jms:inbound-endpoint");
			jmsOutBound.setAttribute("queue", "sample");
			jmsOutBound.setAttribute("connector-ref", "Active_MQ");
			jmsOutBound.setAttribute("doc:name", "JMSSample");
			jmsOutBound.setAttribute("exchange-pattern", "request-response");
			flow.appendChild(jmsOutBound);
			MuleAutomatorUtil.loggerElement(muleConfigPath, flow);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void jmsQueueReceiver(String muleConfigPath, Element flow, JMSQueueEventSource jmsQueueEventSource) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			jmsConfiguration(muleConfigPath, jmsQueueEventSource.getCONFIG_JMSConnection());

			Element jmsQueueReceiver =doc.createElement("jms:inbound-endpoint");
			jmsQueueReceiver.setAttribute("queue", jmsQueueEventSource.getCONFIG_destinationQueue());
			jmsQueueReceiver.setAttribute("connector-ref", jmsQueueEventSource.getCONFIG_JMSConnection().replaceAll(" ", "_"));
			jmsQueueReceiver.setAttribute("doc:name", "JMS");
			flow.appendChild(jmsQueueReceiver);
			MuleAutomatorUtil.loggerElement(muleConfigPath, flow);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void jmsQueueSender(String muleConfigPath, Element flow, JMSQueueSendActivity jmsQueueSendActivity) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			jmsConfiguration(muleConfigPath, jmsQueueSendActivity.getCONFIG_JMSConnection());
			
			Element payload =doc.createElement("set-payload");
			payload.setAttribute("value", jmsQueueSendActivity.getBodyValue());
			payload.setAttribute("doc:name", "Set Payload Message");
			flow.appendChild(payload);
			
			Element jmsQueueSender =doc.createElement("jms:outbound-endpoint");
			jmsQueueSender.setAttribute("queue", jmsQueueSendActivity.getCONFIG_destinationQueue());
			jmsQueueSender.setAttribute("connector-ref", jmsQueueSendActivity.getCONFIG_JMSConnection().replaceAll(" ", "_"));
			jmsQueueSender.setAttribute("doc:name", "JMSSample");
			flow.appendChild(jmsQueueSender);
			MuleAutomatorUtil.loggerElement(muleConfigPath, flow);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void jmsQueueRequestor(String muleConfigPath, Element flow, JMSQueueRequestReplyActivity jmsQueueRequestReplyActivity) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			
			jmsConfiguration(muleConfigPath, jmsQueueRequestReplyActivity.getCONFIG_JMSConnection());

			Element payload =doc.createElement("set-payload");
			payload.setAttribute("value", jmsQueueRequestReplyActivity.getBodyValue());
			payload.setAttribute("doc:name", "Set Payload Message");
			flow.appendChild(payload);
			
			
			Element jmsQueueReceiver =doc.createElement("jms:outbound-endpoint");
			jmsQueueReceiver.setAttribute("queue", jmsQueueRequestReplyActivity.getCONFIG_destinationQueue());
			jmsQueueReceiver.setAttribute("connector-ref", jmsQueueRequestReplyActivity.getCONFIG_JMSConnection().replaceAll(" ", "_"));
			jmsQueueReceiver.setAttribute("doc:name", "JMSSample");
			jmsQueueReceiver.setAttribute("exchange-pattern", "request-response");
			flow.appendChild(jmsQueueReceiver);
			MuleAutomatorUtil.loggerElement(muleConfigPath, flow);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void jmsTopicPublisher(String muleConfigPath, Element flow, JMSTopicPublishActivity jmsTopicPublishActivity) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			
			jmsConfiguration(muleConfigPath, jmsTopicPublishActivity.getCONFIG_JMSConnection());


			Element payload =doc.createElement("set-payload");
			payload.setAttribute("value", jmsTopicPublishActivity.getINP_body());
			payload.setAttribute("doc:name", "Set Payload Message");
			flow.appendChild(payload);
			
			
			Element jmsTopicPublisher =doc.createElement("jms:outbound-endpoint");
			jmsTopicPublisher.setAttribute("connector-ref", jmsTopicPublishActivity.getCONFIG_JMSConnection().replaceAll(" ", "_"));
			jmsTopicPublisher.setAttribute("doc:name", "JMSSample");
			jmsTopicPublisher.setAttribute("topic", jmsTopicPublishActivity.getCONFIG_destinationTopic());
			flow.appendChild(jmsTopicPublisher);
			MuleAutomatorUtil.loggerElement(muleConfigPath, flow);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void jmsTopicRequestor(String muleConfigPath, Element flow, JMSTopicRequestReplyActivity jmsTopicRequestReplyActivity) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			
			jmsConfiguration(muleConfigPath, jmsTopicRequestReplyActivity.getCONFIG_JMSConnection());
			
			Element jmsQueueReceiver =doc.createElement("jms:outbound-endpoint");
			jmsQueueReceiver.setAttribute("connector-ref", jmsTopicRequestReplyActivity.getCONFIG_JMSConnection().replaceAll(" ", "_"));
			jmsQueueReceiver.setAttribute("doc:name", "JMSSample");
			jmsQueueReceiver.setAttribute("topic", jmsTopicRequestReplyActivity.getCONFIG_destinationTopic());
			jmsQueueReceiver.setAttribute("exchange-pattern", "request-response");
			flow.appendChild(jmsQueueReceiver);
			MuleAutomatorUtil.loggerElement(muleConfigPath, flow);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void replyToJmsMessage(String muleConfigPath, Element flow, JMSReplyActivity jmsReplyActivity) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element replyToJmsMessage =doc.createElement("set-payload");
			replyToJmsMessage.setAttribute("value", "#[payload]");
			replyToJmsMessage.setAttribute("doc:name", "Set Payload");
			flow.appendChild(replyToJmsMessage);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public boolean isJmsConfigRequired(String muleConfigPath) throws ParserConfigurationException, SAXException, IOException {
		Document doc;
		try {
			doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element muleTag=(Element)doc.getFirstChild();
			return muleTag.getElementsByTagName("jms:activemq-connector").getLength()>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
}
