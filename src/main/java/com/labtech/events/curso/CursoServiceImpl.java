package com.labtech.events.curso;


import com.labtech.events.constants.Status;
import com.labtech.events.utils.GenericServiceImpl;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl extends GenericServiceImpl<Curso, CursoDTO> implements CursoService {

    private final CursoRepository repository;

    private final CursoMapper mapper;

    public CursoServiceImpl(CursoRepository repository, CursoMapper mapper){
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<CursoDTO> findAll(){
        List<Curso> listCurso = this.repository.findAllByExcluded(Boolean.FALSE);
        listCurso.forEach(e -> e.setNome(e.getNome() == null ? "Null" : e.getNome()));
        return this.mapper.toDto(listCurso);
    }

    @Override
    public CursoDTO findById(Long id) {
        Optional<Curso> cursoOptional = repository.findById(id);
        return cursoOptional.map(mapper::toDto)
                .orElse(null);
    }
}














