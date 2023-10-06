package br.com.labtech.evento;

import br.com.labtech.generic.GenericResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "evento")
@Tag(name = "Evento", description = "Este é um resource de evento")
public class EventoResource extends GenericResource<EventoDTO, EventoResource> {

    private final EventoService service;

    public EventoResource(EventoService service) {
        super(service, "api/evento");
        this.service = service;
    }

    @GetMapping("")
    public List<EventoDTO> listarEventos() {
        return this.service.findAll();
    }

    @PostMapping("")
    public ResponseEntity criarEvento(@RequestBody EventoDTO eventoDTO) throws Exception {
        return super.createObject(eventoDTO);
    }
}
