package com.denigunawan.restapisederhana.services.impl;

import com.denigunawan.restapisederhana.dtos.MCompanyResponse;
import com.denigunawan.restapisederhana.dtos.MEmployeeRequest;
import com.denigunawan.restapisederhana.dtos.MEmployeeResponse;
import com.denigunawan.restapisederhana.models.MCompany;
import com.denigunawan.restapisederhana.models.MEmployee;
import com.denigunawan.restapisederhana.repositories.MCompanyRepository;
import com.denigunawan.restapisederhana.repositories.MEmployeeRepository;
import com.denigunawan.restapisederhana.services.MEmployeeService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MEmployeeServiceImpl implements MEmployeeService {


    private MEmployeeRepository employeeRepository;
    private MCompanyRepository companyRepository;


    private MEmployee transformDataFromModelToRequest(MEmployeeRequest request,
                                                      MCompany company){
       return MEmployee.builder()
               .company(company)
               .employeeAddress(request.getEmployeeAddress())
               .employeeName(request.getEmployeeName())
               .employeeTelephone(request.getEmployeeTelephone())
               .build();
    }

    private MEmployeeResponse transformDataFromResponseToModel(MEmployee employee,
                                                               MCompanyResponse response){
        return  MEmployeeResponse.builder()
                .id(employee.getId())
                .companyId(response)
                .employeeAddress(employee.getEmployeeAddress())
                .employeeName(employee.getEmployeeName())
                .employeeTelephone(employee.getEmployeeTelephone())
                .build();
    }

    private MCompanyResponse transformDataFromCompanyToResponse(MCompany company){
        return  MCompanyResponse.builder()
                .id(company.getId())
                .companyEmail(company.getCompanyEmail())
                .companyAddress(company.getCompanyAddress())
                .companyName(company.getCompanyName())
                .build();
    }

    @SneakyThrows
    @Transactional
    @Override
    public MEmployeeResponse addNewEmployee(MEmployeeRequest request) {

        Optional<MCompany> findExistingCompanyId  = companyRepository.findById(request.getCompanyId());
        log.info("search id existing company");
        if(findExistingCompanyId.isEmpty()){
            throw  new Exception("ID company not founds ");
        }
        log.info("id company founds, get data company");

        MCompany foundCompanyId = findExistingCompanyId.get();

        MEmployee employeeRequest = transformDataFromModelToRequest(request, foundCompanyId);

        MEmployee responseSaved = employeeRepository.save(employeeRequest);

        MCompanyResponse responseDataCompany = transformDataFromCompanyToResponse(foundCompanyId);

        return transformDataFromResponseToModel(responseSaved, responseDataCompany);
    }

    @SneakyThrows
    @Transactional
    @Override
    public MEmployeeResponse updateExistingEmployee(String id, MEmployeeRequest request) {
        Optional<MCompany> findExistingCompanyId  = companyRepository.findById(request.getCompanyId());
        log.info("search id existing company");
        if(findExistingCompanyId.isEmpty()){
            throw  new Exception("ID company not founds ");
        }
        log.info("id company founds, get data company");
        MCompany foundCompanyId = findExistingCompanyId.get();

        Optional<MEmployee> findEmployeeExistingId  = employeeRepository.findById(id);
        log.info("search id existing Employee");

        if(findEmployeeExistingId.isEmpty()){
            throw  new Exception("ID Employee not founds ");
        }

        log.info("id company founds, get data Employee");

        findEmployeeExistingId.get().setEmployeeAddress(request.getEmployeeAddress());
        findEmployeeExistingId.get().setCompany(foundCompanyId);
        findEmployeeExistingId.get().setEmployeeName(request.getEmployeeName());
        findEmployeeExistingId.get().setEmployeeTelephone(request.getEmployeeTelephone());

        MEmployee savedDataEmployee = employeeRepository.save(findEmployeeExistingId.get());

        MCompanyResponse responseFromCompany = transformDataFromCompanyToResponse(foundCompanyId);

        return transformDataFromResponseToModel(savedDataEmployee, responseFromCompany);

    }

    @SneakyThrows
    @Override
    public List<MEmployeeResponse> showEmployeeList() {

        List<MEmployee> findAllExistingEmployee = employeeRepository.findAll();

        List<MEmployeeResponse> returnResponseEmployee = new ArrayList<>();

        findAllExistingEmployee.forEach( searching -> {
            MCompanyResponse getResponseCompany = transformDataFromCompanyToResponse(searching.getCompany());
            MEmployeeResponse getResponseEmployee = transformDataFromResponseToModel(searching, getResponseCompany);

            returnResponseEmployee.add(getResponseEmployee);
        });

        return returnResponseEmployee;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteExistingEmployee(String id) {
        Optional<MEmployee> findExistingEmployeeId = employeeRepository.findById(id);
        if(findExistingEmployeeId.isEmpty()){
            throw  new Exception("ID Not Founds");
        }
        log.info("id founds, execute to delete id");
        employeeRepository.deleteById(id);
    }
}
