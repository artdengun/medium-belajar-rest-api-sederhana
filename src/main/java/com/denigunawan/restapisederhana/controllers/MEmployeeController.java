package com.denigunawan.restapisederhana.controllers;


import com.denigunawan.restapisederhana.dtos.MEmployeeRequest;
import com.denigunawan.restapisederhana.dtos.MEmployeeResponse;
import com.denigunawan.restapisederhana.services.MEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/employee")
@AllArgsConstructor
public class MEmployeeController {

    private MEmployeeService employeeService;

    @PostMapping
    public MEmployeeResponse addEmployee(@RequestBody  @Validated  MEmployeeRequest request){
        return  employeeService.addNewEmployee(request);
    }

    @PutMapping(value = "{id}")
    public MEmployeeResponse updateExistingEmployee(@PathVariable("id") String id,  @RequestBody @Validated MEmployeeRequest request){
        return  employeeService.updateExistingEmployee(id, request);
    }

    @GetMapping
    public List<MEmployeeResponse> listExistingEmployees(){
        return employeeService.showEmployeeList();
    }

    @DeleteMapping(value = "{id}")
    public String deleteExistingEmployee(@PathVariable("id") String id){
        employeeService.deleteExistingEmployee(id);
        return "delete successfully with id " + id;
    }
}
