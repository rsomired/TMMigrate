package com.tek.muleautomator.handler;
import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.JavaElement;
import com.tek.muleautomator.service.JavaService;
import com.tek.muleautomator.util.MuleAutomatorUtil;
public class JavaHandler {
	static JavaService javaService = new JavaService();
	static JavaElement javaElement=new JavaElement();
	public static void generateMuleFlow(ActivityElement activityElement, String muleProjectLocation, Element flowElement) {
		String activityType=activityElement.getActivityType();
		switch(activityType) 
		{
		case "com.tibco.plugin.java.JavaActivity":
			System.out.println("com.tibco.plugin.java.JavaActivity-----This activity allows you to write standard Java code that can manipulate any of the process data or perform any action you choose. ");
			JavaElement.JavaCodeActivity javaCodeActivity=new JavaElement. JavaCodeActivity(activityElement.getTargetNode());
			javaService.javaCode(muleProjectLocation, javaCodeActivity,flowElement);
			break;
		/*case "com.tibco.plugin.java.JavaMethodActivity":
			System.out.println("com.tibco.plugin.java.JavaMethodActivity-----The Java Method activity allows you to invoke a method contained in a Java class. You can construct an instance of the specified Java class, if you choose to invoke the constructor for the class.  ");
			//JavaElement.JavaMethodActivity javaMethodActivity=new JavaElement. JavaMethodActivity(activityElement.getTargetNode());
			//javaService.javaMethod(muleProjectLocation, javaMethodActivity,flowElement);
			break;*/
		default: MuleAutomatorUtil.loggerElement(activityElement, muleProjectLocation, flowElement);
		break;
		}
	}
}
