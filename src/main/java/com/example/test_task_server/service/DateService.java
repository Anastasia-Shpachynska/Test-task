package com.example.test_task_server.service;

import com.example.test_task_server.entity.SalesByDate;

import java.util.Collection;
import java.util.Date;

public interface DateService {
    SalesByDate findByDate(Date date);
    Collection<SalesByDate> findByDates(Date date1, Date date2);
    Collection<SalesByDate> findAllByDate();
}
