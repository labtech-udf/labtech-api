package com.labtech.events.evento;


import com.labtech.events.constants.Status;
import com.labtech.events.utils.GenericServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl extends GenericServiceImpl<Evento, EventoDTO> implements EventoService {

  private final EventoRepository repository;

  private final EventoMapper mapper;

  public EventoServiceImpl(EventoRepository repository, EventoMapper mapper) {
    super(repository, mapper);
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public List<EventoDTO> findAll() {
    List<Evento> listEvento = this.repository.findAllByExcluded(Boolean.FALSE);
    listEvento.forEach(e -> e.setStatus(e.getStatus() == null ? Status.C : e.getStatus()));
    return this.mapper.toDto(listEvento);
  }

  @Override
  public EventoDTO findById(Long id) {
    Optional<Evento> eventoOptional = repository.findById(id);
    return eventoOptional.map(mapper::toDto)
      .orElse(null);
  }
}
