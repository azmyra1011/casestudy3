package com.tcs.CaseStudy3.repository;

import com.tcs.CaseStudy3.model.EventRegister;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRegisterRepository extends MongoRepository<EventRegister, String> {
    List<EventRegister> findByCustId(String custId);

}
