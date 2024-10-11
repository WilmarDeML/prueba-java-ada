package com.wilmar.examen_java.model.dtos;

import com.wilmar.examen_java.model.entities.VersionCompany;

public class VersionCompanyDto {

    private final String codigoCompany;
    private final String nameCompany;
    private final String appName;
    private final String version;

    public VersionCompanyDto(VersionCompany versionCompany) {

        codigoCompany = versionCompany.getCompany().getCodigoCompany();
        nameCompany = versionCompany.getCompany().getNameCompany();
        appName = versionCompany.getVersion().getApplication().getAppName();
        version = versionCompany.getVersion().getVersion();

    }

    public String getCodigoCompany() {
        return codigoCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public String getAppName() {
        return appName;
    }

    public String getVersion() {
        return version;
    }
}
