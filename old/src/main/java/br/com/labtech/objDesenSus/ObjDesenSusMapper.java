package br.com.labtech.objDesenSus;

import br.com.labtech.utils.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjDesenSusMapper extends EntityMapper<ObjDesenSusDTO, ObjDesenSus> {
}
