package com.wilmar.examen_java.controller;

import com.wilmar.examen_java.commons.CommonResponse;
import com.wilmar.examen_java.model.dtos.VersionCompanyDto;
import com.wilmar.examen_java.model.entities.VersionCompany;
import com.wilmar.examen_java.services.VersionCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/versionCompany")
public class VersionCompanyController {

    private final VersionCompanyService versionCompanyService;

    public VersionCompanyController(VersionCompanyService versionCompanyService) {
        this.versionCompanyService = versionCompanyService;
    }

    @GetMapping("{companyCode}")
    public ResponseEntity<CommonResponse<VersionCompanyDto>> findById(@PathVariable String companyCode) {
        VersionCompany versionCompanyFound = versionCompanyService.consultarPorCompanyCode(companyCode);
        return versionCompanyFound != null
                ? ResponseEntity.ok(
                        new CommonResponse<>(
                                "Company found", true, new VersionCompanyDto(versionCompanyFound)))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new CommonResponse<>("VersionCompany not found", false, null));
    }

}
