package com.siddu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.siddu.domain.Student;
import com.siddu.repo.StudentRepo;
import com.siddu.util.CustomResponse;
import com.siddu.util.CustomUtil;

@RestController
@RequestMapping("student")
public class StudentController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CustomUtil customUtil;

	@Autowired
	private StudentRepo studentRepo;

	@RequestMapping(value = "getResult", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getResult(@RequestParam("rollNum") String rollNum) {
		ResponseEntity<CustomResponse> responseEntity = null;
		CustomResponse customResponse = null;
		try {

			Student student = studentRepo.findByRollNum(rollNum);

			String result = null;

			/*
			 * customResponse = restTemplate.getForObject(
			 * "http://localhost:9090/studentResult/getMarks?rollNum=" + rollNum,
			 * CustomResponse.class);
			 */

			if (student != null) {
				int percentage = (int) ((Float.valueOf(student.getMarks()) / 600) * 100);

				if (percentage >= 60) {
					result = "First Class";
				} else if (percentage >= 50) {
					result = "Second Class";
				} else if (percentage >= 35) {
					result = "Third Class";
				} else if (percentage <= 35) {
					result = "Fail";
				}
				customResponse = customUtil.getCustomResponse(HttpStatus.OK, "SUCCESS", result);
			} else {
				customResponse = customUtil.getCustomResponse(HttpStatus.NOT_FOUND, "NOT_FOUND", null);
			}

			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			customResponse = customUtil.getCustomResponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
					e.getMessage());
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@RequestMapping(value = "jsonPlecHolder", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> jsonPlecHolder(@RequestParam("todos") String todos) {
		ResponseEntity<CustomResponse> responseEntity = null;
		CustomResponse customResponse = null;
		try {

			String jsonMsg = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/" + todos,
					String.class);

			if (jsonMsg != null) {
				customResponse = customUtil.getCustomResponse(HttpStatus.OK, "SUCCESS", "jsonPlecHolder responsding");
			} else {
				customResponse = customUtil.getCustomResponse(HttpStatus.NOT_FOUND, "NOT_FOUND",
						"jsonPlecHolder not responsding");
			}

			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			customResponse = customUtil.getCustomResponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
					e.getMessage());
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

}
