package com.tek.muleautomator.element;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tek.muleautomator.util.MuleAutomatorConstants;

public class JDBCElement {

	private static String generateQueryString(String preparedQuery, List<String> params){
		if(!preparedQuery.contains("?"))
			return preparedQuery;
		for(String a:params)
			preparedQuery=preparedQuery.replaceFirst("\\?","#[payload."+a+"]");
		return preparedQuery;
	}
	
	public static class JDBCUpdateActivity {
		private static String description, connectionPath, activityType;
		private int TIMEOUT;
		private String sqlQuery;
		private List<String> queryParams;
		private boolean commit, batchUpdate, emptyStrAsNil;
		private String namedParamsQuery, connectionName;
		
		public String getConnectionName() {
			return connectionName;
		}

		public void setConnectionName(String connectionName) {
			this.connectionName = connectionName;
		}

		public JDBCUpdateActivity(Node targetNode){
			queryParams=new ArrayList<>();
			JDBCUpdateActivity.description="The JDBC Update activity performs the specified SQL INSERT, UPDATE, or DELETE statement";
			Element rootActivityElement = (Element)targetNode;
        	JDBCUpdateActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	//JDBCUpdateActivity.connectionPath=rootActivityElement.getElementsByTagName("jdbcSharedConfig").item(0).getTextContent();
        	this.TIMEOUT=rootActivityElement.getElementsByTagName("timeout").getLength()>0?Integer.parseInt(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("timeout").item(0).getTextContent())):10;
        	this.commit=rootActivityElement.getElementsByTagName("commit").getLength()>0?Boolean.parseBoolean(rootActivityElement.getElementsByTagName("commit").item(0).getTextContent()):false;
        	this.batchUpdate=rootActivityElement.getElementsByTagName("batchUpdate").getLength()>0?Boolean.parseBoolean(rootActivityElement.getElementsByTagName("batchUpdate").item(0).getTextContent()):false;
        	this.emptyStrAsNil=rootActivityElement.getElementsByTagName("emptyStrAsNil").getLength()>0?Boolean.parseBoolean(rootActivityElement.getElementsByTagName("emptyStrAsNil").item(0).getTextContent()):false;
        	this.sqlQuery=rootActivityElement.getElementsByTagName("statement").item(0).getTextContent();
        	if(this.sqlQuery.contains("?")){
        		NodeList params=rootActivityElement.getElementsByTagName("parameter");
        		for(int i=0;i<params.getLength();++i){
        			if(params.item(i).getNodeType()==Node.ELEMENT_NODE){
        			Element currParam=(Element)params.item(i);
        			queryParams.add(currParam.getElementsByTagName("parameterName").item(0).getTextContent());
        			}
        		}
        	}
        	String con=rootActivityElement.getElementsByTagName("jdbcSharedConfig").item(0).getTextContent();
        	this.connectionName=con.substring(con.lastIndexOf("/")+1, con.lastIndexOf("."));
        	this.namedParamsQuery=generateQueryString(this.sqlQuery, this.queryParams);
        	
		}
		
		

		public String getNamedParamsQuery() {
			return namedParamsQuery;
		}
		public static String getDescription() {
			return description;
		}

		public static String getConnectionPath() {
			return connectionPath;
		}

		public static String getActivityType() {
			return activityType;
		}

		public int getTIMEOUT() {
			return TIMEOUT;
		}

		public String getSqlQuery() {
			return sqlQuery;
		}

		public List<String> getQueryParams() {
			return queryParams;
		}

		public boolean isCommit() {
			return commit;
		}

		public boolean isBatchUpdate() {
			return batchUpdate;
		}
		
	}
	
	public static class JDBCQueryActivity {
		private static String description, connectionPath, activityType;
		public String getConnectionName() {
			return connectionName;
		}

		public void setConnectionName(String connectionName) {
			this.connectionName = connectionName;
		}

		private int TIMEOUT;
		private String sqlQuery;
		private List<String> queryParams;
		private boolean commit, batchUpdate, emptyStrAsNil;
		private String namedParamsQuery, connectionName;
		public JDBCQueryActivity(Node targetNode){
			queryParams=new ArrayList<>();
			JDBCUpdateActivity.description="The JDBC Query activity performs the specified SQL SELECT statement";
			Element rootActivityElement = (Element)targetNode;
        	JDBCUpdateActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	//JDBCUpdateActivity.connectionPath=rootActivityElement.getElementsByTagName("jdbcSharedConfig").item(0).getTextContent();
        	try{
        		this.TIMEOUT=Integer.valueOf(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("timeout").getLength()>0?rootActivityElement.getElementsByTagName("timeout").item(0).getTextContent():"10"));
        	} catch (Exception E){
        		this.TIMEOUT=1000;
        	}
        	this.commit=rootActivityElement.getElementsByTagName("commit").getLength()>0?Boolean.parseBoolean(rootActivityElement.getElementsByTagName("commit").item(0).getTextContent()):false;
        	this.batchUpdate=rootActivityElement.getElementsByTagName("batchUpdate").getLength()>0?Boolean.parseBoolean(rootActivityElement.getElementsByTagName("batchUpdate").item(0).getTextContent()):false;
        	this.emptyStrAsNil=rootActivityElement.getElementsByTagName("emptyStrAsNil").getLength()>0?Boolean.parseBoolean(rootActivityElement.getElementsByTagName("emptyStrAsNil").item(0).getTextContent()):false;
        	this.sqlQuery=rootActivityElement.getElementsByTagName("statement").item(0).getTextContent();
        	if(this.sqlQuery.contains("?")){
        		NodeList params=rootActivityElement.getElementsByTagName("parameter");
        		for(int i=0;i<params.getLength();++i){
        			if(params.item(i).getNodeType()==Node.ELEMENT_NODE){
        			Element currParam=(Element)params.item(i);
        			queryParams.add(currParam.getElementsByTagName("parameterName").item(0).getTextContent());
        			}
        		}
        	}
        	String con=rootActivityElement.getElementsByTagName("jdbcSharedConfig").item(0).getTextContent();
        	this.connectionName=con.substring(con.lastIndexOf("/")+1, con.lastIndexOf("."));
        	this.namedParamsQuery=generateQueryString(this.sqlQuery, this.queryParams);
		}
		
		public String getNamedParamsQuery() {
			return namedParamsQuery;
		}

		public static String getDescription() {
			return description;
		}

		public static String getConnectionPath() {
			return connectionPath;
		}

		public static String getActivityType() {
			return activityType;
		}

		public int getTIMEOUT() {
			return TIMEOUT;
		}

		public String getSqlQuery() {
			return sqlQuery;
		}

		public List<String> getQueryParams() {
			return queryParams;
		}

		public boolean isCommit() {
			return commit;
		}

		public boolean isBatchUpdate() {
			return batchUpdate;
		}
		
	}

	public static class JDBCCallActivity {
		private static String description;
		private int TIMEOUT, maxRows;
		private boolean emptyStrAsNil;
		private String procedureName;
		private String packageName;
		private List<String> params;
		private String namedParamQuery, connectionName;
		
		public String getConnectionName() {
			return connectionName;
		}

		public void setConnectionName(String connectionName) {
			this.connectionName = connectionName;
		}

		public static String getDescription() {
			return description;
		}

		public static void setDescription(String description) {
			JDBCCallActivity.description = description;
		}

		public int getTIMEOUT() {
			return TIMEOUT;
		}

		public void setTIMEOUT(int tIMEOUT) {
			TIMEOUT = tIMEOUT;
		}

		public int getMaxRows() {
			return maxRows;
		}

		public void setMaxRows(int maxRows) {
			this.maxRows = maxRows;
		}

		public boolean isEmptyStrAsNil() {
			return emptyStrAsNil;
		}

		public void setEmptyStrAsNil(boolean emptyStrAsNil) {
			this.emptyStrAsNil = emptyStrAsNil;
		}

		public String getProcedureName() {
			return procedureName;
		}

		public void setProcedureName(String procedureName) {
			this.procedureName = procedureName;
		}

		public String getPackageName() {
			return packageName;
		}

		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

		public List<String> getParams() {
			return params;
		}

		public void setParams(List<String> params) {
			this.params = params;
		}

		public String getNamedParamQuery() {
			return namedParamQuery;
		}

		public void setNamedParamQuery(String namedParamQuery) {
			this.namedParamQuery = namedParamQuery;
		}

		public JDBCCallActivity(Node targetNode){
			this.packageName="default.package";
			JDBCCallActivity.description="The JDBC Call Procedure activity calls a database procedure using the specified JDBC connection";
			Element rootActivityElement = (Element)targetNode;
        	JDBCUpdateActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	//JDBCUpdateActivity.connectionPath=rootActivityElement.getElementsByTagName("jdbcSharedConfig").item(0).getTextContent();
        	this.TIMEOUT=rootActivityElement.getElementsByTagName("timeout").getLength()>0?Integer.parseInt(MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(rootActivityElement.getElementsByTagName("timeout").item(0).getTextContent())):10;
        	this.maxRows=rootActivityElement.getElementsByTagName("maxRows").getLength()>0?Integer.parseInt(rootActivityElement.getElementsByTagName("maxRows").item(0).getTextContent()):10;
        	
        	this.emptyStrAsNil=rootActivityElement.getElementsByTagName("emptyStrAsNil").getLength()>0?Boolean.parseBoolean(rootActivityElement.getElementsByTagName("emptyStrAsNil").item(0).getTextContent()):false;
			this.procedureName=rootActivityElement.getElementsByTagName("ProcedureName").item(0).getTextContent();
			
			try{
				NodeList allParamsNodeList=rootActivityElement.getElementsByTagName("inputSet").item(0).getChildNodes();
				this.params=new ArrayList<>();
				
				for(int i=0;i<allParamsNodeList.getLength();++i){
					if(allParamsNodeList.item(i).getNodeType()==Node.ELEMENT_NODE){
					Element curr=(Element)allParamsNodeList.item(i);
					params.add(curr.getNodeName());
				}
				}
				this.namedParamQuery="{call "+this.procedureName+"(";
				for(int i=0;i<params.size();++i){
					this.namedParamQuery+=":"+params.get(i);
					if(i+1<params.size()){
						this.namedParamQuery+=",";
					}
				}
				this.namedParamQuery+=")}";
			} catch (Exception E){
				
			}
			String con=rootActivityElement.getElementsByTagName("jdbcSharedConfig").item(0).getTextContent();
        	this.connectionName=con.substring(con.lastIndexOf("/")+1, con.lastIndexOf("."));
		}
	}

	public static class JDBCSQLDirectActivity {
		private static String description, connectionPath, activityType;
		private int TIMEOUT;
		private String sqlQuery;
		private int OUTPUT_noOfUpdates;
		
		public JDBCSQLDirectActivity(Node targetNode){
			
			JDBCUpdateActivity.description="The SQL Direct activity executes a SQL statement that you provide. This activity allows you to build a SQL statement dynamically (using other activities), then pass the SQL statement into this activity�s input";
			Element rootActivityElement = (Element)targetNode;
        	JDBCUpdateActivity.activityType=rootActivityElement.getElementsByTagName("pd:type").item(0).getTextContent();
        	//JDBCUpdateActivity.connectionPath=rootActivityElement.getElementsByTagName("jdbcSharedConfig").item(0).getTextContent();
        	this.TIMEOUT=rootActivityElement.getElementsByTagName("timeout").getLength()>0?Integer.parseInt(rootActivityElement.getElementsByTagName("timeout").item(0).getTextContent()):10;
        	this.sqlQuery=rootActivityElement.getElementsByTagName("statement").item(0).getTextContent();
        	
		}
		
		public int getNoOfUpdates() {
			return this.OUTPUT_noOfUpdates;
		}

		public static String getDescription() {
			return description;
		}

		public static String getConnectionPath() {
			return connectionPath;
		}

		public static String getActivityType() {
			return activityType;
		}

		public int getTIMEOUT() {
			return TIMEOUT;
		}

		public String getSqlQuery() {
			return sqlQuery;
		}

		
		
	}

}
