package com.example.test_task_server.repository;

import com.example.test_task_server.entity.SalesByAsin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesByAsinRepository extends MongoRepository<SalesByAsin, String> {
}
