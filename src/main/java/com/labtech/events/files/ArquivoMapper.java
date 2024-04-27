package com.labtech.events.files;

import com.labtech.events.utils.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArquivoMapper extends EntityMapper<ArquivoDTO, Arquivo> {
}
