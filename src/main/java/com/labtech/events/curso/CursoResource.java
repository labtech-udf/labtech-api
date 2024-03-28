package com.labtech.events.curso;


import com.labtech.events.utils.GenericResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Tag(name = "Curso", description = "Gerenciamento de Curso")
public class CursoResource extends GenericResource<CursoDTO, CursoResource> {
    private final CursoService service;

    public CursoResource(CursoService service) {
        super(service, "api/");
        this.service = service;
    }

    @GetMapping("/public/teste")
    public String teste(){
        return "ola";
    }

}
