package com.denigunawan.restapisederhana.dtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MEmployeeRequest {

    private String companyId;
    private String employeeName;
    private String employeeAddress;
    private String employeeTelephone;

}
