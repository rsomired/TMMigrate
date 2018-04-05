package com.tek.muleautomator.handler;

import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.HTTPElement;
import com.tek.muleautomator.service.HTTPService;
import com.tek.muleautomator.util.MuleAutomatorUtil;

public class HTTPHandler {

	public static void generateMuleFlow(ActivityElement activityElement, String muleConfigPath, Element flowElement) {
		HTTPService httpService = new HTTPService();
		String activityType=activityElement.getActivityType();
		
		switch(activityType){
		case "com.tibco.plugin.http.HTTPEventSource":
			System.out.println("com.tibco.plugin.http.HTTPEventSource-----Starts a process based on the receipt of a HTTP request.");
			HTTPElement.HTTPReceiverActivity httpReceiverActivity = new HTTPElement.HTTPReceiverActivity(activityElement.getTargetNode());
			httpService.httpReciver(muleConfigPath, httpReceiverActivity, flowElement);
			break;
			
		case "com.tibco.plugin.http.client.HttpRequestActivity":
			System.out.println("com.tibco.plugin.http.client.HttpRequestActivity-----The Send HTTP Request activity sends a HTTP request to a web server.");
			HTTPElement.HTTPSendRequestActivity httpSendRequestActivity = new HTTPElement.HTTPSendRequestActivity(activityElement.getTargetNode());
			httpService.SendHttpRequest(muleConfigPath, httpSendRequestActivity, flowElement);
			break;
			
		case "com.tibco.plugin.http.HTTPResponseActivity":
			System.out.println("com.tibco.plugin.http.HTTPResponseActivity-----Sends a response to a previously received HTTP request. Thisvactivity is used in conjunction with the HTTP Receiver process starter or the Wait for HTTP Request activity");
			HTTPElement.HTTPSendResponseActivity httpSendResponseActivity= new HTTPElement.HTTPSendResponseActivity(activityElement.getTargetNode());
			httpService.sendHttpResopnse(muleConfigPath, httpSendResponseActivity, flowElement);
			break;
			
		/*case "com.tibco.plugin.http.HTTPSignalInActivity":
			System.out.println("com.tibco.plugin.http.HTTPResponseActivity-----Waits for an incoming HTTP request in a process definition. The process instance suspends until the incoming HTTP request is received.");
			break;*/
		default: MuleAutomatorUtil.loggerElement(activityElement, muleConfigPath, flowElement);
			break;
		}
	}

}
