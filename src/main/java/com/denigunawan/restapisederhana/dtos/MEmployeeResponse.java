package com.denigunawan.restapisederhana.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MEmployeeResponse {

    private String id;
    private MCompanyResponse companyId;
    private String employeeName;
    private String employeeAddress;
    private String employeeTelephone;
}
