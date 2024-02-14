package com.labtech.events.evento;

import com.labtech.events.utils.GenericService;

import java.util.List;

public interface EventoService extends GenericService<EventoDTO> {

  List<EventoDTO> findAll();

  EventoDTO findById(Long id);
}
