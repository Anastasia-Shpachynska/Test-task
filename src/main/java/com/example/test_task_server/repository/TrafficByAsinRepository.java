package com.example.test_task_server.repository;

import com.example.test_task_server.entity.TrafficByAsin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficByAsinRepository extends MongoRepository<TrafficByAsin, String> {
}
