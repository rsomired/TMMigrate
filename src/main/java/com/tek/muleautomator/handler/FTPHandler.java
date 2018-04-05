package com.tek.muleautomator.handler;

import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.FTPElement;
import com.tek.muleautomator.service.FTPService;
import com.tek.muleautomator.util.MuleAutomatorUtil;

public class FTPHandler {

	public static void generateMuleFlow(ActivityElement activityElement, String muleProjectLocation, Element flowElement) {
		String activityType=activityElement.getActivityType();
		FTPService ftpService = new FTPService();
		switch(activityType) {
		case "com.tibco.plugin.ftp.FTPGetActivity":
			System.out.println("com.tibco.plugin.ftp.FTPGetActivity-----The FTP Get activity issues an FTP get or mget command to the  specified server. The content of the remote files can be placed in the activity’s output or written directly to local storage. If you choose to place the contents of a remote file into the activity’s output, you can only  retrieve one remote file (FTP get command) and the file’s content will be stored in  memory as part of the activity’s output.");
			FTPElement.FTPGetActivity ftpGetActivity = new FTPElement.FTPGetActivity(activityElement.getTargetNode());
			ftpService.ftpGet(muleProjectLocation, ftpGetActivity, flowElement);
			break;
			
		case "com.tibco.plugin.ftp.FTPPutActivity":
			System.out.println("com.tibco.plugin.ftp.FTPPutActivity-----The FTP Put activity issues an FTP put or mput command to the specified server. You can use process data as the content of the file to send to the remote server or you can send files in local disk storage. If you choose to use process data, you can place only one file on the remote server (FTP put command).");
			FTPElement.FTPPutActivity ftpPutActivity = new FTPElement.FTPPutActivity(activityElement.getTargetNode());
			ftpService.ftpPut(muleProjectLocation, ftpPutActivity, flowElement);
			break;
			
		case "com.tibco.plugin.ftp.FTPRenameActivity":
			System.out.println("com.tibco.plugin.file.FileEventSource-----The File Poller process starter polls for files or directories with the given name and starts a process when the specified change (creation, modification, deletion) is detected. ");
			FTPElement.FTPRenameFileActivity ftpRenameFileActivity = new FTPElement.FTPRenameFileActivity(activityElement.getTargetNode());
			ftpService.ftpRenameFile(muleProjectLocation, ftpRenameFileActivity, flowElement);
			break;
		default: MuleAutomatorUtil.loggerElement(activityElement, muleProjectLocation, flowElement);
			break;	
		}
	}	

}
