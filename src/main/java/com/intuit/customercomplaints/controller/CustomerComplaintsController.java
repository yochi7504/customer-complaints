package com.intuit.customercomplaints.controller;

import com.intuit.customercomplaints.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer-complaints")
public class CustomerComplaintsController {

    @Autowired
    ComplaintService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCustomerComplaints(@RequestParam(required = false) Integer provider,
                                                      @RequestParam(required = false) Integer errorCode,
                                                      @RequestParam(required = false) String status) {
        return new ResponseEntity<>(service.getAllWithFilter(provider, errorCode, status), HttpStatus.OK);
    }

}
