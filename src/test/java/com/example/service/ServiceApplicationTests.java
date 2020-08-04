package com.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ServiceApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void test(){
		assertNotNull(applicationContext);
	}

}
