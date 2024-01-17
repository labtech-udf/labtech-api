package br.com.labtech.objDesenSus;

import br.com.labtech.utils.GenericServiceImpl;

import java.util.List;

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
    return null;
  }
}
