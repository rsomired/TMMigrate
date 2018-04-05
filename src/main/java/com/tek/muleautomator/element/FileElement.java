/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tek.muleautomator.element;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tek.muleautomator.util.MuleAutomatorConstants;

public class FileElement {
    
	
    public class FileWriteActivity { 
    	
    	private String activityType;
    	
    	private String CONFIG_description;
        private String CONFIG_compress;
        private boolean CONFIG_append;
        
        private String INPUT_fileName;
        private String INPUT_filePath="";
        private String INPUT_textContent;
        private byte[] INPUT_binaryContent;
        private String INPUT_encoding;
        
        private String OUTPUT_location;
            
        public FileWriteActivity(Node node){
        	Element rootActivityElement = (Element)node;
        	this.CONFIG_description="The Write File activity writes content to the specified file.";
        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	Element fileElement=(Element)rootActivityElement.getElementsByTagName("fileName").item(0);
        	if(fileElement!=null)
        		this.INPUT_filePath=fileElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
        	this.INPUT_filePath=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.INPUT_filePath);
    		String ns="ns";
    		Element inputBindings=(Element)rootActivityElement.getElementsByTagName("pd:inputBindings").item(0);
    		NodeList nodes=inputBindings.getChildNodes();
    		int i=0;
    		while(true){
    			Node el=nodes.item(i);
    			i++;
    			if(el.getNodeType()==Node.ELEMENT_NODE){
    				ns=el.getNodeName().split(":")[0];
    				break;
    			}
    		}
    		String varName_fullName=generateTibcoVarName(rootActivityElement.getAttribute("name"))+"/"+ns+":WriteActivityOutputClass/fileInfo/fullName";
        	
    		try{
        		this.INPUT_fileName=this.INPUT_filePath.substring(this.INPUT_filePath.lastIndexOf("\\")+1);
        	} catch (Exception E){
        		
        	}
    		MuleAutomatorConstants.tibcoLocalVariables.put(varName_fullName, this.INPUT_filePath);
    		
   	
        	Element textContentElement=(Element)rootActivityElement.getElementsByTagName("textContent").item(0);
        	if(textContentElement!=null){
        		this.INPUT_textContent=textContentElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
        		
        	}
        	Element encodingElement=(Element)rootActivityElement.getElementsByTagName("encoding").item(0);
        	if(encodingElement!=null){
        		this.INPUT_encoding=encodingElement.getTextContent();
        	}
        	
        	if(this.INPUT_filePath.contains("\\")){
        		this.INPUT_filePath=this.INPUT_filePath.substring(0, this.INPUT_filePath.lastIndexOf("\\"));
        	}
        	this.CONFIG_compress=rootActivityElement.getElementsByTagName("compressFile").item(0).getTextContent();
        	this.CONFIG_append=rootActivityElement.getElementsByTagName("append").getLength()>0?Boolean.parseBoolean(rootActivityElement.getElementsByTagName("append").item(0).getTextContent()):true;
        }


		public String getActivityType() {
			return activityType;
		}


		public void setActivityType(String activityType) {
			this.activityType = activityType;
		}


		public String getCONFIG_description() {
			return CONFIG_description;
		}


		public void setCONFIG_description(String cONFIG_description) {
			CONFIG_description = cONFIG_description;
		}


		public String getCONFIG_compress() {
			return CONFIG_compress;
		}


		public void setCONFIG_compress(String cONFIG_compress) {
			CONFIG_compress = cONFIG_compress;
		}


		public boolean isCONFIG_append() {
			return CONFIG_append;
		}


		public void setCONFIG_append(boolean cONFIG_append) {
			CONFIG_append = cONFIG_append;
		}


		public String getINPUT_fileName() {
			return INPUT_fileName;
		}


		public void setINPUT_fileName(String iNPUT_fileName) {
			INPUT_fileName = iNPUT_fileName;
		}
	

		public String getINPUT_filePath() {
			return INPUT_filePath;
		}


		public String getINPUT_textContent() {
			return INPUT_textContent;
		}


		public void setINPUT_textContent(String iNPUT_textContent) {
			INPUT_textContent = iNPUT_textContent;
		}


		public byte[] getINPUT_binaryContent() {
			return INPUT_binaryContent;
		}


		public void setINPUT_binaryContent(byte[] iNPUT_binaryContent) {
			INPUT_binaryContent = iNPUT_binaryContent;
		}


		public String getINPUT_encoding() {
			return INPUT_encoding;
		}


		public void setINPUT_encoding(String iNPUT_encoding) {
			INPUT_encoding = iNPUT_encoding;
		}


		public String getOUTPUT_location() {
			return OUTPUT_location;
		}


		public void setOUTPUT_location(String oUTPUT_location) {
			OUTPUT_location = oUTPUT_location;
		}      

		
        
    }
     
    public class FileCreateActivity {
        private String CONFIG_activityType,CONFIG_description,CONFIG_Overwrite,CONFIG_isADirectory ,CONFIG_createNonExistingDirectories;
        private String INPUT_fileName, INPUT_filePath="";
        private String OUTPUT_fileInfo,OUTPUT_fullName,OUTPUT_fileName,OUTPUT_location,OUTPUT_configuredFileName,OUTPUT_type;
        private String OUTPUT_readProtected,OUTPUT_writeProtected,OUTPUT_size,OUTPUT_lastModified;
        
        public FileCreateActivity(Node activityNode){
        	this.CONFIG_description="The Create File activity creates a new file or directory with the specified name. When creating a file, you can also provide the file contents. ";
        	Element rootActivityElement = (Element)activityNode;
        	this.CONFIG_activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	Element fileElement=(Element)rootActivityElement.getElementsByTagName("fileName").item(0);
        	
        	if(fileElement!=null)
        		this.INPUT_filePath=fileElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();

        	this.INPUT_filePath=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.INPUT_filePath);
    		String ns="";
    		Element inputBindings=(Element)rootActivityElement.getElementsByTagName("pd:inputBindings").item(0);
    		NodeList nodes=inputBindings.getChildNodes();
    		int i=0;
    		while(true){
    			Node el=nodes.item(i);
    			i++;
    			if(el.getNodeType()==Node.ELEMENT_NODE){
    				ns=el.getNodeName().split(":")[0];
    				break;
    			}
    		}
        	
        	String varName_fullName=generateTibcoVarName(rootActivityElement.getAttribute("name"))+"/"+ns+":CreateActivityOutputClass/fileInfo/fullName";
        	try{
        		this.INPUT_fileName=this.INPUT_filePath.substring(this.INPUT_filePath.lastIndexOf("\\")+1);
        	} catch(Exception E){
        		this.INPUT_fileName="DefaultFile";
        	}
    		MuleAutomatorConstants.tibcoLocalVariables.put(varName_fullName, this.INPUT_filePath);
    		if(this.INPUT_filePath.contains("\\"))
    			this.INPUT_filePath=this.INPUT_filePath.substring(0,this.INPUT_filePath.lastIndexOf("\\"));       	
   
        }

        public String getActivityType() {
            return CONFIG_activityType;
        }

        public void setActivityType(String activityType) {
            this.CONFIG_activityType = activityType;
        }

        public String getFileName() {
            return INPUT_fileName;
        }

		public String getINPUT_fileName() {
			return INPUT_fileName;
		}

		public String getINPUT_filePath() {
			return INPUT_filePath;
		}

		public void setINPUT_filePath(String iNPUT_filePath) {
			INPUT_filePath = iNPUT_filePath;
		}
        
    }
    
    public class FileReadActivity {
    	private String CONFIG_activityType,CONFIG_description;
    	private String CONFIG_excludeFileContent, CONFIG_readAs;
    	private String INPUT_fileName, INPUT_filePath="",INPUT_encoding;
    	private String OUTPUT_fileInfo,OUTPUT_fullName,OUTPUT_fileName,OUTPUT_location,OUTPUT_configuredFileName,OUTPUT_type;
        private String OUTPUT_readProtected,OUTPUT_writeProtected,OUTPUT_size,OUTPUT_lastModified,OUTPUT_textContent,OUTPUT_binaryContent;
        public String getActivityType() {
			return CONFIG_activityType;
		}


		public void setActivityType(String activityType) {
			this.CONFIG_activityType = activityType;
		}


		public String getFileName() {
			return INPUT_fileName;
		}


		public void setFileName(String fileName) {
			this.INPUT_fileName = fileName;
		}


		public String getEncoding() {
			return INPUT_encoding;
		}


		public void setEncoding(String encoding) {
			this.INPUT_encoding = encoding;
		}

		

		public String getINPUT_filePath() {
			return INPUT_filePath;
		}


		public void setINPUT_filePath(String iNPUT_filePath) {
			INPUT_filePath = iNPUT_filePath;
		}


		public FileReadActivity(Node node){
        	Element rootActivityElement = (Element)node;
        	this.CONFIG_description="The Read File activity is used to read a file and place its contents into the activity’s output. ";
        	this.CONFIG_activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	Element fileElement=(Element)rootActivityElement.getElementsByTagName("fileName").item(0);
        	if(fileElement!=null)
        		this.INPUT_filePath=fileElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
        	Element encodingElement=(Element)rootActivityElement.getElementsByTagName("encoding").item(0);
        	if(encodingElement!=null){
        		this.INPUT_encoding=encodingElement.getTextContent();
        	}
        	
        	this.INPUT_filePath=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.INPUT_filePath);
        	try{
        		this.INPUT_fileName=this.INPUT_filePath.substring(this.INPUT_filePath.lastIndexOf("\\")+1);
        	} catch(Exception E){
        		this.INPUT_fileName="DefaultFile";
        	}
        }
        
    }

    public class FileRemoveActivity{
    	private String activityType;
    	
    	private String CONFIG_description;
    	private String INPUT_fileName, INPUT_filePath="";
    	
    	private String OUTPUT_location;
    	private boolean OUTPUT_readProtected, OUTPUT_writeProtected;
		


		public String getActivityType() {
			return activityType;
		}



		public void setActivityType(String activityType) {
			this.activityType = activityType;
		}



		public String getCONFIG_description() {
			return CONFIG_description;
		}



		public void setCONFIG_description(String cONFIG_description) {
			CONFIG_description = cONFIG_description;
		}



		public String getINPUT_fileName() {
			return INPUT_fileName;
		}



		public void setINPUT_fileName(String iNPUT_fileName) {
			INPUT_fileName = iNPUT_fileName;
		}



		public String getOUTPUT_location() {
			return OUTPUT_location;
		}



		public void setOUTPUT_location(String oUTPUT_location) {
			OUTPUT_location = oUTPUT_location;
		}



		public boolean isOUTPUT_readProtected() {
			return OUTPUT_readProtected;
		}



		public void setOUTPUT_readProtected(boolean oUTPUT_readProtected) {
			OUTPUT_readProtected = oUTPUT_readProtected;
		}



		public boolean isOUTPUT_writeProtected() {
			return OUTPUT_writeProtected;
		}



		public void setOUTPUT_writeProtected(boolean oUTPUT_writeProtected) {
			OUTPUT_writeProtected = oUTPUT_writeProtected;
		}



		public String getINPUT_filePath() {
			return INPUT_filePath;
		}



		public FileRemoveActivity(Node targetNode){
    		Element rootActivityElement = (Element)targetNode;
    		this.CONFIG_description="The Remove File activity removes the specified file. This activity can also remove empty directories. If a directory that is not empty is specified, an exception is thrown. ";
    		
        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	Element fileElement=(Element)rootActivityElement.getElementsByTagName("fileName").item(0);
        	if(fileElement!=null)
        		this.INPUT_filePath=fileElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
        	this.INPUT_filePath=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.INPUT_filePath);
    		this.INPUT_fileName=this.INPUT_filePath.substring(this.INPUT_filePath.lastIndexOf("\\")+1);
    		try{
    			this.INPUT_filePath=this.INPUT_filePath.substring(0,this.INPUT_filePath.lastIndexOf("\\"));       	
    		} catch (Exception E){
    			
    		}
		}
    	
    }
    	
    public class FileListActivity{
    	private String CONFIG_activityType,CONFIG_description;private String CONFIG_Mode;
    	private String INPUT_directoryPath;
    	private String OUTPUT_fileInfo,OUTPUT_fullName,OUTPUT_fileName,OUTPUT_location,OUTPUT_configuredFileName,OUTPUT_type;
        private String OUTPUT_readProtected,OUTPUT_writeProtected,OUTPUT_size,OUTPUT_lastModified,OUTPUT_textContent,OUTPUT_binaryContent;
		public String getActivityType() {
			return CONFIG_activityType;
		}

	
    	public String getFileName() {
			return INPUT_directoryPath;
		}


		public void setFileName(String fileName) {
			this.INPUT_directoryPath = fileName;
		}


		public void setActivityType(String activityType) {
			this.CONFIG_activityType = activityType;
		}


		public FileListActivity(Node targetNode){
    		Element rootActivityElement = (Element)targetNode;
    		this.CONFIG_description="The List Files activity returns information about files or directories, or a listing of all the files in the specified directory. ";
        	this.CONFIG_activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	Element fileElement=(Element)rootActivityElement.getElementsByTagName("fileName").item(0);
        	if(fileElement!=null)
        		this.INPUT_directoryPath=fileElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
        	else 
        		this.INPUT_directoryPath="";
        	this.INPUT_directoryPath=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.INPUT_directoryPath);
        	String activityName=rootActivityElement.getAttribute("name");
        	String ns="ns";
        	Element inputBindings=(Element)rootActivityElement.getElementsByTagName("pd:inputBindings").item(0);
    		NodeList nodes=inputBindings.getChildNodes();
    		int i=0;
    		while(true){
    			Node el=nodes.item(i);
    			i++;
    			if(el.getNodeType()==Node.ELEMENT_NODE){
    				ns=el.getNodeName().split(":")[0];
    				break;
    			}
    		}
        	
        	
        	MuleAutomatorConstants.tibcoLocalVariables.put(generateTibcoVarName(activityName)+"/"+ns+":ListFilesActivityOutput/files/fileInfo[1]/fullName", this.INPUT_directoryPath);
		}
    	
    }

    public class FileRenameActivity {
    	private String activityType;
    	private String CONFIG_description;
    	private boolean CONFIG_overwrite;
    	
    	private String INPUT_fromFileName, INPUT_toFileName;
    	private String INPUT_fromFilePath="", INPUT_toFilePath="";
    	
    	private String OUTPUT_location;
    	private boolean OUTPUT_writeProtected;
    	
    	public FileRenameActivity(Node targetNode){
    		Element rootActivityElement = (Element)targetNode;
    		this.CONFIG_description="The Rename File activity is used to rename or move files. This activity can also rename directories, but you cannot use this activity to move a directory to a new location. ";
        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	Element fileFromElement=(Element)rootActivityElement.getElementsByTagName("fromFileName").item(0);
        	if(fileFromElement!=null)
        		this.INPUT_fromFilePath=fileFromElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
        	Element filetoElement=(Element)rootActivityElement.getElementsByTagName("toFileName").item(0);
        	if(filetoElement!=null)
        		this.INPUT_toFilePath=filetoElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
        	
        	this.INPUT_fromFilePath=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.INPUT_fromFilePath);
    		try{
    			this.INPUT_fromFileName=this.INPUT_fromFilePath.substring(this.INPUT_fromFilePath.lastIndexOf("\\")+1);
    			this.INPUT_fromFilePath=this.INPUT_fromFilePath.substring(0,this.INPUT_fromFilePath.lastIndexOf("\\"));
    		} catch (Exception E){
    			System.out.println(E.getMessage());
    		}
        	
    		this.INPUT_toFilePath=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.INPUT_toFilePath);
    		try{
    			this.INPUT_toFileName=this.INPUT_toFilePath.substring(this.INPUT_toFilePath.lastIndexOf("\\")+1);
    			//System.out.println(this.INPUT_toFilePath);
    			this.INPUT_toFilePath=this.INPUT_toFilePath.substring(0,this.INPUT_toFilePath.lastIndexOf("\\"));
    		} catch (Exception E){
    			//E.printStackTrace();
    		}
    		
        	String activityName=rootActivityElement.getAttribute("name");
    		MuleAutomatorConstants.tibcoLocalVariables.put(generateTibcoVarName(activityName), this.INPUT_toFilePath+"\\"+this.INPUT_toFileName);

    	}

		public String getActivityType() {
			return activityType;
		}

		public void setActivityType(String activityType) {
			this.activityType = activityType;
		}

		public String getCONFIG_description() {
			return CONFIG_description;
		}

		public void setCONFIG_description(String cONFIG_description) {
			CONFIG_description = cONFIG_description;
		}

		public boolean isCONFIG_overwrite() {
			return CONFIG_overwrite;
		}

		public void setCONFIG_overwrite(boolean cONFIG_overwrite) {
			CONFIG_overwrite = cONFIG_overwrite;
		}

		public String getINPUT_fromFileName() {
			return INPUT_fromFileName;
		}

		public void setINPUT_fromFileName(String iNPUT_fromFileName) {
			INPUT_fromFileName = iNPUT_fromFileName;
		}

		public String getINPUT_toFileName() {
			return INPUT_toFileName;
		}

		public void setINPUT_toFileName(String iNPUT_toFileName) {
			INPUT_toFileName = iNPUT_toFileName;
		}

		public String getOUTPUT_location() {
			return OUTPUT_location;
		}

		public void setOUTPUT_location(String oUTPUT_location) {
			OUTPUT_location = oUTPUT_location;
		}

		public boolean isOUTPUT_writeProtected() {
			return OUTPUT_writeProtected;
		}

		public void setOUTPUT_writeProtected(boolean oUTPUT_writeProtected) {
			OUTPUT_writeProtected = oUTPUT_writeProtected;
		}

		public String getINPUT_fromFilePath() {
			return INPUT_fromFilePath;
		}

		public String getINPUT_toFilePath() {
			return INPUT_toFilePath;
		}

		
    	
    }
    
    public class FileCopyActivity {
    	private String activityType;
    	
    	private boolean CONFIG_overwrite, CONFIG_createMissingDirectories;
    	private String CONFIG_description;
    	
    	private String INPUT_fromFileName, INPUT_toFileName;
    	private String INPUT_fromFilePath="", INPUT_toFilePath="";
    	
    	public FileCopyActivity(Node targetNode){
    		Element rootActivityElement = (Element)targetNode;
    		this.CONFIG_description="The Copy File activity allows you to copy files and directories to a new location";
        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	Element fileFromElement=(Element)rootActivityElement.getElementsByTagName("fromFileName").item(0);
        	if(fileFromElement!=null)
        		this.INPUT_fromFilePath=fileFromElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
        	Element filetoElement=(Element)rootActivityElement.getElementsByTagName("toFileName").item(0);
        	if(filetoElement!=null)
        		this.INPUT_toFilePath=filetoElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
        	
        	this.CONFIG_overwrite=rootActivityElement.getElementsByTagName("overwrite").getLength()>0?Boolean.parseBoolean(rootActivityElement.getElementsByTagName("overwrite").item(0).getTextContent()):false;
        	this.CONFIG_createMissingDirectories=rootActivityElement.getElementsByTagName("createMissingDirectories").getLength()>0?Boolean.parseBoolean(rootActivityElement.getElementsByTagName("createMissingDirectories").item(0).getTextContent()):false;
        	this.INPUT_fromFilePath=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.INPUT_fromFilePath);
    		
        	try{
    			this.INPUT_fromFileName=this.INPUT_fromFilePath.substring(this.INPUT_fromFilePath.lastIndexOf("\\")+1);
    			this.INPUT_fromFilePath=this.INPUT_fromFilePath.substring(0,this.INPUT_fromFilePath.lastIndexOf("\\"));
    		} catch (Exception E){
    			E.printStackTrace();
    		}
        	
    		this.INPUT_toFilePath=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(this.INPUT_toFilePath);
    		try{
    			this.INPUT_toFileName=this.INPUT_toFilePath.substring(this.INPUT_toFilePath.lastIndexOf("\\")+1);
    			this.INPUT_toFilePath=this.INPUT_toFilePath.substring(0,this.INPUT_toFilePath.lastIndexOf("\\"));
    		} catch (Exception E){
    			E.printStackTrace();
    		}
    		
        	String activityName=rootActivityElement.getAttribute("name");
    		MuleAutomatorConstants.tibcoLocalVariables.put(generateTibcoVarName(activityName), this.INPUT_toFilePath+"\\"+this.INPUT_toFileName);
        	
    	}

		public String getActivityType() {
			return activityType;
		}

		public String getFromFileName() {
			return INPUT_fromFileName;
		}

		public String getToFileName() {
			return INPUT_toFileName;
		}

		public boolean isCONFIG_overwrite() {
			return CONFIG_overwrite;
		}

		public boolean isCONFIG_createMissingDirectories() {
			return CONFIG_createMissingDirectories;
		}

		public String getCONFIG_description() {
			return CONFIG_description;
		}

		public String getINPUT_fromFileName() {
			return INPUT_fromFileName;
		}

		public String getINPUT_toFileName() {
			return INPUT_toFileName;
		}

		public String getINPUT_fromFilePath() {
			return INPUT_fromFilePath;
		}

		public String getINPUT_toFilePath() {
			return INPUT_toFilePath;
		}
		
		
    	
    }
    
    private static String generateTibcoVarName(String activityName){
    	String []splitBySpace=activityName.split(" ");
    	String varHeader="$";
    	for(int i=0;i<splitBySpace.length;++i){
    		varHeader+=splitBySpace[i];
    		if(i!=splitBySpace.length-1)
    			varHeader+="-";
    	}
    	return varHeader;	
    }

}
