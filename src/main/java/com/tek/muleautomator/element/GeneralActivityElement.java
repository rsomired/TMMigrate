package com.tek.muleautomator.element;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.tek.muleautomator.util.MuleAutomatorConstants;

public class GeneralActivityElement {

	public static class AssignActivity {
		
		private String variableName, variableValue;
		
		public AssignActivity(Node target){
			Element rootActivityElement=(Element)target;
			this.variableName=rootActivityElement.getElementsByTagName("variableName").item(0).getTextContent();
			try{
				this.variableValue=rootActivityElement.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue();
			} catch(Exception E){
				this.variableValue="";
			}
			
		}

		public String getVariableName() {
			return variableName;
		}

		public void setVariableName(String variableName) {
			this.variableName = variableName;
		}

		public String getVariableValue() {
			return variableValue;
		}

		public void setVariableValue(String variableValue) {
			this.variableValue = variableValue;
		}
		
		
	}
	
	public static class CatchActivity {
		private boolean CONFIG_catchAll;
		private String CONFIG_exception;
		
		public CatchActivity(Node target){
			Element rootEl=(Element)target;
			this.CONFIG_catchAll=rootEl.getElementsByTagName("catchAll").getLength()>0;
			this.CONFIG_exception="";
		}

		public boolean isCONFIG_catchAll() {
			return CONFIG_catchAll;
		}

		public void setCONFIG_catchAll(boolean cONFIG_catchAll) {
			CONFIG_catchAll = cONFIG_catchAll;
		}

		public String getCONFIG_exception() {
			return CONFIG_exception;
		}

		public void setCONFIG_exception(String cONFIG_exception) {
			CONFIG_exception = cONFIG_exception;
		}
		
		
	}
	
	public static class GetSharedVariableActivity {
		
	}
	
	public static class InspectorActivity {
		private String INPUT_fileName;
		private String CONFIG_append;
		private String CONFIG_activity;
		private String OUTPUT_output;
		
		public InspectorActivity(Node target){
			Element rootEL=(Element)target;
			this.INPUT_fileName=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootEL.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue());
			this.CONFIG_append=rootEL.getElementsByTagName("append").item(0).getTextContent();
			this.CONFIG_activity="";
			this.OUTPUT_output="";
		}

		public String getCONFIG_activity() {
			return CONFIG_activity;
		}

		public void setCONFIG_activity(String cONFIG_activity) {
			CONFIG_activity = cONFIG_activity;
		}

		public String getOUTPUT_output() {
			return OUTPUT_output;
		}

		public void setOUTPUT_output(String oUTPUT_output) {
			OUTPUT_output = oUTPUT_output;
		}

		public String getINPUT_fileName() {
			return INPUT_fileName;
		}

		public void setINPUT_fileName(String iNPUT_fileName) {
			INPUT_fileName = iNPUT_fileName;
		}

		public String getCONFIG_append() {
			return CONFIG_append;
		}

		public void setCONFIG_append(String cONFIG_append) {
			CONFIG_append = cONFIG_append;
		}
		
		
		
	}
	
	public static class SharedVariableActivity{
		private static String CONFIG_description,CONFIG_persistent,CONFIG_multiEngine,CONFIG_activityType;
		  public SharedVariableActivity(Node targetNode)
		  {
		   SharedVariableActivity.CONFIG_description=" this activity to retrieve the value of a Shared Variable resource, you may want to use a critical section group to ensure that no other process instances are altering the value of the shared variable at the same time.";
		   Element rootActivityElement = (Element)targetNode;
		   SharedVariableActivity.CONFIG_activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
		   
		  }
		  public static String getCONFIG_description() {
		   return CONFIG_description;
		  }
		  public static void setCONFIG_description(String cONFIG_description) {
		   CONFIG_description = cONFIG_description;
		  }
		  public static String getCONFIG_persistent() {
		   return CONFIG_persistent;
		  }
		  public static void setCONFIG_persistent(String cONFIG_persistent) {
		   CONFIG_persistent = cONFIG_persistent;
		  }
		  public static String getCONFIG_multiEngine() {
		   return CONFIG_multiEngine;
		  }
		  public static void setCONFIG_multiEngine(String cONFIG_multiEngine) {
		   CONFIG_multiEngine = cONFIG_multiEngine;
		  }
		  public static String getCONFIG_activityType() {
		   return CONFIG_activityType;
		  }
		  public static void setCONFIG_activityType(String cONFIG_activityType) {
		   CONFIG_activityType = cONFIG_activityType;
		  }
	}
	
	public static class SleepActivity {
		public long INPUT_miliseconds;
		
		public SleepActivity(Node target){
			Element el=(Element)target;
			Element intervalEl=(Element)el.getElementsByTagName("IntervalInMillisec").item(0);
			try{
				this.INPUT_miliseconds=Long.parseLong(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(intervalEl.getElementsByTagName("xsl:value-of").item(0).getAttributes().getNamedItem("select").getNodeValue()));
			} catch (Exception E){
				this.INPUT_miliseconds = 5000;
			}
		}

		public long getMiliseconds() {
			return INPUT_miliseconds;
		}

		public void setMiliseconds(long miliseconds) {
			this.INPUT_miliseconds = miliseconds;
		}
	}
	
	public static class TimerActivity {
		public String CONFIG_startTime;
		public String CONFIG_timeInterval;
		public String CONFIG_intervalUnit;
		public String MISC_seqKey;
		
		public TimerActivity(Node target){
			Element rootEl=(Element)target;
			this.CONFIG_startTime=MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootEl.getElementsByTagName("StartTime").item(0).getTextContent());
			this.CONFIG_timeInterval=Integer.toString(60000*Integer.parseInt(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootEl.getElementsByTagName("TimeInterval").item(0).getTextContent())));
			this.MISC_seqKey="";
			this.CONFIG_intervalUnit="";
		}

		public String getCONFIG_startTime() {
			return CONFIG_startTime;
		}

		public void setCONFIG_startTime(String cONFIG_startTime) {
			CONFIG_startTime = cONFIG_startTime;
		}

		public String getCONFIG_timeInterval() {
			return CONFIG_timeInterval;
		}

		public void setCONFIG_timeInterval(String cONFIG_timeInterval) {
			CONFIG_timeInterval = cONFIG_timeInterval;
		}

		public String getCONFIG_intervalUnit() {
			return CONFIG_intervalUnit;
		}

		public void setCONFIG_intervalUnit(String cONFIG_intervalUnit) {
			CONFIG_intervalUnit = cONFIG_intervalUnit;
		}

		public String getMISC_seqKey() {
			return MISC_seqKey;
		}

		public void setMISC_seqKey(String mISC_seqKey) {
			MISC_seqKey = mISC_seqKey;
		}
		
	}
	
	public static class MapperActivity {
	
		
		public MapperActivity(Node target){
			
		}
	}
	
	public static class NullActivity {
		
	}
	
}
