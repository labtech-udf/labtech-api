package com.labtech.events.evento;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labtech.events.constants.Status;
import com.labtech.events.files.ArquivoDTO;
import com.labtech.events.utils.GenericResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Tag(name = "Evento", description = "Gerenciamento de Eventos")
public class EventoResource extends GenericResource<EventoDTO, EventoResource> {

  private final EventoService service;

  public EventoResource(EventoService service) {
    super(service, "api/");
    this.service = service;
  }

  @GetMapping("/api/public/getAllEvents")
  public List<EventoDTO> listarEventos() {
    List<EventoDTO> list = service.findAll();
    if (list.isEmpty()) {
      List<EventoDTO> lNew = new ArrayList<>();
      for (long i = 0; i <= 5; i++) {
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
          arquivo.setUrl("https://api.bps.fabricawebsis.com.br/bps-gallery/api/v2/arquivo/418af432-0390-4442-9a9e-6b45eb1a78ef/download");
          events.setPhoto(arquivo);
        }

        lNew.add(events);
      }
      return lNew;
    } else {
      return list;
    }
  }

  @GetMapping("event/get")
  public ResponseEntity<String> getText() throws JsonProcessingException {
    String message = "Eventos teste lasndkjasn";
    // Create a JSON object with the desired key-value pairs
    Map<String, String> response = new HashMap<>();
    response.put("message", message);
    return ResponseEntity.ok(new ObjectMapper().writeValueAsString(response));
  }

}
