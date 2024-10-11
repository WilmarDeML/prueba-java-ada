package com.wilmar.examen_java.model.entities;

import jakarta.persistence.*;

@Entity
@Table
public class VersionCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "versionCompanyId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "versionId")
    private Version version;
    
    private String versionCompanyDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getVersionCompanyDescription() {
        return versionCompanyDescription;
    }

    public void setVersionCompanyDescription(String versionCompanyDescription) {
        this.versionCompanyDescription = versionCompanyDescription;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }
}
