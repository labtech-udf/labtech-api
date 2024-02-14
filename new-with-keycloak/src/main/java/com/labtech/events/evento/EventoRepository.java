package com.labtech.events.evento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findAllByExcluded(Boolean excluded);

}
