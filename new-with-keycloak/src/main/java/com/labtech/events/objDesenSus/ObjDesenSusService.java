package com.labtech.events.objDesenSus;


import com.labtech.events.utils.GenericService;

import java.util.List;

public interface ObjDesenSusService extends GenericService<ObjDesenSusDTO> {

  List<ObjDesenSusDTO> findAll();

  ObjDesenSusDTO findById(Long id);

}
