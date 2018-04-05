package com.tek.muleautomator.handler;

import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.SOAPElement;
import com.tek.muleautomator.service.HTTPService;
import com.tek.muleautomator.util.MuleAutomatorUtil;

public class SOAPHandler {

	public static void generateMuleFlow(ActivityElement activityElement, String muleConfigPath, Element flowElement) {
		HTTPService httpService = new HTTPService();
		String activityType=activityElement.getActivityType();

		switch(activityType){

		/*case "com.tibco.plugin.soap.urlaccess.RetrieveResource":
			System.out.println("com.tibco.plugin.soap.urlaccess.RetrieveResource-----The Retrieve Resources activity generates a WSDL file containing a concrete service description of any process definition that has a SOAP Event Source process starter.");
			break;
*/
		case "com.tibco.plugin.soap.SOAPEventSource":
			System.out.println("com.tibco.plugin.soap.SOAPEventSource-----The SOAP Event Source process starter creates a process instance for incoming SOAP requests. SOAP is a standard protocol for invoking web services. This allows you to create a web service using process definitions.");
			SOAPElement.SOAPEventSourceActivity soapEventSourceActivity = new SOAPElement.SOAPEventSourceActivity(activityElement.getTargetNode());
			httpService.SoapEventSourceToHttp(muleConfigPath, soapEventSourceActivity, flowElement);
			break;

		case "com.tibco.plugin.soap.SOAPSendReceiveActivity":
			System.out.println("com.tibco.plugin.soap.SOAPSendReceiveActivity-----The SOAP Request Reply activity performs a request on the specified web service and optionally expects a reply from the web service. You can invoke both document and RPC web services with this activity.");
			//HTTPElement.HTTPSendRequestActivity httpSendRequestActivity = new HTTPElement.HTTPSendRequestActivity(activityElement.getTargetNode());
			SOAPElement.SOAPSendReceiveActivity soapSendReceiveActivity = new SOAPElement.SOAPSendReceiveActivity(activityElement.getTargetNode());
			httpService.SoapRequestReply(muleConfigPath, soapSendReceiveActivity, flowElement);
			break;

		case "com.tibco.plugin.soap.SOAPSendFaultActivity":
			System.out.println("com.tibco.plugin.soap.SOAPSendFaultActivity-----The SOAP Send Fault activity sends a SOAP fault to the client if an error occurs during processing of a SOAP request.");
			//HTTPElement.HTTPSendResponseActivity httpSendResponseActivity= new HTTPElement.HTTPSendResponseActivity(activityElement.getTargetNode());
			SOAPElement.SOAPSendFaultActivity soapSendFaultActivity = new SOAPElement.SOAPSendFaultActivity(activityElement.getTargetNode());
			httpService.SoapSendFault(muleConfigPath, soapSendFaultActivity, flowElement);
			break;

		case "com.tibco.plugin.soap.SOAPSendReplyActivity":
			System.out.println("com.tibco.plugin.soap.SOAPSendReplyActivity-----The SOAP Send Reply activity sends a reply to an application that sent a SOAP request. This activity is primarily used in process definitions that implement web services.");
			SOAPElement.SOAPSendReplyActivity soapSendReplyActivity = new SOAPElement.SOAPSendReplyActivity(activityElement.getTargetNode());
			httpService.SoapSendReply(muleConfigPath, soapSendReplyActivity, flowElement);
			break;
		default: MuleAutomatorUtil.loggerElement(activityElement, muleConfigPath, flowElement);
		break;
		}
	}

}
