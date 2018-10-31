package com.mycode.inform;

import java.util.HashMap;
import java.util.Map;




public class Message {

	private String messageStatus;
	private String messageDescription;
	private Map<String,Object>  modelMap;
	
	public Message() {
		modelMap=new HashMap<String,Object>();
	}
    
	/**
	 * 请求成功
	 * @return
	 */
	public Message success() {
		
		setMessageStatus("100");
		setMessageDescription("请求成功");
		
		return this;
	}
	
	/**
	 * 请求失败
	 * @return
	 */
	public Message failed() {
		setMessageStatus("104");
		setMessageDescription("请求失败");
		return this;
	}
	/**
	 * 添加Map的值
	 * @param str
	 * @param obj
	 * @return
	 */
	
	public Message addMapValue(String str,Object obj) {
		this.getModelMap().put(str, obj);
		return this;
	}
	
	
	
	
	
	public String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}

	public String getMessageDescription() {
		return messageDescription;
	}

	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}

	public Map<String, Object> getModelMap() {
		return modelMap;
	}

	public void setModelMap(Map<String, Object> modelMap) {
		this.modelMap = modelMap;
	}
	
	
}
