package com.labtech.events.objDesenSus;


import com.labtech.events.utils.GenericResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "ODS", description = "Objetivos de Desenvolvimento Sustentável")
public class ObjDesenSusResource extends GenericResource<ObjDesenSusDTO, ObjDesenSusResource> {

  private final ObjDesenSusService service;

  public ObjDesenSusResource(ObjDesenSusService service) {
    super(service, "api/");
    this.service = service;
  }

  @GetMapping("/private/listods")
  @PreAuthorize("hasRole('ADMIN')")
  public List<ObjDesenSusDTO> listODs() {
    return service.findAll();
  }

  @GetMapping("/public/listods/active")
  public List<ObjDesenSusDTO> listActive() {
    return service.findActive();
  }

  @PostMapping("/private/create/ods")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ObjDesenSusDTO> create(@RequestBody ObjDesenSusDTO dto) throws Exception {
    super.createObject(dto);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/private/update/ods")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ObjDesenSusDTO> update(@RequestBody ObjDesenSusDTO dto) throws Exception {
    super.updateObject(dto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/private/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
    try {
      service.delete(id);
      return ResponseEntity.noContent().build();
    } catch (DataIntegrityViolationException e) {
      String message = "Não é possivel excluir ods em uso ";
      return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }
  }

}
