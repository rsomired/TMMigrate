package com.tek.muleautomator.handler;

import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.ServiceElement;
import com.tek.muleautomator.service.ServiceService;
import com.tek.muleautomator.util.MuleAutomatorUtil;
public class ServiceHandler {

	public static void generateMuleFlow(ActivityElement activityElement, String muleConfigPath, Element flowElement)
	{

		ServiceService srvService = new ServiceService();
		String activityType=activityElement.getActivityType();
		switch(activityType){
		case "com.tibco.ae.tools.palettes.servicepalette.activities.InvokePartnerActivity":
			System.out.println("com.tibco.ae.tools.palettes.servicepalette.activities.InvokePartnerActivity----The Invoke Partner activity can be used in a BusinessWorks Process Definition to invoke external services over SOAP.");
			ServiceElement.InvokePartnerActivity invokePartnerActivity = new ServiceElement.InvokePartnerActivity(activityElement.getTargetNode());
			srvService.webServiceConsumer(muleConfigPath, invokePartnerActivity, flowElement);
			break;
			/*case "com.tibco.ae.tools.palettes.servicepalette.activities.ReceivePartnerNotificationActivity":
		   System.out.println("com.tibco.ae.tools.palettes.servicepalette.activities.InvokePartnerActivity----The Invoke Partner activity can be used in a BusinessWorks Process Definition to invoke external services over SOAP.");
		   ServiceElement.ReceivePartnerNotificationActivity receivePartnerNotificationActivity = new ServiceElement.ReceivePartnerNotificationActivity(activityElement.getTargetNode());
		   httpService.receivePartnerNotification(muleConfigPath);
		   break;
			 */
		default: MuleAutomatorUtil.loggerElement(activityElement, muleConfigPath, flowElement);
		break;
		}
	}
}
