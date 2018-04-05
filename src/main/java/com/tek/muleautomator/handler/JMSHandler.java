package com.tek.muleautomator.handler;

import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.JMSElement;
import com.tek.muleautomator.service.JMSService;
import com.tek.muleautomator.util.MuleAutomatorUtil;

public class JMSHandler {

	

	public static void generateMuleFlow(ActivityElement activityElement, String muleConfigPath, Element flowElement) {
		JMSService jmsService = new JMSService();
		String activityType=activityElement.getActivityType();

		switch(activityType){
		case "com.tibco.plugin.jms.JMSQueueEventSource":
			System.out.println("com.tibco.plugin.jms.JMSQueueEventSource-----This actvity will be used to receive jms messages from jms/tibco ems servers");
			JMSElement.JMSQueueEventSource jmsQueueEventSource = new JMSElement.JMSQueueEventSource(activityElement.getTargetNode());
			jmsService.jmsQueueReceiver(muleConfigPath, flowElement, jmsQueueEventSource);
			break;
		case "com.tibco.plugin.jms.JMSQueueSendActivity":
			System.out.println("This is used to send jms messages to jms/tibco ems servers this is not a blocking activity i.e it will not wait for response from consumer");
			JMSElement.JMSQueueSendActivity jmsQueueSendActivity = new JMSElement.JMSQueueSendActivity(activityElement.getTargetNode());
			jmsService.jmsQueueSender(muleConfigPath, flowElement, jmsQueueSendActivity);
			break;
		case "com.tibco.plugin.jms.JMSQueueRequestReplyActivity":
			System.out.println("This activity is used to send request and wait for response, in request reply scenarios we use this activity");
			JMSElement.JMSQueueRequestReplyActivity jmsQueueRequestReplyActivity = new JMSElement.JMSQueueRequestReplyActivity(activityElement.getTargetNode());
			jmsService.jmsQueueRequestor(muleConfigPath, flowElement, jmsQueueRequestReplyActivity);
			break;
		case "com.tibco.plugin.jms.JMSTopicPublishActivity":
			System.out.println("This is used to send jms messages to jms/tibco ems servers this is not a blocking activity i.e it will not wait for response from consumer");
			JMSElement.JMSTopicPublishActivity jmsTopicPublishActivity = new JMSElement.JMSTopicPublishActivity(activityElement.getTargetNode());
			jmsService.jmsTopicPublisher(muleConfigPath, flowElement, jmsTopicPublishActivity);
			break;
		case "com.tibco.plugin.jms.JMSTopicRequestReplyActivity":
			System.out.println("This activity is used to send request and wait for response, In request reply scenarios we use this activity");
			JMSElement.JMSTopicRequestReplyActivity jmsTopicRequestReplyActivity = new JMSElement.JMSTopicRequestReplyActivity(activityElement.getTargetNode());
			jmsService.jmsTopicRequestor(muleConfigPath, flowElement, jmsTopicRequestReplyActivity);
			break;
		/*case "com.tibco.plugin.jms.JMSTopicSignalInActivity":
			System.out.println("This acitivity is used in scenarios where we have asyncronous pattern.This actvity will wait for messages on a topic");
			break;
		case "com.tibco.plugin.jms.JMSQueueSignalInActivity":
			System.out.println("This acitiviyt is used in scenarios where we have asyncronous pattern.This actvity will wait for messages on a queue");
			break;
		case "com.tibco.plugin.jms.JMSQueueGetMessageActivity":
			System.out.println("This activity is used in scenarios where we need to pull messages from a queue on demand");
			break;*/
		case "com.tibco.plugin.jms.JMSReplyActivity":
			System.out.println("com.tibco.plugin.jms.JMSReplyActivity-----This activity is used while impelementing synchronous pattern, inorder to send response back to requestor");
			JMSElement.JMSReplyActivity jmsReplyActivity = new JMSElement.JMSReplyActivity(activityElement.getTargetNode());
			jmsService.replyToJmsMessage(muleConfigPath, flowElement, jmsReplyActivity);
			break;
		default: MuleAutomatorUtil.loggerElement(activityElement, muleConfigPath, flowElement);
			break;
		}
		
		
	}
	
}
