package com.tek.muleautomator.handler;
import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.MailElement;
import com.tek.muleautomator.service.MailService;
import com.tek.muleautomator.util.MuleAutomatorUtil;
public class MailHandler {
	public static void generateMuleFlow(ActivityElement activityElement, String muleConfigPath, Element flowElement) {
		MailService mailService = new MailService();
		String activityType=activityElement.getActivityType();

		switch(activityType) {
		case "com.tibco.plugin.mail.MailEventSource":
			System.out.println("com.tibco.plugin.mail.MailEventSource-----The Receive Mail process starter polls a POP3 mail server for new mail. When new mail is detected and retrieved, the Receive Mail process starter starts a new process for the process definition it resides in and passes the mail data to the next activity in the process flow..");
			MailElement.ReceiveMailActivity receiveMailActivity = new MailElement.ReceiveMailActivity(activityElement.getTargetNode());
			mailService.receiveMail(muleConfigPath, receiveMailActivity, flowElement);
			break;
		case "com.tibco.plugin.mail.MailPubActivity":
			System.out.println("com.tibco.plugin.mail.MailPubactivity-----The Send Mail activity sends an email by way of a SMTP server.");
			MailElement.SendMailActivity sendMailActivity = new MailElement.SendMailActivity(activityElement.getTargetNode());
			mailService.sendMail(muleConfigPath, sendMailActivity, flowElement);
			break;
		default: MuleAutomatorUtil.loggerElement(activityElement, muleConfigPath, flowElement);
		break;
		}
	}
}
