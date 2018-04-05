package com.tek.muleautomator.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tek.muleautomator.util.MuleAutomatorConstants;

public class SOAPElement {
	public static class SOAPSendReceiveActivity {
		private static String CONFIG_description;
		private static String CONFIG_connectionPath;
		private static String CONFIG_activityType;
		private List<String> CONFIG_operationParams;
		private String CONFIG_service, CONFIG_port, CONFIG_soapAction, CONFIG_timeout, CONFIG_attachmentStyle,
				CONFIG_operationType;
		private String INPUT_endpointURL, INPUT_host, INPUT_inputMessage, OUTPUT_outputMessage, OUTPUT_headers;
		private Map<String, String> OUTPUT_mimeHeaders, OUTPUT_mimeEnvelopeElement;

		public SOAPSendReceiveActivity(Node targetNode) {
			this.CONFIG_operationParams = new ArrayList<>();
			SOAPSendReceiveActivity.CONFIG_description = "The SOAP Request Reply activity performs a request on the specified web service and optionally expects a reply from the webservice. You can invoke both document and RPC web serviceswith this activity.";
			Element rootActivityElement = (Element) targetNode;
			SOAPSendReceiveActivity.CONFIG_activityType = rootActivityElement.getElementsByTagName("pd:type").item(0)
					.getTextContent();
			// SOAPSendReceiveActivity.CONFIG_connectionPath=rootActivityElement.getElementsByTagName("sharedChannel").item(0).getTextContent();
			try {
				String x = rootActivityElement.getElementsByTagName("service").item(0).getTextContent();
				x = x.split(":")[1];
				this.CONFIG_service = x;
			} catch (Exception E) {
				this.CONFIG_service = "Operation_default";
			}
			this.CONFIG_soapAction = rootActivityElement.getElementsByTagName("soapAction").item(0).getTextContent();
			this.CONFIG_timeout = rootActivityElement.getElementsByTagName("timeout").item(0).getTextContent();
			try {
				this.CONFIG_attachmentStyle = rootActivityElement.getElementsByTagName("soapAttachmentStyle").item(0)
						.getTextContent();
			} catch (Exception E) {

			}
			this.CONFIG_operationType = MuleAutomatorConstants.tibcoVarsResolver
					.resolveExpression(rootActivityElement.getElementsByTagName("operation").item(0).getTextContent());
			try {
				this.INPUT_endpointURL = MuleAutomatorConstants.tibcoVarsResolver.resolveExpression(
						rootActivityElement.getElementsByTagName("endpointURL").item(0).getTextContent());
			} catch (Exception E) {
				this.INPUT_endpointURL = "default:";
			}
			this.CONFIG_port = rootActivityElement.getElementsByTagName("servicePort").item(0).getTextContent();
			NodeList paramTag = rootActivityElement.getElementsByTagName(this.CONFIG_operationType);

			if (paramTag.getLength() > 0) {
				NodeList params = paramTag.item(0).getChildNodes();
				for (int i = 0; i < params.getLength(); ++i) {
					if (params.item(i).getNodeType() != Node.ELEMENT_NODE)
						continue;
					this.CONFIG_operationParams.add(params.item(i).getNodeName());
				}
			}
		}

		public static String getCONFIG_description() {
			return CONFIG_description;
		}

		public static void setCONFIG_description(String cONFIG_description) {
			CONFIG_description = cONFIG_description;
		}

		public static String getCONFIG_connectionPath() {
			return CONFIG_connectionPath;
		}

		public static void setCONFIG_connectionPath(String cONFIG_connectionPath) {
			CONFIG_connectionPath = cONFIG_connectionPath;
		}

		public static String getCONFIG_activityType() {
			return CONFIG_activityType;
		}

		public static void setCONFIG_activityType(String cONFIG_activityType) {
			CONFIG_activityType = cONFIG_activityType;
		}

		public List<String> getCONFIG_operationParams() {
			return CONFIG_operationParams;
		}

		public void setCONFIG_operationParams(List<String> cONFIG_operationParams) {
			CONFIG_operationParams = cONFIG_operationParams;
		}

		public String getCONFIG_service() {
			return CONFIG_service;
		}

		public void setCONFIG_service(String cONFIG_service) {
			CONFIG_service = cONFIG_service;
		}

		public String getCONFIG_port() {
			return CONFIG_port;
		}

		public void setCONFIG_port(String cONFIG_port) {
			CONFIG_port = cONFIG_port;
		}

		public String getCONFIG_soapAction() {
			return CONFIG_soapAction;
		}

		public void setCONFIG_soapAction(String cONFIG_soapAction) {
			CONFIG_soapAction = cONFIG_soapAction;
		}

		public String getCONFIG_timeout() {
			return CONFIG_timeout;
		}

		public void setCONFIG_timeout(String cONFIG_timeout) {
			CONFIG_timeout = cONFIG_timeout;
		}

		public String getCONFIG_attachmentStyle() {
			return CONFIG_attachmentStyle;
		}

		public void setCONFIG_attachmentStyle(String cONFIG_attachmentStyle) {
			CONFIG_attachmentStyle = cONFIG_attachmentStyle;
		}

		public String getCONFIG_operationType() {
			return CONFIG_operationType;
		}

		public void setCONFIG_operationType(String cONFIG_operationType) {
			CONFIG_operationType = cONFIG_operationType;
		}

		public String getINPUT_endpointURL() {
			return INPUT_endpointURL;
		}

		public void setINPUT_endpointURL(String iNPUT_endpointURL) {
			INPUT_endpointURL = iNPUT_endpointURL;
		}

		public String getINPUT_host() {
			return INPUT_host;
		}

		public void setINPUT_host(String iNPUT_host) {
			INPUT_host = iNPUT_host;
		}

		public String getINPUT_inputMessage() {
			return INPUT_inputMessage;
		}

		public void setINPUT_inputMessage(String iNPUT_inputMessage) {
			INPUT_inputMessage = iNPUT_inputMessage;
		}

		public String getOUTPUT_outputMessage() {
			return OUTPUT_outputMessage;
		}

		public void setOUTPUT_outputMessage(String oUTPUT_outputMessage) {
			OUTPUT_outputMessage = oUTPUT_outputMessage;
		}

		public String getOUTPUT_headers() {
			return OUTPUT_headers;
		}

		public void setOUTPUT_headers(String oUTPUT_headers) {
			OUTPUT_headers = oUTPUT_headers;
		}

		public Map<String, String> getOUTPUT_mimeHeaders() {
			return OUTPUT_mimeHeaders;
		}

		public void setOUTPUT_mimeHeaders(Map<String, String> oUTPUT_mimeHeaders) {
			OUTPUT_mimeHeaders = oUTPUT_mimeHeaders;
		}

		public Map<String, String> getOUTPUT_mimeEnvelopeElement() {
			return OUTPUT_mimeEnvelopeElement;
		}

		public void setOUTPUT_mimeEnvelopeElement(Map<String, String> oUTPUT_mimeEnvelopeElement) {
			OUTPUT_mimeEnvelopeElement = oUTPUT_mimeEnvelopeElement;
		}

	}

	public static class SOAPSendFaultActivity {
		private static String CONFIG_description, CONFIG_activityType, CONFIG_replyTo, CONFIG_faultDetails;
		private String INPUT_faultCode, INPUT_faultString, INPUT_faultActor, INPUT_detail, INPUT_configData;

		public SOAPSendFaultActivity(Node targetNode) {
			Element rootActivityElement = (Element) targetNode;
			CONFIG_description = "The SOAP Send Fault activity sends a SOAP fault to the client if an error occurs during processing of a SOAP request.";
			SOAPSendFaultActivity.CONFIG_activityType = rootActivityElement.getElementsByTagName("pd:type").item(0)
					.getTextContent();

		}

		public static String getCONFIG_description() {
			return CONFIG_description;
		}

		public static void setCONFIG_description(String cONFIG_description) {
			CONFIG_description = cONFIG_description;
		}

		public static String getCONFIG_activityType() {
			return CONFIG_activityType;
		}

		public static void setCONFIG_activityType(String cONFIG_activityType) {
			CONFIG_activityType = cONFIG_activityType;
		}

		public static String getCONFIG_replyTo() {
			return CONFIG_replyTo;
		}

		public static void setCONFIG_replyTo(String cONFIG_replyTo) {
			CONFIG_replyTo = cONFIG_replyTo;
		}

		public static String getCONFIG_faultDetails() {
			return CONFIG_faultDetails;
		}

		public static void setCONFIG_faultDetails(String cONFIG_faultDetails) {
			CONFIG_faultDetails = cONFIG_faultDetails;
		}

		public String getINPUT_faultCode() {
			return INPUT_faultCode;
		}

		public void setINPUT_faultCode(String iNPUT_faultCode) {
			INPUT_faultCode = iNPUT_faultCode;
		}

		public String getINPUT_faultString() {
			return INPUT_faultString;
		}

		public void setINPUT_faultString(String iNPUT_faultString) {
			INPUT_faultString = iNPUT_faultString;
		}

		public String getINPUT_faultActor() {
			return INPUT_faultActor;
		}

		public void setINPUT_faultActor(String iNPUT_faultActor) {
			INPUT_faultActor = iNPUT_faultActor;
		}

		public String getINPUT_detail() {
			return INPUT_detail;
		}

		public void setINPUT_detail(String iNPUT_detail) {
			INPUT_detail = iNPUT_detail;
		}

		public String getINPUT_configData() {
			return INPUT_configData;
		}

		public void setINPUT_configData(String iNPUT_configData) {
			INPUT_configData = iNPUT_configData;
		}

	}

	public static class SOAPEventSourceActivity {

		private static String activityType, CONFIG_description;
		private String CONFIG_portTypeNameInWSDL, CONFIG_portTypePrefixInWSDL;
		private String CONFIG_operationType;
		private String CONFIG_soapAction;
		private String WSDLNamespace, CONFIG_inputNamespace, CONFIG_outputNamespace, CONFIG_faultNamespace;
		private String CONFIG_portType, CONFIG_transport, CONFIG_soapVersion, CONFIG_exposeSecurityContext;
		private Map<String, String> CONFIG_operation;
		private String TRANSPORT_endpointURI;
		private String ADVANCED_Service, ADVANCED_URISOAPAction, ADVANCED_WSDLNamespace, ADVANCED_style,
				ADVANCED_encoding, ADVANCED_inputMessageNamespace, ADVANCED_outputMessageNamespace,
				ADVANCED_faultMessageNamespace, ADVANCED_embedInterface;
		private String ADVANCED_embedTypes, ADVANCED_embedJNDIProperties;
		private String MISC_sequencingKey, MISC_customId;
		private String OUTPUT_inputMessage, OUTPUT_remoteAddress, OUTPUT_remoteHost;
		private Map<String, String> OUTPUT_mimeEnvelopeElement, OUTPUT_mimeHeadersheaders, OUTPUT_Context,
				OUTPUT_SecurityContext, OUTPUT_Transport, OUTPUT_WSSE, OUTPUT_Authentication, OUTPUT_Signature;

		public SOAPEventSourceActivity(Node targetNode) {

			SOAPEventSourceActivity.CONFIG_description = "The SOAP Event Source process starter creates a process instance for incoming SOAP requests. SOAP is a standard protocol for invoking web services. This allows you to create a web service using process definitions..";
			Element rootActivityElement = (Element) targetNode;
			SOAPSendReceiveActivity.CONFIG_activityType = rootActivityElement.getElementsByTagName("pd:type").item(0)
					.getTextContent();
			// SOAPSendReceiveActivity.CONFIG_connectionPath=rootActivityElement.getElementsByTagName("sharedChannel").item(0).getTextContent();
			String wsdlC = rootActivityElement.getElementsByTagName("service").item(0).getTextContent();
			this.CONFIG_portTypePrefixInWSDL = wsdlC.split(":")[0];
			this.CONFIG_portTypeNameInWSDL = wsdlC.split(":")[1];
			this.CONFIG_soapAction = rootActivityElement.getElementsByTagName("soapAction").item(0).getTextContent();
			this.CONFIG_operationType = rootActivityElement.getElementsByTagName("operation").item(0).getTextContent();
			this.CONFIG_inputNamespace = rootActivityElement.getElementsByTagName("inputNamespace").item(0)
					.getTextContent();
			this.CONFIG_outputNamespace = rootActivityElement.getElementsByTagName("outputNamespace").item(0)
					.getTextContent();
			this.CONFIG_faultNamespace = rootActivityElement.getElementsByTagName("faultNamespace").item(0)
					.getTextContent();
			this.CONFIG_soapVersion = rootActivityElement.getElementsByTagName("soapVersion").item(0).getTextContent();
		}
	}

	public static class SOAPSendReplyActivity {

		private static String CONFIG_description;
		private static String CONFIG_activityType;
		private static String CONFIG_replyto;
		private Map<String, String> INPUT_Header, INPUT_mimeEnvelopeElement, INPUT__configData;

		public SOAPSendReplyActivity(Node targetNode) {
			SOAPSendReplyActivity.CONFIG_description = "The SOAP Send Reply activity sends a reply to an application that sent a SOAP request. This activity is primarily used in process definitions that implement web services.";
			Element rootActivityElement = (Element) targetNode;
			SOAPSendReplyActivity.CONFIG_activityType = rootActivityElement.getElementsByTagName("pd:type").item(0)
					.getTextContent();
		}

		public static String getCONFIG_description() {
			return CONFIG_description;
		}

		public static void setCONFIG_description(String cONFIG_description) {
			CONFIG_description = cONFIG_description;
		}

		public static String getCONFIG_activityType() {
			return CONFIG_activityType;
		}

		public static void setCONFIG_activityType(String cONFIG_activityType) {
			CONFIG_activityType = cONFIG_activityType;
		}

		public Map<String, String> getINPUT_Header() {
			return INPUT_Header;
		}

		public void setINPUT_Header(Map<String, String> iNPUT_Header) {
			INPUT_Header = iNPUT_Header;
		}

		public Map<String, String> getINPUT_mimeEnvelopeElement() {
			return INPUT_mimeEnvelopeElement;
		}

		public void setINPUT_mimeEnvelopeElementr(Map<String, String> iNPUT_mimeEnvelopeElement) {
			INPUT_mimeEnvelopeElement = iNPUT_mimeEnvelopeElement;
		}

		public static String getCONFIG_replyto() {
			return CONFIG_replyto;
		}

		public static void setCONFIG_replyto(String cONFIG_replyto) {
			CONFIG_replyto = cONFIG_replyto;
		}

		public Map<String, String> getINPUT__configData() {
			return INPUT__configData;
		}

		public void setINPUT__configData(Map<String, String> iNPUT__configData) {
			INPUT__configData = iNPUT__configData;
		}

		public void setINPUT_mimeEnvelopeElement(Map<String, String> iNPUT_mimeEnvelopeElement) {
			INPUT_mimeEnvelopeElement = iNPUT_mimeEnvelopeElement;
		}

	}

	public static class SOAPRetrieveResourcesActivity {
		private static String CONFIG_activityType, description;

		private String INPUT_resourcePath, INPUT_filter, INPUT_hostName, INPUT_port;

		private String OUPUT_outputClass, OUTPUT_resourceData;
		private byte[] OUTPUT_resourceBytes;

		public SOAPRetrieveResourcesActivity(Node targetNode) {
			Element rootActivityElement = (Element) targetNode;
			SOAPRetrieveResourcesActivity.description = "The Retrieve Resources activity generates a WSDL file containinga concrete service description of any process definition that has aSOAP Event Source process starter.";
			SOAPRetrieveResourcesActivity.CONFIG_activityType = rootActivityElement.getElementsByTagName("pd:type")
					.item(0).getTextContent();
		}

		public static String getCONFIG_activityType() {
			return CONFIG_activityType;
		}

		public static void setCONFIG_activityType(String cONFIG_activityType) {
			CONFIG_activityType = cONFIG_activityType;
		}

		public static String getDescription() {
			return description;
		}

		public static void setDescription(String description) {
			SOAPRetrieveResourcesActivity.description = description;
		}

		public String getINPUT_resourcePath() {
			return INPUT_resourcePath;
		}

		public void setINPUT_resourcePath(String iNPUT_resourcePath) {
			INPUT_resourcePath = iNPUT_resourcePath;
		}

		public String getINPUT_filter() {
			return INPUT_filter;
		}

		public void setINPUT_filter(String iNPUT_filter) {
			INPUT_filter = iNPUT_filter;
		}

		public String getINPUT_hostName() {
			return INPUT_hostName;
		}

		public void setINPUT_hostName(String iNPUT_hostName) {
			INPUT_hostName = iNPUT_hostName;
		}

		public String getINPUT_port() {
			return INPUT_port;
		}

		public void setINPUT_port(String iNPUT_port) {
			INPUT_port = iNPUT_port;
		}

		public String getOUPUT_outputClass() {
			return OUPUT_outputClass;
		}

		public void setOUPUT_outputClass(String oUPUT_outputClass) {
			OUPUT_outputClass = oUPUT_outputClass;
		}

		public String getOUTPUT_resourceData() {
			return OUTPUT_resourceData;
		}

		public void setOUTPUT_resourceData(String oUTPUT_resourceData) {
			OUTPUT_resourceData = oUTPUT_resourceData;
		}

		public byte[] getOUTPUT_resourceBytes() {
			return OUTPUT_resourceBytes;
		}

		public void setOUTPUT_resourceBytes(byte[] oUTPUT_resourceBytes) {
			OUTPUT_resourceBytes = oUTPUT_resourceBytes;
		}

	}
}
