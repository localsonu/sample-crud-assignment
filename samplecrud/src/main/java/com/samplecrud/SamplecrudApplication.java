package com.samplecrud;

import com.samplecrud.model.Department;
import com.samplecrud.model.Employee;
import com.samplecrud.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Date;


@SpringBootApplication
@EnableSwagger2
public class SamplecrudApplication {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SamplecrudApplication.class, args);
	}



	@EventListener
	public void createDemoData(ApplicationReadyEvent event) {
		Employee dummyData = new Employee(1 ,"muhammad","haseeb","raza@gmai.com",
				0324,new Date(),140,1,null);
		Employee dummyData1 = new Employee(2 ,"muhammad","haseeb","raza@gmai.com",
				0325,new Date(),140,1,null);
		Employee dummyData2 = new Employee(3 ,"muhammad","haseeb","raza@gmai.com",
				0321,new Date(),140,1,null);
		employeeRepository.saveAll(Arrays.asList(dummyData,dummyData1,dummyData2));
	}
}
