package com.labtech.events.evento;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labtech.events.constants.Status;
import com.labtech.events.files.ArquivoDTO;
import com.labtech.events.utils.GenericResource;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@ApiResponse
@RequestMapping("api")
@Tag(name = "Evento", description = "Gerenciamento de Eventos")
public class EventoResource extends GenericResource<EventoDTO, EventoResource> {

  private final EventoService service;

  public EventoResource(EventoService service) {
    super(service, "api/");
    this.service = service;
  }

  @GetMapping("/public/getAllEvents")
  public List<EventoDTO> listarEventos() {
    List<EventoDTO> list = service.findAll();
    if (list.isEmpty()) {
      List<EventoDTO> lNew = new ArrayList<>();
      for (long i = 0; i <= 15; i++) {
        EventoDTO events = new EventoDTO();
        events.setId(i);
        events.setName("Name teste: " + i);
        events.setDescription("Description test: " + i);
        events.setNameCard("Name Card: " + i);
        events.setCor("#3f83f0");
        Status[] statuses = Status.values();
        int randomIndex = new Random().nextInt(statuses.length);
        events.setStatus(statuses[randomIndex]);
        if (i % 2 == 0) {
          ArquivoDTO arquivo = new ArquivoDTO();
          arquivo.setId(i);
          arquivo.setName("Name file" + i);
          arquivo.setUrl("https://source.unsplash.com/random");
          events.setPhoto(arquivo);
        }

        lNew.add(events);
      }
      return lNew;
    } else {
      return list;
    }
  }

  @GetMapping("/public/evento/{id}")
  public ResponseEntity<EventoDTO> obterEventoPorId(@PathVariable Long id) {
    EventoDTO evento = service.findById(id);
    evento.setStatus(evento.getStatus() != null ? Status.valueOf(evento.getStatus()) : Status.C);
    return ResponseEntity.ok(evento);
  }

  @PostMapping(path = "/private/evento", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @PutMapping(path = "/private/evento", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> eventImage(
    @RequestParam("evento") String evento,
    @RequestParam("file") MultipartFile file
  ) {
    try {
      if (ObjectUtils.isEmpty(evento) || file == null || file.isEmpty()) {
        return ResponseEntity.badRequest().body("Dados de evento e arquivo são obrigatórios.");
      }

      EventoDTO obj = new ObjectMapper().readValue(evento, EventoDTO.class);
      obj.setStatus(obj.getStatus() != null ? Status.valueOf(obj.getStatus()) : Status.C);
//      obj.setCategorias(null);

//      if (!file.isEmpty()) {
//        obj.setPhoto(obj.getId() != null ?
//          arquivoService.update(obj.getPhoto().getId(), file) :
//          arquivoService.insert(file)
//        );
//      }

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

  @PutMapping("/private/noImg")
  public ResponseEntity eventNoImage(@RequestParam EventoDTO evento) throws Exception {
    if (evento.getId() != null) {
      evento.setStatus(evento.getStatus() != null ? Status.valueOf(evento.getStatus()) : Status.C);
      return ResponseEntity.ok(super.updateObject(evento));
    } else {
      evento.setStatus(evento.getStatus() != null ? Status.valueOf(evento.getStatus()) : Status.C);
      super.createObject(evento);
      return ResponseEntity.ok().build();
    }
  }

  @DeleteMapping("/private/del/{id}")
  public ResponseEntity delete(@PathVariable Long id) throws Exception {
    super.delete(id);
    return ResponseEntity.noContent().build();
  }


}
