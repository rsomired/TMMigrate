package com.tek.muleautomator.service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tek.muleautomator.element.ParseElement.ParseDataActivity;
import com.tek.muleautomator.element.ParseElement.RenderDataActivity;
import com.tek.muleautomator.util.MuleAutomatorConstants;
import com.tek.muleautomator.util.MuleConfigConnection;
public class ParseService {
	public  void parseData(String muleConfigPath, ParseDataActivity parseDataActivity, Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element parser = doc.createElement("dw:transform-message");
			
			Element el=(Element)doc.getFirstChild();
			el.setAttribute("xmlns:dw", "http://www.mulesoft.org/schema/mule/ee/dw");
					
			boolean skip=false;		
			if(MuleAutomatorConstants.specifiedSchema.containsKey(muleConfigPath)){
				String val=MuleAutomatorConstants.specifiedSchema.get(muleConfigPath);
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
			}parser.setAttribute("set-payload","");
			parser.setAttribute("doc:name", "Transform message");
			flow.appendChild(parser);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
		public  void renderData(String muleConfigPath, RenderDataActivity renderDataActivity, Element flow) {
			try {
				Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
				Element el=(Element)doc.getFirstChild();
				el.setAttribute("xmlns:dw", "http://www.mulesoft.org/schema/mule/ee/dw");

				Element render = doc.createElement("dw:transform-message");
				boolean skip=false;		
				if(MuleAutomatorConstants.specifiedSchema.containsKey(muleConfigPath)){
					String val=MuleAutomatorConstants.specifiedSchema.get(muleConfigPath);
					for(String temp: val.split(";")){
						if(temp.contains("dw:transform-message")){
							skip=true;
						}
					}
				}
				if(!skip){
					//render.setAttribute("xsi:schemaLocation", "http://www.mulesoft.org/schema/mule/ee/dw/current/dw.xsd");
					String val=MuleAutomatorConstants.specifiedSchema.get(muleConfigPath);
					val+=";dw:transform-message";
					MuleAutomatorConstants.specifiedSchema.put(muleConfigPath, val);
				}
				render.setAttribute("set-payload","");
				render.setAttribute("doc:name", "Transform message");
				flow.appendChild(render);
			} catch (Exception e) {
				e.printStackTrace();
			}
}
}
