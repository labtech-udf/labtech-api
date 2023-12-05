package br.com.labtech.evento;

import br.com.labtech.enums.Status;
import br.com.labtech.utils.GenericServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
