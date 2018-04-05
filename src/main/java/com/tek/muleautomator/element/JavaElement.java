package com.tek.muleautomator.element;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
public class JavaElement {
public static class JavaCodeActivity{
	private String CONFIG_description;
	private String activityType;
	private static String CONFIG_inputParameters,CONFIG_outputParameters;
	public static String CODE_JavaCode,CODE_packageName,CODE_className;
	private static String IN_parameters;
	private static String OUT_parameters;
	public  JavaCodeActivity(Node target) {
		Element rootActivityElement=(Element)target;
    	this.CONFIG_description="This activity allows you to write standard Java code that can manipulate any of the process data or perform any action you choose. ";
    	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
	    this.CODE_className=rootActivityElement.getElementsByTagName("fileName").item(0).getTextContent();
	    this.CODE_JavaCode=rootActivityElement.getElementsByTagName("fullsource").item(0).getTextContent();
	    this.CODE_packageName=rootActivityElement.getElementsByTagName("packageName").item(0).getTextContent();
	}
	public String getCONFIG_description() {
		return CONFIG_description;
	}
	public void setCONFIG_description(String cONFIG_description) {
		CONFIG_description = cONFIG_description;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public static String getCONFIG_inputParameters() {
		return CONFIG_inputParameters;
	}
	public static void setCONFIG_inputParameters(String cONFIG_inputParameters) {
		CONFIG_inputParameters = cONFIG_inputParameters;
	}
	public static String getCONFIG_outputParameters() {
		return CONFIG_outputParameters;
	}
	public static void setCONFIG_outputParameters(String cONFIG_outputParameters) {
		CONFIG_outputParameters = cONFIG_outputParameters;
	}
	public static String getCODE_JavaCode() {
		return CODE_JavaCode;
	}
	public static void setCODE_JavaCode(String cODE_JavaCode) {
		CODE_JavaCode = cODE_JavaCode;
	}
	public static String getCODE_packageName() {
		return CODE_packageName;
	}
	public static void setCODE_packageName(String cODE_packageName) {
		CODE_packageName = cODE_packageName;
	}
	public static String getCODE_className() {
		return CODE_className;
	}
	public static void setCODE_className(String cODE_className) {
		CODE_className = cODE_className;
	}
	public static String getIN_parameters() {
		return IN_parameters;
	}
	public static void setIN_parameters(String iN_parameters) {
		IN_parameters = iN_parameters;
	}
	public static String getOUT_parameters() {
		return OUT_parameters;
	}
	public static void setOUT_parameters(String oUT_parameters) {
		OUT_parameters = oUT_parameters;
	}
}


}
