package com.tek.muleautomator.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.tek.muleautomator.config.Connection;
import com.tek.muleautomator.config.HTTPConnection;
import com.tek.muleautomator.element.HTTPElement;
import com.tek.muleautomator.element.HTTPElement.HTTPReceiverActivity;
import com.tek.muleautomator.element.SOAPElement.SOAPEventSourceActivity;
import com.tek.muleautomator.element.SOAPElement.SOAPSendFaultActivity;
import com.tek.muleautomator.element.SOAPElement.SOAPSendReceiveActivity;
import com.tek.muleautomator.element.SOAPElement.SOAPSendReplyActivity;
import com.tek.muleautomator.util.MuleAutomatorConstants;
import com.tek.muleautomator.util.MuleConfigConnection;

public class HTTPService {

	public void httpListnerConfiguration(String muleConfigPath, String conName) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element muleTag = (Element) doc.getFirstChild();		
			ArrayList<HTTPConnection> connections = new ArrayList<>();
			for (Map.Entry<String, Connection> entry : MuleAutomatorConstants.connectionConfigs.entrySet()) {
				if (entry.getValue().getConnectionType().equals("HTTP")) {
					connections.add((HTTPConnection) entry.getValue());
				}
			}	
			if(connections.size()>0){
				for(HTTPConnection con: connections){
					//System.out.println(con+"  comparing:  "+conName);
					if(!con.CONNECTION_NAME.equals(conName))
						continue;
					if(con.IS_CONFIGURED)
						break;
					con.IS_CONFIGURED=true;
					Element httpConfig = doc.createElement("http:listener-config");
					httpConfig.setAttribute("name", con.CONNECTION_NAME.replaceAll(" ", "_"));
					httpConfig.setAttribute("host", con.HOST);
					httpConfig.setAttribute("port", con.PORT);
					httpConfig.setAttribute("doc:name", "HTTP Listener Configuration");
					muleTag.appendChild(httpConfig);
				}
			} else {
				if(MuleAutomatorConstants.configuredConnections.contains("HTTP Listener Configuration")){
					return;
				}
				System.err.println("NO HTTP Connection file found. Setting default config...");
				Element httpConfig = doc.createElement("http:listener-config");
				httpConfig.setAttribute("name", conName);
				httpConfig.setAttribute("host", "0.0.0.0");
				httpConfig.setAttribute("port", "8081");
				httpConfig.setAttribute("doc:name", "HTTP Listener Configuration");
				muleTag.appendChild(httpConfig);
				MuleAutomatorConstants.configuredConnections.add("HTTP Listener Configuration");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void httpRequestConfiguration(String muleConfigPath, String conName) {
		try {
			
			boolean added=false;
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element muleTag = (Element) doc.getFirstChild();		
			ArrayList<HTTPConnection> connections = new ArrayList<>();
			for (Map.Entry<String, Connection> entry : MuleAutomatorConstants.connectionConfigs.entrySet()) {
				if (entry.getValue().getConnectionType().equals("HTTP")) {
					connections.add((HTTPConnection) entry.getValue());
				}
			}	
			if(connections.size()>0){
				for(HTTPConnection con: connections){
					
					if(!con.CONNECTION_NAME.equals(conName))
						continue;
					if(con.IS_CONFIGURED){
						added=true;
						break;
					}
					con.IS_CONFIGURED=true;
					added=true;
					Element httpConfig = doc.createElement("http:request-config");
					httpConfig.setAttribute("name", con.CONNECTION_NAME.replaceAll(" ", "_"));
					httpConfig.setAttribute("host", con.HOST);
					httpConfig.setAttribute("port", con.PORT);
					httpConfig.setAttribute("doc:name", "HTTP Request Configuration");
					muleTag.appendChild(httpConfig);
					return;
				}
			}
			
			
				if(MuleAutomatorConstants.configuredConnections.contains("HTTP Request Configuration")){
					return;
				}
				System.err.println("NO HTTP Connection file found. Setting default config...");
				Element httpConfig = doc.createElement("http:request-config");
				httpConfig.setAttribute("name", "HTTP_Request_Configuration");
				httpConfig.setAttribute("host", "0.0.0.0");
				httpConfig.setAttribute("port", "8081");
				httpConfig.setAttribute("doc:name", "HTTP Request Configuration");
				muleTag.appendChild(httpConfig);
				MuleAutomatorConstants.configuredConnections.add("HTTP Request Configuration");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void httpReciver(String muleConfigPath, HTTPElement.HTTPReceiverActivity httpReceiverActivity,
			Element flow) {

		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);

			
				httpListnerConfiguration(muleConfigPath, HTTPReceiverActivity.getConnectionName());
			
			Element httpListnerRecvier = doc.createElement("http:listener");
			httpListnerRecvier.setAttribute("config-ref", HTTPReceiverActivity.getConnectionName().replaceAll(" ", "_"));
			httpListnerRecvier.setAttribute("path", "/");
			httpListnerRecvier.setAttribute("doc:name", "HTTP");

			flow.appendChild(httpListnerRecvier);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SendHttpRequest(String muleConfigPath, HTTPElement.HTTPSendRequestActivity httpSendRequestActivity,
			Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			
			httpRequestConfiguration(muleConfigPath, httpSendRequestActivity.getCONFIG_connectionName());
			
			Element httpRequest = doc.createElement("http:request");
			httpRequest.setAttribute("config-ref", httpSendRequestActivity.getCONFIG_connectionName());
			httpRequest.setAttribute("path", "/path");
			httpRequest.setAttribute("method", "GET");
			httpRequest.setAttribute("port", ""+httpSendRequestActivity.getPort());
			httpRequest.setAttribute("host", httpSendRequestActivity.getHost());
			httpRequest.setAttribute("doc:name", "HTTP");

			Element requestBuilder = doc.createElement("http:request-builder");
			for(Map.Entry<String, String> param: httpSendRequestActivity.getCONFIG_parameters().entrySet()){
				Element httpParams = doc.createElement("http:query-param");
				httpParams.setAttribute("paramName", param.getKey());
				httpParams.setAttribute("value", param.getValue());
				requestBuilder.appendChild(httpParams);
			}
			httpRequest.appendChild(requestBuilder);
			flow.appendChild(httpRequest);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendHttpResopnse(String muleConfigPath, HTTPElement.HTTPSendResponseActivity httpSendResponseActivity,
			Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element httpResponse = doc.createElement("set-payload");
			if(httpSendResponseActivity.getINPUT_content()!=null)
				httpResponse.setAttribute("value", httpSendResponseActivity.getINPUT_content());
			else
				httpResponse.setAttribute("value", "#[payload]");
			httpResponse.setAttribute("doc:name", "Send Response");

			flow.appendChild(httpResponse);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	

	// ****************************************SOAP to HTTP starts here***************************************************************

	public String webserviceConsumerConfiguration(String muleConfigPath, SOAPSendReceiveActivity soapObj) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);

			Element muleTag = (Element) doc.getFirstChild();
			String append="";
			int i=1;
			while(MuleAutomatorConstants.configuredConnections.contains("Web_Service_Consumer"+append)){
				append=""+i++;
			}
			
			Element webserviceConsumerConfig = doc.createElement("ws:consumer-config");
			webserviceConsumerConfig.setAttribute("name", "Web_Service_Consumer"+append);
			webserviceConsumerConfig.setAttribute("service", soapObj.getCONFIG_service());
			webserviceConsumerConfig.setAttribute("port", soapObj.getCONFIG_port());
			webserviceConsumerConfig.setAttribute("serviceAddress", soapObj.getINPUT_endpointURL());
			webserviceConsumerConfig.setAttribute("wsdlLocation", soapObj.getINPUT_endpointURL()+"?WSDL");
			webserviceConsumerConfig.setAttribute("doc:name", "Web Service Consumer");
			muleTag.appendChild(webserviceConsumerConfig);
			MuleAutomatorConstants.configuredConnections.add("Web_Service_Consumer"+append);
			return "Web_Service_Consumer"+append;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Web_Service_Consumer";
	}

	public void apiKitSoapRouterConfiguration(String muleConfigPath) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);

			Element muleTag = (Element) doc.getFirstChild();

			Element webserviceConsumerConfig = doc.createElement("ws:consumer-config");
			webserviceConsumerConfig.setAttribute("name", "Web_Service_Consumer");
			webserviceConsumerConfig.setAttribute("service", "Calculator");
			webserviceConsumerConfig.setAttribute("port", "CalculatorSoap");
			webserviceConsumerConfig.setAttribute("serviceAddress", "http://www.dneonline.com/calculator.asmx");
			webserviceConsumerConfig.setAttribute("wsdlLocation", "http://www.dneonline.com/calculator.asmx?WSDL");
			webserviceConsumerConfig.setAttribute("doc:name", "Web Service Consumer");
			muleTag.appendChild(webserviceConsumerConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SoapEventSourceToHttp(String muleConfigPath,
			SOAPEventSourceActivity soapEventSourceActivity, Element flow) {

		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);

				httpListnerConfiguration(muleConfigPath,"HTTP_Listener_Configuration");
			Element httpListnerRecvier = doc.createElement("http:listener");
			httpListnerRecvier.setAttribute("config-ref", "HTTP_Listener_Configuration");
			httpListnerRecvier.setAttribute("path", "/pathName");
			httpListnerRecvier.setAttribute("doc:name", "HTTP");

			flow.appendChild(httpListnerRecvier);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SoapRequestReply(String muleConfigPath,
			SOAPSendReceiveActivity soapSendReceiveActivity, Element flow) {

		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);

			httpRequestConfiguration(muleConfigPath,"HTTP_Request_Configuration");
			Element httpListnerRecvier = doc.createElement("http:request");
			httpListnerRecvier.setAttribute("config-ref", "HTTP_Request_Configuration");
			httpListnerRecvier.setAttribute("path", "/pathName");
			httpListnerRecvier.setAttribute("method", "GET, POST");
			
			httpListnerRecvier.setAttribute("doc:name", "HTTP");
			flow.appendChild(httpListnerRecvier);

			String config_ref=webserviceConsumerConfiguration(muleConfigPath, soapSendReceiveActivity);
			
			Element webserviceConsumer = doc.createElement("ws:consumer");
			webserviceConsumer.setAttribute("config-ref", config_ref);
			webserviceConsumer.setAttribute("operation", soapSendReceiveActivity.getCONFIG_operationType());
			webserviceConsumer.setAttribute("doc:name", "Web Service Consumer");
			flow.appendChild(webserviceConsumer);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SoapSendFault(String muleConfigPath,
			SOAPSendFaultActivity soapSendFaultActivity, Element flow) {

		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			apiKitSoapRouterConfiguration(muleConfigPath);
			Element soapFault = doc.createElement("apikit-soap:fault");
			soapFault.setAttribute("config-ref", "APIkit_SOAP__Configuration");
			soapFault.setAttribute("faultType", "GenericSF||1.1");
			soapFault.setAttribute("doc:name", "SOAP Fault");
			flow.appendChild(soapFault);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SoapSendReply(String muleConfigPath,
			SOAPSendReplyActivity soapSendReplyActivity, Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element xmlToJsonTransformer = doc.createElement("json:xml-to-json-transformer");
			xmlToJsonTransformer.setAttribute("doc:name", "XML to JSON");
			flow.appendChild(xmlToJsonTransformer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
