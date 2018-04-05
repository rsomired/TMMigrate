package com.tek.muleautomator.handler;
import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.TCPElement;
import com.tek.muleautomator.service.TCPService;
import com.tek.muleautomator.util.MuleAutomatorUtil;
public class TCPHandler {
	public static void generateMuleFlow(ActivityElement activityElement, String muleConfigPath, Element flowElement) {
		TCPService tcpService = new TCPService();
		String activityType=activityElement.getActivityType();

		switch(activityType){
		case "com.tibco.plugin.tcp.MailEventSource":
			System.out.println("com.tibco.plugin.mail.MailEventSource-----The TCP Connection is a shared configuration resource that specifies the connection information for the TCP server. This resource is used when a process definition acts as a TCP client connecting to a remote server or when a process definition acts as a TCP server accepting incoming TCP connections.");
			TCPElement.TCPConnectionActivity tcpConnectionActivity = new TCPElement.TCPConnectionActivity(activityElement.getTargetNode());
			tcpService.tcpConnection(muleConfigPath, tcpConnectionActivity, flowElement);
			break;
		case "com.tibco.plugin.tcp.MailPubActivity":
			System.out.println("com.tibco.plugin.mail.MailPubactivity-----The TCP Open Connection activity opens a connection to a TCP server. After establishing the connection, the activity places a handle to the open connection in the connectionKey output element. This connection key can be used by subsequent activities in the process definition to read data from, write data to, or close the connection.");
			TCPElement.TCPOpenConnectionActivity tcpOpenConnectionActivity = new TCPElement.TCPOpenConnectionActivity(activityElement.getTargetNode());
			tcpService.tcpOpenConnection(muleConfigPath,tcpOpenConnectionActivity, flowElement);
			break;
		default: MuleAutomatorUtil.loggerElement(activityElement, muleConfigPath, flowElement);
		break;
		}
	}
}
