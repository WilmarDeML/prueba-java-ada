package com.wilmar.examen_java.model.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "versionId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appId", unique = true, nullable = false)
    private Application application;
    
    private String version;
    private String versionDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long versionId) {
        this.id = versionId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionDescription() {
        return versionDescription;
    }

    public void setVersionDescription(String versionDescription) {
        this.versionDescription = versionDescription;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application appId) {
        this.application = appId;
    }
}
