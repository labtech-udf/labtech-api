package br.com.labtech.evento;

import br.com.labtech.enums.Status;
import br.com.labtech.arquivo.ArquivoDTO;
import br.com.labtech.arquivo.ArquivoService;
import br.com.labtech.eventoCategoria.EventoCategoria;
import br.com.labtech.eventoCategoria.EventoCategoriaDTO;
import br.com.labtech.utils.GenericResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "evento")
@Tag(name = "Evento", description = "Este é um resource de evento")
public class EventoResource extends GenericResource<EventoDTO, EventoResource> {

  private final EventoService service;
  private final ArquivoService arquivoService;

  public EventoResource(EventoService service, ArquivoService arquivoService) {
    super(service, "api/evento");
    this.service = service;
    this.arquivoService = arquivoService;
  }

  @GetMapping("")
  public List<EventoDTO> listarEventos() {
    List<EventoDTO> list = this.service.findAll();
    return list;
  }

  @GetMapping("/{id}")
  public ResponseEntity<EventoDTO> obterEventoPorId(@PathVariable Long id) {
    EventoDTO evento = this.service.findById(id);
    evento.setStatus(evento.getStatus() != null ? evento.getStatus() : Status.C);
    if (evento != null) {
      return ResponseEntity.ok(evento);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> insert(
    @RequestPart("evento") String evento,
    @RequestPart("file") MultipartFile file
  ) {
    try {
      if (StringUtils.isEmpty(evento) || file == null || file.isEmpty()) {
        return ResponseEntity.badRequest().body("Dados de evento e arquivo são obrigatórios.");
      }
      ObjectMapper objectMapper = new ObjectMapper();
      EventoDTO eventoDTO = objectMapper.readValue(evento, EventoDTO.class);
      eventoDTO.setStatus(Status.C);
      EventoCategoriaDTO cat = new EventoCategoriaDTO();
      cat.setDescricao("Evento acadêmico");
      eventoDTO.setCategorias(null);
      ArquivoDTO arquivo = arquivoService.insert(file);
      eventoDTO.setPhoto(arquivo);
      super.createObject(eventoDTO);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação.");
    }
  }


  @PutMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity update(@RequestPart("evento") String evento, @RequestPart("file") MultipartFile file) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    EventoDTO eventoDTO = mapper.readValue(evento, EventoDTO.class);
    if (eventoDTO.getPhoto() != null && eventoDTO.getPhoto().getId() != null) {
      if (!file.isEmpty()) {
        ArquivoDTO arquivo = arquivoService.update(eventoDTO.getPhoto().getId(), file);
        eventoDTO.setPhoto(arquivo);
      }
    } else {
      if (!file.isEmpty()) {
        ArquivoDTO arquivo = arquivoService.insert(file);
        eventoDTO.setPhoto(arquivo);
      }
    }
    return super.updateObject(eventoDTO);
  }


  @DeleteMapping(value = "/evento/{id}")
  public ResponseEntity delete(@PathVariable Long id) throws Exception {
    super.delete(id);
    return ResponseEntity.noContent().build();
  }

}
