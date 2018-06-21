package com.tek.muleautomator.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tek.muleautomator.config.Connection;
import com.tek.muleautomator.config.JDBCConnection;
import com.tek.muleautomator.element.JDBCElement;
import com.tek.muleautomator.util.MuleAutomatorConstants;
import com.tek.muleautomator.util.MuleConfigConnection;

public class JDBCService {

	public void jdbcConfiguration(String muleConfigPath, String conName) {

		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			Element muleTag = (Element) doc.getFirstChild();
			ArrayList<JDBCConnection> connections = new ArrayList<>();
			for (Map.Entry<String, Connection> entry : MuleAutomatorConstants.connectionConfigs.entrySet()) {
				if (entry.getValue().getConnectionType().equals("JDBC")) {
					connections.add((JDBCConnection) entry.getValue());
				}
			}
			if (connections.size() == 0) {
				System.err.println(">>> No JDBC Configuration file found. Setting default Config");
				System.err.println(
						">>> Please make sure the location of TIBCO Root folder is correct and has 'Shared' folder");
				// Hardcoded values if configuration file not found
				Element jdbcConfig = doc.createElement("db:oracle-config");
				jdbcConfig.setAttribute("name", "Oracle_Configuration");
				jdbcConfig.setAttribute("host", "localhost");
				jdbcConfig.setAttribute("port", "1521");
				jdbcConfig.setAttribute("instance", "xe");
				jdbcConfig.setAttribute("user", "admin");
				jdbcConfig.setAttribute("password", "1234");
				jdbcConfig.setAttribute("doc:name", "Oracle Configuration");
				muleTag.appendChild(jdbcConfig);
			} else {

				for (JDBCConnection con : connections) {
					if (!con.CONNECTION_NAME.equals(conName))
						continue;
					if (con.IS_CONFIGURED)
						break;
					con.IS_CONFIGURED = true;
					Element jdbcConfig = doc.createElement("db:oracle-config");
					jdbcConfig.setAttribute("name", con.CONNECTION_NAME.replaceAll(" ", "_"));
					jdbcConfig.setAttribute("host", con.HOST);
					jdbcConfig.setAttribute("port", con.PORT);
					jdbcConfig.setAttribute("instance", con.INSTANCE);
					jdbcConfig.setAttribute("user", con.USERNAME);
					jdbcConfig.setAttribute("password", con.PASSWORD);
					jdbcConfig.setAttribute("doc:name", con.CONNECTION_NAME);
					muleTag.appendChild(jdbcConfig);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void jdbcCallProcedure(String muleConfigPath, JDBCElement.JDBCCallActivity jdbcCallActivity, Element flow) {
		try {

			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);

			jdbcConfiguration(muleConfigPath, jdbcCallActivity.getConnectionName());

			Element stordProc = doc.createElement("db:stored-procedure");
			stordProc.setAttribute("config-ref", jdbcCallActivity.getConnectionName().replaceAll(" ", "_"));
			stordProc.setAttribute("doc:name", "Database");

			Element dbParamQuery = doc.createElement("db:parameterized-query");
			dbParamQuery.setTextContent(jdbcCallActivity.getConnectionName().replaceAll(" ", "_"));
			stordProc.appendChild(dbParamQuery);
			if(jdbcCallActivity.getParams()!=null){
				for (int i = 0; i < jdbcCallActivity.getParams().size(); ++i) {
					Element dbParam = doc.createElement("db:in-param");
					dbParam.setAttribute("name", jdbcCallActivity.getParams().get(i));
					dbParam.setAttribute("value", "#[payload." + jdbcCallActivity.getParams().get(i) + "]");
					stordProc.appendChild(dbParam);
				}
			}

			flow.appendChild(stordProc);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void jdbcSelect(String muleConfigPath, JDBCElement.JDBCQueryActivity jdbcQueryActivity, Element flow) {

		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);

			jdbcConfiguration(muleConfigPath, jdbcQueryActivity.getConnectionName());

			Element dbSelect = doc.createElement("db:select");
			dbSelect.setAttribute("config-ref", jdbcQueryActivity.getConnectionName().replaceAll(" ", "_"));
			dbSelect.setAttribute("doc:name", "Database");

			Element dbParamQuery = doc.createElement("db:parameterized-query");
			dbParamQuery.setTextContent(jdbcQueryActivity.getNamedParamsQuery());
			dbSelect.appendChild(dbParamQuery);

			for (String param : jdbcQueryActivity.getQueryParams()) {
				Element dbParamAccountType = doc.createElement("db:in-param");
				dbParamAccountType.setAttribute("name", param);
				dbParamAccountType.setAttribute("value", "#[payload." + param + "]");
				dbSelect.appendChild(dbParamAccountType);
			}

			flow.appendChild(dbSelect);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void jdbcInsert(String muleConfigPath, JDBCElement.JDBCUpdateActivity jdbcUpdateActivity, Element flow) {

		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);

			jdbcConfiguration(muleConfigPath, jdbcUpdateActivity.getConnectionName());

			Element dbInsert = doc.createElement("db:insert");
			dbInsert.setAttribute("config-ref", jdbcUpdateActivity.getConnectionName().replaceAll(" ", "_"));
			dbInsert.setAttribute("doc:name", "Database");

			Element dbParamQuery = doc.createElement("db:parameterized-query");
			dbParamQuery.setTextContent(jdbcUpdateActivity.getNamedParamsQuery());
			dbInsert.appendChild(dbParamQuery);
			
			Element jdbcTemplateConfig = doc.createElement("db:template-query");
			jdbcTemplateConfig.setAttribute("name", "Template_Query");
			jdbcTemplateConfig.setAttribute("doc:name", "Template Query");
			Element pQuery = doc.createElement("db:parameterized-query");
			Element cData = doc.createElement("![CDATA["+jdbcUpdateActivity.getNamedParamsQuery()+"]]");
			pQuery.appendChild(cData);
			jdbcTemplateConfig.appendChild(pQuery);

			for (String param : jdbcUpdateActivity.getQueryParams()) {

				Element dbParam = doc.createElement("db:in-param");
				dbParam.setAttribute("name", param);
				dbParam.setAttribute("defaultValue", "#[payload." + param + "]");
				jdbcTemplateConfig.appendChild(dbParam);

			}

			flow.appendChild(jdbcTemplateConfig);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void jdbcUpdate(String muleConfigPath, JDBCElement.JDBCUpdateActivity jdbcUpdateActivity, Element flow) {

		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);

			jdbcConfiguration(muleConfigPath, jdbcUpdateActivity.getConnectionName());

			Element dbUpdate = doc.createElement("db:insert");
			dbUpdate.setAttribute("config-ref", jdbcUpdateActivity.getConnectionName().replaceAll(" ", "_"));
			dbUpdate.setAttribute("doc:name", "Database");
			
			Element dbParamQuery = doc.createElement("db:parameterized-query");
			dbParamQuery.setTextContent(jdbcUpdateActivity.getNamedParamsQuery());
			dbUpdate.appendChild(dbParamQuery);
			
			/*for (String param : jdbcUpdateActivity.getQueryParams()) {

				Element dbParam = doc.createElement("db:in-param");
				dbParam.setAttribute("name", param);
				dbParam.setAttribute("value", "#[payload." + param + "]");
				dbUpdate.appendChild(dbParam);

			}
*/
			flow.appendChild(dbUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void jdbcDelete(String muleConfigPath, JDBCElement.JDBCUpdateActivity jdbcUpdateActivity, Element flow) {

		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);

			jdbcConfiguration(muleConfigPath, jdbcUpdateActivity.getConnectionName());

			Element dbDelete = doc.createElement("db:delete");
			dbDelete.setAttribute("config-ref", jdbcUpdateActivity.getConnectionName().replaceAll(" ", "_"));
			dbDelete.setAttribute("doc:name", "Database");

			Element dbParamQuery = doc.createElement("db:parameterized-query");
			dbParamQuery.setTextContent(jdbcUpdateActivity.getNamedParamsQuery());
			dbDelete.appendChild(dbParamQuery);

			for (String param : jdbcUpdateActivity.getQueryParams()) {
				Element dbParam = doc.createElement("db:in-param");
				dbParam.setAttribute("name", param);
				dbParam.setAttribute("value", "#[payload." + param + "]");
				dbDelete.appendChild(dbParam);
			}

			flow.appendChild(dbDelete);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isJDBCConfigRequired(String muleConfigPath, String conName)
			throws ParserConfigurationException, SAXException, IOException {
		Document doc;
		try {
			doc = MuleConfigConnection.getDomObj(muleConfigPath);
			NodeList nodeList = doc.getElementsByTagName("db:oracle-config");
			for (int i = 0; i < nodeList.getLength(); ++i) {
				Element el = (Element) nodeList.item(i);
				if (el.getAttribute("name").replaceAll("_", " ").equals(conName)) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
