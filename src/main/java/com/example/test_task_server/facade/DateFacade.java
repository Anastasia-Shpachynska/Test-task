package com.example.test_task_server.facade;

import com.example.test_task_server.data.response.date.SalesByDate;

import java.text.ParseException;
import java.util.Collection;

public interface DateFacade {
    SalesByDate findByDate(String date) throws ParseException;
    Collection<SalesByDate> findByDates (String date1, String date2) throws ParseException;
    Collection<SalesByDate> findAllByDate();
}
