package com.tek.muleautomator.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tek.muleautomator.config.Connection;
import com.tek.muleautomator.config.FTPConnection;
import com.tek.muleautomator.element.FTPElement.FTPGetActivity;
import com.tek.muleautomator.element.FTPElement.FTPPutActivity;
import com.tek.muleautomator.element.FTPElement.FTPRenameFileActivity;
import com.tek.muleautomator.util.MuleAutomatorConstants;
import com.tek.muleautomator.util.MuleConfigConnection;

public class FTPService {

	
	public void ftpConfiguration(String muleConfigPath, String conName) throws ParserConfigurationException, SAXException, IOException {
		Document doc = null;
		try {
			doc = MuleConfigConnection.getDomObj(muleConfigPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<FTPConnection> ftpConnections=new ArrayList<>();	
		for (Map.Entry<String, Connection> entry : MuleAutomatorConstants.connectionConfigs.entrySet()) {
			if (entry.getValue().getConnectionType().equals("FTP")) {
				ftpConnections.add((FTPConnection) entry.getValue());
			}
		}
		Element muleTag = (Element) doc.getFirstChild();
		
		
		if (ftpConnections.size() == 0) {
			Element ftpConfigElement = doc.createElement("ftp:connector");
			System.err.println("Couldn't find Shared FTP configuration. Using Default FTP Config..");
			ftpConfigElement.setAttribute("doc:name", "FTP");
			ftpConfigElement.setAttribute("name", "FTP");
			ftpConfigElement.setAttribute("validateConnections", "true");
			ftpConfigElement.setAttribute("pollingFrequency", "1000");
			muleTag.appendChild(ftpConfigElement);
			
		} else {
			try {
				for(FTPConnection ftp: ftpConnections){
					
					if (!ftp.CONNECTION_NAME.equals(conName))
						continue;
					if (ftp.IS_CONFIGURED)
						break;
					ftp.IS_CONFIGURED = true;
					
					Element ftpConfigElement = doc.createElement("ftp:connector");
					ftpConfigElement.setAttribute("doc:name", "FTP");
					ftpConfigElement.setAttribute("name",ftp.CONNECTION_NAME.replaceAll(" ","_"));
					ftpConfigElement.setAttribute("validateConnections", "true");
					ftpConfigElement.setAttribute("pollingFrequency", "1000");
					muleTag.appendChild(ftpConfigElement);
				}
			} catch (Exception E) {
			}
		}
		
	}

	public void ftpGet(String muleConfigPath, FTPGetActivity ftpGetActivity, Element flowElement) {
		try {
			
			ftpConfiguration(muleConfigPath,FTPGetActivity.getConnectionName());
			
			
			Document doc = null;
			try {
				doc = MuleConfigConnection.getDomObj(muleConfigPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FTPConnection requiredConnection=null;
			for(Map.Entry<String, Connection> conEntry: MuleAutomatorConstants.connectionConfigs.entrySet()){
				if(conEntry.getValue().getConnectionType().equals("FTP")){
					FTPConnection ftpCon=(FTPConnection)conEntry.getValue();
					if(ftpCon.CONNECTION_NAME.equals(FTPGetActivity.getConnectionName())){
						requiredConnection=ftpCon;
					}
				}
			}
			
			Element ftpEl = doc.createElement("ftp:inbound-endpoint");
			ftpEl.setAttribute("host", requiredConnection.HOST);
			ftpEl.setAttribute("doc:name", "FTP Get");
			ftpEl.setAttribute("port", "" + requiredConnection.PORT);
			ftpEl.setAttribute("user", requiredConnection.USERNAME);
			ftpEl.setAttribute("connector-ref", requiredConnection.CONNECTION_NAME.replaceAll(" ", "_"));
			ftpEl.setAttribute("path", ftpGetActivity.getIN_remoteFilename());
			ftpEl.setAttribute("password", requiredConnection.PASSWORD);
			flowElement.appendChild(ftpEl);
			
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			e1.printStackTrace();
		}
		
		

	}

	public void ftpPut(String muleConfigPath, FTPPutActivity ftpPutActivity, Element flowElement) {
		try {
			
			ftpConfiguration(muleConfigPath,FTPPutActivity.getConnectionName());
			
			Document doc = null;
			try {
				doc = MuleConfigConnection.getDomObj(muleConfigPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FTPConnection requiredConnection=null;
			for(Map.Entry<String, Connection> conEntry: MuleAutomatorConstants.connectionConfigs.entrySet()){
				if(conEntry.getValue().getConnectionType().equals("FTP")){
					FTPConnection ftpCon=(FTPConnection)conEntry.getValue();
					if(ftpCon.CONNECTION_NAME.equals(FTPPutActivity.getConnectionName())){
						requiredConnection=ftpCon;
					}
				}
			}
			
			Element ftpEl = doc.createElement("ftp:inbound-endpoint");
			ftpEl.setAttribute("host", requiredConnection.HOST);
			ftpEl.setAttribute("port", "" + requiredConnection.PORT);
			ftpEl.setAttribute("user", requiredConnection.USERNAME);
			ftpEl.setAttribute("connector-ref", requiredConnection.CONNECTION_NAME.replaceAll(" ", "_"));
			ftpEl.setAttribute("path", ftpPutActivity.getIN_remoteFilename());
			ftpEl.setAttribute("password", requiredConnection.PASSWORD);
			ftpEl.setAttribute("doc:name", "FTP Put");
			flowElement.appendChild(ftpEl);
			
			Element fileOut=doc.createElement("file:outbound-endpoint");
			fileOut.setAttribute("responseTimeout", "10000");
			fileOut.setAttribute("doc:name", "File");
			fileOut.setAttribute("path", ftpPutActivity.getIN_localFilename());
			flowElement.appendChild(fileOut);
			
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			e1.printStackTrace();
		}
		
	}


	
	public void ftpRenameFile(String muleConfigPath, FTPRenameFileActivity ftpRenameFileActivity, Element flowElement){
		try {
				
			ftpConfiguration(muleConfigPath,FTPRenameFileActivity.getConnectionName());
			
			Document doc = null;
			try {
				doc = MuleConfigConnection.getDomObj(muleConfigPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FTPConnection requiredConnection=null;
			for(Map.Entry<String, Connection> conEntry: MuleAutomatorConstants.connectionConfigs.entrySet()){
				if(conEntry.getValue().getConnectionType().equals("FTP")){
					FTPConnection ftpCon=(FTPConnection)conEntry.getValue();
					if(ftpCon.CONNECTION_NAME.equals(FTPRenameFileActivity.getConnectionName())){
						requiredConnection=ftpCon;
					}
				}
			}
			
			Element ftpEl = doc.createElement("ftp:inbound-endpoint");
			ftpEl.setAttribute("host", requiredConnection.HOST);
			ftpEl.setAttribute("port", "" + requiredConnection.PORT);
			ftpEl.setAttribute("user", requiredConnection.USERNAME);
			ftpEl.setAttribute("path", ftpRenameFileActivity.getIN_oldRemoteFilename());
			ftpEl.setAttribute("password", requiredConnection.PASSWORD);
			ftpEl.setAttribute("connector-ref", requiredConnection.CONNECTION_NAME.replaceAll(" ", "_"));
			flowElement.appendChild(ftpEl);
			
			Element ftpEl2 = doc.createElement("ftp:outbound-endpoint");
			ftpEl2.setAttribute("host", requiredConnection.HOST);
			ftpEl2.setAttribute("port", "" + requiredConnection.PORT);
			ftpEl2.setAttribute("user", requiredConnection.USERNAME);
			ftpEl.setAttribute("connector-ref", requiredConnection.CONNECTION_NAME.replaceAll(" ", "_"));
			ftpEl2.setAttribute("path", ftpRenameFileActivity.getIN_newRemoteFilename());
			ftpEl2.setAttribute("password", requiredConnection.PASSWORD);
			flowElement.appendChild(ftpEl2);
			
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			e1.printStackTrace();
		}
	}

	public boolean isFTPConfigRequired(String muleConfigPath)
			throws ParserConfigurationException, SAXException, IOException {
		Document doc;
		try {
			doc = MuleConfigConnection.getDomObj(muleConfigPath);
			NodeList nodeList = doc.getElementsByTagName("ftp:connector");
			return nodeList.getLength() == 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
