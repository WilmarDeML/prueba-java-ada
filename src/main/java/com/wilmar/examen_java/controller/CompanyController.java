package com.wilmar.examen_java.controller;

import com.wilmar.examen_java.commons.CommonResponse;
import com.wilmar.examen_java.model.dtos.VersionCompanyDto;
import com.wilmar.examen_java.model.entities.Company;
import com.wilmar.examen_java.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/api/company")
public class CompanyController {

    CompanyService companyService;

    CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Company>>> findAll() {
        return ResponseEntity
                .ok(new CommonResponse<>("Lista de compañías", true, companyService.consultarTodos()));
    }

    @GetMapping("{companyId}")
    public ResponseEntity<CommonResponse<Company>> findById(@PathVariable Long companyId) {
        Company companyFound = companyService.consultarPorCompanyId(companyId);
        return companyFound != null
                ? ResponseEntity.ok(new CommonResponse<>("Company found", true, companyFound))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new CommonResponse<>("Company not found", false, null));
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Company>> create(@RequestBody Company company) {
        Company companyCreated = companyService.insertar(company);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(companyCreated.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(new CommonResponse<>("Compañía creada", true, companyCreated));
    }

    @PutMapping("{companyId}")
    public ResponseEntity<CommonResponse<Company>> update(@RequestBody Company company, @PathVariable Long companyId) {
        Company companyUpdated = companyService.actualizar(company, companyId);
        return companyUpdated != null
                ? ResponseEntity.ok(new CommonResponse<>("Compañía actualizada", true, companyUpdated))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new CommonResponse<>("Company not found", false, null));
    }

    @DeleteMapping("{companyId}")
    public ResponseEntity<CommonResponse<Company>> delete(@PathVariable Long companyId) {
        Company companyDeleted = companyService.eliminar(companyId);
        return companyDeleted != null
                ? ResponseEntity.ok(new CommonResponse<>("Compañía eliminada", true, companyDeleted))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new CommonResponse<>("Company not found", false, null));
    }
}
