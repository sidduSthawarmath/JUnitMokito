package com.siddu.controller.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.siddu.controller.StudentController;
import com.siddu.domain.Student;
import com.siddu.repo.StudentRepo;
import com.siddu.util.CustomResponse;
import com.siddu.util.CustomUtil;

public class StudentControllerTest {

	@org.junit.Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@InjectMocks
	private StudentController studentController;

	@Mock
	private CustomUtil customUtil;

	@Mock
	private StudentRepo studentRepo;
	
	@Mock
	private RestTemplate restTemplate;

	@Test
	public void getResultFirstClass() {

		CustomResponse customResponse = new CustomResponse();
		customResponse.setStatus("200");
		customResponse.setDescription("Success");
		customResponse.setData("First Class");

		Student student = new Student();
		student.setId(Long.valueOf(1));
		student.setMarks(500);
		student.setRollNum("111");

		Mockito.when(studentRepo.findByRollNum(Mockito.anyString())).thenReturn(student);
		Mockito.when(customUtil.getCustomResponse(Mockito.any(), Mockito.anyString(), Mockito.any()))
				.thenReturn(customResponse);

		ResponseEntity<CustomResponse> responseEntity = studentController.getResult("130");
		CustomResponse response = (CustomResponse) responseEntity.getBody();
		Assert.assertEquals("First Class", response.getData());

	}

	
	@Test
	public void getResultSecondClass() {

		CustomResponse customResponse = new CustomResponse();
		customResponse.setStatus("200");
		customResponse.setDescription("Success");
		customResponse.setData("Second Class");

		Student student = new Student();
		student.setId(Long.valueOf(1));
		student.setMarks(350);
		student.setRollNum("111");

		Mockito.when(studentRepo.findByRollNum(Mockito.anyString())).thenReturn(student);
		Mockito.when(customUtil.getCustomResponse(Mockito.any(), Mockito.anyString(), Mockito.any()))
				.thenReturn(customResponse);

		ResponseEntity<CustomResponse> responseEntity = studentController.getResult("130");
		CustomResponse response = (CustomResponse) responseEntity.getBody();
		Assert.assertEquals("Second Class", response.getData());

	}
	
	
	@Test
	public void getResultThirdClass() {

		CustomResponse customResponse = new CustomResponse();
		customResponse.setStatus("200");
		customResponse.setDescription("Success");
		customResponse.setData("Third Class");

		Student student = new Student();
		student.setId(Long.valueOf(1));
		student.setMarks(260);
		student.setRollNum("111");

		Mockito.when(studentRepo.findByRollNum(Mockito.anyString())).thenReturn(student);
		Mockito.when(customUtil.getCustomResponse(Mockito.any(), Mockito.anyString(), Mockito.any()))
				.thenReturn(customResponse);

		ResponseEntity<CustomResponse> responseEntity = studentController.getResult("130");
		CustomResponse response = (CustomResponse) responseEntity.getBody();
		Assert.assertEquals("Third Class", response.getData());

	}
	
	
	@Test
	public void getResultFail() {

		CustomResponse customResponse = new CustomResponse();
		customResponse.setStatus("200");
		customResponse.setDescription("Success");
		customResponse.setData("Fail");

		Student student = new Student();
		student.setId(Long.valueOf(1));
		student.setMarks(200);
		student.setRollNum("111");

		Mockito.when(studentRepo.findByRollNum(Mockito.anyString())).thenReturn(student);
		Mockito.when(customUtil.getCustomResponse(Mockito.any(), Mockito.anyString(), Mockito.any()))
				.thenReturn(customResponse);

		ResponseEntity<CustomResponse> responseEntity = studentController.getResult("130");
		CustomResponse response = (CustomResponse) responseEntity.getBody();
		Assert.assertEquals("Fail", response.getData());

	}
	
	
	@Test
	public void jsonPlecHolderTest() {

		CustomResponse customResponse = new CustomResponse();
		customResponse.setStatus("200");
		customResponse.setDescription("Success");
		customResponse.setData("jsonPlecHolder responsding");

		Mockito.when(restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1", String.class)).thenReturn("data returning from online service");
	
		Mockito.when(customUtil.getCustomResponse(Mockito.any(), Mockito.anyString(), Mockito.any()))
				.thenReturn(customResponse);

		ResponseEntity<CustomResponse> responseEntity = studentController.jsonPlecHolder("1");
		CustomResponse response = (CustomResponse) responseEntity.getBody();
		Assert.assertEquals("jsonPlecHolder responsding", response.getData());
	}
	
	
	
	
	@Test
	public void jsonPlecHolderDataNotFoundTest() {

		CustomResponse customResponse = new CustomResponse();
		customResponse.setStatus("200");
		customResponse.setDescription("Success");
		customResponse.setData("jsonPlecHolder not responsding");

		Mockito.when(restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1", String.class)).thenReturn(null);
	
		Mockito.when(customUtil.getCustomResponse(Mockito.any(), Mockito.anyString(), Mockito.any()))
				.thenReturn(customResponse);

		ResponseEntity<CustomResponse> responseEntity = studentController.jsonPlecHolder("1");
		CustomResponse response = (CustomResponse) responseEntity.getBody();
		Assert.assertEquals("jsonPlecHolder not responsding", response.getData());

	}
}
