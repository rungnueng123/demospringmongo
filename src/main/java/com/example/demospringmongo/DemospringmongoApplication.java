package com.example.demospringmongo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class DemospringmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemospringmongoApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner runner(EmployeeRepository repository, MongoTemplate mongoTemplate) {
//		return args -> {
//			Address address = new Address(
//					"123",
//					"bangna",
//					"bangna",
//					"bangkok",
//					"10260"
//			);
//			
//			String email = "email2@email.com";
//			Employee employee = new Employee(
//					"firstName",
//					"lastName",
//					email,
//					Gender.MALE,
//					address,
//					List.of("Software Engineer")
//			);
//			
////			usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);
//			
//			repository.findEmployeeByEmail(email)
//				.ifPresentOrElse(s -> {
//					System.out.println(s + " already exists");
//				}, () -> {
//					System.out.println("Insert employee: " + employee);
//					repository.insert(employee);
//				});
//		};
//	}
	
	private void usingMongoTemplateAndQuery(EmployeeRepository repository, MongoTemplate mongoTemplate, String email, Employee employee) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		
		List<Employee> students = mongoTemplate.find(query, Employee.class);
		
		if (students.size() > 1) {
			throw new IllegalStateException(
					"found many employee email: " + email
			);
		}
		
		if (students.isEmpty()) {
			System.out.println("Insert employee: " + employee);
			repository.insert(employee);
		} else {
			System.out.println(employee + " already exists");
		}
	}

}
