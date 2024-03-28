package com.labtech.events.curso;

import com.labtech.events.utils.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface CursoMapper extends EntityMapper<CursoDTO, Curso> {
}
