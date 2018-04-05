package com.tek.muleautomator.handler;
import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.XMLElement;
import com.tek.muleautomator.service.XMLService;
import com.tek.muleautomator.util.MuleAutomatorUtil;
public class XMLHandler {
	public static void generateMuleFlow(ActivityElement activityElement, String muleConfigPath, Element flowElement) {
		XMLService xmlService = new XMLService();
		String activityType=activityElement.getActivityType();

		switch(activityType){
		case "com.tibco.plugin.xml.XMLRendererActivity":
			System.out.println("com.tibco.plugin.xml.XMLRendererActivity-----The Render XML activity takes an instance of an XML schema element and renders it as a stream of bytes containing XML or an XML string. The schema is processed based on the XSD file specified.");
			XMLElement.XMLRendererActivity xmlRendererActivity = new XMLElement.XMLRendererActivity(activityElement.getTargetNode());
			xmlService.rendererXML(muleConfigPath,xmlRendererActivity, flowElement);
			break;
		case "com.tibco.plugin.mail.MailPubActivity":
			System.out.println("com.tibco.plugin.mail.MailPubactivity-----The Transform XML activity allows you to transform an input XML document into the output specified by the given XSLT File shared configuration resource.");
			XMLElement.XMLTransformActivity xmlTransformActivity = new XMLElement.XMLTransformActivity(activityElement.getTargetNode());
			xmlService.transformXML(muleConfigPath, xmlTransformActivity, flowElement);
			break;
		default: MuleAutomatorUtil.loggerElement(activityElement, muleConfigPath, flowElement);
		break;
		}
	}
}
