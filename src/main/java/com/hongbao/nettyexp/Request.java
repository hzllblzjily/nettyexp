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
public class Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8187497353782005324L;

	private int id;
	private String requestName;
	private String requestData;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public String getRequestData() {
		return requestData;
	}
	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
	
	
}
