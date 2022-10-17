package com.example.demospringmongo;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeService {

    @Autowired
	private EmployeeRepository employeeRepository;
    
    private MongoTemplate mongoTemplate;
    
    public Map<String, String> createEmployee(Employee employee) {
    	Map<String, String> result = new HashMap<String, String>();
    	employeeRepository.findEmployeeByEmail(employee.getEmail())
		.ifPresentOrElse(s -> {
			result.put("status",s + " already exists");
		}, () -> {
			employeeRepository.insert(employee);
			result.put("status","Insert employee: " + employee);
		});
		return result;
    }

	public List<Employee> getListEmployees() {
		return employeeRepository.findAll();
	}
	
	public List<Employee> findEmployeeByQuery(String firstName) {
		return employeeRepository.findEmployeeByQuery(firstName);
	}

//	public Map<String, Object> findEmployeeByPage(Pageable pageable) throws Exception {
//    	Page<Employee> list = employeeRepository.findAll(pageable);
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put("employee", list.getContent());
//
//        return result;
//	}
	
	public Page<Employee> findEmployeeByPage(Pageable pageable) throws Exception {
    	Page<Employee> list = employeeRepository.findAll(pageable);
        return list;
	}

	public Map<String, Object> findEmployeeByQueryPage(Pageable pageable, String firstName) throws Exception {
		Page<Employee> list;
		if (firstName == null || firstName == "") {
			list = findEmployeeByPage(pageable);
		} else {
			list = employeeRepository.findByFirstNameContainingIgnoreCase(firstName, pageable);
		}
		Map<String, Object> result = new HashMap<String, Object>();
        result.put("employee", list.getContent());
        return result;
	}

	public Map<String, Object> findEmployeeByProperties(Pageable pageable, String firstName, String email) throws Exception {
		List<Employee> list = employeeRepository.findEmployeeByProperties(firstName, email, pageable);
		Map<String, Object> result = new HashMap<String, Object>();
        result.put("employee", list);
		
        return result;
	}
	
	
}
