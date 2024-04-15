package com.labtech.events.objDesenSus;


import com.labtech.events.utils.GenericService;

import java.util.List;

public interface ObjDesenSusService extends GenericService<ObjDesenSusDTO> {

  List<ObjDesenSusDTO> findAll();

  List<ObjDesenSusDTO> findActive();

  ObjDesenSusDTO findById(Long id);



}
