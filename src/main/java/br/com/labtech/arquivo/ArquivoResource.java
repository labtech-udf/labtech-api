package br.com.labtech.arquivo;

import br.com.labtech.utils.GenericResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "arquivo")
@Tag(name = "Arquivo", description = "Este é um resource de arquivo")
public class ArquivoResource extends GenericResource<ArquivoDTO, ArquivoResource> {

  private final ArquivoService service;

  public ArquivoResource(ArquivoService service) {
    super(service, "api/arquivo");
    this.service = service;
  }

  @GetMapping(value = "{id}/download")
  public ResponseEntity<Resource> download(@PathVariable long id, HttpServletRequest request) throws Exception {
    Resource resource = service.download(id);
    String contentType = request.getServletContext()
      .getMimeType(resource.getFile().getAbsolutePath());
    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(contentType))
      .header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"" + resource.getFilename() + "\"")
      .body(resource);
  }

  @PostMapping("")
  public ResponseEntity<ArquivoDTO> insert(@RequestParam MultipartFile file) {
    try {
         ArquivoDTO arquivo = service.insert(file);
      return new ResponseEntity<>(arquivo, HttpStatus.CREATED);
    } catch (Exception ex) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}
