package com.tek.muleautomator.service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tek.muleautomator.element.JavaElement.JavaCodeActivity;
import com.tek.muleautomator.util.MuleAutomatorUtil;
import com.tek.muleautomator.util.MuleConfigConnection;

public class JavaService {

 
	public void javaCode(String muleConfigPath, JavaCodeActivity javaCodeActivity, Element flow) {
		//  <component doc:name="Java" class="javamule.Test"/>
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element javaCode = doc.createElement("component");
			
			String x = muleConfigPath;
			String packageName=javaCodeActivity.getCODE_packageName();
			packageName=packageName.replaceAll("\\.", "\\\\");
	        String javaPath=x.substring(0,x.lastIndexOf("main\\")+5)+"\\java\\"+packageName+"\\"+javaCodeActivity.getCODE_className()+".java";
	        
	        javaPath=javaPath.replaceAll("/","\\\\");    
			
	        MuleAutomatorUtil.writeToFile(javaPath, javaCodeActivity.getCODE_JavaCode());
			
			javaCode.setAttribute("doc:name", "Java");
			//String x=JavaCodeActivity.CODE_packageName+JavaCodeActivity.CODE_className;
			javaCode.setAttribute("class",javaCodeActivity.getCODE_packageName()+"."+javaCodeActivity.CODE_className);
			javaCode.setAttribute("doc:name", "Java");
			flow.appendChild(javaCode);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	}
