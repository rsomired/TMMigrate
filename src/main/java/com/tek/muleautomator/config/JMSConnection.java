package com.tek.muleautomator.config;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.tek.muleautomator.util.MuleAutomatorConstants;

public class JMSConnection extends Connection {

	public String USERNAME,PASSWORD, PROVIDER_URL, TOPIC_NAME, QUEUE_NAME;
	public String CONNECTION_NAME;
	public boolean IS_CONFIGURED, DURABLE;
	
	
	public JMSConnection(Node target){
		this.IS_CONFIGURED=false;
		this.DURABLE=true;
		Element rootElement=(Element)target;
		this.fillDetails(rootElement);
	}
	
	private void fillDetails(Element rootElement) {
		try{
			this.CONNECTION_NAME=rootElement.getElementsByTagName("name").item(0).getTextContent();
			this.PROVIDER_URL=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("ProviderURL").item(0).getTextContent());
			try{
				this.TOPIC_NAME=rootElement.getElementsByTagName("TopicFactoryName").item(0).getTextContent();
			} catch(Exception E){
				
			}
			try{
				this.QUEUE_NAME=rootElement.getElementsByTagName("QueueConnectionFactory").item(0).getTextContent();
			} catch(Exception E){
				
			}
			this.USERNAME=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("username").item(0).getTextContent());
			this.PASSWORD=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("password").item(0).getTextContent());
			
		} catch(Exception E){
			System.err.println(">>> ERROR OCCURED WHILE FILLING JMS CONNECTION  ");
			E.printStackTrace();
		}
		
	}

	
	public JMSConnection(File currFile){
		this.IS_CONFIGURED=false;
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document jdbcDoc = documentBuilder.parse(currFile);
			this.fillDetails((Element)jdbcDoc.getFirstChild());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getConnectionType() {
		return "JMS";
	}

}
