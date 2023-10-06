package br.com.labtech.evento;

import br.com.labtech.generic.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventoMapper extends EntityMapper<EventoDTO, Evento> {
}
