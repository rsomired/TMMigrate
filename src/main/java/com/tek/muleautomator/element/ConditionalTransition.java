package com.tek.muleautomator.element;

import java.util.List;

public class ConditionalTransition {
	String whenConditionPath;
	List<ActivityElement> orderedElements;

	public String getWhenConditionPath() {
		return whenConditionPath;
	}

	public void setWhenConditionPath(String whenConditionPath) {
		this.whenConditionPath = whenConditionPath;
	}

	public List<ActivityElement> getOrderedElements() {
		return orderedElements;
	}

	public void setOrderedElements(List<ActivityElement> orderedElements) {
		this.orderedElements = orderedElements;
	}

	public ConditionalTransition(String whenConditionPath, List<ActivityElement> orderedElements) {
		super();
		this.whenConditionPath = whenConditionPath;
		this.orderedElements = orderedElements;
	}

	@Override
	public String toString() {
		return "ConditionalTransition [whenConditionPath=" + whenConditionPath + ", orderedElements="
				+ orderedElements + "]";
	}

}
