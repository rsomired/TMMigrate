package com.tek.muleautomator.element;

public class TransitionElement {
	String from;
	String to;
	String condition, condition_xpath;
	
	public TransitionElement(String from, String to, String condition) {
		this.from = from;
		this.to = to;
		this.condition = condition;
		this.condition_xpath=null;
	}
	public String getFrom() {
		return from;
	}
	
	public String getXPath() {
		return condition_xpath;
	}
	
	public void setXPath(String xpath) {
		this.condition_xpath=xpath;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	@Override
	public String toString() {
		return "TransitionElement [from=" + from + ", to=" + to + ", condition=" + condition + "]";
	}
	
	

}
