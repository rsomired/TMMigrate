package com.tek.muleautomator.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tek.muleautomator.element.ActivityElement;
import com.tek.muleautomator.element.ConditionalTransition;
import com.tek.muleautomator.element.TransitionElement;
import com.tek.muleautomator.handler.FTPHandler;
import com.tek.muleautomator.handler.FileHandler;
import com.tek.muleautomator.handler.GeneralActivityHandler;
import com.tek.muleautomator.handler.HTTPHandler;
import com.tek.muleautomator.handler.JDBCHandler;
import com.tek.muleautomator.handler.JMSHandler;
import com.tek.muleautomator.handler.JavaHandler;
import com.tek.muleautomator.handler.MailHandler;
import com.tek.muleautomator.handler.ParseHandler;
import com.tek.muleautomator.handler.SOAPHandler;
import com.tek.muleautomator.handler.ServiceHandler;
import com.tek.muleautomator.handler.TCPHandler;
import com.tek.muleautomator.handler.XMLHandler;

public class MuleFlowTools {

	public static void removeDefaultFlow(String filepath, String flowName) throws Exception{
		Document docIn;
		docIn = MuleConfigConnection.getDomObj(filepath);
		NodeList flowTags = docIn.getElementsByTagName("flow");
		Node[] flowTagNodes = new Node[flowTags.getLength()];
		for (int i = 0; i < flowTags.getLength(); ++i) {
			flowTagNodes[i] = flowTags.item(i);
		}
		Node muleTag = docIn.getDocumentElement();
		for (Node X : flowTagNodes) {
			muleTag.removeChild(X);
		}
	}
	
	public static Element createMuleFlow(String filepath, String flowName) throws Exception {
		Document docIn;
		docIn = MuleConfigConnection.getDomObj(filepath);
		
		Element muleRootElement = (Element) docIn.getFirstChild();
		muleRootElement.setAttribute("xmlns:doc", "http://www.mulesoft.org/schema/mule/documentation");
		Element flowElement = docIn.createElement("flow");
		String append="";
		int i=1;
		while(MuleAutomatorConstants.generatedFlows.contains(flowName+append)){
			append = "" + i++;		
		}
		MuleAutomatorConstants.generatedFlows.add(flowName+append);
		flowElement.setAttribute("name", flowName+append);
		return flowElement;
	}
	
	public static Element createMuleSubFlow(String filepath, String flowName) throws Exception {
		Document docIn;
		docIn = MuleConfigConnection.getDomObj(filepath);
		
		//System.out.println("01");
		MuleAutomatorConstants.generatedFlows.add(flowName);
		Element muleRootElement = (Element) docIn.getFirstChild();
		muleRootElement.setAttribute("xmlns:doc", "http://www.mulesoft.org/schema/mule/documentation");
		Element flowElement = docIn.createElement("sub-flow");
		flowElement.setAttribute("name", flowName);
		return flowElement;
	}
	
	public static void loopGenerator(ActivityElement activityElement, String muleConfigPath, Element flowElement){
		List<ActivityElement> activityElements=getOrderedActivitiesFromGroup(activityElement);
		// Append components inside the Loop's forEach flow
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			
			Element forEachTag=doc.createElement("foreach");
			forEachTag.setAttribute("doc:name", "For Each");
			
			flowElement.appendChild(forEachTag);
			
			for(ActivityElement actEl: activityElements){
				try{
					MuleFlowTools.generateFlowForActivity(actEl, muleConfigPath, forEachTag);
				} catch (Exception E){
					E.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/*
	 * Method to get a List of Activities in sorted order based on its inner Transitions.
	 * 
	 * @param Reference to Group activity in TIBCO document
	 * @return List of Activity Elements
	 */
	public static List<ActivityElement> getOrderedActivitiesFromGroup(ActivityElement groupElement){
		Element loopElement=(Element)groupElement.getTargetNode();
		List<ActivityElement> activityElements=new ArrayList<>();
		List<TransitionElement> transitionElements=new ArrayList<>();
		
		NodeList loopActivities=loopElement.getElementsByTagName("pd:activity");
		NodeList loopTransitions=loopElement.getElementsByTagName("pd:transition");
		
		// Get all inner transitions
		for(int transIndex=0;transIndex<loopTransitions.getLength();++transIndex){
			Element currTrans=(Element)loopTransitions.item(transIndex);
			TransitionElement t=new TransitionElement(currTrans.getElementsByTagName("pd:from").item(0).getTextContent(), currTrans.getElementsByTagName("pd:to").item(0).getTextContent(), currTrans.getElementsByTagName("pd:conditionType").item(0).getTextContent());
			transitionElements.add(t);
		}
		List<TransitionElement> transitionsByOrder=null;
		// Sort Transition list
		int count1=0;
		if (transitionElements.size() > 1) {
			transitionsByOrder = new ArrayList<>();
			String activity = "start";
			boolean breakNow=false;
			while (transitionElements.size() != 0 || breakNow) {
				for (int count = 0; count < transitionElements.size() || breakNow; count++) {
					count1++;
					//System.out.println("For: "+activity);
					TransitionElement transition = transitionElements.get(count);
					if (transition.getFrom().toLowerCase().equals(activity.toLowerCase())&& (transition.getCondition().contains("always")|| !transition.getCondition().contains("error"))) {
						transitionsByOrder.add(transition);
						activity = transition.getTo();
						transitionElements.remove(transition);
						break;
					}
					
					if(count1==1000){
						breakNow=true;
						break ;
					}
					
					
					/*if(prev.equals(activity)){
						inf_state++;
					}
					if(!prev.equals(activity)){
						System.out.println(">>> ZEROED: "+inf_state);
						inf_state=0;
					}
					if(inf_state==200){
						transitionElements.clear();
						System.err.println(">>> Error! Choice affected: "+inf_state);
						break;
					}*/
				}
				
				if (activity.equalsIgnoreCase("End")) {
					break;
				}
				
			}
			
		}
		// Get ordered activities corresponding to transition
		for(TransitionElement transitionElement: transitionsByOrder){
			if(transitionElement.getTo().toLowerCase().equals("end"))
				break;
			for(int actIndex=0;actIndex<loopActivities.getLength();++actIndex){
				Element actElement=(Element)loopActivities.item(actIndex);
				String actName=actElement.getAttribute("name");
				if((transitionElement.getTo().equals(actName))){
					activityElements.add(new ActivityElement(actElement.getElementsByTagName("pd:type").item(0).getTextContent(), actName, actElement,true));
					break;
				}
			}
		}
		return activityElements;
	}
	
	
	/**
	 * Generates 'Transactional' component if inside flowElement Transaction is detected in TIBCO file.
	 *  
	 * @param activityElement
	 * @param muleConfigPath
	 * @param flowElement
	 */
	
	public static void transactionElements(ActivityElement activityElement, String muleConfigPath, Element flowElement){
		List<ActivityElement> activityElements=getOrderedActivitiesFromGroup(activityElement);
		// Append components inside transaction
		try {
			Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			
			Element transactionalTag=doc.createElement("transactional");
			transactionalTag.setAttribute("doc:name", "Transactional");
			transactionalTag.setAttribute("action", "ALWAYS_BEGIN");
			flowElement.appendChild(transactionalTag);
			
			for(ActivityElement actEl: activityElements){
				try{
					MuleFlowTools.generateFlowForActivity(actEl, muleConfigPath, transactionalTag);
				} catch (Exception E){
					E.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	public static void filterTibcoFiles(){
		List<File> allFiles=MuleAutomatorConstants.allTibcoProcessFiles;
		List<File> tempFiles=new ArrayList<>(allFiles);
		System.out.print("\n* * Analysing Tibco Process files in the specified folder...");
		for(File file: allFiles){
			try{
				DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
				DocumentBuilder builder =factory.newDocumentBuilder();
				Document doc=builder.parse(file);
				NodeList activities = doc.getElementsByTagName("pd:activity");
				NodeList starters = doc.getElementsByTagName("pd:starter");
				
				for(int i=0;i<activities.getLength();++i){
					Element thisEl=(Element)activities.item(i);
					if(thisEl.getNodeType()==Node.ELEMENT_NODE){

						String type=thisEl.getElementsByTagName("pd:type").item(0).getTextContent();
						if(type.contains("CallProcessActivity")){
							String processFileLoc=thisEl.getElementsByTagName("processName").item(0).getTextContent();
							processFileLoc=processFileLoc.replaceAll("/", "\\\\");
							
							for(File file1: allFiles){
								if(file1.getCanonicalPath().contains(processFileLoc)){
									tempFiles.remove(file1);
								}
							}
							
						}
					}
				}
			} catch (Exception E){
				
			}
			
			
		}
		MuleAutomatorConstants.filteredTibcoProcessFiles=tempFiles;
		System.out.println(" Complete\n");
	}
	
	
	
	public static void generateFlowForActivity(ActivityElement activityElement, String muleConfigPath,
			Element flowElement) throws Exception {
		if (activityElement != null) {
			
			if(activityElement.getActivityType().contains("LoopGroup")){
				loopGenerator(activityElement, muleConfigPath, flowElement);
				return;
			} else if (activityElement.getActivityType().contains("TransactionGroup")){
				transactionElements(activityElement, muleConfigPath, flowElement);
				return;
			}
			
			if(activityElement.getActivityType().contains("CallProcessActivity")){
				
				System.out.println("* * Generating Sub-Flow: "+activityElement.getActivityName()+"  * *");
				Element el=(Element)activityElement.getTargetNode();
				String processFileLoc="";
				String flowName="Sub_Flow";
				String append="";
				Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
				processFileLoc=el.getElementsByTagName("processName").item(0).getTextContent();
				processFileLoc=processFileLoc.replaceAll("/", "\\\\");
				for(File file: MuleAutomatorConstants.allTibcoProcessFiles){
					if(file.getCanonicalPath().contains(processFileLoc)){
						int i=1;
						append="";
						flowName=activityElement.getActivityName().replaceAll(" ", "_");
						while(MuleAutomatorConstants.generatedFlows.contains(flowName+append)){
							append = "" + i++;		
						}
						Element subFlow= MuleFlowTools.createMuleSubFlow(muleConfigPath, flowName+append);
						subFlow=generateMuleFlowFromTibcoProcessOrderByTransitionsWithChoice(file.getCanonicalPath(),muleConfigPath,subFlow);
						Element muleRootElement = (Element) doc.getFirstChild();
						muleRootElement.appendChild(subFlow);
						
						Element flowRef=doc.createElement("flow-ref");
						flowRef.setAttribute("name", flowName+append);
						flowRef.setAttribute("doc:name", flowName+"_Reference");
						
						flowElement.appendChild(flowRef);
						try{
							//MuleAutomatorConstants.tibcoProcessFiles.remove(file);
						} catch (Exception E){
							
						}
						return;
						
					}
				}
				System.err.println(">>> Referenced process file: "+ processFileLoc+" not found!");
				Element logger=doc.createElement("logger");
				logger.setAttribute("name", flowName+append);
				logger.setAttribute("doc:name", "SendLog");
				logger.setAttribute("message","Referenced process file: \\UtilityServices\\CommonLE\\SendLog.process not found!");
				flowElement.appendChild(logger);
				return;
			}
			
			String pluginType = getPluginType(activityElement.getActivityType());
			switch (pluginType) {
			case "jms":
				JMSHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
				break;
			case "file":
				FileHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
				break;
			case "jdbc":
				JDBCHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
				break;
			case "http":
				HTTPHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
				break;
			case "soap":
				SOAPHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
				break;
			case "ftp":
				FTPHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
				break;
		/*	case "core":
			case "mapper":*/
			case "timer":
				GeneralActivityHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
				break;
			case "parse":
				ParseHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
				break;
			case "mail":
				MailHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
				break;
			case "xml":
			    XMLHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
			     break;
			case "java":
				JavaHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
				break;
			case "servicepalette":
				ServiceHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
				break;
			case "tcp":
				TCPHandler.generateMuleFlow(activityElement, muleConfigPath, flowElement);
				break;
			default: MuleAutomatorUtil.loggerElement(activityElement, muleConfigPath, flowElement);
			break;
			}
		}
	}
	
	
	/**
	 * Method to get a List of All ActivityElements Containing Type and a
	 * Reference to Node in Document.
	 * 
	 * @param doc
	 *            Parsed DOM Document.
	 * @param tag
	 *            Tag inside which we expect "pd:type".
	 * @return List type ActivityElement.
	 */

	public static List<ActivityElement> getActivityTypes(Document doc, String tag) {
		NodeList allNodes = doc.getElementsByTagName(tag);
		List<ActivityElement> activityTypes = new ArrayList<>();
		for (int count = 0; count < allNodes.getLength(); count++) {
			Node tempNode = allNodes.item(count);
			Element tempNodeElement = (Element) tempNode;
			String activityType = tempNodeElement.getElementsByTagName("pd:type").item(0).getTextContent();
			String activityName = tempNodeElement.getAttributes().getNamedItem("name").getNodeValue();
			activityTypes.add(new ActivityElement(activityType, activityName, tempNode));
		}
		return activityTypes;
	}

	/**
	 * Method to return a list of transitions, in non-sorted order from document
	 * 
	 * @param doc
	 * @return List of type TransitionElement
	 */

	public static List<TransitionElement> getTransitions(Document doc) {
		NodeList allNodes = doc.getElementsByTagName("pd:transition");
		List<TransitionElement> transitions = new ArrayList<>();
		for (int count = 0; count < allNodes.getLength(); count++) {
			Node tempNode = allNodes.item(count);
			Element tempNodeElement = (Element) tempNode;
			String from = tempNodeElement.getElementsByTagName("pd:from").item(0).getTextContent();
			String to = tempNodeElement.getElementsByTagName("pd:to").item(0).getTextContent();
			String condition = tempNodeElement.getElementsByTagName("pd:conditionType").item(0).getTextContent();
			TransitionElement temp = new TransitionElement(from, to, condition);
			if (condition.contains("xpath")) {
				temp.setXPath(tempNodeElement.getElementsByTagName("pd:xpath").item(0).getTextContent());
			} else if (condition.contains("otherwise")) {
				temp.setXPath("otherwise");
			}
			transitions.add(temp);
		}
		return transitions;
	}

	/**
	 * Method to know the count of occurrences of supplied activity name. Useful
	 * to deduce whether the given activity may have multiple sub childs
	 * 
	 * @param elements
	 * @param activityName
	 * @return count
	 */

	public static int getActivityStartCount(List<TransitionElement> elements, String activityName) {
		int c = 0;
		for (TransitionElement currTransElement : elements) {
			if (currTransElement.getFrom().equals(activityName)) {
				c++;
			}
		}
		return c;
	}

	/**
	 * Method to extract subchilds from given activity name. Also appends XPath
	 * Condition in the name seperated by " -- "
	 * 
	 * @param elements
	 * @param activityName
	 * @return List of Strings of form:
	 *         " <Sub-Activity-Name> -- <Corresponding-Xpath> "
	 */

	private static List<String> getAllSubActivitiesWithXPath(List<TransitionElement> elements, String activityName) {
		List<String> subAct = new ArrayList<>();
		for (TransitionElement currTransElement : elements) {
			if (currTransElement.getFrom().equals(activityName)) {
				subAct.add(currTransElement.getTo() + " -- " + currTransElement.getXPath());
			}
		}
		return subAct;
	}

	/**
	 * Method to get a List of All transitions Containing from,to and condition
	 * type.
	 * 
	 * @param doc
	 *            Parsed DOM Document.
	 * @return List type TransitionElement.
	 */

	public static List<TransitionElement> getSortedTransitions(Document doc) {
		List<TransitionElement> transitions = getTransitions(doc);

		if (transitions.size() > 1) {
			List<TransitionElement> tranistionsByOrder = new ArrayList<>();
			String activity = getStartActivity(doc);
			while (transitions.size() != 0) {
				for (int count = 0; count < transitions.size(); count++) {
					TransitionElement transition = transitions.get(count);
					if (transition.getFrom().equals(activity)) {
						tranistionsByOrder.add(transition);
						activity = transition.getTo();
						transitions.remove(transition);
						break;
					}
				}
				if (activity.equals("End")) {
					break;
				}
			}
			return tranistionsByOrder;
		}
		return transitions;
	}
	
	
	/**
	 * Checks TIBCO process file for start Activity, returns the name of the same
	 * @param doc
	 * @return start activity name
	 */

	private static String getStartActivity(Document doc) {

		String startActivity = doc.getElementsByTagName("pd:startName").item(0).getTextContent();
		return startActivity;
	}
	
	
	
	/**
	 * Method to generate mule flow by reading tibco processes, taking Sub
	 * Activities into Account
	 * 
	 * @param filePath
	 * @param flowElement
	 * @param tibco
	 *            process path.
	 * @param mule
	 *            project location.
	 */
	public static Element generateMuleFlowFromTibcoProcessOrderByTransitionsWithChoice(String tibcoProcessPath,
			String muleConfigPath, Element flowElement) {
		try {
			List<ActivityElement> activityElements = new ArrayList<>();
			Map<String, ActivityElement> activityCache = new HashMap<>();
			Map<String, TransitionElement> transitionCache = new HashMap<>();
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document docIn = documentBuilder.parse(new File(tibcoProcessPath));
			List<TransitionElement> transitionElements = getTransitions(docIn);
			Document docOut = MuleConfigConnection.getDomObj(muleConfigPath);
			activityElements.addAll(getActivityTypes(docIn, "pd:starter"));
			activityElements.addAll(getActivityTypes(docIn, "pd:activity"));
			activityElements.addAll(getActivityTypes(docIn, "pd:group"));
			// Add all activity Elements to hashMap so that retrieval would be
			// in O(1) Constant time.
			for (ActivityElement currActElement : activityElements) {
				activityCache.put(currActElement.getActivityName(), currActElement);
			}

			for (TransitionElement currTransElement : transitionElements) {
				if (transitionCache.containsKey(currTransElement.getFrom()))
					continue;
				transitionCache.put(currTransElement.getFrom(), currTransElement);
			}

			String tempActivityName = getStartActivity(docIn);
			// System.out.println("Transiiton Cache: "+transitionCache);
			while (true) {
				TransitionElement currTrans = null;
				if (tempActivityName.equals("End"))
					break;
				int choiceCases = getActivityStartCount(transitionElements, tempActivityName);
				if (choiceCases == 1) {
					// System.out.println("Curr Trans: "+currTrans);
					currTrans = transitionCache.get(tempActivityName);
					if (currTrans.getCondition().contains("always")) {
						ActivityElement activityElement = activityCache.get(tempActivityName);
						generateFlowForActivity(activityElement, muleConfigPath, flowElement);
					}
					tempActivityName = currTrans.getTo();
					continue;
				}

				System.out.println("* * Working with the Choice in Mule...");

				List<ConditionalTransition> conditionalTransitions = new ArrayList<>();

				List<String> allSubActivities = getAllSubActivitiesWithXPath(transitionElements, tempActivityName);
				for (String currSubActivity : allSubActivities) {
					String tempAct = currSubActivity.split(" -- ")[0];
					List<ActivityElement> choiceTransitionElement = new ArrayList<>();
					for (int j = 0; j < transitionElements.size(); ++j) {
						TransitionElement transEl = transitionElements.get(j);
						if (transEl.getFrom().equals(tempAct)) {
							tempAct = transEl.getTo();
							choiceTransitionElement.add(activityCache.get(transEl.getFrom()));
							if (tempAct.contains("End")) {
								break;
							}
							j = 0;
						}
					}
					String xpath = currSubActivity.split(" -- ")[1];
					ConditionalTransition conditionalTransition = new ConditionalTransition(xpath,
							choiceTransitionElement);
					conditionalTransitions.add(conditionalTransition);
				}

				Element choiceTag = docOut.createElement("choice");
				choiceTag.setAttribute("doc:name", "Choice");
				for (ConditionalTransition currCondTr : conditionalTransitions) {
					Element whenTag = docOut.createElement("when");
					whenTag.setAttribute("expression", currCondTr.getWhenConditionPath());
					if (currCondTr.getWhenConditionPath().contains("otherwise")) {
						Element otherwiseTag = docOut.createElement("otherwise");
						for (ActivityElement aE : currCondTr.getOrderedElements()) {
							generateFlowForActivity(aE, muleConfigPath, otherwiseTag);
						}
						choiceTag.appendChild(otherwiseTag);
						continue;
					}
					for (ActivityElement aE : currCondTr.getOrderedElements()) {
						generateFlowForActivity(aE, muleConfigPath, whenTag);
					}
					choiceTag.appendChild(whenTag);
				}
				flowElement.appendChild(choiceTag);
				break;
			}
			return flowElement;
			/*Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
			doc.getFirstChild().appendChild(flowElement);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(muleConfigPath);
			transformer.transform(source, result);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * Method to get plugin type of activityType.
	 * 
	 * @param activity
	 *            type.
	 * @return mule plugin type.
	 */

	private static String getPluginType(String activityType) {
		if(activityType.contains("servicepalette"))
			return "servicepalette";
		return activityType.split("\\.")[3];
	}

	
}
