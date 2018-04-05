package com.tek.muleautomator.handler;

import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.ParseElement;
import com.tek.muleautomator.service.ParseService;
import com.tek.muleautomator.util.MuleAutomatorUtil;

public class ParseHandler {
	public static void generateMuleFlow(ActivityElement activityElement, String muleConfigPath, Element flowElement) {
		ParseService parseService = new ParseService();
		String activityType = activityElement.getActivityType();
		switch (activityType) {
		case "com.tibco.plugin.parse.ParseActivity":
			System.out.println(
					"com.tibco.plugin.parse.ParseActivity-----The Parse Data activity takes a text string or input from a file and processes it, turning it into a schema tree based on the specified Data Format shared configuration. ");
			ParseElement.ParseDataActivity parseDataActivity = new ParseElement.ParseDataActivity(
					activityElement.getTargetNode());
			parseService.parseData(muleConfigPath, parseDataActivity, flowElement);
			break;
		case "com.tibco.plugin.parse.RenderActivity":
			System.out.println(
					"com.tibco.plugin.parse.RenderActivity-----The Render Data activity takes an instance of a data schema and renders it as a text string. The schema processed is based on a specified Data Format shared configuration");
			ParseElement.RenderDataActivity renderDataActivity = new ParseElement.RenderDataActivity(
					activityElement.getTargetNode());
			parseService.renderData(muleConfigPath, renderDataActivity, flowElement);
			break;
		default: MuleAutomatorUtil.loggerElement(activityElement, muleConfigPath, flowElement);
		break;
		}
	}
}
