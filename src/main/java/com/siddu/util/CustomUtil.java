package com.siddu.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomUtil {

	public CustomResponse getCustomResponse(HttpStatus status, String description, Object data) {
		CustomResponse response = new CustomResponse();
		response.setStatus(status.toString());
		response.setDescription(description);
		response.setData(data);
		return response;
	}

}
