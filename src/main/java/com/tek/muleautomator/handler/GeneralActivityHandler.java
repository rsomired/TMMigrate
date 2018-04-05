package com.tek.muleautomator.handler;

import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.GeneralActivityElement;
import com.tek.muleautomator.service.GeneralActivityService;
import com.tek.muleautomator.util.MuleAutomatorUtil;

public class GeneralActivityHandler {
	public static void generateMuleFlow(ActivityElement activityElement, String muleConfigPath, Element flowElement) {
		GeneralActivityService generalElementService = new GeneralActivityService();
		String activityType = activityElement.getActivityType();
		switch (activityType) {
		case "com.tibco.pe.core.AssignActivity":
			System.out.println(
					"com.tibco.pe.core.AssignActivity-----The Assign activity allows you to assign a value to a user-defined process variable..");
			GeneralActivityElement.AssignActivity assignActivity = new GeneralActivityElement.AssignActivity(
					activityElement.getTargetNode());
			generalElementService.assign(muleConfigPath, assignActivity, flowElement);
			break;
			
			
		case "com.tibco.pe.core.CatchActivity":
			System.out.println(
					"com.tibco.pe.core.CatchActivity----The Catch activity receives control of execution when an unhandled exception occurs. You can select a specific exception type to catch or you can specify that this activity should catch all unhandled exceptions.");
			GeneralActivityElement.CatchActivity catchActivity = new GeneralActivityElement.CatchActivity(
					activityElement.getTargetNode());
			generalElementService.catchService(muleConfigPath, catchActivity, flowElement);
			break;
		case "com.tibco.pe.core.InspectorActivity":
			System.out.println(
					"com.tibco.pe.core.InspectorActivity---The Inspector activity is used to write the output of any or all activities and process variables to a file and/or stdout.");
			GeneralActivityElement.InspectorActivity inspectorActivity = new GeneralActivityElement.InspectorActivity(
					activityElement.getTargetNode());
			generalElementService.inspector(muleConfigPath, inspectorActivity, flowElement);
			break;
		case "com.tibco.plugin.timer.SleepActivity":
			System.out.println(
					"com.tibco.plugin.timer.SleepActivity---The Sleep activity suspends the process on the current transition for the given amount of time");
			GeneralActivityElement.SleepActivity sleepActivity = new GeneralActivityElement.SleepActivity(
					activityElement.getTargetNode());
			generalElementService.sleep(muleConfigPath, sleepActivity, flowElement);
			break;
			
			
		case "com.tibco.plugin.timer.NullActivity":
			GeneralActivityElement.NullActivity nullActivity = new GeneralActivityElement.NullActivity();
			generalElementService.nullactivity(muleConfigPath, nullActivity, flowElement);
			break;	
		case "com.tibco.plugin.timer.TimerEventSource":
			System.out.println(
					"com.tibco.plugin.timer.TimerEventSource---The Timer process starter starts a process at a specific time.");
			GeneralActivityElement.TimerActivity timerActivity = new GeneralActivityElement.TimerActivity(
					activityElement.getTargetNode());
			generalElementService.timer(muleConfigPath, timerActivity, flowElement);
			break;
		case "com.tibco.plugin.mapper.MapperActivity":
			System.out.println(
					"com.tibco.plugin.mapper.MapperActivity--- map data values from the current list of process variables to the elements of the variable added with the Mapper activity. ");
			GeneralActivityElement.MapperActivity mapperActivity = new GeneralActivityElement.MapperActivity(
					activityElement.getTargetNode());
			generalElementService.mapper(muleConfigPath, mapperActivity, flowElement);
			break;
		case "com.tibco.pe.core.SetSharedVariableActivity":
			System.out.println(
					"com.tibco.pe.core.SetSharedVariableActivity------The Set Shared Variable activity allows you to change the value of a shared variable");
			GeneralActivityElement.SharedVariableActivity sharedVariableActivity = new GeneralActivityElement.SharedVariableActivity(
					activityElement.getTargetNode());
			generalElementService.sharedVariable(muleConfigPath, sharedVariableActivity, flowElement);
			break;
		default: MuleAutomatorUtil.loggerElement(activityElement, muleConfigPath, flowElement);
			break;
		}

	}
}