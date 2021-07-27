package com.denigunawan.restapisederhana.controllers;

import com.denigunawan.restapisederhana.dtos.MCompanyRequest;
import com.denigunawan.restapisederhana.dtos.MCompanyResponse;
import com.denigunawan.restapisederhana.services.MCompanyService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/company")
@AllArgsConstructor
public class MCompanyController {

    private MCompanyService companyService;

    @PostMapping
    public MCompanyResponse addCompany(@RequestBody @Validated MCompanyRequest request){
        return companyService.addCompany(request);
    }

    @PutMapping(value = "/{id}")
    public MCompanyResponse updateExistingCompany(@PathVariable("id") String id,
                                                  @RequestBody @Validated MCompanyRequest request){
      return companyService.updateCompany(id, request);
    }

    @GetMapping
    public List<MCompanyResponse> companyListData(){
        return companyService.listCompany();
    }
    @DeleteMapping(value = "/{id}")
    public String deleteExistingCompany(@PathVariable("id") String id){
        companyService.deleteCompany(id);
        return "Delete Successfully with id = " + id;
    }

}
