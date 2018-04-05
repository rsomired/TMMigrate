package com.tek.muleautomator.service;

import java.util.ArrayList;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tek.muleautomator.config.Connection;
import com.tek.muleautomator.config.WSDLConnection;
import com.tek.muleautomator.element.ServiceElement.InvokePartnerActivity;
import com.tek.muleautomator.util.MuleAutomatorConstants;
import com.tek.muleautomator.util.MuleConfigConnection;

public class ServiceService {

	
	public void webserviceConsumerConfiguration(String muleConfigPath, String conName) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element muleTag = (Element) doc.getFirstChild();		
			ArrayList<WSDLConnection> connections = new ArrayList<>();
			for (Map.Entry<String, Connection> entry : MuleAutomatorConstants.connectionConfigs.entrySet()) {
				if (entry.getValue().getConnectionType().equals("WSDL")) {
					connections.add((WSDLConnection) entry.getValue());
				}
			}	
			if(connections.size()>0){
				for(WSDLConnection con: connections){
					System.out.println(con.CONNECTION_NAME+"  comparing:  "+conName);
					if(!con.CONNECTION_NAME.equals(conName))
						continue;
					if(con.IS_CONFIGURED)
						break;
					con.IS_CONFIGURED=true;
					Element webserviceConsumerConfig = doc.createElement("ws:consumer-config");
					webserviceConsumerConfig.setAttribute("name", conName.replaceAll(" ", "_") );
					webserviceConsumerConfig.setAttribute("service", con.SERVICE_NAME);
					webserviceConsumerConfig.setAttribute("port", con.PORT_NAME);
					webserviceConsumerConfig.setAttribute("serviceAddress", con.ADDRESS_NAME);
					webserviceConsumerConfig.setAttribute("wsdlLocation", con.WSDL_LOCATION);
					webserviceConsumerConfig.setAttribute("doc:name", "Web Service Consumer");
					muleTag.appendChild(webserviceConsumerConfig);
				}
			} else {
				System.err.println("NO WSDL Connection file found.");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	public void webServiceConsumer(String muleConfigPath, InvokePartnerActivity invokePartnerActivity, Element flow){
		webserviceConsumerConfiguration(muleConfigPath, invokePartnerActivity.getCONFIG_partnerConfig());
		
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			
			Element wsConsume=doc.createElement("ws:consumer");
			wsConsume.setAttribute("operation", invokePartnerActivity.getCONFIG_operation());
			wsConsume.setAttribute("config-ref", invokePartnerActivity.getCONFIG_partnerConfig().replaceAll(" ", "_"));
			wsConsume.setAttribute("doc:name", "Web Consumer");
			flow.appendChild(wsConsume);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


}
