package br.com.labtech.evento;

import br.com.labtech.utils.GenericService;

import java.util.List;
import java.util.Optional;

public interface EventoService extends GenericService<EventoDTO> {

  List<EventoDTO> findAll();

  EventoDTO findById(Long id);
}
