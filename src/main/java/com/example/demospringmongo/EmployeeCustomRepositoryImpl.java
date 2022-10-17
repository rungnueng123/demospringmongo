package com.example.demospringmongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

@Repository
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {
	
	 @Autowired
	 MongoTemplate mongoTemplate;

	@Override
	public List<Employee> findEmployeeByProperties(String firstName, String email, Pageable pageable) {
		final Query query = new Query().with(pageable);
//	     query.fields().include("id").include("name");
	    final List<Criteria> criteria = new ArrayList<>();
	    if (firstName != null && !firstName.isEmpty()) {
	    	criteria.add(Criteria.where("firstName").is(firstName));
	    }
	    if (email != null && !email.isEmpty()) {
	    	criteria.add(Criteria.where("email").is(email));
	    }
	     
		if (!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
			return mongoTemplate.find(query, Employee.class);
		} else {
			return mongoTemplate.find(query, Employee.class);
		}
	}

}
