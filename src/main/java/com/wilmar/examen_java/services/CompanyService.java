package com.wilmar.examen_java.services;

import com.wilmar.examen_java.commons.ValidationCrudException;
import com.wilmar.examen_java.model.entities.Company;
import com.wilmar.examen_java.persistence.ICompany;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {

    private ICompany companyRep;

    public CompanyService(ICompany companyRep) {
        this.companyRep = companyRep;
    }

    public List<Company> consultarTodos() {
        return companyRep.findAll();
    }

    public Company consultarPorCompanyId(Long companyId) {
        return companyRep.findById(companyId).orElse(null);
    }

    public Company insertar(Company company) {
        Company companyFound = companyRep.findByCodigoCompany(company.getCodigoCompany()).orElse(null);
        if (companyFound != null && !Objects.equals(companyFound.getId(), company.getId())) {
            throw new ValidationCrudException("El codigoCompany pertenece a otra compañia");
        }
        return companyRep.save(company);
    }

    public Company actualizar(Company company, Long companyId) {
        Company companyFound = consultarPorCompanyId(companyId);
        if (companyFound != null) {
            if (company.getCodigoCompany() != null) companyFound.setCodigoCompany(company.getCodigoCompany());
            if (company.getNameCompany() != null) companyFound.setNameCompany(company.getNameCompany());
            if (company.getDescriptionCompany() != null)
                companyFound.setDescriptionCompany(company.getDescriptionCompany());

            return insertar(companyFound);
        }

        return null;
    }

    public Company eliminar(Long companyId) {
        Company companyFound = consultarPorCompanyId(companyId);
        if (companyFound != null) {
            companyRep.deleteById(companyId);
        }

        return companyFound;
    }
}
