package com.tek.muleautomator.service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tek.muleautomator.element.TCPElement;
import com.tek.muleautomator.util.MuleConfigConnection;
public class TCPService {
	 public void tcpConnection(String muleConfigPath,TCPElement.TCPConnectionActivity tcpConnectionActivity, Element flow){
		 try {
			   Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			   Element connecterRef=doc.createElement("tcp:connector");
			   connecterRef.setAttribute("name", "TCP");
			   connecterRef.setAttribute("sendBufferSize", "0");
			   connecterRef.setAttribute("receiveBufferSize", "0");
			   connecterRef.setAttribute("receiveBacklog", "0");
			   connecterRef.setAttribute("clientSoTimeout", "10000");
			   connecterRef.setAttribute("serverSoTimeout", "10000");
			   connecterRef.setAttribute("socketSoLinger", "0");
			   connecterRef.setAttribute("doc:name", "TCP");
			   doc.getFirstChild().appendChild(connecterRef);
			   Element el=(Element)doc.getFirstChild();
			   el.setAttribute("xmlns:tcp", "http://www.mulesoft.org/schema/mule/tcp");
			     
			   Element tcp1 = doc.createElement("tcp:outbound-endpoint");
			   tcp1.setAttribute("xsi:schemaLocation", "http://www.mulesoft.org/schema/mule/tcp http://www.mulesoft.org/schema/mule/tcp/current/mule-tcp.xsd");
			   tcp1.setAttribute("host",tcpConnectionActivity.getCONFIG_host());
			   tcp1.setAttribute("port",tcpConnectionActivity.getCONFIG_port() );
			   tcp1.setAttribute("exchange-pattern","request-response" );
			   tcp1.setAttribute("connector-ref","TCP" );
			   tcp1.setAttribute("responseTimeout","10000" );
			   tcp1.setAttribute("doc:name", "TCP");
			   flow.appendChild(tcp1);
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
		/* try {
			   Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			   Element connecterRef=doc.createElement("tcp:connector");
			   connecterRef.setAttribute("name", "TCP");
			   connecterRef.setAttribute("sendBufferSize", "0");
			   connecterRef.setAttribute("receiveBufferSize", "0");
			   connecterRef.setAttribute("receiveBacklog", "0");
			   connecterRef.setAttribute("clientSoTimeout", "10000");
			   connecterRef.setAttribute("serverSoTimeout", "10000");
			   connecterRef.setAttribute("socketSoLinger", "0");
			   connecterRef.setAttribute("doc:name", "TCP");
			   doc.getFirstChild().appendChild(connecterRef);
			   Element el=(Element)doc.getFirstChild();
			   el.setAttribute("xmlns:tcp", "http://www.mulesoft.org/schema/mule/tcp");
			     
			   Element tcp = doc.createElement("tcp:inbound-endpoint");
			   tcp.setAttribute("xsi:schemaLocation", "http://www.mulesoft.org/schema/mule/tcp http://www.mulesoft.org/schema/mule/tcp/current/mule-tcp.xsd");
			   tcp.setAttribute("host",tcpConnectionActivity.getCONFIG_host());
			   tcp.setAttribute("port",tcpConnectionActivity.getCONFIG_port() );
			   tcp.setAttribute("exchange-pattern","request-response" );
			   tcp.setAttribute("responseTimeout","10000" );
			   tcp.setAttribute("doc:name", "TCP");
			   flow.appendChild(tcp);
			  } catch (Exception e) {
			   e.printStackTrace();
			  }        */
	 }
		  public void tcpOpenConnection(String muleConfigPath,TCPElement.TCPOpenConnectionActivity tcpOpenConnectionActivity, Element flow){
			  try {
				   Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
				   Element connecterRef=doc.createElement("tcp:connector");
				   connecterRef.setAttribute("name", "TCP");
				   connecterRef.setAttribute("sendBufferSize", "0");
				   connecterRef.setAttribute("receiveBufferSize", "0");
				   connecterRef.setAttribute("receiveBacklog", "0");
				   connecterRef.setAttribute("clientSoTimeout", "10000");
				   connecterRef.setAttribute("serverSoTimeout", "10000");
				   connecterRef.setAttribute("socketSoLinger", "0");
				   connecterRef.setAttribute("doc:name", "TCP");
				   doc.getFirstChild().appendChild(connecterRef);
				   Element el=(Element)doc.getFirstChild();
				   el.setAttribute("xmlns:tcp", "http://www.mulesoft.org/schema/mule/tcp");
				     
				   Element tcp1 = doc.createElement("tcp:outbound-endpoint");
				   tcp1.setAttribute("xsi:schemaLocation", "http://www.mulesoft.org/schema/mule/tcp http://www.mulesoft.org/schema/mule/tcp/current/mule-tcp.xsd");
				   tcp1.setAttribute("host",tcpOpenConnectionActivity.getIn_host());
				   tcp1.setAttribute("port",tcpOpenConnectionActivity.getIn_port() );
				   tcp1.setAttribute("exchange-pattern","request-response" );
				   tcp1.setAttribute("connector-ref","TCP" );
				   tcp1.setAttribute("responseTimeout","10000" );
				   tcp1.setAttribute("doc:name", "TCP");
				   flow.appendChild(tcp1);
				  } catch (Exception e) {
				   e.printStackTrace();
				  }
		  }
}
