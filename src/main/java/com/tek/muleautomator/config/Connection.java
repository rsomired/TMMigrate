package com.tek.muleautomator.config;

public abstract class Connection {

	public enum CONNECTION_TYPE {
		FTP, HTTP, JDBC, JMS, WSDL;
	};
	public abstract String getConnectionType();
	
}
