package com.tek.muleautomator.util;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


public class MuleConfigConnection {

	private static Document doc;

	private MuleConfigConnection() {
		
	}
	
	public static synchronized Document getDomObj(String filepath) throws Exception {
		if ( doc == null ) {
			doc = getDomConfig(filepath);
		}
		return doc;
	}
	
	public static boolean updateConfigDom(String filepath) throws Exception{
		getDomConfig(filepath);
		return true;
	}

	private static  Document getDomConfig(String filepath) throws Exception {
		doc = null;
		try {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		docBuilder = docFactory.newDocumentBuilder();
		doc = docBuilder.parse(filepath);
	} catch(Exception e) {
		e.printStackTrace();
	}
		return doc;
	}

}
