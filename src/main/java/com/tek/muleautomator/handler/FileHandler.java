package com.tek.muleautomator.handler;

import org.w3c.dom.Element;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.FileElement;
import com.tek.muleautomator.service.FileService;
import com.tek.muleautomator.util.MuleAutomatorUtil;

public class FileHandler {

	static FileService fileService = new FileService();
	static FileElement fileElement=new FileElement();
	public static void generateMuleFlow(ActivityElement activityElement, String muleProjectLocation, Element flowElement) {
		String activityType=activityElement.getActivityType();
		
		switch(activityType) {
		case "com.tibco.plugin.file.FileCopyActivity":
			System.out.println("com.tibco.plugin.file.FileCopyActivity-----The Copy File activity allows you to copy files and directories to a new location");
			FileElement.FileCopyActivity fileCopyActivity=fileElement.new FileCopyActivity(activityElement.getTargetNode());
			fileService.fileCopy(muleProjectLocation, fileCopyActivity,flowElement);
			break;
			
		case "com.tibco.plugin.file.FileCreateActivity":
			System.out.println("com.tibco.plugin.file.FileCreateActivity-----The Create File activity creates a new file or directory with the specified name. When creating a file, you can also provide the file contents. ");
			FileElement.FileCreateActivity fileCreateActivity=fileElement.new FileCreateActivity(activityElement.getTargetNode());
			fileService.fileCreate(muleProjectLocation, fileCreateActivity,flowElement);
			break;
			
		/*case "com.tibco.plugin.file.FileEventSource":
			System.out.println("com.tibco.plugin.file.FileEventSource-----The File Poller process starter polls for files or directories with the given name and starts a process when the specified change (creation, modification, deletion) is detected. ");
			break;
			
		case "com.tibco.plugin.file.ListFilesActivity":
			System.out.println("com.tibco.plugin.file.ListFilesActivity-----The List Files activity returns information about files or directories, or a listing of all the files in the specified directory. ");
			FileElement.FileListActivity fileListActivity=fileElement.new FileListActivity(activityElement.getTargetNode());
			//fileService.listFiles(muleProjectLocation, fileListActivity,flowElement);
			break;
			
		case "com.tibco.plugin.file.FileSignalIn":
			System.out.println("com.tibco.plugin.file.FileSignalIn-----The Wait for File Change activity waits for a file creation, modification, or deletion event to occur during process execution. When this activity is executed, the process instance suspends and waits for the specified change to occur before resuming.");
			break;*/

		case "com.tibco.plugin.file.FileReadActivity":
			System.out.println("com.tibco.plugin.file.FileReadActivity-----The Read File activity is used to read a file and place its contents into the activity�s output. ");
			FileElement.FileReadActivity fileReadActivity=fileElement.new FileReadActivity(activityElement.getTargetNode());
			fileService.fileRead(muleProjectLocation, fileReadActivity,flowElement);
			break;

		case "com.tibco.plugin.file.FileRemoveActivity":
			System.out.println("com.tibco.plugin.file.FileRemoveActivity-----The Remove File activity removes the specified file. This activity can also remove empty directories. If a directory that is not empty is specified, an exception is thrown. ");
			FileElement.FileRemoveActivity fileDeleteActivity=fileElement.new FileRemoveActivity(activityElement.getTargetNode());
			fileService.fileDelete(muleProjectLocation, fileDeleteActivity,flowElement);
			break;

		case "com.tibco.plugin.file.FileRenameActivity":
			System.out.println("com.tibco.plugin.file.FileRenameActivity-----The Rename File activity is used to rename or move files. This activity can also rename directories, but you cannot use this activity to move a directory to a new location. ");
			FileElement.FileRenameActivity fileRenameActivity=fileElement.new FileRenameActivity(activityElement.getTargetNode());
			fileService.fileRename(muleProjectLocation, fileRenameActivity,flowElement);
			break;

		case "com.tibco.plugin.file.FileWriteActivity":
			System.out.println("com.tibco.plugin.file.FileWriteActivity-----The Write File activity writes content to the specified file.");
			FileElement.FileWriteActivity fileWriteActivity=fileElement.new FileWriteActivity(activityElement.getTargetNode());
			fileService.fileWrite(muleProjectLocation, fileWriteActivity,flowElement);
			break;
		default: MuleAutomatorUtil.loggerElement(activityElement, muleProjectLocation, flowElement);
			break;
		}
	}	

}
