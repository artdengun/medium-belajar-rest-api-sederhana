package com.denigunawan.restapisederhana.repositories;

import com.denigunawan.restapisederhana.models.MEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MEmployeeRepository extends JpaRepository<MEmployee, String> {
}
