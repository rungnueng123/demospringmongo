package com.example.demospringmongo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
    @Autowired
	private EmployeeRepository employeeRepository;
    
    @PostMapping("/create")
	public Map<String, String> createEmployees(@RequestBody Employee employee) throws Exception {
		return employeeService.createEmployee(employee);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployees() throws Exception {
		return employeeService.getListEmployees();
	}
	
	@RequestMapping(value = "/listfilter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployees(@RequestParam("firstName") String firstName) throws Exception {
		return employeeService.findEmployeeByQuery(firstName);
	}
	
//	@RequestMapping(value = "/listfilterPage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public Page<Employee> getEmployees(Pageable pageable) throws Exception {
//		return employeeRepository.findAll(pageable);
//	}
	
//	@RequestMapping(value = "/listfilterPage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public Map<String, Object> getEmployees(Pageable pageable) throws Exception {
//		return employeeService.findEmployeeByPage(pageable);
//	}
	
	@RequestMapping(value = "/listfilterQueryPage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> findEmployeeByQueryPage(@RequestParam("page") int page,
											@RequestParam("size") int size,
											@RequestParam(required = false) String firstName) throws Exception {
		Pageable pageable = PageRequest.of(page,size);
		return employeeService.findEmployeeByQueryPage(pageable, firstName);
	}
	
	@RequestMapping(value = "/listfilterMultiQueryPage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> findEmployeeByQueryPage(@RequestParam("page") int page,
											@RequestParam("size") int size,
											@RequestParam(required = false) String firstName,
											@RequestParam(required = false) String email) throws Exception {
		Pageable pageable = PageRequest.of(page,size);
		return employeeService.findEmployeeByProperties(pageable, firstName, email);
	}

}
