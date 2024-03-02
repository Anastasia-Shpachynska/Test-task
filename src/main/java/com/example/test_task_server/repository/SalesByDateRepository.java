package com.example.test_task_server.repository;

import com.example.test_task_server.entity.SalesByDate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface SalesByDateRepository extends MongoRepository<SalesByDate, String> {
    SalesByDate findByDate(Date date);
    Collection<SalesByDate> findByDateBetween(Date date1, Date date2);
}
