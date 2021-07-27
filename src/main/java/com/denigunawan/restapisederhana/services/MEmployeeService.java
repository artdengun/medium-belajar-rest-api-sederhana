package com.denigunawan.restapisederhana.services;

import com.denigunawan.restapisederhana.dtos.MEmployeeRequest;
import com.denigunawan.restapisederhana.dtos.MEmployeeResponse;

import java.util.List;

public interface MEmployeeService {

    MEmployeeResponse addNewEmployee(MEmployeeRequest request);

    MEmployeeResponse updateExistingEmployee(String id, MEmployeeRequest request);

    List<MEmployeeResponse> showEmployeeList();

    void deleteExistingEmployee(String id);

}
