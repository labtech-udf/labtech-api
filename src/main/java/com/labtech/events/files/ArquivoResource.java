package com.labtech.events.files;

import com.labtech.events.utils.GenericResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api")
@Tag(name = "Arquivos", description = "Gerenciamento de Arquivos")
public class ArquivoResource extends GenericResource<ArquivoDTO, ArquivoResource> {

  private final ArquivoService service;

  public ArquivoResource(final ArquivoService service) {
    super(service, "api/");
    this.service = service;
  }

  @PostMapping(value = "/private/arquivo/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ArquivoDTO> crate(@RequestParam("file") MultipartFile file) throws Exception {
    ArquivoDTO arquivo = this.service.create(file);
    return ResponseEntity.ok().body(arquivo);
  }

  @PostMapping(value = "/private/arquivo/{id}/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> update(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws Exception {
    ArquivoDTO arquivo = this.service.update(id, file);
    if (arquivo != null) {
      return ResponseEntity.ok().body(arquivo);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Arquivo não encontrado\"}");
  }

  @GetMapping(value = "/public/arquivo/get/{id}")
  public ResponseEntity<Resource> download(@PathVariable Long id, HttpServletRequest request) throws Exception {
    Resource resource = this.service.getFile(id);
    String contenType = request.getServletContext()
      .getMimeType(resource.getFile().getAbsolutePath());
    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(contenType))
      .header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"" + resource.getFilename() + "\"")
      .body(resource);
  }

}
