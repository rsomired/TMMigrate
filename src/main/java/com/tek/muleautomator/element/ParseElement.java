package com.tek.muleautomator.element;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
public class ParseElement {
	public static class ParseDataActivity{
		private String CONFIG_dataFormat,CONFIG_inputType,CONFIG_encoding,CONFIG_skipBlankLines,CONFIG_manuallySpecifyStartRecord;
		private String CONFIG_strictValidation,CONFIG_continueOnError;	
		private String IN_text,IN_fileName;
		private int IN_startRecord,IN_noOfRecords,IN_skipHeaderCharacters;
		private HashMap<String,String> OUT_rows,OUT_schema,OUT_errorRows;
		private boolean OUT_EOF;
		private String CONFIG_description;
		private String activityType;
		public ParseDataActivity(Node target){
			Element rootActivityElement=(Element)target;
        	this.CONFIG_description="The Parse Data activity takes a text string or input from a file and processes it, turning it into a schema tree based on the specified DataFormat shared configuration.";
        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	
			}
		public String getCONFIG_dataFormat() {
			return CONFIG_dataFormat;
		}
		public void setCONFIG_dataFormat(String cONFIG_dataFormat) {
			CONFIG_dataFormat = cONFIG_dataFormat;
		}
		public String getCONFIG_inputType() {
			return CONFIG_inputType;
		}
		public void setCONFIG_inputType(String cONFIG_inputType) {
			CONFIG_inputType = cONFIG_inputType;
		}
		public String getCONFIG_encoding() {
			return CONFIG_encoding;
		}
		public void setCONFIG_encoding(String cONFIG_encoding) {
			CONFIG_encoding = cONFIG_encoding;
		}
		public String getCONFIG_skipBlankLines() {
			return CONFIG_skipBlankLines;
		}
		public void setCONFIG_skipBlankLines(String cONFIG_skipBlankLines) {
			CONFIG_skipBlankLines = cONFIG_skipBlankLines;
		}
		public String getCONFIG_manuallySpecifyStartRecord() {
			return CONFIG_manuallySpecifyStartRecord;
		}
		public void setCONFIG_manuallySpecifyStartRecord(String cONFIG_manuallySpecifyStartRecord) {
			CONFIG_manuallySpecifyStartRecord = cONFIG_manuallySpecifyStartRecord;
		}
		public String getCONFIG_strictValidation() {
			return CONFIG_strictValidation;
		}
		public void setCONFIG_strictValidation(String cONFIG_strictValidation) {
			CONFIG_strictValidation = cONFIG_strictValidation;
		}
		public String getCONFIG_continueOnError() {
			return CONFIG_continueOnError;
		}
		public void setCONFIG_continueOnError(String cONFIG_continueOnError) {
			CONFIG_continueOnError = cONFIG_continueOnError;
		}
		public String getIN_text() {
			return IN_text;
		}
		public void setIN_text(String iN_text) {
			IN_text = iN_text;
		}
		public String getIN_fileName() {
			return IN_fileName;
		}
		public void setIN_fileName(String iN_fileName) {
			IN_fileName = iN_fileName;
		}
		public int getIN_startRecord() {
			return IN_startRecord;
		}
		public void setIN_startRecord(int iN_startRecord) {
			IN_startRecord = iN_startRecord;
		}
		public int getIN_noOfRecords() {
			return IN_noOfRecords;
		}
		public void setIN_noOfRecords(int iN_noOfRecords) {
			IN_noOfRecords = iN_noOfRecords;
		}
		public int getIN_skipHeaderCharacters() {
			return IN_skipHeaderCharacters;
		}
		public void setIN_skipHeaderCharacters(int iN_skipHeaderCharacters) {
			IN_skipHeaderCharacters = iN_skipHeaderCharacters;
		}
		public HashMap<String, String> getOUT_rows() {
			return OUT_rows;
		}
		public void setOUT_rows(HashMap<String, String> oUT_rows) {
			OUT_rows = oUT_rows;
		}
		public HashMap<String, String> getOUT_schema() {
			return OUT_schema;
		}
		public void setOUT_schema(HashMap<String, String> oUT_schema) {
			OUT_schema = oUT_schema;
		}
		public HashMap<String, String> getOUT_errorRows() {
			return OUT_errorRows;
		}
		public void setOUT_errorRows(HashMap<String, String> oUT_errorRows) {
			OUT_errorRows = oUT_errorRows;
		}
		public boolean isOUT_EOF() {
			return OUT_EOF;
		}
		public void setOUT_EOF(boolean oUT_EOF) {
			OUT_EOF = oUT_EOF;
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
			
		}
	public static class RenderDataActivity{
		private String CONFIG_description;
		private String activityType;
		private String CONFIG_dataFormat;
        private HashMap<String,String> IN_rows,IN_root;
        private String OUT_text;
		public RenderDataActivity(Node target){
			Element rootActivityElement=(Element)target;
        	this.CONFIG_description="The Render Data activity takes an instance of a data schema and renders it as a text string. The schema processed is based on a specified Data Format shared configuration. ";
        	this.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
			
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
		public String getCONFIG_dataFormat() {
			return CONFIG_dataFormat;
		}
		public void setCONFIG_dataFormat(String cONFIG_dataFormat) {
			CONFIG_dataFormat = cONFIG_dataFormat;
		}
		public HashMap<String, String> getIN_rows() {
			return IN_rows;
		}
		public void setIN_rows(HashMap<String, String> iN_rows) {
			IN_rows = iN_rows;
		}
		public HashMap<String, String> getIN_root() {
			return IN_root;
		}
		public void setIN_root(HashMap<String, String> iN_root) {
			IN_root = iN_root;
		}
		public String getOUT_text() {
			return OUT_text;
		}
		public void setOUT_text(String oUT_text) {
			OUT_text = oUT_text;
		}
}
}
