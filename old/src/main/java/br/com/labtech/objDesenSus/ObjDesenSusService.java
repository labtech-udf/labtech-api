package br.com.labtech.objDesenSus;

import br.com.labtech.utils.GenericService;
import java.util.List;
public interface ObjDesenSusService extends GenericService<ObjDesenSusDTO> {

  List<ObjDesenSusDTO> findAll();

  ObjDesenSusDTO findById(Long id);

}
