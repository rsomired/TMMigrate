package com.tek.muleautomator.config;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tek.muleautomator.util.MuleAutomatorConstants;

public class WSDLConnection extends Connection {

	public String PORT_NAME, SERVICE_NAME, ADDRESS_NAME, WSDL_LOCATION, CONNECTION_NAME;
	public boolean IS_CONFIGURED=false;
	
	public WSDLConnection(File currFile){
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document wsdlDoc = documentBuilder.parse(currFile);
			this.fillDetails((Element)wsdlDoc.getFirstChild());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		} catch (IOException e) {
			//TODO Auto-generated catch block
			  e.printStackTrace();
		} catch (Exception E){
			E.printStackTrace();
		}
 
	}
	
	
	public void fillDetails(Element rootElement){
		Element row=(Element)((Element)rootElement.getElementsByTagName("partners").item(0)).getElementsByTagName("row").item(0);
		String loc=row.getAttribute("endpoint");
		this.PORT_NAME=loc.split("#")[1];
		loc=loc.split("#")[0];
		loc=loc.substring(loc.lastIndexOf("/")+1, loc.length());
		this.WSDL_LOCATION=MuleAutomatorConstants.muleResourcesPath+loc;
		
		try {
			DocumentBuilderFactory fact=DocumentBuilderFactory.newInstance();
			DocumentBuilder dbuilder=fact.newDocumentBuilder();
			File file = new File(WSDL_LOCATION);
			Document d=dbuilder.parse(file);
			Element serviceNode=((Element)d.getElementsByTagName("wsdl:service").item(0));
			NodeList ports=null;
			try{
				ports=serviceNode.getElementsByTagName("wsdl:port");
			} catch (Exception E){
				System.err.println(">>> Error while reading WSDL configuration. Service Palette may contain invalid values.");
				this.SERVICE_NAME="AUTOMATER_GENERATED_SERVICE";
				this.ADDRESS_NAME="AUTOMATER_GENERATED_ADDRESS";
				return;
			}
			for(int i=0;i<ports.getLength();++i){
				Element currPort=(Element)ports.item(i);
				if(currPort.getAttribute("name").equals(this.PORT_NAME)){
					this.SERVICE_NAME=serviceNode.getAttribute("name");
					Element add=(Element)currPort.getElementsByTagName("soap:address").item(0);
					this.ADDRESS_NAME=add.getAttribute("location");
					break;
				}
			}
			
			
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		return "WSDL";
	}

}
