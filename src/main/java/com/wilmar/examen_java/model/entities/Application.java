package com.wilmar.examen_java.model.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appId")
    private Long id;

    @Column(unique = true)
    private String appCode;

    private String appName;
    private String appDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long appId) {
        this.id = appId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

}
