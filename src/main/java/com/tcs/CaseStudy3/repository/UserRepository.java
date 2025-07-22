package com.tcs.CaseStudy3.repository;

import com.tcs.CaseStudy3.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
