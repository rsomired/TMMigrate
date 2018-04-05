package com.tek.muleautomator.service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tek.muleautomator.element.XMLElement;
import com.tek.muleautomator.util.MuleAutomatorConstants;
import com.tek.muleautomator.util.MuleConfigConnection;
public class XMLService {
	public void rendererXML(String muleConfigPath,XMLElement.XMLRendererActivity receiveMailActivity, Element flow){
		 try {
			   Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			   Element renderer = doc.createElement("mulexml:xml-to-object-transformer");
			   renderer.setAttribute("doc:name", "XML to Object");
			   Element el=(Element)doc.getFirstChild();
			   el.setAttribute("xmlns:mulexml", "http://www.mulesoft.org/schema/mule/xml");
			   renderer.setAttribute("xsi:schemaLocation", "http://www.mulesoft.org/schema/mule/xml http://www.mulesoft.org/schema/mule/xml/current/mule-xml.xsd");
			   flow.appendChild(renderer);
  
			   Element renderer1 = doc.createElement("object-to-string-transformer");
			   renderer1.setAttribute("doc:name", "Object to String");
			   renderer1.setAttribute("xmlns:mulexml", "http://www.mulesoft.org/schema/mule/xml");
			   renderer1.setAttribute("xsi:schemaLocation", "http://www.mulesoft.org/schema/mule/xml http://www.mulesoft.org/schema/mule/xml/current/mule-xml.xsd");
			   flow.appendChild(renderer1);
		 } catch (Exception e) {
			   e.printStackTrace();
			  } 

}
	public void transformXML(String muleConfigPath,XMLElement.XMLTransformActivity receiveMailActivity, Element flow){
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element parser = doc.createElement("dw:transform-message");
			
			Element el=(Element)doc.getFirstChild();

			el.setAttribute("xmlns:dw", "http://www.mulesoft.org/schema/mule/ee/dw");
			boolean skip=false;		
			if(MuleAutomatorConstants.specifiedSchema.containsKey(muleConfigPath)){
				String val=MuleAutomatorConstants.specifiedSchema.get(muleConfigPath);
				//System.out.println(" VALUE FOR MULECONFIG: "+val);
				for(String temp: val.split(";")){
					if(temp.contains("dw:transform-message")){
						skip=true;
					}
				}
			}
			if(!skip){
				//parser.setAttribute("xsi:schemaLocation", "http://www.mulesoft.org/schema/mule/ee/dw/current/dw.xsd");
				String val=MuleAutomatorConstants.specifiedSchema.get(muleConfigPath);
				val+=";dw:transform-message";
				MuleAutomatorConstants.specifiedSchema.put(muleConfigPath, val);
			}
			parser.setAttribute("set-payload","");
			parser.setAttribute("doc:name", "Transform message");
			flow.appendChild(parser);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
}