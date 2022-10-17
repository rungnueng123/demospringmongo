package com.example.demospringmongo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EmployeeRepository extends MongoRepository<Employee, String>,
	EmployeeCustomRepository {
	
	Optional<Employee> findEmployeeByEmail(String email);
	
	@Query(" { firstName : ?0 }")
	List<Employee> findEmployeeByQuery(String firstName);

	Page<Employee> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
	
	Page<Employee> findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String firstName, String email, Pageable pageable);
}
