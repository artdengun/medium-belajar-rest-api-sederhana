package com.denigunawan.restapisederhana.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MCompanyRequest {

    private String companyName;
    private String companyAddress;
    private String companyEmail;
}
