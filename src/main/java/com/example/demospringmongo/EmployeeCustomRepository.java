package com.example.demospringmongo;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface EmployeeCustomRepository {
	public List<Employee> findEmployeeByProperties(String firstName, String email, Pageable pageable);
}
