package com.denigunawan.restapisederhana.repositories;

import com.denigunawan.restapisederhana.models.MCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MCompanyRepository extends JpaRepository<MCompany, String> {
}
