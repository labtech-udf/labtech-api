package br.com.labtech.evento;

import br.com.labtech.arquivo.ArquivoService;
import br.com.labtech.enums.Status;
import br.com.labtech.utils.GenericResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("evento")
@Tag(name = "Evento", description = "Este é um resource de evento")
public class EventoResource extends GenericResource<EventoDTO, EventoResource> {

  private final EventoService service;
  private final ArquivoService arquivoService;

  public EventoResource(EventoService service, ArquivoService arquivoService) {
    super(service, "api/evento");
    this.service = service;
    this.arquivoService = arquivoService;
  }

  @GetMapping
  public List<EventoDTO> listarEventos() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<EventoDTO> obterEventoPorId(@PathVariable Long id) {
    EventoDTO evento = service.findById(id);
    evento.setStatus(evento.getStatus() != null ? evento.getStatus() : Status.C);
    return evento != null ? ResponseEntity.ok(evento) : ResponseEntity.notFound().build();
  }

  @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @PutMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> eventImage(
    @RequestParam("evento") String evento,
    @RequestParam("file") MultipartFile file
  ) {
    try {
      if (ObjectUtils.isEmpty(evento) || file == null || file.isEmpty()) {
        return ResponseEntity.badRequest().body("Dados de evento e arquivo são obrigatórios.");
      }

      EventoDTO obj = new ObjectMapper().readValue(evento, EventoDTO.class);
      obj.setStatus(obj.getStatus() != null ? obj.getStatus() : Status.C);
      obj.setCategorias(null);

      if (!file.isEmpty()) {
        obj.setPhoto(obj.getId() != null ? arquivoService.update(obj.getPhoto().getId(), file) : arquivoService.insert(file));
      }

      if (obj.getId() != null) {
        return super.updateObject(obj);
      } else {
        super.createObject(obj);
        return ResponseEntity.ok().build();
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação.");
    }

  }

  @PutMapping
  public ResponseEntity eventNoImage(@RequestParam EventoDTO evento) throws Exception {
    if (evento.getId() != null) {
      evento.setStatus(evento.getStatus() != null ? evento.getStatus() : Status.C);
      return ResponseEntity.ok(super.updateObject(evento));
    } else {
      evento.setStatus(evento.getStatus() != null ? evento.getStatus() : Status.C);
      super.createObject(evento);
      return ResponseEntity.ok().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) throws Exception {
    super.delete(id);
    return ResponseEntity.noContent().build();
  }
}
