package com.wilmar.examen_java.persistence;

import com.wilmar.examen_java.model.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICompany extends JpaRepository<Company, Long> {
    Optional<Company> findByCodigoCompany(String codigoCompany);
}
