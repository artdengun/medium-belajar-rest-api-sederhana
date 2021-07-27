package com.denigunawan.restapisederhana.services;

import com.denigunawan.restapisederhana.dtos.MCompanyRequest;
import com.denigunawan.restapisederhana.dtos.MCompanyResponse;

import java.util.List;

public interface MCompanyService {

    MCompanyResponse addCompany(MCompanyRequest request);

    MCompanyResponse updateCompany(String id, MCompanyRequest request);

    List<MCompanyResponse> listCompany();

    void deleteCompany(String id);
}
