package com.example.test_task_server.service.impl;

import com.example.test_task_server.entity.SalesByDate;
import com.example.test_task_server.repository.SalesByDateRepository;
import com.example.test_task_server.service.DateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
@AllArgsConstructor
public class DateServiceImpl implements DateService {

    private final SalesByDateRepository salesByDateRepository;

    @Override
    public SalesByDate findByDate(Date date) {
        return salesByDateRepository.findByDate(date);
    }

    @Override
    public Collection<SalesByDate> findByDates(Date date1, Date date2) {
        return salesByDateRepository.findByDateBetween(date1, date2);
    }

    @Override
    public Collection<SalesByDate> findAllByDate() {
        return salesByDateRepository.findAll();
    }
}
