package com.tek.muleautomator.element;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.tek.muleautomator.util.MuleAutomatorConstants;
public class FTPElement {

	public static class FTPChangeDefaultDirectoryActivity
	{
		private static String description, connectionPath, activityType;
		private List<String> CONFIG_FTPConnection;
		private String CONFIG_quit;
		private String IN_host,IN_userName,IN_password,IN_newDefaultDir;
		private int IN_port,IN_timeout;
		public FTPChangeDefaultDirectoryActivity(Node targetNode)
		{
			FTPChangeDefaultDirectoryActivity.description="The FTP Change Default Directory activity changes the current default directory on the remote machine to the specified directory path. ";
			Element rootActivityElement = (Element)targetNode;
			FTPChangeDefaultDirectoryActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
		}
		public static String getDescription() {
			return description;
		}
		public static void setDescription(String description) {
			FTPChangeDefaultDirectoryActivity.description = description;
		}
		public static String getConnectionPath() {
			return connectionPath;
		}
		public static void setConnectionPath(String connectionPath) {
			FTPChangeDefaultDirectoryActivity.connectionPath = connectionPath;
		}
		public static String getActivityType() {
			return activityType;
		}
		public static void setActivityType(String activityType) {
			FTPChangeDefaultDirectoryActivity.activityType = activityType;
		}
		public List<String> getCONFIG_FTPConnection() {
			return CONFIG_FTPConnection;
		}
		public void setCONFIG_FTPConnection(List<String> cONFIG_FTPConnection) {
			CONFIG_FTPConnection = cONFIG_FTPConnection;
		}
		public String getCONFIG_quit() {
			return CONFIG_quit;
		}
		public void setCONFIG_quit(String cONFIG_quit) {
			CONFIG_quit = cONFIG_quit;
		}
		public String getIN_host() {
			return IN_host;
		}
		public void setIN_host(String iN_host) {
			IN_host = iN_host;
		}
		public String getIN_userName() {
			return IN_userName;
		}
		public void setIN_userName(String iN_userName) {
			IN_userName = iN_userName;
		}
		public String getIN_password() {
			return IN_password;
		}
		public void setIN_password(String iN_password) {
			IN_password = iN_password;
		}
		public String getIN_newDefaultDir() {
			return IN_newDefaultDir;
		}
		public void setIN_newDefaultDir(String iN_newDefaultDir) {
			IN_newDefaultDir = iN_newDefaultDir;
		}
		public int getIN_port() {
			return IN_port;
		}
		public void setIN_port(int iN_port) {
			IN_port = iN_port;
		}
		public int getIN_timeout() {
			return IN_timeout;
		}
		public void setIN_timeout(int iN_timeout) {
			IN_timeout = iN_timeout;
		}
		
	}
	public static class FTPDeleteFileActivity
	{
		private static String description, activityType;
		private List<String> CONFIG_FTPConnection;
		private String CONFIG_quit;
		private String IN_host,IN_userName,IN_password,IN_remoteDirectory,IN_remoteFileName;
		private int IN_port,IN_timeout;
		public FTPDeleteFileActivity(Node targetNode)
		{
			FTPDeleteFileActivity.description="The FTP Delete File activity issues an FTP delete or delete command to remove one or more files from the remote server";
			Element rootActivityElement = (Element)targetNode;
			FTPDeleteFileActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
		}
		public static String getDescription() {
			return description;
		}
		public static void setDescription(String description) {
			FTPDeleteFileActivity.description = description;
		}
		public static String getActivityType() {
			return activityType;
		}
		public static void setActivityType(String activityType) {
			FTPDeleteFileActivity.activityType = activityType;
		}
		public List<String> getCONFIG_FTPConnection() {
			return CONFIG_FTPConnection;
		}
		public void setCONFIG_FTPConnection(List<String> cONFIG_FTPConnection) {
			CONFIG_FTPConnection = cONFIG_FTPConnection;
		}
		public String getCONFIG_quit() {
			return CONFIG_quit;
		}
		public void setCONFIG_quit(String cONFIG_quit) {
			CONFIG_quit = cONFIG_quit;
		}
		public String getIN_host() {
			return IN_host;
		}
		public void setIN_host(String iN_host) {
			IN_host = iN_host;
		}
		public String getIN_userName() {
			return IN_userName;
		}
		public void setIN_userName(String iN_userName) {
			IN_userName = iN_userName;
		}
		public String getIN_password() {
			return IN_password;
		}
		public void setIN_password(String iN_password) {
			IN_password = iN_password;
		}
		public String getIN_remoteDirectory() {
			return IN_remoteDirectory;
		}
		public void setIN_remoteDirectory(String iN_remoteDirectory) {
			IN_remoteDirectory = iN_remoteDirectory;
		}
		public String getIN_remoteFileName() {
			return IN_remoteFileName;
		}
		public void setIN_remoteFileName(String iN_remoteFileName) {
			IN_remoteFileName = iN_remoteFileName;
		}
		public int getIN_port() {
			return IN_port;
		}
		public void setIN_port(int iN_port) {
			IN_port = iN_port;
		}
		public int getIN_timeout() {
			return IN_timeout;
		}
		public void setIN_timeout(int iN_timeout) {
			IN_timeout = iN_timeout;
		}
		
	}
	public static class FTPDirActivity
	{
		private static String description, activityType, connectionName;
		private List<String> CONFIG_FTPConnection;
		private String CONFIG_quit,CONFIG_NLST;
		private int IN_port,IN_timeout;
		private String IN_host,IN_userName,IN_password,IN_directory,IN_dirParameters,IN_directoryElement,IN_dirParametersElement;
		private int OUT_itemCount;
		private String OUT_directoryItems;
		public FTPDirActivity(Node targetNode)
		{
			FTPDirActivity.description="The FTP DIR activity provides a listing of files in the specified directory of the FTP server. ";
			Element rootActivityElement = (Element)targetNode;
			FTPDirActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
			FTPDirActivity.activityType=rootActivityElement.getElementsByTagName("NLST").item(0).getTextContent();
			Element IN_directoryElement=(Element)rootActivityElement.getElementsByTagName("Directory").item(0);
			if(IN_directoryElement!=null)
				this.IN_directory=IN_directoryElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
        	this.IN_directory=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.IN_directory);
			this.IN_directoryElement=this.IN_directory.substring(this.IN_directory.lastIndexOf("/")+1);
			
        	Element IN_dirParametersElement=(Element)rootActivityElement.getElementsByTagName("DirParameters").item(0);
			if(IN_dirParametersElement!=null)
        		this.IN_dirParameters=IN_dirParametersElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
        	this.IN_dirParameters=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.IN_dirParameters);
			this.IN_dirParametersElement=this.IN_dirParameters.substring(this.IN_dirParameters.lastIndexOf("/")+1);
			String con=rootActivityElement.getElementsByTagName("SharedUserData").item(0).getTextContent();
        	this.connectionName=con.substring(con.lastIndexOf("/")+1, con.lastIndexOf("."));
        	
		}
		public static String getConnectionName() {
			return connectionName;
		}
		public static void setConnectionName(String connectionName) {
			FTPDirActivity.connectionName = connectionName;
		}
		public static String getDescription() {
			return description;
		}
		public static void setDescription(String description) {
			FTPDirActivity.description = description;
		}
		public static String getActivityType() {
			return activityType;
		}
		public static void setActivityType(String activityType) {
			FTPDirActivity.activityType = activityType;
		}
		public List<String> getCONFIG_FTPConnection() {
			return CONFIG_FTPConnection;
		}
		public void setCONFIG_FTPConnection(List<String> cONFIG_FTPConnection) {
			CONFIG_FTPConnection = cONFIG_FTPConnection;
		}
		public String getCONFIG_quit() {
			return CONFIG_quit;
		}
		public void setCONFIG_quit(String cONFIG_quit) {
			CONFIG_quit = cONFIG_quit;
		}
		public String getCONFIG_NLST() {
			return CONFIG_NLST;
		}
		public void setCONFIG_NLST(String cONFIG_NLST) {
			CONFIG_NLST = cONFIG_NLST;
		}
		public int getIN_port() {
			return IN_port;
		}
		public void setIN_port(int iN_port) {
			IN_port = iN_port;
		}
		public int getIN_timeout() {
			return IN_timeout;
		}
		public void setIN_timeout(int iN_timeout) {
			IN_timeout = iN_timeout;
		}
		public String getIN_host() {
			return IN_host;
		}
		public void setIN_host(String iN_host) {
			IN_host = iN_host;
		}
		public String getIN_userName() {
			return IN_userName;
		}
		public void setIN_userName(String iN_userName) {
			IN_userName = iN_userName;
		}
		public String getIN_password() {
			return IN_password;
		}
		public void setIN_password(String iN_password) {
			IN_password = iN_password;
		}
		public String getIN_directory() {
			return IN_directory;
		}
		public void setIN_directory(String iN_directory) {
			IN_directory = iN_directory;
		}
		public String getIN_dirParameters() {
			return IN_dirParameters;
		}
		public void setIN_dirParameters(String iN_dirParameters) {
			IN_dirParameters = iN_dirParameters;
		}
		public String getIN_directoryElement() {
			return IN_directoryElement;
		}
		public void setIN_directoryElement(String iN_directoryElement) {
			IN_directoryElement = iN_directoryElement;
		}
		public String getIN_dirParametersElement() {
			return IN_dirParametersElement;
		}
		public void setIN_dirParametersElement(String iN_dirParametersElement) {
			IN_dirParametersElement = iN_dirParametersElement;
		}
		public int getOUT_itemCount() {
			return OUT_itemCount;
		}
		public void setOUT_itemCount(int oUT_itemCount) {
			OUT_itemCount = oUT_itemCount;
		}
		public String getOUT_directoryItems() {
			return OUT_directoryItems;
		}
		public void setOUT_directoryItems(String oUT_directoryItems) {
			OUT_directoryItems = oUT_directoryItems;
		}
		
	}
	public static class FTPGetDefaultDirectoryActivity
	{
		private static String description,  activityType;
		private List<String> CONFIG_FTPConnection;
		private String CONFIG_quit;
		private String IN_host,IN_userName,IN_password;
		private int IN_port,IN_timeout;
		private String OUT_currentDirectory;
		public FTPGetDefaultDirectoryActivity(Node targetNode)
		{
			FTPGetDefaultDirectoryActivity.description= "The FTP Get Default Directory activity retrieves the name of the current remote directory. The default remote directory is operating system-dependent and determined by the remote FTP server. On UNIX systems, the default remote directory is usually the home directory of the user account that is used to establish an FTP connection";
			Element rootActivityElement = (Element)targetNode;
			FTPGetDefaultDirectoryActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
		}
		public static String getDescription() {
			return description;
		}
		public static void setDescription(String description) {
			FTPGetDefaultDirectoryActivity.description = description;
		}
		public static String getActivityType() {
			return activityType;
		}
		public static void setActivityType(String activityType) {
			FTPGetDefaultDirectoryActivity.activityType = activityType;
		}
		public List<String> getCONFIG_FTPConnection() {
			return CONFIG_FTPConnection;
		}
		public void setCONFIG_FTPConnection(List<String> cONFIG_FTPConnection) {
			CONFIG_FTPConnection = cONFIG_FTPConnection;
		}
		public String getCONFIG_quit() {
			return CONFIG_quit;
		}
		public void setCONFIG_quit(String cONFIG_quit) {
			CONFIG_quit = cONFIG_quit;
		}
		public String getIN_host() {
			return IN_host;
		}
		public void setIN_host(String iN_host) {
			IN_host = iN_host;
		}
		public String getIN_userName() {
			return IN_userName;
		}
		public void setIN_userName(String iN_userName) {
			IN_userName = iN_userName;
		}
		public String getIN_password() {
			return IN_password;
		}
		public void setIN_password(String iN_password) {
			IN_password = iN_password;
		}
		public int getIN_port() {
			return IN_port;
		}
		public void setIN_port(int iN_port) {
			IN_port = iN_port;
		}
		public int getIN_timeout() {
			return IN_timeout;
		}
		public void setIN_timeout(int iN_timeout) {
			IN_timeout = iN_timeout;
		}
		public String getOUT_currentDirectory() {
			return OUT_currentDirectory;
		}
		public void setOUT_currentDirectory(String oUT_currentDirectory) {
			OUT_currentDirectory = oUT_currentDirectory;
		}
		
	}
	
	public static class FTPGetActivity
	{
		private static String description,  activityType, connectionName;
		private List<String> CONFIG_FTPConnection;
		private String CONFIG_quit,CONFIG_useProcessData,CONFIG_binary,CONFIG_overwriteExistingFile,CONFIG_maintainCompatibility;
		private int IN_port,IN_timeout;
		private String IN_host,IN_userName,IN_password,IN_remoteFilename,IN_localFilename,IN_encoding,IN_remoteFileElement,IN_localFileElement;
		private String OUT_data,OUT_errorMsg;
		private List<String> OUT_filesTransferred,OUT_success;
		private int OUT_name,OUT_numOfBytes;
		public FTPGetActivity(Node targetNode)
		{
			FTPGetActivity.description="The FTP Get activity issues an FTP get or mget command to the specified server. The content of the remote files can be placed in the activity’s output or written directly to local storage. If you choose to place the contents of a remote file into the activity’s output, you can only retrieve one remote file (FTP get command) and the file’s content will be stored in	memory as part of the activity’s output.";
			Element rootActivityElement = (Element)targetNode;
			FTPGetActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
			FTPGetActivity.activityType=rootActivityElement.getElementsByTagName("Timeout").item(0).getTextContent();
			FTPGetActivity.activityType=rootActivityElement.getElementsByTagName("isBinary").item(0).getTextContent();
			FTPGetActivity.activityType=rootActivityElement.getElementsByTagName("Overwrite").item(0).getTextContent();
			FTPGetActivity.activityType=rootActivityElement.getElementsByTagName("useProcessData").item(0).getTextContent();
			Element IN_remoteFileElement=(Element)rootActivityElement.getElementsByTagName("RemoteFileName").item(0);
			if(IN_remoteFileElement!=null)
        		this.IN_remoteFilename=IN_remoteFileElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
			 this.IN_remoteFilename=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.IN_remoteFilename);
			 this.IN_remoteFileElement=this.IN_remoteFilename.substring(this.IN_remoteFilename.lastIndexOf("/")+1);
        	Element IN_localFileElement=(Element)rootActivityElement.getElementsByTagName("LocalFileName").item(0);
			if(IN_localFileElement!=null)
        		this.IN_localFilename=IN_localFileElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
			this.IN_localFilename=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.IN_localFilename);
			this.IN_localFileElement=this.IN_localFilename.substring(this.IN_localFilename.lastIndexOf("/")+1);
			String con=rootActivityElement.getElementsByTagName("SharedUserData").item(0).getTextContent();
        	this.connectionName=con.substring(con.lastIndexOf("/")+1, con.lastIndexOf("."));
        	
		}
		public static String getConnectionName() {
			return connectionName;
		}
		public static void setConnectionName(String connectionName) {
			FTPGetActivity.connectionName = connectionName;
		}
		public static String getDescription() {
			return description;
		}
		public static void setDescription(String description) {
			FTPGetActivity.description = description;
		}
		public static String getActivityType() {
			return activityType;
		}
		public static void setActivityType(String activityType) {
			FTPGetActivity.activityType = activityType;
		}
		public List<String> getCONFIG_FTPConnection() {
			return CONFIG_FTPConnection;
		}
		public void setCONFIG_FTPConnection(List<String> cONFIG_FTPConnection) {
			CONFIG_FTPConnection = cONFIG_FTPConnection;
		}
		public String getCONFIG_quit() {
			return CONFIG_quit;
		}
		public void setCONFIG_quit(String cONFIG_quit) {
			CONFIG_quit = cONFIG_quit;
		}
		public String getCONFIG_useProcessData() {
			return CONFIG_useProcessData;
		}
		public void setCONFIG_useProcessData(String cONFIG_useProcessData) {
			CONFIG_useProcessData = cONFIG_useProcessData;
		}
		public String getCONFIG_binary() {
			return CONFIG_binary;
		}
		public void setCONFIG_binary(String cONFIG_binary) {
			CONFIG_binary = cONFIG_binary;
		}
		public String getCONFIG_overwriteExistingFile() {
			return CONFIG_overwriteExistingFile;
		}
		public void setCONFIG_overwriteExistingFile(String cONFIG_overwriteExistingFile) {
			CONFIG_overwriteExistingFile = cONFIG_overwriteExistingFile;
		}
		public String getCONFIG_maintainCompatibility() {
			return CONFIG_maintainCompatibility;
		}
		public void setCONFIG_maintainCompatibility(String cONFIG_maintainCompatibility) {
			CONFIG_maintainCompatibility = cONFIG_maintainCompatibility;
		}
		public int getIN_port() {
			return IN_port;
		}
		public void setIN_port(int iN_port) {
			IN_port = iN_port;
		}
		public int getIN_timeout() {
			return IN_timeout;
		}
		public void setIN_timeout(int iN_timeout) {
			IN_timeout = iN_timeout;
		}
		public String getIN_host() {
			return IN_host;
		}
		public void setIN_host(String iN_host) {
			IN_host = iN_host;
		}
		public String getIN_userName() {
			return IN_userName;
		}
		public void setIN_userName(String iN_userName) {
			IN_userName = iN_userName;
		}
		public String getIN_password() {
			return IN_password;
		}
		public void setIN_password(String iN_password) {
			IN_password = iN_password;
		}
		public String getIN_remoteFilename() {
			return IN_remoteFilename;
		}
		public void setIN_remoteFilename(String iN_remoteFilename) {
			IN_remoteFilename = iN_remoteFilename;
		}
		public String getIN_localFilename() {
			return IN_localFilename;
		}
		public void setIN_localFilename(String iN_localFilename) {
			IN_localFilename = iN_localFilename;
		}
		public String getIN_encoding() {
			return IN_encoding;
		}
		public void setIN_encoding(String iN_encoding) {
			IN_encoding = iN_encoding;
		}
		public String getIN_remoteFileElement() {
			return IN_remoteFileElement;
		}
		public void setIN_remoteFileElement(String iN_remoteFileElement) {
			IN_remoteFileElement = iN_remoteFileElement;
		}
		public String getIN_localFileElement() {
			return IN_localFileElement;
		}
		public void setIN_localFileElement(String iN_localFileElement) {
			IN_localFileElement = iN_localFileElement;
		}
		public String getOUT_data() {
			return OUT_data;
		}
		public void setOUT_data(String oUT_data) {
			OUT_data = oUT_data;
		}
		public String getOUT_errorMsg() {
			return OUT_errorMsg;
		}
		public void setOUT_errorMsg(String oUT_errorMsg) {
			OUT_errorMsg = oUT_errorMsg;
		}
		public List<String> getOUT_filesTransferred() {
			return OUT_filesTransferred;
		}
		public void setOUT_filesTransferred(List<String> oUT_filesTransferred) {
			OUT_filesTransferred = oUT_filesTransferred;
		}
		public List<String> getOUT_success() {
			return OUT_success;
		}
		public void setOUT_success(List<String> oUT_success) {
			OUT_success = oUT_success;
		}
		public int getOUT_name() {
			return OUT_name;
		}
		public void setOUT_name(int oUT_name) {
			OUT_name = oUT_name;
		}
		public int getOUT_numOfBytes() {
			return OUT_numOfBytes;
		}
		public void setOUT_numOfBytes(int oUT_numOfBytes) {
			OUT_numOfBytes = oUT_numOfBytes;
		}
		
	}
	public static class FTPMakeRemoteDirectoryActivity
	{
		private static String description,  activityType;
		private List<String> CONFIG_FTPConnection;
		private String CONFIG_quit;
		private int IN_port,IN_timeout;
		private String IN_host,IN_userName,IN_password,IN_remoteSiteCommand,IN_parentRemoteDirectory,IN_remoteDirName;
		public FTPMakeRemoteDirectoryActivity(Node targetNode)
		{
			FTPMakeRemoteDirectoryActivity.description="The FTP Make Remote Directory activity creates the specified directory on the remote FTP server. ";
			Element rootActivityElement = (Element)targetNode;
			FTPMakeRemoteDirectoryActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
		}
		public static String getDescription() {
			return description;
		}
		public static void setDescription(String description) {
			FTPMakeRemoteDirectoryActivity.description = description;
		}
		public static String getActivityType() {
			return activityType;
		}
		public static void setActivityType(String activityType) {
			FTPMakeRemoteDirectoryActivity.activityType = activityType;
		}
		public List<String> getCONFIG_FTPConnection() {
			return CONFIG_FTPConnection;
		}
		public void setCONFIG_FTPConnection(List<String> cONFIG_FTPConnection) {
			CONFIG_FTPConnection = cONFIG_FTPConnection;
		}
		public String getCONFIG_quit() {
			return CONFIG_quit;
		}
		public void setCONFIG_quit(String cONFIG_quit) {
			CONFIG_quit = cONFIG_quit;
		}
		public int getIN_port() {
			return IN_port;
		}
		public void setIN_port(int iN_port) {
			IN_port = iN_port;
		}
		public int getIN_timeout() {
			return IN_timeout;
		}
		public void setIN_timeout(int iN_timeout) {
			IN_timeout = iN_timeout;
		}
		public String getIN_host() {
			return IN_host;
		}
		public void setIN_host(String iN_host) {
			IN_host = iN_host;
		}
		public String getIN_userName() {
			return IN_userName;
		}
		public void setIN_userName(String iN_userName) {
			IN_userName = iN_userName;
		}
		public String getIN_password() {
			return IN_password;
		}
		public void setIN_password(String iN_password) {
			IN_password = iN_password;
		}
		public String getIN_remoteSiteCommand() {
			return IN_remoteSiteCommand;
		}
		public void setIN_remoteSiteCommand(String iN_remoteSiteCommand) {
			IN_remoteSiteCommand = iN_remoteSiteCommand;
		}
		public String getIN_parentRemoteDirectory() {
			return IN_parentRemoteDirectory;
		}
		public void setIN_parentRemoteDirectory(String iN_parentRemoteDirectory) {
			IN_parentRemoteDirectory = iN_parentRemoteDirectory;
		}
		public String getIN_remoteDirName() {
			return IN_remoteDirName;
		}
		public void setIN_remoteDirName(String iN_remoteDirName) {
			IN_remoteDirName = iN_remoteDirName;
		}
		
	}
	public static class FTPPutActivity
	{
		private static String description, activityType, connectionName;
		private List<String> CONFIG_FTPConnection;
		private String CONFIG_quit,CONFIG_useProcessData,CONFIG_binary,CONFIG_overwriteExistingFile,CONFIG_maintainCompatibility,CONFIG_append;
		private int IN_port,IN_timeout;
		private String IN_host,IN_userName,IN_password,IN_remoteFilename,IN_localFilename,IN_encoding,IN_remoteFileElement,IN_localFileElement;
		private String OUT_errorMsg;
		private List<String> OUT_filesTransferred,OUT_success;
		public FTPPutActivity(Node targetNode)
		{
			FTPPutActivity.description="The FTP Put activity issues an FTP put or mput command to the specified server. You can use process data as the content of the file to send to the remote server or you can send files in local disk storage. If you choose to use process data, you can place only one file on the	remote server (FTP put command).";
			Element rootActivityElement = (Element)targetNode;
			FTPPutActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
			this.CONFIG_append=rootActivityElement.getElementsByTagName("Append").item(0).getTextContent();
			this.IN_timeout=Integer.parseInt(rootActivityElement.getElementsByTagName("Timeout").item(0).getTextContent());
			this.CONFIG_binary=rootActivityElement.getElementsByTagName("isBinary").item(0).getTextContent();
			this.CONFIG_overwriteExistingFile=rootActivityElement.getElementsByTagName("Overwrite").item(0).getTextContent();
			this.CONFIG_useProcessData=rootActivityElement.getElementsByTagName("useProcessData").item(0).getTextContent();
			Element IN_remoteFileElement=(Element)rootActivityElement.getElementsByTagName("RemoteFileName").item(0);
			if(IN_remoteFileElement!=null)
        		this.IN_remoteFilename=IN_remoteFileElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
			this.IN_remoteFilename=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.IN_remoteFilename);
			this.IN_remoteFileElement=this.IN_remoteFilename.substring(this.IN_remoteFilename.lastIndexOf("/")+1);
        	Element IN_localFileElement=(Element)rootActivityElement.getElementsByTagName("LocalFileName").item(0);
			if(IN_localFileElement!=null)
        		this.IN_localFilename=IN_localFileElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
			this.IN_localFilename=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.IN_localFilename);
			this.IN_localFileElement=this.IN_localFilename.substring(this.IN_localFilename.lastIndexOf("/")+1);
			String con=rootActivityElement.getElementsByTagName("SharedUserData").item(0).getTextContent();
        	this.connectionName=con.substring(con.lastIndexOf("/")+1, con.lastIndexOf("."));
        	
		}
		public static String getConnectionName() {
			return connectionName;
		}
		public static void setConnectionName(String connectionName) {
			FTPPutActivity.connectionName = connectionName;
		}
		public static String getDescription() {
			return description;
		}
		public static void setDescription(String description) {
			FTPPutActivity.description = description;
		}
		public static String getActivityType() {
			return activityType;
		}
		public static void setActivityType(String activityType) {
			FTPPutActivity.activityType = activityType;
		}
		public List<String> getCONFIG_FTPConnection() {
			return CONFIG_FTPConnection;
		}
		public void setCONFIG_FTPConnection(List<String> cONFIG_FTPConnection) {
			CONFIG_FTPConnection = cONFIG_FTPConnection;
		}
		public String getCONFIG_quit() {
			return CONFIG_quit;
		}
		public void setCONFIG_quit(String cONFIG_quit) {
			CONFIG_quit = cONFIG_quit;
		}
		public String getCONFIG_useProcessData() {
			return CONFIG_useProcessData;
		}
		public void setCONFIG_useProcessData(String cONFIG_useProcessData) {
			CONFIG_useProcessData = cONFIG_useProcessData;
		}
		public String getCONFIG_binary() {
			return CONFIG_binary;
		}
		public void setCONFIG_binary(String cONFIG_binary) {
			CONFIG_binary = cONFIG_binary;
		}
		public String getCONFIG_overwriteExistingFile() {
			return CONFIG_overwriteExistingFile;
		}
		public void setCONFIG_overwriteExistingFile(String cONFIG_overwriteExistingFile) {
			CONFIG_overwriteExistingFile = cONFIG_overwriteExistingFile;
		}
		public String getCONFIG_maintainCompatibility() {
			return CONFIG_maintainCompatibility;
		}
		public void setCONFIG_maintainCompatibility(String cONFIG_maintainCompatibility) {
			CONFIG_maintainCompatibility = cONFIG_maintainCompatibility;
		}
		public String getCONFIG_append() {
			return CONFIG_append;
		}
		public void setCONFIG_append(String cONFIG_append) {
			CONFIG_append = cONFIG_append;
		}
		public int getIN_port() {
			return IN_port;
		}
		public void setIN_port(int iN_port) {
			IN_port = iN_port;
		}
		public int getIN_timeout() {
			return IN_timeout;
		}
		public void setIN_timeout(int iN_timeout) {
			IN_timeout = iN_timeout;
		}
		public String getIN_host() {
			return IN_host;
		}
		public void setIN_host(String iN_host) {
			IN_host = iN_host;
		}
		public String getIN_userName() {
			return IN_userName;
		}
		public void setIN_userName(String iN_userName) {
			IN_userName = iN_userName;
		}
		public String getIN_password() {
			return IN_password;
		}
		public void setIN_password(String iN_password) {
			IN_password = iN_password;
		}
		public String getIN_remoteFilename() {
			return IN_remoteFilename;
		}
		public void setIN_remoteFilename(String iN_remoteFilename) {
			IN_remoteFilename = iN_remoteFilename;
		}
		public String getIN_localFilename() {
			return IN_localFilename;
		}
		public void setIN_localFilename(String iN_localFilename) {
			IN_localFilename = iN_localFilename;
		}
		public String getIN_encoding() {
			return IN_encoding;
		}
		public void setIN_encoding(String iN_encoding) {
			IN_encoding = iN_encoding;
		}
		public String getIN_remoteFileElement() {
			return IN_remoteFileElement;
		}
		public void setIN_remoteFileElement(String iN_remoteFileElement) {
			IN_remoteFileElement = iN_remoteFileElement;
		}
		public String getIN_localFileElement() {
			return IN_localFileElement;
		}
		public void setIN_localFileElement(String iN_localFileElement) {
			IN_localFileElement = iN_localFileElement;
		}
		public String getOUT_errorMsg() {
			return OUT_errorMsg;
		}
		public void setOUT_errorMsg(String oUT_errorMsg) {
			OUT_errorMsg = oUT_errorMsg;
		}
		public List<String> getOUT_filesTransferred() {
			return OUT_filesTransferred;
		}
		public void setOUT_filesTransferred(List<String> oUT_filesTransferred) {
			OUT_filesTransferred = oUT_filesTransferred;
		}
		public List<String> getOUT_success() {
			return OUT_success;
		}
		public void setOUT_success(List<String> oUT_success) {
			OUT_success = oUT_success;
		}
		
	}
	public static class FTPQuoteActivity
	{
		private static String description,  activityType;
		private List<String> CONFIG_FTPConnection;
		private String CONFIG_quit,CONFIG_hasSocketData;
		private int IN_port,IN_timeout;
		private String IN_host,IN_userName,IN_password,IN_remoteSiteCommand,IN_validReturnCode,IN_RemoteDirName;
		private String OUT_commandItems;
		public FTPQuoteActivity(Node targetNode)
		{
			FTPQuoteActivity.description="The FTP Quote activity sends an arbitrary FTP command to the FTP server. You can determine which FTP commands are supported by using the Available Commands button on the FTP Connection shared configuration resource.";

			Element rootActivityElement = (Element)targetNode;
			FTPQuoteActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
		}
		public static String getDescription() {
			return description;
		}
		public static void setDescription(String description) {
			FTPQuoteActivity.description = description;
		}
		public static String getActivityType() {
			return activityType;
		}
		public static void setActivityType(String activityType) {
			FTPQuoteActivity.activityType = activityType;
		}
		public List<String> getCONFIG_FTPConnection() {
			return CONFIG_FTPConnection;
		}
		public void setCONFIG_FTPConnection(List<String> cONFIG_FTPConnection) {
			CONFIG_FTPConnection = cONFIG_FTPConnection;
		}
		public String getCONFIG_quit() {
			return CONFIG_quit;
		}
		public void setCONFIG_quit(String cONFIG_quit) {
			CONFIG_quit = cONFIG_quit;
		}
		public String getCONFIG_hasSocketData() {
			return CONFIG_hasSocketData;
		}
		public void setCONFIG_hasSocketData(String cONFIG_hasSocketData) {
			CONFIG_hasSocketData = cONFIG_hasSocketData;
		}
		public int getIN_port() {
			return IN_port;
		}
		public void setIN_port(int iN_port) {
			IN_port = iN_port;
		}
		public int getIN_timeout() {
			return IN_timeout;
		}
		public void setIN_timeout(int iN_timeout) {
			IN_timeout = iN_timeout;
		}
		public String getIN_host() {
			return IN_host;
		}
		public void setIN_host(String iN_host) {
			IN_host = iN_host;
		}
		public String getIN_userName() {
			return IN_userName;
		}
		public void setIN_userName(String iN_userName) {
			IN_userName = iN_userName;
		}
		public String getIN_password() {
			return IN_password;
		}
		public void setIN_password(String iN_password) {
			IN_password = iN_password;
		}
		public String getIN_remoteSiteCommand() {
			return IN_remoteSiteCommand;
		}
		public void setIN_remoteSiteCommand(String iN_remoteSiteCommand) {
			IN_remoteSiteCommand = iN_remoteSiteCommand;
		}
		public String getIN_validReturnCode() {
			return IN_validReturnCode;
		}
		public void setIN_validReturnCode(String iN_validReturnCode) {
			IN_validReturnCode = iN_validReturnCode;
		}
		public String getIN_RemoteDirName() {
			return IN_RemoteDirName;
		}
		public void setIN_RemoteDirName(String iN_RemoteDirName) {
			IN_RemoteDirName = iN_RemoteDirName;
		}
		public String getOUT_commandItems() {
			return OUT_commandItems;
		}
		public void setOUT_commandItems(String oUT_commandItems) {
			OUT_commandItems = oUT_commandItems;
		}
		
	}
	public static class FTPRemoveRemoteDirectoryActivity
	{
		private static String description, activityType;
		private List<String> CONFIG_FTPConnection;
		private String CONFIG_quit;
		private int IN_port,IN_timeout;
		private String IN_host,IN_userName,IN_password,IN_parentRemoteDirectory,IN_remoteDirName;
		public FTPRemoveRemoteDirectoryActivity(Node targetNode)
		{
			FTPRemoveRemoteDirectoryActivity.description="The FTP Remove Remote Directory activity deletes the specified directory from the remote FTP server. ";

			Element rootActivityElement = (Element)targetNode;
			FTPRemoveRemoteDirectoryActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
		}
		public static String getDescription() {
			return description;
		}
		public static void setDescription(String description) {
			FTPRemoveRemoteDirectoryActivity.description = description;
		}
		public static String getActivityType() {
			return activityType;
		}
		public static void setActivityType(String activityType) {
			FTPRemoveRemoteDirectoryActivity.activityType = activityType;
		}
		public List<String> getCONFIG_FTPConnection() {
			return CONFIG_FTPConnection;
		}
		public void setCONFIG_FTPConnection(List<String> cONFIG_FTPConnection) {
			CONFIG_FTPConnection = cONFIG_FTPConnection;
		}
		public String getCONFIG_quit() {
			return CONFIG_quit;
		}
		public void setCONFIG_quit(String cONFIG_quit) {
			CONFIG_quit = cONFIG_quit;
		}
		public int getIN_port() {
			return IN_port;
		}
		public void setIN_port(int iN_port) {
			IN_port = iN_port;
		}
		public int getIN_timeout() {
			return IN_timeout;
		}
		public void setIN_timeout(int iN_timeout) {
			IN_timeout = iN_timeout;
		}
		public String getIN_host() {
			return IN_host;
		}
		public void setIN_host(String iN_host) {
			IN_host = iN_host;
		}
		public String getIN_userName() {
			return IN_userName;
		}
		public void setIN_userName(String iN_userName) {
			IN_userName = iN_userName;
		}
		public String getIN_password() {
			return IN_password;
		}
		public void setIN_password(String iN_password) {
			IN_password = iN_password;
		}
		public String getIN_parentRemoteDirectory() {
			return IN_parentRemoteDirectory;
		}
		public void setIN_parentRemoteDirectory(String iN_parentRemoteDirectory) {
			IN_parentRemoteDirectory = iN_parentRemoteDirectory;
		}
		public String getIN_remoteDirName() {
			return IN_remoteDirName;
		}
		public void setIN_remoteDirName(String iN_remoteDirName) {
			IN_remoteDirName = iN_remoteDirName;
		}
		
	}
	public static class FTPRenameFileActivity
	{
		private static String description,  activityType, connectionName;
		public static String getConnectionName() {
			return connectionName;
		}
		public static void setConnectionName(String connectionName) {
			FTPRenameFileActivity.connectionName = connectionName;
		}
		private List<String> CONFIG_FTPConnection;
		private String CONFIG_quit;
		private int IN_port,IN_timeout;
		private String IN_host,IN_userName,IN_oldRemoteFileElement,IN_newRemoteFileElement,IN_password,IN_oldRemoteDirectory,IN_newRemoteDirectory,IN_newRemoteFilename,IN_oldRemoteFilename ;
		public FTPRenameFileActivity(Node targetNode)
		{
			FTPRenameFileActivity.description="The FTP Rename File activity renames the specified file on the remote FTP server. ";
			Element rootActivityElement = (Element)targetNode;
			FTPRenameFileActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
			Element IN_oldRemoteFileElement=(Element)rootActivityElement.getElementsByTagName("OldRemoteFileName").item(0);
			if(IN_oldRemoteFileElement!=null)
        		this.IN_oldRemoteFilename=IN_oldRemoteFileElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
        	this.IN_oldRemoteFilename=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.IN_oldRemoteFilename);
			this.IN_oldRemoteFileElement=this.IN_oldRemoteFilename.substring(this.IN_oldRemoteFilename.lastIndexOf("/")+1);
        	Element IN_newRemoteFileElement=(Element)rootActivityElement.getElementsByTagName("NewRemoteFileName").item(0);
			if(IN_newRemoteFileElement!=null)
        		this.IN_newRemoteFilename=IN_newRemoteFileElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
			this.IN_newRemoteFilename=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.IN_newRemoteFilename);
			this.IN_newRemoteFileElement=this.IN_newRemoteFilename.substring(this.IN_newRemoteFilename.lastIndexOf("/")+1);
			String con=rootActivityElement.getElementsByTagName("SharedUserData").item(0).getTextContent();
        	this.connectionName=con.substring(con.lastIndexOf("/")+1, con.lastIndexOf("."));
        	
		}
		public static String getDescription() {
			return description;
		}
		public static void setDescription(String description) {
			FTPRenameFileActivity.description = description;
		}
		public static String getActivityType() {
			return activityType;
		}
		public static void setActivityType(String activityType) {
			FTPRenameFileActivity.activityType = activityType;
		}
		public List<String> getCONFIG_FTPConnection() {
			return CONFIG_FTPConnection;
		}
		public void setCONFIG_FTPConnection(List<String> cONFIG_FTPConnection) {
			CONFIG_FTPConnection = cONFIG_FTPConnection;
		}
		public String getCONFIG_quit() {
			return CONFIG_quit;
		}
		public void setCONFIG_quit(String cONFIG_quit) {
			CONFIG_quit = cONFIG_quit;
		}
		public int getIN_port() {
			return IN_port;
		}
		public void setIN_port(int iN_port) {
			IN_port = iN_port;
		}
		public int getIN_timeout() {
			return IN_timeout;
		}
		public void setIN_timeout(int iN_timeout) {
			IN_timeout = iN_timeout;
		}
		public String getIN_host() {
			return IN_host;
		}
		public void setIN_host(String iN_host) {
			IN_host = iN_host;
		}
		public String getIN_userName() {
			return IN_userName;
		}
		public void setIN_userName(String iN_userName) {
			IN_userName = iN_userName;
		}
		public String getIN_oldRemoteFileElement() {
			return IN_oldRemoteFileElement;
		}
		public void setIN_oldRemoteFileElement(String iN_oldRemoteFileElement) {
			IN_oldRemoteFileElement = iN_oldRemoteFileElement;
		}
		public String getIN_newRemoteFileElement() {
			return IN_newRemoteFileElement;
		}
		public void setIN_newRemoteFileElement(String iN_newRemoteFileElement) {
			IN_newRemoteFileElement = iN_newRemoteFileElement;
		}
		public String getIN_password() {
			return IN_password;
		}
		public void setIN_password(String iN_password) {
			IN_password = iN_password;
		}
		public String getIN_oldRemoteDirectory() {
			return IN_oldRemoteDirectory;
		}
		public void setIN_oldRemoteDirectory(String iN_oldRemoteDirectory) {
			IN_oldRemoteDirectory = iN_oldRemoteDirectory;
		}
		public String getIN_newRemoteDirectory() {
			return IN_newRemoteDirectory;
		}
		public void setIN_newRemoteDirectory(String iN_newRemoteDirectory) {
			IN_newRemoteDirectory = iN_newRemoteDirectory;
		}
		public String getIN_newRemoteFilename() {
			return IN_newRemoteFilename;
		}
		public void setIN_newRemoteFilename(String iN_newRemoteFilename) {
			IN_newRemoteFilename = iN_newRemoteFilename;
		}
		public String getIN_oldRemoteFilename() {
			return IN_oldRemoteFilename;
		}
		public void setIN_oldRemoteFilename(String iN_oldRemoteFilename) {
			IN_oldRemoteFilename = iN_oldRemoteFilename;
		}
		
        	}
	public static class FTPSysTypeActivity
	{
		private static String description,  activityType;
		private List<String> CONFIG_FTPConnection;
		private String CONFIG_quit;
		private int IN_port,IN_timeout;
		private String IN_host,IN_userName,IN_password;
		private String OUT_systemType;
		public FTPSysTypeActivity(Node targetNode)
		{
			FTPSysTypeActivity.description="The FTP Sys Type activity retrieves the FTP server’s operating system type. ";
			Element rootActivityElement = (Element)targetNode;
			FTPSysTypeActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
		}
		public static String getDescription() {
			return description;
		}
		public static void setDescription(String description) {
			FTPSysTypeActivity.description = description;
		}
		public static String getActivityType() {
			return activityType;
		}
		public static void setActivityType(String activityType) {
			FTPSysTypeActivity.activityType = activityType;
		}
		public List<String> getCONFIG_FTPConnection() {
			return CONFIG_FTPConnection;
		}
		public void setCONFIG_FTPConnection(List<String> cONFIG_FTPConnection) {
			CONFIG_FTPConnection = cONFIG_FTPConnection;
		}
		public String getCONFIG_quit() {
			return CONFIG_quit;
		}
		public void setCONFIG_quit(String cONFIG_quit) {
			CONFIG_quit = cONFIG_quit;
		}
		public int getIN_port() {
			return IN_port;
		}
		public void setIN_port(int iN_port) {
			IN_port = iN_port;
		}
		public int getIN_timeout() {
			return IN_timeout;
		}
		public void setIN_timeout(int iN_timeout) {
			IN_timeout = iN_timeout;
		}
		public String getIN_host() {
			return IN_host;
		}
		public void setIN_host(String iN_host) {
			IN_host = iN_host;
		}
		public String getIN_userName() {
			return IN_userName;
		}
		public void setIN_userName(String iN_userName) {
			IN_userName = iN_userName;
		}
		public String getIN_password() {
			return IN_password;
		}
		public void setIN_password(String iN_password) {
			IN_password = iN_password;
		}
		public String getOUT_systemType() {
			return OUT_systemType;
		}
		public void setOUT_systemType(String oUT_systemType) {
			OUT_systemType = oUT_systemType;
		}
		
	}
}

	
