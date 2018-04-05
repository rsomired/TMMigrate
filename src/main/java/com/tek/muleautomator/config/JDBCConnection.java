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

public class JDBCConnection extends Connection {

	
	public String CONNECTION_NAME;
	public String USERNAME,PASSWORD, HOST, INSTANCE, DRIVER_TYPE, PORT;
	public int MAX_CONNECTIONS;
	public boolean IS_CONFIGURED;
	
	public JDBCConnection(Node target){
		Element rootElement=(Element)target;
		this.fillDetails(rootElement);
	}
	
	@Override
	public String toString() {
		return "JDBCConnection [CONNECTION_NAME=" + CONNECTION_NAME + ", USERNAME=" + USERNAME + ", PASSWORD="
				+ PASSWORD + ", HOST=" + HOST + ", INSTANCE=" + INSTANCE + ", DRIVER_TYPE=" + DRIVER_TYPE + ", PORT="
				+ PORT + ", MAX_CONNECTIONS=" + MAX_CONNECTIONS + "]";
	}

	public String getConnectionType(){
		return "JDBC";
	}
	
	
	private void fillDetails(Element rootElement){
		this.CONNECTION_NAME=rootElement.getElementsByTagName("name").item(0).getTextContent();
		String drv=rootElement.getElementsByTagName("driver").item(0).getTextContent();
		this.DRIVER_TYPE=drv.substring(drv.lastIndexOf(".")+1, drv.length());
		this.MAX_CONNECTIONS=Integer.parseInt(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("maxConnections").item(0).getTextContent()));
		try{
			this.USERNAME=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("user").item(0).getTextContent());
		} catch (Exception E){
			this.USERNAME="default";
		}
		try{
			this.PASSWORD=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("password").item(0).getTextContent());
		} catch (Exception E){
			this.PASSWORD="admin";
		}
		String t=null;
		try{
			t=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootElement.getElementsByTagName("location").item(0).getTextContent());
		} catch(Exception E){
			t="localhost";
		}
        try{
        	t=t.split("//")[1];
        	this.HOST=t.split(":")[0];
            this.INSTANCE=t.split("=")[1];
            this.PORT=t.split(":")[1].split(";")[0];
        } catch (Exception E){
        	this.HOST=t;
        	this.PORT="1521";
        	this.INSTANCE="XE";
        }
	}
	
	public JDBCConnection(File currFile){
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
