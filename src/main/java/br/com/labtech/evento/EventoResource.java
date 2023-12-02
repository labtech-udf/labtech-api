package br.com.labtech.evento;

import br.com.labtech.arquivo.ArquivoDTO;
import br.com.labtech.arquivo.ArquivoService;
import br.com.labtech.utils.GenericResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    return this.service.findAll();
  }

  @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity insert(@RequestPart("evento") String evento, @RequestPart("file") MultipartFile file
  ) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    EventoDTO eventoDTO = objectMapper.readValue(evento, EventoDTO.class);

    if (!file.isEmpty()) {
      ArquivoDTO arquivo = arquivoService.insert(file);
      eventoDTO.setPhoto(arquivo);
    }

    return super.createObject(eventoDTO);
  }

  @PutMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity update(@RequestPart("evento") String evento, @RequestPart("file") MultipartFile file) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    EventoDTO eventoDTO = objectMapper.readValue(evento, EventoDTO.class);
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

    return super.createObject(eventoDTO);
  }


}
