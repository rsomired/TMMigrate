package com.tek.muleautomator.element;

import org.w3c.dom.Node;

public class ActivityElement {
	String activityType;
	String activityName;
	Node targetNode;
	boolean isInsideLoop;
	
	
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public ActivityElement(String activityType, String activityName, Node targetNode) {
		this(activityType,activityName,targetNode,false);
	}
	
	public boolean isInsideLoop() {
		return isInsideLoop;
	}

	public void setInsideLoop(boolean isInsideLoop) {
		this.isInsideLoop = isInsideLoop;
	}

	public ActivityElement(String activityType, String activityName, Node targetNode, boolean insideLoop) {
		this.activityType = activityType;
		this.activityName = activityName;
		this.targetNode = targetNode;
		this.isInsideLoop=insideLoop;
	}
	
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public Node getTargetNode() {
		return targetNode;
	}
	public void setTargetNode(Node targetNode) {
		this.targetNode = targetNode;
	}

	@Override
	public String toString() {
		return "ActivityElement [activityType=" + activityType + ", activityName=" + activityName + "]";
	}
	
	
	
	
}
