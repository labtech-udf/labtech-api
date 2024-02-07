package br.com.labtech.arquivo;

import br.com.labtech.utils.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArquivoMapper  extends EntityMapper<ArquivoDTO, Arquivo> {
}
