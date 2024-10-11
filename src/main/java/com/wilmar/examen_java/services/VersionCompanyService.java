package com.wilmar.examen_java.services;

import com.wilmar.examen_java.commons.NotFoundCrudException;
import com.wilmar.examen_java.model.entities.Company;
import com.wilmar.examen_java.model.entities.VersionCompany;
import com.wilmar.examen_java.persistence.ICompany;
import com.wilmar.examen_java.persistence.IVersionCompany;
import org.springframework.stereotype.Service;

@Service
public class VersionCompanyService {

    private final IVersionCompany versionCompanyRep;
    private final ICompany companyRep;

    public VersionCompanyService(IVersionCompany versionCompanyRep, ICompany companyRep) {
        this.versionCompanyRep = versionCompanyRep;
        this.companyRep = companyRep;
    }

    public VersionCompany consultarPorCompanyCode(String codigoCompany) {
        Company versionCompanyFound = companyRep.findByCodigoCompany(codigoCompany).orElse(null);

        if (versionCompanyFound == null) {
            throw new NotFoundCrudException("La compañía buscada no existe");
        }

        return versionCompanyRep.findByCompany(versionCompanyFound).orElse(null);
    }

}
