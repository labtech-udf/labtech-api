package br.com.labtech.objDesenSus;

import br.com.labtech.utils.GenericResource;
import br.com.labtech.utils.GenericService;

public class ObjDesenSusResource extends GenericResource<ObjDesenSusDTO, ObjDesenSusResource> {

  private final ObjDesenSusService service;

  public ObjDesenSusResource(ObjDesenSusService service) {
    super(service, "api/ods");
    this.service = service;
  }
}
