package com.example.test_task_server.controller.user;

import com.example.test_task_server.data.response.ResponseData;
import com.example.test_task_server.data.response.date.SalesByDate;
import com.example.test_task_server.facade.DateFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/open/dateSort")
public class DateController {

    private final DateFacade dateFacade;

    @GetMapping("/byDate/{date}")
    public ResponseEntity<ResponseData<SalesByDate>> findByDate(@PathVariable(name = "date") String date) throws ParseException {
        return ResponseEntity.ok(new ResponseData<>(dateFacade.findByDate(date)));
    }

    @GetMapping("/byDates/{date1}/{date2}")
    public ResponseEntity<ResponseData<Collection<SalesByDate>>> findByDate(@PathVariable(name = "date1") String date1, @PathVariable(name = "date2") String date2) throws ParseException {
        return ResponseEntity.ok(new ResponseData<>(dateFacade.findByDates(date1, date2)));
    }

    @GetMapping("/byDates/all")
    public ResponseEntity<ResponseData<Collection<SalesByDate>>> findByAllDate()  {
        return ResponseEntity.ok(new ResponseData<>(dateFacade.findAllByDate()));
    }
}
