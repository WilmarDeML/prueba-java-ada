package com.wilmar.examen_java.persistence;

import com.wilmar.examen_java.model.entities.Company;
import com.wilmar.examen_java.model.entities.VersionCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IVersionCompany extends JpaRepository<VersionCompany, Long> {
    Optional<VersionCompany> findByCompany(Company company);
}
