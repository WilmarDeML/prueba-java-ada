package com.wilmar.examen_java.model.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCompany")
    private Long id;

    @Column(unique = true)
    private String codigoCompany;

    private String nameCompany;
    private String descriptionCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long idCompany) {
        this.id = idCompany;
    }

    public String getCodigoCompany() {
        return codigoCompany;
    }

    public void setCodigoCompany(String codigoCompany) {
        this.codigoCompany = codigoCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getDescriptionCompany() {
        return descriptionCompany;
    }

    public void setDescriptionCompany(String descriptionCompany) {
        this.descriptionCompany = descriptionCompany;
    }
}
