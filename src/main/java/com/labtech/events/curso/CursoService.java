package com.labtech.events.curso;

//import com.labtech.events.curso.CursoDTO;
import com.labtech.events.utils.GenericService;

import java.util.List;

public interface CursoService extends GenericService<CursoDTO> {

    List<CursoDTO> findAll();

    CursoDTO findById(Long id);
}
