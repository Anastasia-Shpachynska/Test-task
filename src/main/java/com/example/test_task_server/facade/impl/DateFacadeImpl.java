package com.example.test_task_server.facade.impl;

import com.example.test_task_server.data.response.date.SalesByDate;
import com.example.test_task_server.facade.DateFacade;
import com.example.test_task_server.service.DateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Service
@AllArgsConstructor
public class DateFacadeImpl implements DateFacade {

    private final DateService dateService;

    @Override
    public SalesByDate findByDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateStr);
        com.example.test_task_server.entity.SalesByDate salesByDate = dateService.findByDate(date);
        if(salesByDate != null) {
            return new SalesByDate(salesByDate);
        }else {
            return null;
        }
    }

    @Override
    public Collection<SalesByDate> findByDates(String date1Str, String date2Str) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(date1Str);
        Date date2 = dateFormat.parse(date2Str);
        return dateService.findByDates(date1, date2).stream().map(SalesByDate::new).toList();
    }

    @Override
    public Collection<SalesByDate> findAllByDate() {
        return dateService.findAllByDate().stream().map(SalesByDate::new).toList();
    }
}
