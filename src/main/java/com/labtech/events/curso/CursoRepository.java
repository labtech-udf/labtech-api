package com.labtech.events.curso;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    //
    List<Curso>  findAllByExcluded(Boolean excluded);//(findAllBy<Property>)

}
