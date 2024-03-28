package com.labtech.events.objDesenSus;



//import com.labtech.events.evento.EventoDTO;
import com.labtech.events.utils.GenericServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjDesenSusServiceImpl extends GenericServiceImpl<ObjDesenSus, ObjDesenSusDTO> implements ObjDesenSusService {

  private final ObjDesenSusRepository repository;

  private final ObjDesenSusMapper mapper;

  public ObjDesenSusServiceImpl(ObjDesenSusRepository repository, ObjDesenSusMapper mapper) {
    super(repository, mapper);
    this.mapper = mapper;
    this.repository = repository;
  }

  @Override
  public ObjDesenSusDTO findById(final Long id) {
    return null;
  }


  @Override
  public List<ObjDesenSusDTO> findAll() {
    List<ObjDesenSus> list = this.repository.findAllByExcludedOrderByIdAsc(Boolean.FALSE);
    return this.mapper.toDto(list);
  }

  @Override
  public List<ObjDesenSusDTO> findActive() {
    List<ObjDesenSus> list = this.repository.findAllByStatusOrderByIdAsc(Boolean.TRUE);
    return this.mapper.toDto(list);
  }


}
