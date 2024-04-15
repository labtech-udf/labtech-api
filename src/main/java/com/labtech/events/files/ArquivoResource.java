package com.labtech.events.files;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "arquivo")
@Tag(name = "Arquivos", description = "Gerenciamento de Arquivos")
public class ArquivoResource {

  @GetMapping(value = "test")
  public String getText() {
    return "Working";
  }

}
