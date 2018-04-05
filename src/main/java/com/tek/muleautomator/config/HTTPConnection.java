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

public class HTTPConnection extends Connection {

	public String PORT, HOST, CONNECTION_NAME;
	public boolean IS_CONFIGURED;
	@Override
	public String getConnectionType() {
		return "HTTP";
	}
	
	@Override
	public String toString() {
		return "HTTPConnection [PORT=" + PORT + ", HOST=" + HOST + ", CONNECTION_NAME=" + CONNECTION_NAME + "]";
	}

	public HTTPConnection(Node target){
		Element rootElement=(Element)target;
		this.fillDetails(rootElement);
	}
	
	private void fillDetails(Element rootElement){
		try{
			this.PORT=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("Port").item(0).getTextContent());
		} catch (Exception E){
			this.PORT="8081";
		}
		try{
			this.HOST=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("Host").item(0).getTextContent());
		} catch(Exception E){
			this.HOST="localhost";
		}
		
	}
	
	public HTTPConnection(File currFile){
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

}
