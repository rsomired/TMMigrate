package com.tek.muleautomator.handler;

import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.JDBCElement;
import com.tek.muleautomator.service.JDBCService;
import com.tek.muleautomator.util.MuleAutomatorUtil;

public class JDBCHandler {
	
	public static JDBCService jdbcService = new JDBCService();
	
	public static void generateMuleFlow(ActivityElement activityElement, String muleProjectLocation, Element flowElement) {
		String activityType=activityElement.getActivityType();
		switch(activityType) {
		case "com.tibco.plugin.jdbc.JDBCCallActivity":
			System.out.println("com.tibco.plugin.jdbc.JDBCCallActivity-----The JDBC Call Procedure activity calls a database procedure using the specified JDBC connection");
			JDBCElement.JDBCCallActivity jdbcCallActivity = new JDBCElement.JDBCCallActivity(activityElement.getTargetNode());
			jdbcService.jdbcCallProcedure(muleProjectLocation, jdbcCallActivity, flowElement);
			break;
		case "com.tibco.plugin.jdbc.JDBCQueryActivity":
			System.out.println("com.tibco.plugin.jdbc.JDBCQueryActivity-----The JDBC Call Procedure activity calls a database procedure using the specified JDBC connection");
			JDBCElement.JDBCQueryActivity jdbcQueryActivity = new JDBCElement.JDBCQueryActivity(activityElement.getTargetNode());
			jdbcService.jdbcSelect(muleProjectLocation, jdbcQueryActivity, flowElement);
			break;
		case "com.tibco.plugin.jdbc.JDBCUpdateActivity":
			System.out.println("com.tibco.plugin.jdbc.JDBCUpdateActivity-----The JDBC Call Procedure activity calls a database procedure using the specified JDBC connection");
			JDBCElement.JDBCUpdateActivity jdbcUpdateActivity = new JDBCElement.JDBCUpdateActivity(activityElement.getTargetNode());
			//jdbcService.jdbcInsert(muleProjectLocation, jdbcUpdateActivity, flowElement);
			jdbcService.jdbcUpdate(muleProjectLocation, jdbcUpdateActivity, flowElement);
			//jdbcService.jdbcDelete(muleProjectLocation, jdbcUpdateActivity, flowElement);
			break;	
		/*case "com.tibco.plugin.jdbc.JDBCGeneralActivity":
			System.out.println("com.tibco.plugin.jdbc.JDBCGeneralActivity-----The JDBC Call Procedure activity calls a database procedure using the specified JDBC connection");
			break;	
		case "com.tibco.plugin.jdbc.JDBCGetConnectionActivity":
			System.out.println("com.tibco.plugin.jdbc.JDBCGetConnectionActivity-----The JDBC Get Connection activity retrieves an object reference to a JavaConnectionAccessor object for the specified JDBC Connection from the connection pool.");
			break;*/
		default: MuleAutomatorUtil.loggerElement(activityElement, muleProjectLocation, flowElement);
			break;
		}
	}
	

}
