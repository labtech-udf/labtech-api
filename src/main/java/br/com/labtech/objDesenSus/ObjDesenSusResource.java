package br.com.labtech.objDesenSus;

import br.com.labtech.utils.GenericResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ods")
@Tag(name = "ODS", description = "Objetivos de Desenvolvimento Sustentável")
public class ObjDesenSusResource extends GenericResource<ObjDesenSusDTO, ObjDesenSusResource> {

  private final ObjDesenSusService service;

  public ObjDesenSusResource(ObjDesenSusService service) {
    super(service, "api/ods");
    this.service = service;
  }

  @GetMapping
  public List<ObjDesenSusDTO> listODs() {
    return service.findAll();
  }

  @PostMapping("")
  @PutMapping(path = "")
  public ResponseEntity<ObjDesenSusDTO> createupdate(@RequestBody ObjDesenSusDTO dto) throws Exception {
    if (dto.getId() != null) {
      super.updateObject(dto);
      return ResponseEntity.ok().build();
    } else {
      super.createObject(dto);
      return ResponseEntity.ok().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) throws Exception {
    super.delete(id);
    return ResponseEntity.noContent().build();
  }
}
