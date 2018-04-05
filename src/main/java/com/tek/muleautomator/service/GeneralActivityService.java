package com.tek.muleautomator.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tek.muleautomator.element.GeneralActivityElement.AssignActivity;
import com.tek.muleautomator.element.GeneralActivityElement.CatchActivity;
import com.tek.muleautomator.element.GeneralActivityElement.InspectorActivity;
import com.tek.muleautomator.element.GeneralActivityElement.MapperActivity;
import com.tek.muleautomator.element.GeneralActivityElement.NullActivity;
import com.tek.muleautomator.element.GeneralActivityElement.SharedVariableActivity;
import com.tek.muleautomator.element.GeneralActivityElement.SleepActivity;
import com.tek.muleautomator.element.GeneralActivityElement.TimerActivity;
import com.tek.muleautomator.util.MuleAutomatorConstants;
import com.tek.muleautomator.util.MuleConfigConnection;

public class GeneralActivityService {
	public  void assign(String muleConfigPath, AssignActivity assignActivity, Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element assign = doc.createElement("set-variable");
			assign.setAttribute("variableName", assignActivity.getVariableName());
			assign.setAttribute("value", assignActivity.getVariableValue());
			assign.setAttribute("doc:name", "Variable");
			flow.appendChild(assign);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void catchService(String muleConfigPath, CatchActivity catchActivity, Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element catch_Exception_Strategy = doc.createElement("catch-exception-strategy");
			catch_Exception_Strategy.setAttribute("value", "!exception.causedBy(java.lang.ArithmeticException)");
			catch_Exception_Strategy.setAttribute("doc:name", "Catch_Exception_Strategy");
			flow.appendChild(catch_Exception_Strategy);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void inspector(String muleConfigPath, InspectorActivity inspectorActivity, Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element inspector = doc.createElement("logger");
			inspector.setAttribute("message", "Payload:   #[payload]");
			inspector.setAttribute("doc:name", "Logger");
			flow.appendChild(inspector);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  void nullactivity(String muleConfigPath, NullActivity nullActivity, Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element inspector = doc.createElement("logger");
			inspector.setAttribute("message", "Payload:   #[payload]");
			inspector.setAttribute("doc:name", "Null Activity");
			flow.appendChild(inspector);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void sleep(String muleConfigPath, SleepActivity sleepActivity, Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element sleep = doc.createElement("expression-component");
			sleep.setAttribute("doc:name", "Sleep");
			Long t=sleepActivity.getMiliseconds();
			sleep.setTextContent("Thread.sleep("+t+")");
			flow.appendChild(sleep);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void timer(String muleConfigPath, TimerActivity timerActivity, Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element connecterRef=doc.createElement("quartz:connector");
			connecterRef.setAttribute("name", "Quartz");
			connecterRef.setAttribute("validateConnections", "true");
			connecterRef.setAttribute("doc:name", "Quartz");
			doc.getFirstChild().appendChild(connecterRef);
			Element el=(Element)doc.getFirstChild();
			el.setAttribute("xmlns:quartz", "http://www.mulesoft.org/schema/mule/quartz");
					
			Element timer = doc.createElement("quartz:inbound-endpoint");
			timer.setAttribute("xsi:schemaLocation", "http://www.mulesoft.org/schema/mule/quartz/current/mule-quartz.xsd");
            timer.setAttribute("jobName", "myJob");
			timer.setAttribute("repeatCount", "1");
			timer.setAttribute("responseTimeout", "10000");
			timer.setAttribute("connector-ref", "Quartz");
			
			timer.setAttribute("repeatInterval",timerActivity.getCONFIG_timeInterval());
			timer.setAttribute("startDelay","10000000");
			Element eventgen=doc.createElement("quartz:event-generator-job");
			timer.appendChild(eventgen);
			timer.setAttribute("cronExpression", "0 30 7 1/1 * ?");
			timer.setAttribute("doc:name", "Quartz");
			flow.appendChild(timer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void mapper(String muleConfigPath, MapperActivity mapperActivity, Element flow) {
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			
			Element el=(Element)doc.getFirstChild();
			el.setAttribute("xmlns:dw", "http://www.mulesoft.org/schema/mule/ee/dw");
			
			Element mapper = doc.createElement("dw:transform-message");
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
				//mapper.setAttribute("xsi:schemaLocation", "http://www.mulesoft.org/schema/mule/ee/dw/current/dw.xsd");
				String val=MuleAutomatorConstants.specifiedSchema.get(muleConfigPath);
				val+=";dw:transform-message";
				MuleAutomatorConstants.specifiedSchema.put(muleConfigPath, val);
			}mapper.setAttribute("mimeType", "application/json");
			mapper.setAttribute("set-payload", "");
			mapper.setAttribute("doc:name", "Transform message");
			
			flow.appendChild(mapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void sharedVariable(String muleConfigPath, SharedVariableActivity sharedVariableActivity,
			   Element flow) {
			  try
			  {
			  Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			  Element sharedVariable = doc.createElement("set-session-variable");
			  sharedVariable.setAttribute("variableName", "Sesion_Variable");
			  sharedVariable.setAttribute("value","#[payload]");
			  sharedVariable.setAttribute("doc:name", "Session Variable");
			  flow.appendChild(sharedVariable);
			 }
			   catch (Exception e) {
			    e.printStackTrace();
			   }
	}
}
