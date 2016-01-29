/**
 * 
 */
package com.hongbao.nettyexp;

import java.io.Serializable;

/**
 * @author hzllb
 *
 * 2016年1月27日
 */
public class Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2694565072032145298L;
	private int id;
	private String responseName;
	private String responseData;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResponseName() {
		return responseName;
	}
	public void setResponseName(String responseName) {
		this.responseName = responseName;
	}
	public String getResponseData() {
		return responseData;
	}
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	
	
}
