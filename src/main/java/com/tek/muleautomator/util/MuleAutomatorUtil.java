package com.tek.muleautomator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.tek.muleautomator.element.ActivityElement;

public class MuleAutomatorUtil {

	public static void trasfromData(Document doc, String filepath) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loggerElement(String muleConfigPath, Element flow) {

		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element loggerElement = doc.createElement("logger");
			loggerElement.setAttribute("message", "#[payload]");
			loggerElement.setAttribute("level", "INFO");
			loggerElement.setAttribute("doc:name", "Logger");
			flow.appendChild(loggerElement);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loggerElement(ActivityElement activityElement, String muleConfigPath, Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element loggerElement = doc.createElement("logger");
			System.out.println("Missing for: "+activityElement.getActivityName()+" - " + activityElement.getActivityType());
			loggerElement.setAttribute("message", "Please add "+activityElement.getActivityType()+" logic manually");
			loggerElement.setAttribute("level", "INFO");
			loggerElement.setAttribute("doc:name", "Missing Activity");
			flow.appendChild(loggerElement);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void includeLibraries(String tibcoPath, String muleBasePath) throws IOException, TransformerException{
		System.out.println("\n* *  Looking for external Libraries  * *");
		List<File> jarFiles=new ArrayList<>();
		fileFinder(new File(tibcoPath), jarFiles, new String[]{"jar"});
		if(jarFiles.size()==0)
			return;
		File classPathFile=new File(muleBasePath+"/.classPath");
		
		Document doc=null;
		if(!classPathFile.exists()){
			System.err.println(">> ClassPath file not found! Generating .classpath file");
			String temp="<?xml version=\"1.0\" encoding=\"UTF-8\"?><classpath></classpath>";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			try {
				docBuilder = docFactory.newDocumentBuilder();
				InputSource is=new InputSource();
				is.setCharacterStream(new StringReader(temp));
				doc = docBuilder.parse(is);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			classPathFile.createNewFile();
		
		} else {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			
			try {
				docBuilder = docFactory.newDocumentBuilder();
				doc = docBuilder.parse(classPathFile);
				
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		for(File currFile: jarFiles){
			
			String currFileName=muleBasePath+"/libs/"+currFile.getName();
			MuleAutomatorUtil.copyFile(currFile, new File(currFileName));
			Element entry=doc.createElement("classpathentry");
			entry.setAttribute("kind", "lib");
			entry.setAttribute("path", currFileName);
			doc.getFirstChild().appendChild(entry);
			
		}
		
		TransformerFactory transformerFactory=TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(classPathFile.getCanonicalPath());
		transformer.transform(source, result);
		System.out.println("Libraries included!");
		
		
		
	}

	public static void writeToFile(String filePath, String content) {
		BufferedWriter bufferedWriter = null;
		try {
			String folderPath = filePath.substring(0, filePath.lastIndexOf("\\"));
			// File folder = new File(folderPath);
			// folder.mkdirs();
			// check if file exist, otherwise creates the file before writing
			File myFile = new File(filePath);
			myFile.getParentFile().mkdirs();
			myFile.createNewFile();

			Writer writer = new FileWriter(myFile);
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null)
					bufferedWriter.close();
			} catch (Exception ex) {

			}
		}
	}

	public static boolean renameFile(String oldFile, String newFile) {
		File file = new File(oldFile);
		File file2 = new File(newFile);
		return file.renameTo(file2);

	}

	public static void fileFinder(File rootFile, List<File> fileList, String[] fileTypes) {
		if (rootFile == null)
			return;
		if (rootFile.isDirectory()) {
			for (int i = 0; i < rootFile.listFiles().length; ++i) {
				fileFinder(rootFile.listFiles()[i], fileList, fileTypes);
			}
		} else {
			for (String fileType : fileTypes) {
				if (!fileType.startsWith("."))
					fileType = "." + fileType;
				if (rootFile.getName().toLowerCase().endsWith(fileType)) {
					fileList.add(rootFile);
				}
			}
		}
	}

	public static boolean deleteDirectory(File dir) {
		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDirectory(children[i]);
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();

	}
	
	private static void copyFile(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    dest.getParentFile().mkdirs();
	   
	    if(!dest.exists())
	    	dest.createNewFile();
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	
}
