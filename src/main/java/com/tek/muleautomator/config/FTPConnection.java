package com.tek.muleautomator.config;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.tek.muleautomator.util.MuleAutomatorConstants;

public class FTPConnection extends Connection {

	@Override
	public String toString() {
		return "FTPConnection [USERNAME=" + USERNAME + ", PASSWORD=" + PASSWORD + ", HOST=" + HOST
				+ ", CONNECTION_NAME=" + CONNECTION_NAME + ", PORT=" + PORT + ", TIMEOUT=" + TIMEOUT + "]";
	}

	public String USERNAME, PASSWORD, HOST, CONNECTION_NAME, PORT, TIMEOUT;
	public boolean IS_CONFIGURED;
	
	public void fillDetails(Element rootElement){
		this.CONNECTION_NAME=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("name").item(0).getTextContent());
		this.USERNAME=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("UserName").item(0).getTextContent());
		this.PASSWORD=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("Password").item(0).getTextContent());
		this.HOST=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("Host").item(0).getTextContent());
		this.PORT=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("Port").item(0).getTextContent());
		this.TIMEOUT=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("Timeout").item(0).getTextContent());
	}
	
	public FTPConnection(Node target){
		Element rootElement=(Element)target;
		this.fillDetails(rootElement);
	}
	
	public FTPConnection(File currFile){
		IS_CONFIGURED=false;
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
		return "FTP";
	}

	

}
