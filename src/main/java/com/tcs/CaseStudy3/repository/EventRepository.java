package com.tcs.CaseStudy3.repository;

import com.tcs.CaseStudy3.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
    Event findByType(String type);

}
