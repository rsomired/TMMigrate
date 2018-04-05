package com.tek.muleautomator.element;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
public class XMLElement {
public static class XMLRendererActivity{
	   private String CONFIG_description;
	   private  String activityType;
	   private static String CONFIG_name,CONFIG_outputStyle,CONFIG_validateInput,CONFIG_encoding;
	   private static String In_byteEncoding,In_xmlschema;
	   private static String Out_xmlstring,Out_xmlbyte;
	   public XMLRendererActivity(Node target)
	   {
	    Element rootActivityElement=(Element)target;
	       this.CONFIG_description="The Render XML activity takes an instance of an XML schema element and renders it as a stream of bytes containing XML or an XML string. The schema is processed based on the XSD file specified. ";
	       this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
	       this.CONFIG_validateInput=rootActivityElement.getElementsByTagName("validateInput").item(0).getTextContent();
	       this.CONFIG_encoding=rootActivityElement.getElementsByTagName("textEncoding").item(0).getTextContent();
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
	public static String getCONFIG_name() {
		return CONFIG_name;
	}
	public static void setCONFIG_name(String cONFIG_name) {
		CONFIG_name = cONFIG_name;
	}
	public static String getCONFIG_outputStyle() {
		return CONFIG_outputStyle;
	}
	public static void setCONFIG_outputStyle(String cONFIG_outputStyle) {
		CONFIG_outputStyle = cONFIG_outputStyle;
	}
	public static String getCONFIG_validateInput() {
		return CONFIG_validateInput;
	}
	public static void setCONFIG_validateInput(String cONFIG_validateInput) {
		CONFIG_validateInput = cONFIG_validateInput;
	}
	public static String getCONFIG_encoding() {
		return CONFIG_encoding;
	}
	public static void setCONFIG_encoding(String cONFIG_encoding) {
		CONFIG_encoding = cONFIG_encoding;
	}
	public static String getIn_byteEncoding() {
		return In_byteEncoding;
	}
	public static void setIn_byteEncoding(String in_byteEncoding) {
		In_byteEncoding = in_byteEncoding;
	}
	public static String getIn_xmlschema() {
		return In_xmlschema;
	}
	public static void setIn_xmlschema(String in_xmlschema) {
		In_xmlschema = in_xmlschema;
	}
	public static String getOut_xmlstring() {
		return Out_xmlstring;
	}
	public static void setOut_xmlstring(String out_xmlstring) {
		Out_xmlstring = out_xmlstring;
	}
	public static String getOut_xmlbyte() {
		return Out_xmlbyte;
	}
	public static void setOut_xmlbyte(String out_xmlbyte) {
		Out_xmlbyte = out_xmlbyte;
	}
}
public static class XMLTransformActivity{
	private String CONFIG_description;
	   private  String activityType;
	   private static String CONFIG_name,CONFIG_styleSheet,CONFIG_inputAndOutputStyle,CONFIG_xsltEngine;
	   private static String In_styleSheet,In_parameter,In_name,In_value;
	   private static boolean In_isXMLDocument;
	   public XMLTransformActivity(Node target)
	   {
	    Element rootActivityElement=(Element)target;
	       this.CONFIG_description="The Transform XML activity allows you to transform an input XML document into the output specified by the given XSLT File shared configuration resource. ";
	       this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
}
}
}
