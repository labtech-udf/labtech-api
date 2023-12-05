package br.com.labtech.evento;

import br.com.labtech.utils.GenericService;

import java.util.List;

public interface EventoService extends GenericService<EventoDTO> {

  List<EventoDTO> findAll();

}
