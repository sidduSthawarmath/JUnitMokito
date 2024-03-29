package com.siddu.util;


public class CustomResponse {

	private String status;
	
	private String description;
	
	private Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CustomResponse [status=" + status + ", description=" + description + ", data=" + data + "]";
	}
	
	
	

	
	
	
}
